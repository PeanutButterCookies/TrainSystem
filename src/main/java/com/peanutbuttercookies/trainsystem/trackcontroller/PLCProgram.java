/**
 * 
 */
package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import com.peanutbuttercookies.trainsystem.commonresources.Block;

/**
 * @author chris
 *
 */
public class PLCProgram implements PLCProgramInterface {
	
	private JexlEngine jexl					=new JexlEngine();
	private String stopExpression			="(NB_1_occupied || NB_2_occupied)";
	private String slowDownExpression		="(NB_3_occupied || NB_4_occupied)";
	private String switchExpression			="(!CB_occupied)";
	private String rrCrossingExpression		="(CB_rr || (NB_rr && !NB_1_occupied))";
	private String maintenanceExpression	="(!PB_occupied && !CB_occupied)";

	
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
	public boolean maintenance(Block currBlock) {
		return evaluateExpression(maintenanceExpression,currBlock);
	}
	
	@Override
	public boolean loadPLCProgram(String file) {
		if(file.contains(".plc")){
			try(BufferedReader bufferReader = new BufferedReader(new FileReader(file))){
				String currentLine=bufferReader.readLine();
				while(currentLine!=null){
					String[] sections = currentLine.split(":");
					
					if(sections.length!=2){
						System.err.println("ERROR: FILE INCORRECTLY FORMATTED");
					}
					
					if(sections[0].toLowerCase().equals("stop")){
						stopExpression=sections[1];
						
					}
					else if(sections[0].toLowerCase().equals("slowdown")){
						slowDownExpression=sections[1];
					}
					else if(sections[0].toLowerCase().contains("switch")){
						switchExpression=sections[1];		
					}
					else if(sections[0].toLowerCase().contains("rrcrossing")){
						rrCrossingExpression=sections[1];
					}
					else if(sections[0].toLowerCase().contains("maintenance")){
						maintenanceExpression=sections[1];
					}
					
					currentLine=bufferReader.readLine();
				}
			}
			catch(IOException e){
				e.printStackTrace();
				return false;
			}
			
			if(stopExpression==null || slowDownExpression==null ||switchExpression==null 
					|| rrCrossingExpression==null || maintenanceExpression==null){
				System.err.println("ERROR: INVALID PLC FILE");
				return false;
			}
			
			jexl=new JexlEngine();
			return true;
		}
		else{
			System.err.println("ERROR: INVALID PLC FILE");
			return false;
		}
		
	}
		
	
	private boolean evaluateExpression(String expressionString, Block currBlock){
		boolean result=true;
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
			if(expressionString.contains("CB_rr")){
				context.set("CB_rr", currBlock.hasRRCrossing());
			}
			if(expressionString.contains("NB_rr")){
				context.set("NB_rr", currBlock.getNext().hasRRCrossing());
			}
			
			
			for(int i=0; i<3; i++){
				result&=(boolean)expression.evaluate(context);
			}
		
		return result;
	}

}
