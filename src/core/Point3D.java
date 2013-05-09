package core;

import lejos.geom.Point;


/**
 * A class that handles 3D Points
 * @author Marios Papachristou
 *
 */
public class Point3D


{
	public float x,y,z;
	public Point3D(float xval,float yval,float zval) {
		x=xval;y=yval;z=zval;
	}
	
	/**
	 * Converts coordinates to inches (from mm)
	 */
	public void toInches() {
		this.x = (float) (this.x/25.4);
		this.y = (float) (this.y/25.4);
		this.z = (float) (this.z/25.4);
	}
	
	/**
	 * Converts coordinates to millimeters (from in) 
	 */
	public void toMillimeters() {
		this.x = (float) (this.x*25.4);
		this.y = (float) (this.y*25.4);
		this.z = (float) (this.z*25.4);
	}
	


}
