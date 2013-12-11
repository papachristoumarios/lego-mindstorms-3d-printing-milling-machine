package core;

import java.util.ArrayList;

/**
 * This class can be used to create simple harmonic oscilators. The standard equation for an harmonic oscilator is <code>x(t)=A*sin(ωt + φ_0)<code>
 * @author marios
 *
 */
class HarmonicOscilator {
		double period,width,initial_phase;
	int resolution,ending;
	double[] points;
	
	/**
	 * 
	 * @param T (period)
	 * @param A (width)
	 * @param φ_0 (initial phase)
	 * @param end (ending point)
	 * @param res (step: Δt)
	 */
	public HarmonicOscilator(double T, double A, double φ_0, int end, int res) {
		period = T; width=A; φ_0=initial_phase; resolution=res; ending=end;
		for(int t=0; t<=end; t = t +res) {
			double n = A * Math.sin(((2*Math.PI)/T) * t + φ_0);
			points[t] = n;
		}
	}	
}
