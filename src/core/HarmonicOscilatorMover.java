package core;

import lejos.nxt.NXTRegulatedMotor;

public class HarmonicOscilatorMover extends Thread {
	public NXTRegulatedMotor motor;
	//public long length;
	//public float velocity;
	private boolean running = true;
	private HarmonicOscilator H;
	
	public HarmonicOscilatorMover(NXTRegulatedMotor m, HarmonicOscilator h) {
		motor=m; h = H; 	
	}
	
	public void run() {
		if (running) {
			for (int t=0; t<=H.ending; t += H.resolution) {
				motor.setSpeed((int) H.points[t]);
				motor.rotate(H.resolution);
			}
	}		
	}	
}
