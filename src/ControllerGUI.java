import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
@SuppressWarnings("serial")
public class ControllerGUI extends javax.swing.JFrame implements MouseListener{

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch(Exception e) {
			System.out.println("Unable to set UI 'Look and Feel'.");
			e.printStackTrace();
		}
	}

	private JScrollPane jScrollPane2;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel4;
	private JTextField jTextField3;
	private JButton jButton7;
	private JSeparator jSeparator6;
	private JSeparator jSeparator5;
	private JButton jButton6;
	private JSeparator jSeparator4;
	private JSeparator jSeparator3;
	private JSeparator jSeparator2;
	private JSeparator jSeparator1;
	private JSlider jSlider2;
	private JLabel jLabel11;
	private JLabel jLabel10;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JSlider jSlider1;
	private JTextField jTextField2;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JButton jButton10;
	private JButton jButton9;
	private JLabel jLabel1;
	private JButton jButton5;
	private JButton jButton1;
	private JButton jButton4;
	private JButton jButton3;
	private JButton jButton2;
	private JTextPane jTextPane4;
	private JTextPane jTextPane3;
	private JScrollPane jScrollPane5;
	private JTextPane jTextPane2;
	private JScrollPane jScrollPane4;
	private JScrollPane jScrollPane3;
	private JTextPane jTextPane1;
	private JTabbedPane jTabbedPane1;
	
	
	static Scanner scanner = new Scanner(System.in);

	/**
	* Auto-generated main method to display this JFrame
	 * @throws NXTCommException 
	*/
	
	private static boolean USBtest = false;
	static long start = 0, latency = 0;
	static Boolean readFlag = true;
	static Object lock = new Object();
	
	public static NXTComm connection;
	public static OutputStream os; //= connection.getOutputStream();
	static InputStream is; //= connection.getInputStream(); 
	static DataOutputStream oHandle ;//= new DataOutputStream(os);
	static  DataInputStream iHandle ;//= new DataInputStream(is);
	
	public static void main(String[] args) throws NXTCommException {
		
		//System.out.println("String is: " + input);
		start = System.currentTimeMillis();
		final NXTComm connection;
		NXTInfo[] info;

		if (USBtest) {
			connection = NXTCommFactory.createNXTComm(NXTCommFactory.USB);
			info = connection.search(null, 0); // no need for name if it is conn
												// via USB
		} else {
			connection = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			//info = connection.search("DATA_Robotics", 1234); // your robot's name must be NXT and the code is 1234
			info = connection.search("7a", 1234); // your robot's name must be NXT and the code is 1234

		}

		if (info.length == 0) {
			System.out.println("Cant find any device to connect");
			return;
		}
		// open connections and data streams
		connection.open(info[0]); //Opens the first connection it found that matches connection.search(" ", int);
		
		OutputStream os = connection.getOutputStream(); //To or from robot?
		InputStream is = connection.getInputStream(); //To or from robot?

		final DataOutputStream oHandle = new DataOutputStream(os);
		final DataInputStream iHandle = new DataInputStream(is);
		latency = System.currentTimeMillis() - start;
		System.out.printf("Connection is established [%dms]\n", latency);

		String input = "Initiate.";

		Scanner scanner = new Scanner(System.in);		
		
		
		
//		final NXTComm connection;
//		NXTInfo[] info;
//		
//		connection = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
//		info = connection.search("7B", 1234); // your robot's name must be NXT and the code is 1234
//		
//		connection.open(info[0]);

		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ControllerGUI inst = new ControllerGUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	

	

	
	public static void sendCommand(String input) throws NXTCommException
	{
		System.out.println("The command sent is "+input);
		do{
		    try {
		    	
               // input = scanner.nextLine(); //Input is of type String
                start = System.currentTimeMillis();
                
                
                int command;
                String parity;
                parity = input.concat(input);
                command = Integer.parseInt(parity);
                input = parity;
                
                System.out.println("Your command is " + command);
                //oHandle.write(input.getBytes()); //The command that is sent to the robot
                //oHandle.writeInt(command);
                oHandle.writeInt(0x11FF11FF);
                oHandle.flush();

                oHandle.writeInt(0x0);
                //input = "forward";
                //oHandle.write(input.getBytes());
                
                
                //System.out.println("Sending over: " + input.getBytes());
                oHandle.flush();
                latency = System.currentTimeMillis() - start;
                
                System.out.println("\nPC: " + input + " [" + latency + "ms]");

			} catch (IOException e) {
				System.out.println("Fail to write to oHandle bc "
						+ e.toString());
				return;
			}
		} while (!input.equalsIgnoreCase("exit"));
		
	}
	
	public ControllerGUI() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setResizable(false);
			this.setTitle("Control Station");
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1);
				jTabbedPane1.setBounds(12, 274, 317, 236);
				{
					jScrollPane2 = new JScrollPane();
					jTabbedPane1.addTab("Command Log", null, jScrollPane2, null);
					{
						jTextPane1 = new JTextPane();
						jScrollPane2.setViewportView(jTextPane1);
					}
				}
				{
					jScrollPane3 = new JScrollPane();
					jTabbedPane1.addTab("Sent", null, jScrollPane3, null);
					{
						jScrollPane4 = new JScrollPane();
						jScrollPane3.setViewportView(jScrollPane4);
						{
							jTextPane2 = new JTextPane();
							jScrollPane4.setViewportView(jTextPane2);
							jTextPane2.setText("Only sent commands will appear here...");
						}
					}
				}
				{
					jScrollPane5 = new JScrollPane();
					jTabbedPane1.addTab("Received", null, jScrollPane5, null);
					{
						jTextPane3 = new JTextPane();
						jScrollPane5.setViewportView(jTextPane3);
						jTextPane3.setText("Only received confirmation messages will appear here...");
					}
				}
			}
			{
				jTextPane4 = new JTextPane();
				getContentPane().add(jTextPane4);
				jTextPane4.setText("Camera Display");
				jTextPane4.setBounds(12, 12, 317, 250);
				jTextPane4.setEditable(false);
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
				jButton1.setBounds(341, 99, 65, 73);
				jButton1.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
		                //Execute when button is pressed
						jButton1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingLeft.png")));
						jButton2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
						jButton3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
						jButton4.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
		            	
						
						String command = "21";
						
						String angleOfRotation = "";
						angleOfRotation = jTextField2.getText();
						
						String hexAngle = Integer.toHexString(Integer.parseInt(angleOfRotation));
						
						if(hexAngle.length() == 1)
						{
							String temp = hexAngle;
							hexAngle = "0" + temp;
						}
						
						if(angleOfRotation.length() > 0)
							command += hexAngle;
						
						if(angleOfRotation.length() == 0)
							command += Integer.toHexString(90);
						
						jTextPane1.setText(jTextPane1.getText() + command + " -- Rotate left " + Integer.parseInt(angleOfRotation) + " degrees\n\n");
						
						try {
							sendCommand(command);
						} catch (NXTCommException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
												
//		        		double wheelDiameter = 2.5;
//		        		double trackWidth = 5; // distance between center of two wheels
//		        		
//		        		DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, Motor.B, Motor.C);
//		        		pilot.rotate(-90);
		            }
		        });     
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2);
				jButton2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
				jButton2.setBounds(483, 99, 60, 73);
				jButton2.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
		                //Execute when button is pressed
						jButton1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
						jButton2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingRight.png")));
						jButton3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
						jButton4.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
		            	
						String command = "20";
						
						String angleOfRotation = "";
						angleOfRotation = jTextField2.getText();
						
						String hexAngle = Integer.toHexString(Integer.parseInt(angleOfRotation));
						
						if(hexAngle.length() == 1)
						{
							String temp = hexAngle;
							hexAngle = "0" + temp;
						}
						
						if(angleOfRotation.length() > 0)
							command += hexAngle;
						
						if(angleOfRotation.length() == 0)
							command += Integer.toHexString(90);
						
						jTextPane1.setText(jTextPane1.getText() + command + " -- Rotate right " + Integer.parseInt(angleOfRotation) + " degrees\n\n");
						
						try {
							sendCommand(command);
						} catch (NXTCommException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
//		        		double wheelDiameter = 2.5;
//		        		double trackWidth = 5; // distance between center of two wheels
//		        		
//		        		DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, Motor.A, Motor.C);
//		        		pilot.rotate(90);
		            }
		        });     
			}
			{
				jButton3 = new JButton();
				getContentPane().add(jButton3);
				jButton3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
				jButton3.setBounds(406, 35, 77, 64);
				jButton3.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
//		        		double wheelDiameter = 2.5;
//		        		double trackWidth = 5; // distance between center of two wheels
//		        		
//		        		DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, Motor.A, Motor.C);
//		        		pilot.forward();
		        		
		        		//Execute when button is pressed
						jButton1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
						jButton2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
						jButton3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingUp.png")));
						jButton4.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
						
						int speed = jSlider1.getValue();

						String command = "1";
						
						String hexSpeed = Integer.toHexString(speed);
						
						/*
						 * TODO: Why?
						 */
						if(hexSpeed.length() == 1)
						{
							String temp = hexSpeed;
							hexSpeed = "0" + temp;
						}
						
						hexSpeed += "0";
						
						command += hexSpeed;
						
						jTextPane1.setText(jTextPane1.getText() + command + " -- Set speed to " + speed + "\n");

						try {
							sendCommand(command);
						} catch (NXTCommException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						command = "0000";
						
						jTextPane1.setText(jTextPane1.getText() + command + " -- Move forward\n\n");

						try {
							sendCommand(command);
						} catch (NXTCommException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            }
		        });     
			}
			{
				jButton4 = new JButton();
				getContentPane().add(jButton4);
				jButton4.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
				jButton4.setBounds(406, 172, 77, 61);
				jButton4.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
//		        		double wheelDiameter = 2.5;
//		        		double trackWidth = 5; // distance between center of two wheels
//		        		
//		        		DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, Motor.A, Motor.C);
//		        		pilot.backward();
		        		
		                //Execute when button is pressed
						jButton1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
						jButton2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
						jButton3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
						jButton4.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingDown.png")));
						
						int speed = jSlider1.getValue();

						String command = "1";
						
						String hexSpeed = Integer.toHexString(speed);
						
						if(hexSpeed.length() == 1)
						{
							String temp = hexSpeed;
							hexSpeed = "0" + temp;
						}
						
						hexSpeed += "0";
						
						command += hexSpeed;
						
						jTextPane1.setText(jTextPane1.getText() + command + " -- Set speed to " + speed + "\n");

						try {
							sendCommand(command);
						} catch (NXTCommException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						command = "0100";
						
						jTextPane1.setText(jTextPane1.getText() + command + " -- Move backward\n\n");

						try {
							sendCommand(command);
						} catch (NXTCommException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            }
		        });     
			}
			{
				jButton5 = new JButton();
				getContentPane().add(jButton5);
				jButton5.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Stop.png")));
				jButton5.setBounds(411, 104, 67, 63);
				jButton5.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
//		        		double wheelDiameter = 2.5;
//		        		double trackWidth = 5; // distance between center of two wheels
//		        		
//		        		DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, Motor.A, Motor.C);
//		        		pilot.stop();
		        		
		                //Execute when button is pressed
						jButton1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
						jButton2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
						jButton3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
						jButton4.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
						
						String command = "6100";
						
						jTextPane1.setText(jTextPane1.getText() + command + " -- Stop movement\n\n");

						try {
							sendCommand(command);
						} catch (NXTCommException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            }
		        });    
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Arm Control");
				jLabel1.setBounds(757, 124, 75, 16);
			}
			{
				jButton9 = new JButton();
				getContentPane().add(jButton9);
				jButton9.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
				jButton9.setBounds(752, 36, 79, 63);
				jButton9.addMouseListener(this);
			}
			{
				jButton10 = new JButton();
				getContentPane().add(jButton10);
				jButton10.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
				jButton10.setBounds(752, 167, 80, 61);
				jButton10.addMouseListener(this);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setBounds(737, 311, 87, 16);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				int batteryPercent = 12;
				
				jLabel2.setText("Battery " + batteryPercent + "%");
				jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
				jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
				
				int closest = 100;
				
				if(Math.abs((batteryPercent - 75)) < Math.abs((batteryPercent - closest)))
					closest = 75;
				
				if(Math.abs((batteryPercent - 50)) < Math.abs((batteryPercent - closest)))
					closest = 50;
				
				if(Math.abs((batteryPercent - 25)) < Math.abs((batteryPercent - closest)))
					closest = 25;
				
				if(Math.abs((batteryPercent - 0)) < Math.abs((batteryPercent - closest)))
					closest = 0;
				
				if(closest == 100)
					jLabel3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Battery100%.png")));
				
				if(closest == 75)
					jLabel3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Battery75%.png")));
				
				if(closest == 50)
					jLabel3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Battery50%.png")));
				
				if(closest == 25)
					jLabel3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Battery25%.png")));
				
				if(closest == 0)
					jLabel3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Battery0%.png")));
				
				jLabel3.setBounds(737, 345, 87, 157);
			}
			{
				jTextField2 = new JTextField();
				jTextField2.setText("25");
				getContentPane().add(jTextField2);
				jTextField2.setBounds(653, 183, 73, 20);
			}
			{
				jTextField3 = new JTextField();
				jTextField3.setText("0");
				getContentPane().add(jTextField3);
				jTextField3.setBounds(652, 211, 74, 20);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Driving Speed:");
				jLabel4.setBounds(606, 36, 87, 16);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Rotate Angle:");
				jLabel5.setBounds(566, 185, 80, 16);
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("Distance:");
				jLabel6.setBounds(566, 212, 53, 16);
			}
			{
				jSlider1 = new JSlider();
				getContentPane().add(jSlider1);
				jSlider1.setBounds(558, 57, 178, 16);
			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setText("Forward");
				jLabel7.setBounds(421, 14, 47, 16);
			}
			{
				jLabel8 = new JLabel();
				getContentPane().add(jLabel8);
				jLabel8.setText("Backward");
				jLabel8.setBounds(416, 236, 58, 16);
			}
			{
				jLabel9 = new JLabel();
				getContentPane().add(jLabel9);
				jLabel9.setText("Turn Left");
				jLabel9.setBounds(346, 80, 62, 16);
			}
			{
				jLabel10 = new JLabel();
				getContentPane().add(jLabel10);
				jLabel10.setText("Turn Right");
				jLabel10.setBounds(484, 81, 69, 16);
			}
			{
				jLabel11 = new JLabel();
				getContentPane().add(jLabel11);
				jLabel11.setText("Turning Radius:");
				jLabel11.setBounds(602, 90, 88, 16);
			}
			{
				jSlider2 = new JSlider();
				getContentPane().add(jSlider2);
				jSlider2.setBounds(559, 110, 177, 16);
			}
			{
				jSeparator1 = new JSeparator(SwingConstants.VERTICAL);
				getContentPane().add(jSeparator1);
				jSeparator1.setBounds(554, 32, 6, 206);
			}
			{
				jSeparator2 = new JSeparator();
				getContentPane().add(jSeparator2);
				jSeparator2.setOrientation(SwingConstants.VERTICAL);
				jSeparator2.setBounds(738, 32, 6, 206);
			}
			{
				jSeparator3 = new JSeparator();
				getContentPane().add(jSeparator3);
				jSeparator3.setBounds(554, 32, 186, 10);
			}
			{
				jSeparator4 = new JSeparator();
				getContentPane().add(jSeparator4);
				jSeparator4.setBounds(554, 238, 185, 10);
			}
			{
				jButton6 = new JButton();
				jButton6.setMargin(new Insets(0,0,0,0));
				getContentPane().add(jButton6);
				jButton6.setText("Circle Left");
				jButton6.setBounds(566, 135, 80, 26);
				jButton6.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {		            	
//		            	double arcRadius = (double)jSlider2.getValue();
//		            	
//		        		double wheelDiameter = 2.5;
//		        		double trackWidth = 5; // distance between center of two wheels
//		        		
//		        		DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, Motor.A, Motor.C);
//		        		pilot.arc(arcRadius, 360);
						
		        		String hexRadius = Integer.toHexString(jSlider2.getValue());
		        		
		        		if(hexRadius.length() == 1)
		        		{
		        			String temp = hexRadius;
		        			hexRadius = "0" + temp;
		        		}
		        		
						String command = "71";
						
						command += hexRadius;
						
						jTextPane1.setText(jTextPane1.getText() + command + " -- Circle left with radius " + jSlider2.getValue() + "\n\n");

						try {
							sendCommand(command);
						} catch (NXTCommException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            }
		        }); 
			}
			{
				jSeparator5 = new JSeparator();
				getContentPane().add(jSeparator5);
				jSeparator5.setBounds(554, 84, 185, 10);
			}
			{
				jSeparator6 = new JSeparator();
				getContentPane().add(jSeparator6);
				jSeparator6.setBounds(554, 172, 185, 10);
			}
			{
				jButton7 = new JButton();
				jButton7.setMargin(new Insets(0,0,0,0));
				getContentPane().add(jButton7);
				jButton7.setText("Circle Right");
				jButton7.setBounds(651, 135, 76, 26);
				jButton7.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {		            	
//		            	double arcRadius = (double)jSlider2.getValue();
//		            	
//		        		double wheelDiameter = 2.5;
//		        		double trackWidth = 5; // distance between center of two wheels
//		        		
//		        		DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, Motor.A, Motor.C);
//		        		pilot.arc(arcRadius * -1, 360);
						
		        		String hexRadius = Integer.toHexString(jSlider2.getValue());
		        		
		        		if(hexRadius.length() == 1)
		        		{
		        			String temp = hexRadius;
		        			hexRadius = "0" + temp;
		        		}
		        		
						String command = "70";
						
						command += hexRadius;
						
						jTextPane1.setText(jTextPane1.getText() + command + " -- Circle left with radius " + jSlider2.getValue() + "\n\n");

						try {
							sendCommand(command);
						} catch (NXTCommException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            }
		        }); 
			}
			pack();
			this.setSize(849, 550);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getComponent() == jButton9)
			jButton9.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingUp.png")));
		
		if(arg0.getComponent() == jButton10)
			jButton10.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingDown.png")));
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(arg0.getComponent() == jButton9)
			jButton9.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
		
		if(arg0.getComponent() == jButton10)
			jButton10.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
		
	}

}
