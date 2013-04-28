package core;

import lejos.nxt.NXTRegulatedMotor;

public class AcceleratingMover extends Thread{
	public NXTRegulatedMotor motor;
	public long length;
	public int acceleration;
	private boolean running = true;
	
	public AcceleratingMover(NXTRegulatedMotor m,long l,int accel) {
		motor=m;length=l;acceleration=accel;
	}
	
	public void run() {
		if (running) {
		motor.setAcceleration(acceleration);
		motor.rotate((int) length);
		
	}
	}


	}
