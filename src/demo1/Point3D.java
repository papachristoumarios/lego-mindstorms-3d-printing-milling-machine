package demo1;

import lejos.geom.Point;



public class Point3D{
	public float x,y,z;
	public Point3D(float xval,float yval,float zval) {
		x=xval;y=yval;z=zval;
	}
	
	public void toInches() {
		this.x = (float) (this.x/25.4);
		this.y = (float) (this.y/25.4);
		this.z = (float) (this.z/25.4);
	}
	
	public void toMillimeters() {
		this.x = (float) (this.x*25.4);
		this.y = (float) (this.y*25.4);
		this.z = (float) (this.z*25.4);
	}
	


}
