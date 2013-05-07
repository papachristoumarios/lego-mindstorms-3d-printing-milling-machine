package core.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import lejos.nxt.Motor;
import lejos.nxt.NXT;
import lejos.nxt.Sound;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.USB;
import lejos.nxt.remote.NXTComm;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class GUINavigation {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//System.out.println(USB.getAddress());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUINavigation window = new GUINavigation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUINavigation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("GUI Navigation");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][][][][][][][]", "[][][][][][][][][][]"));
		
		JButton beepButton = new JButton("Beep!");
		beepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sound.beep();
			}
		});
		frame.getContentPane().add(beepButton, "cell 1 0");
		
		/*
		JCheckBox drillToggle = new JCheckBox("Drill Toggle");
		frame.getContentPane().add(drillToggle, "cell 3 0 2 1"); */
		
		JButton zstop = new JButton("Stop!");
		zstop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Motor.C.stop();
				
			}
		});
		frame.getContentPane().add(zstop, "cell 6 5");
		
		final JSlider slider = new JSlider();
		slider.setToolTipText("Slider has a min of 10 and a max of 150");
		slider.setValue(10);
		slider.setMaximum(150);
		frame.getContentPane().add(slider, "cell 1 8 8 1");
		
		JButton xstop = new JButton("Stop!");
		xstop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Motor.A.stop();
			}
		});
		
		
		
		JLabel zlabel = new JLabel("Z");
		frame.getContentPane().add(zlabel, "cell 6 2,alignx center");
		
		JButton zplus = new JButton("+");
		zplus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Motor.C.setSpeed(slider.getValue());
				Motor.C.forward();
				
			}
		});
		frame.getContentPane().add(zplus, "cell 6 3,growx");
		
		JButton zminus = new JButton("-");
		zminus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Motor.C.setSpeed(slider.getValue());
				Motor.C.backward();
			}
		});
		frame.getContentPane().add(zminus, "cell 6 4,growx");
		frame.getContentPane().add(xstop, "cell 1 5");
		
		JButton btnNewButton = new JButton("Stop!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Motor.B.stop();
			}
		});
		frame.getContentPane().add(btnNewButton, "cell 3 5,growx");
		
		
		
		JLabel xlabel = new JLabel("X");
		frame.getContentPane().add(xlabel, "cell 1 2,alignx center");
		
		JLabel ylabel = new JLabel("Y");
		frame.getContentPane().add(ylabel, "cell 3 2,alignx center");
		
		JButton xplus = new JButton("+");
		xplus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Motor.A.setSpeed(slider.getValue());
				Motor.A.forward();
			}
		});
		frame.getContentPane().add(xplus, "cell 1 3,growx");
		
		JButton xminus = new JButton("-");
		xminus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Motor.A.setSpeed(slider.getValue());
				Motor.A.backward();
				
			}
		});
		
		JButton yplus = new JButton("+");
		yplus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Motor.B.setSpeed(slider.getValue());
				Motor.B.forward();
			}
		});
		frame.getContentPane().add(yplus, "cell 3 3,growx");
		frame.getContentPane().add(xminus, "cell 1 4,growx");
		
		JButton yminus = new JButton("-");
		yminus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Motor.B.setSpeed(slider.getValue());
				Motor.B.backward();
			}
		});
		frame.getContentPane().add(yminus, "cell 3 4,growx");
		
		JLabel lblNewLabel = new JLabel("Velocity");
		frame.getContentPane().add(lblNewLabel, "cell 1 7");
		
		JButton shutdown = new JButton("Close connection");
		shutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NXT.exit(0);
			}
		});
		frame.getContentPane().add(shutdown, "cell 1 9");
		
		
		//TODO Add it to till next release
		//JLabel lblNewLabel_1 = new JLabel("USB Address: ");
		//frame.getContentPane().add(lblNewLabel_1, "cell 3 9");
		
		
		
	}
	
	
}