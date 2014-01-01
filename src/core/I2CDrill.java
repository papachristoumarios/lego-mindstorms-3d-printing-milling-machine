package core;

import exceptions.UnrecognizedI2CMessageException;
import lejos.nxt.I2CPort;
import lejos.nxt.I2CSensor;
import lejos.nxt.SensorPort;

public class I2CDrill extends Thread {
	
	public boolean running;
	private I2CPort i2cport;
	public I2CSensor s;
	public final byte DRILL_HIGH = 2;
	public final byte DRILL_MEDIUM = 1;
	public final byte  DRILL_LOW = 0;
	public final byte ADDR = 1; //Arduino's Slave Addr

	public I2CDrill(SensorPort p) {
		i2cport = p;
		s = new I2CSensor(p);	
		
	}
	
	
	public void run(byte mode) throws Exception {
		if (mode == 0 || mode ==1 || mode == 2) {
			running = true; //set running at true
			s.sendData(ADDR, mode); //send 0,1 or 2
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		throw new UnrecognizedI2CMessageException();
	}

	
	public byte getMode() {
		if (running) {
			return (byte) s.getData(ADDR);		
		}
		return 0; //signal is not sent
	}
		
	
	public String getInfo() {
		return s.getProductID() + ":" + s.getVendorID();
	}
	
	
	public void close() {
		running = false;
		this.notifyAll();
	}
	
	
}


