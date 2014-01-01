package core;

/**
 * This class can be used to create simple harmonic oscilators. The standard equation for an harmonic oscilator is <code>x(t)=A*sin(ωt + φ_0)</code>
 * @author marios
 *
 */
class HarmonicOscilator {
		double period,width,initial_phase;
	int resolution,ending;
	Point3D[] points;
	
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
			points[t].x = t;
			points[t].y = (float) n;
			
		}
	}	
	
	/**
	 * <code>x = a*sin(ωt + φ_0) + b*sin(θt + c_0)</code>
	 * @param Oscilator A
	 * @param Oscilator B
	 * @param angle between a.width and b.width
	 * @return
	 * @throws Exception
	 */
	public static HarmonicOscilator synthesizeOscilators (HarmonicOscilator a, HarmonicOscilator b, double angle) throws Exception {
		if (a.ending == b.ending && a.resolution == b.resolution) {
		HarmonicOscilator h = null;
		h.width = Math.sqrt(Math.pow(a.width, 2) + Math.pow(b.width, 2) + 2*a.width*b.width*Math.cos(angle));//cosine law 
		for (int t=0; t<=a.ending; t+=a.resolution) {
			double n = a.width* Math.sin(((2*Math.PI)/a.period) * t + a.initial_phase) + b.width* Math.sin(((2*Math.PI)/b.period) * t + b.initial_phase);
			h.points[t].x = t;
			h.points[t].y = (float) n;
			}
		return h;
		}
		throw new Exception("Cannot synthesize oscilations");
		
	}
}
