/**
 * 
 */
package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import com.peanutbuttercookies.trainsystem.commonresources.Block;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

/**
 * @author chris
 *
 */
public class PLCProgram implements PLCProgramInterface {
	
	private int loadType;
	private JexlEngine jexl;
	private ExpressionFactory juel;
	private String stopExpression;
	private String slowDownExpression;
	private String switchExpression;
	private String rrCrossingExpression;

	
	@Override
	public boolean stop(Block currBlock) {
		return evaluateExpression(stopExpression,currBlock);
	}

	
	@Override
	public boolean slowDown(Block currBlock) {
		return evaluateExpression(slowDownExpression,currBlock);
	}

	
	@Override
	public boolean engageSwitch(Block currBlock) {
		return evaluateExpression(switchExpression,currBlock);
	}

	@Override
	public boolean engageRRCrossing(Block currBlock) {
		return evaluateExpression(rrCrossingExpression,currBlock);
	}

	
	@Override
	public boolean loadPLCProgram(String fileLocation, int loadType) {
		
		//TEST ONLY
		System.out.println("***Entering loadPLCProgram***");
		System.out.println("loadType: "+loadType);
		System.out.println("fileLocation: "+fileLocation);
		//TEST ONLY
		
		if(fileLocation.contains(".plc")){
			
			//TEST ONLY
			System.out.println(">>Checkpoint: Before try");
			//TEST ONLY
			
			try(BufferedReader bufferReader = new BufferedReader(new FileReader(fileLocation))){
				String currentLine=bufferReader.readLine();
				
				//TEST ONLY
				System.out.println(">>Checkpoint: Inside try, before while");
				int counter=0;
				//TEST ONLY
				
				while(currentLine!=null){
					String[] sections = currentLine.split(":");
					
					if(sections.length!=2){
						System.err.println("ERROR: FILE INCORRECTLY FORMATTED");
					}
					
					if(sections[0].toLowerCase().equals("stop")){
						stopExpression=sections[1];
						
						//TEST ONLY
						System.out.println(">>Checkpoint: inside stop if");
						//TEST ONLY
						
					}
					else if(sections[0].toLowerCase().equals("slowdown")){
						slowDownExpression=sections[1];
						
						//TEST ONLY
						System.out.println(">>Checkpoint: inside slowdown if");
						//TEST ONLY
						
					}
					else if(sections[0].toLowerCase().contains("switch")){
						switchExpression=sections[1];
						
						//TEST ONLY
						System.out.println(">>Checkpoint: inside switch if");
						//TEST ONLY
						
					}
					else if(sections[0].toLowerCase().contains("rrcrossing")){
						rrCrossingExpression=sections[1];
						
						//TEST ONLY
						System.out.println(">>Checkpoint: inside rrcrossing if");
						//TEST ONLY
						
					}
					
					currentLine=bufferReader.readLine();
					
					//TEST ONLY
					System.out.println(">>Checkpoint: inside while, counter="+counter);
					counter++;
					if(counter>100){
						System.out.println(">>Breakpoint: counter maxed out");
						break;
					}
					//TEST ONLY
				}
			}
			catch(IOException e){
				e.printStackTrace();
				return false;
			}
			
			
			//TEST ONLY
			System.out.println(">>Checkpoint: Outside try");
			//TEST ONLY
			this.loadType=loadType;
			
			switch(loadType){
			case 0:{
				jexl=new JexlEngine();
				
				//TEST ONLY
				System.out.println("***Exiting loadPLCProgram***");
				//TEST ONLY
				
				return true;
			}
			case 1:{
				juel= new ExpressionFactoryImpl();
				
				//TEST ONLY
				System.out.println("***Exiting loadPLCProgram***");
				//TEST ONLY
				
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
	
	private boolean evaluateExpression(String expressionString, Block currBlock){
		boolean result=true;
		
		switch(this.loadType){
		case 0:{
			Expression expression=jexl.createExpression(expressionString);
			JexlContext context = new MapContext();
			
			if(expressionString.contains("CB_occupied")){
				context.set("CB_occupied", currBlock.isBlockOccupied());
			}
			if(expressionString.contains("PB_occupied")){
				context.set("PB_occupied", currBlock.getPrevBlock().isBlockOccupied());
			}
			if(expressionString.contains("NB_1_occupied")){
				context.set("NB_1_occupied", currBlock.getNext().isBlockOccupied());
			}
			if(expressionString.contains("NB_2_occupied")){
				context.set("NB_2_occupied", currBlock.getNext().getNext().isBlockOccupied());
			}
			if(expressionString.contains("NB_3_occupied")){
				context.set("NB_3_occupied", currBlock.getNext().getNext().getNext().isBlockOccupied());
			}
			if(expressionString.contains("NB_4_occupied")){
				context.set("NB_4_occupied", currBlock.getNext().getNext().getNext().getNext().isBlockOccupied());
			}
			if(expressionString.contains("NB_rr")){
				context.set("NB_rr", currBlock.getNext().hasRRCrossing());
			}
			
			
			for(int i=0; i<3; i++){
				result&=(boolean)expression.evaluate(context);
			}
		}break;
		case 1:{
			SimpleContext context= new SimpleContext();
			//how to set context, fill in later
			context.setVariable("plcVariableName", juel.createValueExpression(expressionString, String.class));
			
			if(expressionString.contains("CB_occupied")){
				context.setVariable("CB_occupied", juel.createValueExpression(currBlock.isBlockOccupied(), Boolean.class));
			}
			if(expressionString.contains("PB_occupied")){
				context.setVariable("PB_occupied",  juel.createValueExpression(currBlock.getPrevBlock().isBlockOccupied(), Boolean.class));
			}
			if(expressionString.contains("NB_1_occupied")){
				context.setVariable("NB_1_occupied",  juel.createValueExpression(currBlock.getNext().isBlockOccupied(), Boolean.class));
			}
			if(expressionString.contains("NB_2_occupied")){
				context.setVariable("NB_2_occupied", juel.createValueExpression(currBlock.getNext().getNext().isBlockOccupied(), Boolean.class));
			}
			if(expressionString.contains("NB_3_occupied")){
				context.setVariable("NB_3_occupied", juel.createValueExpression(currBlock.getNext().getNext().getNext().isBlockOccupied(), Boolean.class));
			}
			if(expressionString.contains("NB_4_occupied")){
				context.setVariable("NB_4_occupied", juel.createValueExpression(currBlock.getNext().getNext().getNext().getNext().isBlockOccupied(), Boolean.class));
			}
			if(expressionString.contains("NB_rr")){
				context.setVariable("NB_rr", juel.createValueExpression(currBlock.getNext().hasRRCrossing(), Boolean.class));
			}
			
			ValueExpression expression=juel.createValueExpression(context,expressionString, String.class);
			for(int i=0; i<3; i++){
				result&=(boolean)expression.getValue(context);
			}

		}break;
		default:{
			System.err.println("ERROR: INCORRECT LOADTYPE");
			return false;
		}
		}
		
		return result;
	}

}
