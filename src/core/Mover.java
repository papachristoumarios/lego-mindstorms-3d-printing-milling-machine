package core;

import lejos.nxt.NXTRegulatedMotor;

public class Mover extends Thread{
	public NXTRegulatedMotor motor;
	public long length;
	public float velocity;
	private boolean running = true;
	
	public Mover(NXTRegulatedMotor m,long l,float velx) {
		motor=m;length=l;velocity=velx;
	}
	
	public void run() {
		if (running) {
		motor.setSpeed(velocity);
		motor.rotate((int) length);

	}		
	}
}


