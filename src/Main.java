import demo1.Parser;
import demo1.Reader;


public class Main {
	
	public static void main(String[] argv) throws InterruptedException {
		String line = "G0 Y-150 F50";
		String line2 = "G4 P3000";
		Reader r = new Reader();
		String[][] cmd = r.splitLine(line);
		String[][] cmd2 = r.splitLine(line2);
		Parser.parse(cmd, 0);
		Parser.parse(cmd2, 0);
		Parser.parse(cmd, 0);
	}
}
