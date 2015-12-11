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
		if(fileLocation.contains(".plc")){
			
			try(BufferedReader bufferReader = new BufferedReader(new FileReader(fileLocation))){
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
				}
			}
			catch(IOException e){
				e.printStackTrace();
				return false;
			}
			
			this.loadType=loadType;
			
			switch(loadType){
			case 0:{
				jexl=new JexlEngine();
				return true;
			}
			case 1:{
				juel= new ExpressionFactoryImpl();
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
			
			for(int i=0; i<3; i++){
				result&=(boolean)expression.evaluate(context);
			}
		}break;
		case 1:{
			SimpleContext context= new SimpleContext();
			//how to set context, fill in later
			context.setVariable("plcVariableName", juel.createValueExpression(expressionString, String.class));
			
			
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
