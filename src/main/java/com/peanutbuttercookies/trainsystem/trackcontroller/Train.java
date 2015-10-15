package com.peanutbuttercookies.trainsystem.trackcontroller;

/**
 * 
 * @author Chris Good
 *
 *	This class is for the containment and management of data relating to the trains that are currently on the track
 */
public class Train {
	final private int 		trainId;
	private int 			authority;
	private int 			commandedSpeed;
	private int 			presence;
	final private String 	line;
	
	/**
	 * Constructor for the train object
	 * @param initTrainId  the permanent ID for the train object
	 * @param initAuthority  the initial authority value for the train object
	 * @param initCommandedSpeed  the initial commanded speed for the train object
	 * @param initPresence  the initial train presence (e.g. location) of the train object
	 * @param lineName  the permanent line on which the train object is confined
	 */
	public Train (int initTrainId, int initAuthority, int initCommandedSpeed, int initPresence, String lineName){
		trainId			=initTrainId;
		authority		=initAuthority;
		commandedSpeed	=initCommandedSpeed;
		presence		=initPresence;
		line			=lineName;
	}
	
	/**
	 * 
	 * @return  returns the ID of the train object
	 */
	public int getTrainId(){
		return trainId;
	}
	
	/**
	 * 
	 * @return  returns the line on which the train object is confined
	 */
	public String getLine(){
		return line;
	}
	
	/**
	 * This function sets train object's authority property to a new value
	 * @param  newAuthority the value that the authority variable is to be set to
	 */
	public void setAuthority(int newAuthority){
		authority=newAuthority;
	}
	
	/**
	 * 
	 * @return  the current authority value of the train object
	 */
	public int getAuthority(){
		return authority;
	}
	
	/**
	 * This function sets train object's commanded speed property to a new value
	 * @param newCommandedSpeed  the value that the commanded speed variable is to be set to
	 */
	public void setCommandedSpeed(int newCommandedSpeed){
		commandedSpeed=newCommandedSpeed;
	}
	
	/**
	 * 
	 * @return  the current commanded speed value of the train object
	 */
	public int getCommandedSpeed(){
		return commandedSpeed;
	}
	
	/**
	 * This function sets train object's presence property to a new value
	 * @param newPresence  the value that the presence variable is to be set to
	 */
	public void setPresence(int newPresence){
		presence=newPresence;
	}
	
	/**
	 * 
	 * @return  the current presence value of the train object
	 */
	public int getPresence(){
		return presence;
	}
	
}
