package core;

import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.TouchSensor;

public class MarginTracker extends Thread {
	public TouchSensor sensor;
	public NXTRegulatedMotor motor;
	public float pos;
	private boolean running = true;
	public long refresh_interval;
	
	public MarginTracker(TouchSensor s, NXTRegulatedMotor m,long refreshInterval) {
		motor = m; sensor = s; refresh_interval = refreshInterval; 
	}
	
	public void run() {
		while(running) {
			
			if (motor.isMoving() &&  sensor.isPressed() == true) {
				motor.stop(true);
				break;
			} else {
				pos = motor.getTachoCount();
				
			} 
			
		try {
			Thread.sleep(refresh_interval);
		} catch (InterruptedException e) {};
		
		}
	}
	
	//public void stop() {running=false;};
	
}
