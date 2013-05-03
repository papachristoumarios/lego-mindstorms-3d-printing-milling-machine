package core;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;


public class Drill extends Thread {
	public MotorPort port;
	public boolean running;
	private NXTRegulatedMotor motor;
	
	
	public Drill(MotorPort p) {
		port=p;
		motor = new NXTRegulatedMotor(p);
	}
	
	
	public void run() {
		running = true;
		if (running) {
			motor.forward();
		}		
	}
	
	public boolean isRotating() {
		return motor.isMoving();
	}
	
	
	public void close() {
		motor.stop();
	}
	
	
}
