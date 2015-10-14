/*
*	Fauzul Azim
*	10/14/2015
*/

public interface trackModelInterface {
	public void getSpeed(int trainId, int speed);
	public void getAuthority(int trainId, int authority);
	public void getBlockOccupied(int blockId);
	public void getBlockUnoccupied(int blockId);
	public void setBeacon(int blockId);
	public void setLayout(block blockId);
	public void setBlock(String line, String section, int BlockNum, int BlockLength, int SpeedLim, int Infra);
}
