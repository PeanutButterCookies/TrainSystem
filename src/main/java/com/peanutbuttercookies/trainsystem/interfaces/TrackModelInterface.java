/*
*	Fauzul Azim
*	10/14/2015
*/


public interface TrackModelInterface {
	public void getSpeed(int trainId, int speed);
	public void getAuthority(int trainId, int authority);
	public void getBlockOccupied(int blockId);
	public void getBlockUnoccupied(int blockId);
	public void getBeacon(int blockId);
	
	//will likely change how things are set but I assume other modules will mostly use the gets? please correct me 
	//if I am wrong on Slack
	public void setBeacon(int blockId);
	public void setLayout();
	public void setBlock(String line, String section, int blockId, int blockLen, int speedLim, String infra);
}

