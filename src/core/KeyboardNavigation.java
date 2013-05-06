package core;

import javax.xml.bind.Marshaller.Listener;

import lejos.addon.keyboard.KeyEvent;
import lejos.addon.keyboard.KeyListener;
import lejos.nxt.Motor;

/*
* KeyboardNavigation
*/


public class KeyboardNavigation {
    
	public static void main (String arg) {
		while(true) {System.out.println("ha");}
		
		
		
	}
	
	
    
    private static final KeyListener listener = new KeyListener() {
		
	
    
    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        displayInfo(e);
    }
    
    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
    	 displayInfo(e);
		if (e.getKeyCode() == KeyEvent.VK_A) {
			Motor.A.backward();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			Motor.A.forward();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Motor.B.forward(); }
       
    }
    
    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
        displayInfo(e);
    }
    

    private void displayInfo(KeyEvent e){
    
		if (e.getKeyCode() == KeyEvent.VK_A) {
			System.out.println("X axis : +\n");
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			System.out.println("X axis: -\n");		
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("Y axis: +\n"); 
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("Y axis: -\n");
		} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
			System.out.println("Z axis: +\n"); 
		} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
			System.out.println("Z axis: -\n");
		} else { System.out.println("Invalid key-press\n");
		}
		
    	
    }    	
    	
    	
    };
}

    
    
