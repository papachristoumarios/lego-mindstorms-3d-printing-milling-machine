package demo1;

import lejos.nxt.SensorPort;

public class Drill extends Thread {
	public SensorPort port;
	public boolean running;
	
	
	public Drill(SensorPort p) {
		port=p;
	}
	
	
	public void run() {
		running = true;
		if (running) {
			port.activate();
		}		
	}
	
	public boolean isRotating() {
		return port.readBooleanValue();
	}
	
	
	public void stop() {
		port.passivate();
	}
	
	
}
