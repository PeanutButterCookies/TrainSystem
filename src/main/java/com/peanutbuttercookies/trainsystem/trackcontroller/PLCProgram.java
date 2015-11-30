package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class PLCProgram implements PLCProgramInterface {
	
	
	private enum PLCFunctionType{
		SWITCH,STOP,SLOWDOWN,ENGAGESWITCH,ENGAGERR;
	}
	
	private enum Operator{
		AND{
			public boolean operation(boolean I1,boolean I2){
				return I1 && I2;
			}
		},
		OR{
			public boolean operation(boolean I1,boolean I2){
				return I1 || I2;
			}
		},
		NOT{
			public boolean operation(boolean I1){
				return !I1;
			}
		};
	}
	
	private enum Input{
		CURRBLOCK,
		PREVBLOCK,
		NEXTBLOCK,
		RR,
		SWITCH;
		
		private int multiplier;
		void setMultiplier(int multiplier){
			this.multiplier=multiplier;
		}
		int getMultiplier(){
			return multiplier;
		}
	}

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
	
	private PLCFunction stop				=null;
	private PLCFunction slowDown			=null;
	private PLCFunction engageSwitch		=null;
	private PLCFunction engageRRCrossing	=null;
	
	//private ArrayList<PLCFunction> plcFunctionList=new ArrayList<PLCFunction>();

	//private boolean runBooleanLogic(Input I1, Input I2,)
	
	@Override
	public boolean stop(int currentBlockId, int nextBlockId) {
		if(stop.equals(null)){
			System.err.println("ERROR: PLC PROGRAM NOT LOADED");
			return false;
		}
		else if((stop.getInputs().size()==1 && stop.getOperators().size()>0 && stop.getOperators().get(1).equals(Operator.NOT))
				|| (stop.getInputs().size()>1 && stop.getOperators().size()>0)){
			Iterator<Input> inputIterator=stop.getInputs().iterator();
			Iterator<Operator> opIterator=stop.getOperators().iterator();
			boolean stopValue=false;
			
			
			
			return true;
		}
		else{
			System.err.println("ERROR: PLC STOP FUNCTION INPUTS AND OPERATORS DO NOT EXIST");
			return false;
		}
	}

	@Override
	public boolean slowDown(int currentBlockId, int nextBlockId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean engageSwitch(int currentBlockId, int nextBlockId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean engageRRCrossing(int currentBlockId, int nextBlockId) {
		// TODO Auto-generated method stub
		return false;
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
