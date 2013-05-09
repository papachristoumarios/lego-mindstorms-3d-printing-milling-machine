package core;

import lejos.nxt.LCD;
import lejos.nxt.Motor;

/**
 * Tracks motors' position 
 * @author Marios Papachristou
 *
 */
public class PositionTracker extends Thread{
	public long refresh_interval;
	public final long STANDARD_REFRESH_INTERVAL = 20;
	private boolean running = true;
	
	public PositionTracker(long refreshInterval) {
		if (refreshInterval != 0) {
			refresh_interval = refreshInterval;
		} else {refresh_interval = STANDARD_REFRESH_INTERVAL;};
	}
	
	/**
	 * Returns current position of each motor 
	 * @return an array
	 */
	public int[] getPosition() {
		int[] pos = {Motor.A.getTachoCount(),Motor.B.getTachoCount(),Motor.C.getTachoCount()};
		return pos;
	}
	
	
	/**
	 * Prints position on the NXT Screen
	 */
	public void printPositiononLCD() {
		int[] pos = getPosition();
		String position = "X: " + pos[0] + " Y: " + pos[1] + " Z: " + pos[2];
		LCD.drawString(position, 0, 0);
	}
	
	/**
	 * Prints position on console
	 */
	public void printPositiononPC() {
		int[] pos = getPosition();
		String position = "X: " + pos[0] + " Y: " + pos[1] + " Z: " + pos[2];
		System.out.println(position);
	}
	
	public void run() {
		while(running) {
			this.printPositiononLCD(); this.printPositiononPC();
			
			try {
			Thread.sleep(refresh_interval); }
			catch (InterruptedException e) {};
			
		}
	}
	
	/**
	 * Terminates the thread <code>run()</code> method
	 */
	public void close() {running=false;};

}