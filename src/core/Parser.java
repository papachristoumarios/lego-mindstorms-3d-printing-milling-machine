package core;

import exceptions.UnrecognizedGCodeException;
import lejos.geom.Point;
import lejos.nxt.Motor;





/**
 * G-Code Parser
 * @author Marios Papachristou (mrmarios97@gmail.com) 
 * @category Main Application
 */

public class Parser {
	
	public static final double CHIP_LOAD = 0.02;
	public static final int TEETH = 8;
	
	
	public static boolean OPTIONAL_STOP_SWITCH = false;
	public Parser() {
	}
		
					
		/**
		 * 
		 * @param cmd
		 * @param mode (0 for INCREMENTAL, 1 for ABSOLUTE) 
		 * @param i 
		 * @throws UnrecognizedGCodeException 
		 */
		public static void parse(String[][] cmd, int mode) throws InterruptedException {
			char command = getCmd(cmd);
			int command_num = getCmdNum(cmd);
			float currentx = get("X",cmd);
			float currenty = get("Y",cmd);
			float currentz = get("Z",cmd);
			float currentf = get("F",cmd);
			float currentp = get("P",cmd);
			//float currentr = get("R",cmd);
			Point3D W = new Point3D(0, 0, 0); //point W(0,0,0)
			Point3D A = null; //initialize target

			
			//Coordinate System declaration
			if (mode == 0) {
				Point3D Apoint = new Point3D(currentx, currenty, currentz);
				A = Apoint;
			} else if (mode == 1) {
				int prevx = Motor.A.getTachoCount();
				int prevy = Motor.A.getTachoCount();
				int prevz = Motor.A.getTachoCount();
				Point3D Apoint = new Point3D(currentx-prevx,currenty-prevy,currentz-prevz);
				A = Apoint;
			} else {A = W;};
			Point W2D = new Point(W.x,W.y);
			Point A2D = new Point(A.x,A.y);
			
			switch(command) {
			case 'G':
				switch(command_num) {
				case 0: //G0 Rapid Positioning
					GCodeExecutor.G0(W, A, currentf); 
					Thread.sleep(1); break;
				case 1: //G1 Linear Movement
					GCodeExecutor.G1(W, A, currentf);
					Thread.sleep(1); break;
				case 2: //G2 Arc in XY Plane
					GCodeExecutor.G2(W2D,A2D,currentf); Thread.sleep(1); break;
				case 3:
					GCodeExecutor.G2(W2D,A2D,currentf); Thread.sleep(1); break;
				case 4:
					Thread.sleep((long)(currentp)); break;
				case 28: //G28 Move to origin
					GCodeExecutor.G28(W, A, currentf); Thread.sleep(1); break;
				}
			case 'M':
				switch(command_num) {
				case 0:
					GCodeExecutor.M0(); break;
				case 1: //M1 Optional machine sleep
					GCodeExecutor.M1(OPTIONAL_STOP_SWITCH); break;
				case 2: //M2 End of program
					GCodeExecutor.M2(); break;
				}
			
		
			}
			
	
			
			
		}
		


		/**
		 * 
		 * @param cmd
		 * @return Command type (G, M or T) as character
		 */
		public static char getCmd(String[][] cmd) {
			return cmd[0][0].charAt(0);
			}
		
		
		/**
		 * 
		 * @param cmd
		 * @return Command number as integer
		 */
		public static int getCmdNum(String[][] cmd) {
			return Integer.parseInt(cmd[0][1]);
		} //getCmdNum
		
		
		/**
		 * 
		 * @param attribute
		 * @param cmd[][]
		 * @return Defined field value
		 */
		public static float get(String s, String[][] cmd) {
			for(int i=0; i<cmd.length; i++) {
				if (cmd[i][0].equals(s)) {
					return Float.parseFloat(cmd[i][1]);
				}
			} return 0;
			
			
			
		} //get	
		
		/**
		 * Returns rotational frequency from given feedrate
		 * @param feedrate
		 * @param unit_mode (0 to return rad/sec and 1 to return deg/sec)
		 * @return
		 */
		public static double getRotationalFrequency(float feedrate, int unit_mode) {
			if (unit_mode == 1) { 
				final double c = (Math.PI * Math.PI) / (TEETH * CHIP_LOAD);
				return c * feedrate;
			} else if (unit_mode == 0) {
				final double c = (2*Math.PI) / (TEETH * CHIP_LOAD);
				return c * feedrate;
			} else {
				return 0;
			}
		}
		
		/**
		 * Returns feedrate from given rotational frequency
		 * @param rotational_frequency
		 * @param unit_mode (0 to give rad/sec as argument and 1 to give deg/sec as argument)
		 * @return
		 */
		public static double getFeedrate(float rotational_frequency, int unit_mode) {
			if (unit_mode == 1) { 
				final double c = (Math.PI * Math.PI) / (TEETH * CHIP_LOAD);
				return rotational_frequency / c;
			} else if (unit_mode == 0) {
				final double c = (2*Math.PI) / (TEETH * CHIP_LOAD);
				return rotational_frequency / c;
			} else {
				return 0;
			}
		}
			
			
					
		}

//class