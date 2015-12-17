package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import com.peanutbuttercookies.trainsystem.commonresources.Block;

public class PLCProgram_OLD implements PLCProgramInterface {
	
	/**
	 * 
	 * PLCFunctionType
	 * The PLCFunctionType enum serves to limit the functions that
	 * the PLC program is allowed to have
	 *
	 */
	private enum PLCFunctionType{
		SWITCH("SWITCH"),
		STOP("STOP"),
		SLOWDOWN("SLOWDOWN"),
		ENGAGESWITCH("ENGAGESWITCH"),
		ENGAGERR("ENGAGERR");
		
		private String value;
		PLCFunctionType(String value){
			this.value=value;
		}
		String getString(){
			return value;
		}
	}
	
	/**
	 * 
	 * Operator
	 * The Operator enum is to limit the logical operators that the
	 * PLC program can use, as well as ease the use of the read in
	 * logical operators in real time use
	 *
	 */
	private enum Operator{
		AND,OR,NOT;
		
		boolean operation(boolean i1, boolean i2){
			switch(this){
			case AND:{return i1 && i2;}
			case OR:{return i1 || i2;}
			case NOT:{return !i1;}
			default:{
				System.err.println("ERROR: OPERATION FAILED");
				return false;
			}
			}
		}
	}
	
	/**
	 * 
	 * Input
	 * The Input enum is used in establishing the allowed 'inputs' that the
	 * read-in PLC program can use. The inputBoolean function makes it easier
	 * to extract the necessary logical value from the track data structure
	 *
	 */
	private enum Input{
		CURRBLOCK,
		PREVBLOCK,
		NEXTBLOCK,
		RR,
		SWITCH;
		
		private int nextMultiplier;
		private int prevMultiplier;
		
		void setNextMultiplier(int multiplier){
			this.nextMultiplier=multiplier;
		}
		int getNextMultiplier(){
			return nextMultiplier;
		}
		
		void setPrevMultiplier(int multiplier){
			this.prevMultiplier=multiplier;
		}
		int getPrevMultiplier(){
			return prevMultiplier;
		}
		
		boolean inputBoolean(Block currBlock){
			switch(this){
			case CURRBLOCK:{return currBlock.isBlockOccupied();}
			case PREVBLOCK:{
				Block prev=currBlock;
				for(int i=1;i<this.prevMultiplier;i++){
					prev=prev.getPrev();
				}
				return prev.isBlockOccupied();
				}
			case NEXTBLOCK:{
				Block next=currBlock;
				for(int i=1;i<this.nextMultiplier;i++){
					next=next.getNext();
				}
				return next.isBlockOccupied();
				}
			case RR:{return currBlock.hasRRCrossing();}
			case SWITCH:{return currBlock.hasSwitch();}
			default:{
				System.err.println("ERROR: INPUTBOOLEAN FAILED");
				return false;
			}
			}
		}
	}
	
	/**
	 * 
	 * PLCFunction
	 * This class serves as a data structure to hold the input and operator values
	 * read in from the PLC program file
	 *
	 */
	private class PLCFunction{
		private final ArrayList<Input> input;
		private final ArrayList<Operator> ops;
		private final PLCFunctionType function;
		
		PLCFunction(PLCFunctionType function, ArrayList<Input> input, ArrayList<Operator> ops){
			this.function	=function;
			this.input		=input;
			this.ops		=ops;
		}
		
		public ArrayList<Input> getInputs(){
			return input;
		}
		
		public ArrayList<Operator> getOperators(){
			return ops;
		}
		
		public PLCFunctionType getFunctionType(){
			return function;
		}
	}
	
	//PLCFunction objects corresponding to the interface methods (excluding loadPLCProgram)
	private PLCFunction stop				=null;
	private PLCFunction slowDown			=null;
	private PLCFunction engageSwitch		=null;
	private PLCFunction engageRRCrossing	=null;
	
	/**
	 * 
	 * @param plc - The PLCFunction object that the function is to be run for
	 * @param currBlock - the current block that the logical function is to be run on
	 * @return the boolean results of the logical function
	 */
	private boolean runPLCFunction(PLCFunction plc, Block currBlock){
		if(plc.equals(null)){
			System.err.println("ERROR: PLC PROGRAM NOT LOADED");
			return false;
		}
		else if((plc.getInputs().size()==1 && plc.getOperators().size()>0 && plc.getOperators().get(1).equals(Operator.NOT))
				|| (plc.getInputs().size()>1 && plc.getOperators().size()>0)){
			Iterator<Input> inputIterator	=plc.getInputs().iterator();
			Iterator<Operator> opIterator	=plc.getOperators().iterator();
			Input currInput					=inputIterator.next();
			boolean stopValue				=currInput.inputBoolean(currBlock);

			while(inputIterator.hasNext() && opIterator.hasNext()){
				Operator currOp=opIterator.next();
				if(currOp.equals(Operator.NOT)){
					stopValue=currOp.operation(stopValue,false);
				}
				else{
					currInput=inputIterator.next();
					stopValue=currOp.operation(stopValue, currInput.inputBoolean(currBlock));
				}
			}
			if(inputIterator.hasNext() || opIterator.hasNext()){
				System.err.println("ERROR: PLC LOGIC INCORRECTLY READ - INPUTS OR OPERATIONS NOT FULLY READ");
				return false;
			}
			
			return stopValue;
		}
		else{
			System.err.println("ERROR: PLC "+plc.getFunctionType().getString()+" FUNCTION INPUTS AND OPERATORS DO NOT EXIST");
			return false;
		}
	}
	
	@Override
	public boolean stop(Block currBlock) {
		return runPLCFunction(stop,currBlock);
	}

	@Override
	public boolean slowDown(Block currBlock) {
		return runPLCFunction(slowDown,currBlock);
	}

	@Override
	public boolean engageSwitch(Block currBlock) {
		return runPLCFunction(engageSwitch,currBlock);
	}

	@Override
	public boolean engageRRCrossing(Block currBlock) {
		return runPLCFunction(engageRRCrossing,currBlock);
	}

	@Override
	public boolean loadPLCProgram(String fileLocation, int loadType) {
		if(fileLocation.contains(".plc")){
			switch(loadType){
			case 0:{
				try{
					boolean success=false;
					
					FileReader file=new FileReader(fileLocation);
					BufferedReader bufferReader = new BufferedReader(file);
					
					
					
					
					file.close();
					return success;
				}
				catch(Exception e){
					e.printStackTrace();
					return false;
				}
			}
			case 1:{
				return true;
			}
			default:{
				System.err.println("ERROR: INVALID LOAD TYPE ID");
				return false;
			}
			}
			
		}
		else{
			return false;
		}
		
	}

}
