package core.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import lejos.nxt.SensorPort;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.I2CDrill;

public class I2CSpeedControllerGUI {

	public final byte ADDR = 1;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private final SensorPort[] ports = {SensorPort.S1,SensorPort.S2,SensorPort.S3,SensorPort.S4};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					I2CSpeedControllerGUI window = new I2CSpeedControllerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws BadLocationException 
	 * @throws NumberFormatException 
	 */
	public I2CSpeedControllerGUI() throws NumberFormatException, BadLocationException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws BadLocationException 
	 * @throws NumberFormatException 
	 */
	private void initialize() throws NumberFormatException, BadLocationException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblSensorportenter = new JLabel("SensorPort (enter #)");
		frame.getContentPane().add(lblSensorportenter, "2, 2, right, default");
		
		
		final JSlider slider = new JSlider();
		slider.setPaintLabels(true);
		slider.setValue(0);
		slider.setMaximum(2);
		slider.setMinimum(0);
		frame.getContentPane().add(slider, "2, 6, 3, 1");
		
		textField = new JTextField();
		frame.getContentPane().add(textField, "4, 2, fill, default");
		textField.setColumns(10);
		//final I2CDrill drill = new I2CDrill(ports[p-1]);
		
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openDrilling(Integer.parseInt(textField.getText()));
			}
		});
		frame.getContentPane().add(btnStart, "2, 4");
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		frame.getContentPane().add(btnStop, "4, 4");

		
		JLabel lblSpeed = new JLabel("Speed");
		frame.getContentPane().add(lblSpeed, "2, 8, 3, 1, center, default");
		
		JLabel lblIcMessages = new JLabel("I2C Messages");
		frame.getContentPane().add(lblIcMessages, "2, 14, 3, 1, center, default");
		
		textField_1 = new JTextField();
		frame.getContentPane().add(textField_1, "2, 16, 3, 1, fill, default");
		textField_1.setColumns(10);
		
		JButton btnWrite = new JButton("Write");
		btnWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int p = Integer.parseInt(textField.getText());
				I2CDrill drill = new I2CDrill(ports[p-1]);
				drill.s.sendData(ADDR, Byte.parseByte(textField_1.getText()));
				drill = null; System.gc();
			}
		});
		frame.getContentPane().add(btnWrite, "2, 18");
		
		JButton btnRead = new JButton("Read");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int p = Integer.parseInt(textField.getText());
				I2CDrill drill = new I2CDrill(ports[p-1]);
				textField_1.setText("" +  drill.s.getData(ADDR));
				drill = null; System.gc();
			}
			});
		frame.getContentPane().add(btnRead, "4, 18");
	}
	
	private void openDrilling(int p) {
		I2CDrill drill = new I2CDrill(ports[p-1]);
	}
	
	private void closeDrilling(I2CDrill d) {
		d = null; System.gc(); //deletes object
	}
	}


