package demo1;

import lejos.nxt.Button;



public class Bucky {
	public String[][] cmd1 = {{"G","0"},{"X","100"},{"Y","50"},{"F","50"}};
	public String[][] cmd2 = {{"G","1"},{"X","100"},{"Y","50"},{"F","50"}};
	public String[][] cmd3 = {{"G","4"},{"P","5000"}};
	
	
	public static void main(String args[]) throws InterruptedException {
		
		
		while(Button.ENTER.isDown() == false) {
			Thread.sleep(1);
		}
		
		System.out.println("Tasdadada");
		
		Thread.sleep(3000);
		
	}
}
