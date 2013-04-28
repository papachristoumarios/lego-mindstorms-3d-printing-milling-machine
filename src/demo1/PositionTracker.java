package demo1;

import lejos.nxt.LCD;
import lejos.nxt.Motor;

public class PositionTracker extends Thread{
	public long refresh_interval;
	public final long STANDARD_REFRESH_INTERVAL = 20;
	private boolean running = true;
	
	public PositionTracker(long refreshInterval) {
		if (refreshInterval != 0) {
			refresh_interval = refreshInterval;
		} else {refresh_interval = STANDARD_REFRESH_INTERVAL;};
	}
	
	public int[] getPosition() {
		int[] pos = {Motor.A.getTachoCount(),Motor.B.getTachoCount(),Motor.C.getTachoCount()};
		return pos;
	}
	
	
	
	public void printPositiononLCD() {
		int[] pos = getPosition();
		String position = "X: " + pos[0] + " Y: " + pos[1] + " Z: " + pos[2];
		LCD.drawString(position, 0, 0);
	}
	
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
	
	
	public void stop() {running=false;};
}
