import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.SwingUtilities;

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
public class ControllerGUI extends javax.swing.JFrame implements MouseListener, KeyListener{

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabelRotateAngle;
	private JLabel jLabelDistance;
	private JLabel jLabelDrivingSpeed;
	private JLabel jLabelCentimeters;
	private JLabel jLabelTouchIcon;
	private JLabel jLabelArmUp;
	private JLabel jLabelArmDown;
	private JLabel jLabelCamera;
	private JLabel jLabelLightLevel;
	private JLabel jLabelDecibels;
	private JLabel jLabelTouch;
	private JLabel jLabelUltrasonic;
	private JLabel jLabelLight;
	private JLabel jLabelSound;
	private JLabel jLabelSensors;
	private JLabel jLabelBluetooth;
	private JLabel jLabelTurnRadius;
	private JLabel jLabelRight;
	private JLabel jLabelLeft;
	private JLabel jLabelBackward;
	private JLabel jLabelForward;
	private JLabel jLabelBatteryIcon;
	private JLabel jLabelBattery;
	private JLabel jLabelArmSpeed;

	private JButton jButtonLaunchBluetooth;
	private JButton jButtonBluetooth;
	private JButton jButtonCircleRight;
	private JButton jButtonCircleLeft;
	private JButton jButtonArmDown;
	private JButton jButtonArmUp;
	private JButton jButtonStop;
	private JButton jButtonLeft;
	private JButton jButtonDown;
	private JButton jButtonUp;
	private JButton jButtonRight;
	private JButton jButtonRefresh;

	private JScrollPane jScrollCommandLog;
	private JScrollPane jScrollReceivedCommands;
	private JScrollPane jScrollPane4;
	private JScrollPane jScrollSentCommands;
	
	private JTextField jTextDistance;
	private JTextField jTextRotateAngle;

	private JSlider jSliderTurnRadius;
	private JSlider jSliderDrivingSpeed;

	private JTextPane jTextReceivedCommands;
	private JTextPane jTextSentCommands;
	private JTextPane jTextCommandLog;
	
	private JTabbedPane jTabCommands;
	
	private JSeparator jSeparator15;
	private JSeparator jSeparator14;
	private JSeparator jSeparator13;
	private JSeparator jSeparator12;
	private JSeparator jSeparator11;
	private JSeparator jSeparator10;
	private JSeparator jSeparator9;
	private JSeparator jSeparator8;
	private JSeparator jSeparator7;
	private JSeparator jSeparator6;
	private JSeparator jSeparator5;
	private JSeparator jSeparator4;
	private JSeparator jSeparator3;
	private JSeparator jSeparator2;
	private JSeparator jSeparator1;
	
	private static DataOutputStream os;
	private static DataInputStream is;
	
    static long start = 0, latency = 0;
    static Boolean readFlag = true;
    static Object lock = new Object();
    
    static DataOutputStream oHandle;
    static DataInputStream iHandle;
    
    //SENSOR INFORMATION
	boolean TOUCH = true;
	int LIGHT_PERCENT = 40;
	int ULTRASONIC = 73;
	int DECIBELS = 40;
	int BATTERY_PERCENT = 84;
	private JTextArea jTextAreaInstructions;
	private JLabel jLabelDistanceUnits;
	private JLabel jLabelAngleUnits;
	private JSlider jSliderArmSpeed;

	boolean firstPressed = true;
	
	/**
	* Auto-generated main method to display this JFrame
	 * @throws NXTCommException 
	*/
  public void paint(Graphics g2) {
	  super.paint(g2);
	  
	  int MAX_BATTERY_LEVEL = 137;
	  int BASE = 514;
	  int y = BASE - MAX_BATTERY_LEVEL * BATTERY_PERCENT / 100;
	  
	  Graphics2D g = (Graphics2D) g2;
	  
	  if(BATTERY_PERCENT > 75)
		  g.setPaint(Color.blue);
	  else if(BATTERY_PERCENT > 50)
		  g.setPaint(Color.green);
	  else if(BATTERY_PERCENT > 25)
		  g.setPaint(Color.yellow);
	  else
		  g.setPaint(Color.red);
	  
	  g.fill(new Rectangle2D.Double(748, y, 82, MAX_BATTERY_LEVEL * BATTERY_PERCENT / 100));
}
    
	public static void main(String[] args) throws NXTCommException {
		// START THE GUI
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ControllerGUI inst = new ControllerGUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	// SENDS COMMAND TO ROBOT (called whenever GUI action occurs)
	public static void sendCommand(String input) throws NXTCommException
	{	
		int message = Integer.parseInt(input, 16);
		try {
			int command = (message << 16) + message;
			os.writeInt(command);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

			try {
				os.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
	}
	
	public ControllerGUI() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.addKeyListener(this);
			this.setFocusable(true);
			this.setResizable(false);
			this.setTitle("Control Station");
			{
				jTabCommands = new JTabbedPane();
				getContentPane().add(jTabCommands);
				jTabCommands.setBounds(12, 274, 317, 236);
				{
					jScrollCommandLog = new JScrollPane();
					jTabCommands.addTab("Command Log", null, jScrollCommandLog, null);
					{
						jTextCommandLog = new JTextPane();
						jScrollCommandLog.setViewportView(jTextCommandLog);
					}
				}
				{
					jScrollSentCommands = new JScrollPane();
					jTabCommands.addTab("Sent", null, jScrollSentCommands, null);
					{
						jScrollPane4 = new JScrollPane();
						jScrollSentCommands.setViewportView(jScrollPane4);
						{
							jTextSentCommands = new JTextPane();
							jScrollPane4.setViewportView(jTextSentCommands);
							jTextSentCommands.setText("Only sent commands will appear here...");
						}
					}
				}
				{
					jScrollReceivedCommands = new JScrollPane();
					jTabCommands.addTab("Received", null, jScrollReceivedCommands, null);
					{
						jTextReceivedCommands = new JTextPane();
						jScrollReceivedCommands.setViewportView(jTextReceivedCommands);
						jTextReceivedCommands.setText("Only received confirmation messages will appear here...");
					}
				}
			}
			{
				jButtonLeft = new JButton();
				getContentPane().add(jButtonLeft);
				jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
				jButtonLeft.setBounds(340, 92, 65, 73);
				jButtonLeft.addKeyListener(this);
				jButtonLeft.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						//Execute when button is pressed
						jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingLeft.png")));
						jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
						jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
						jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
						
						String command = "21";
						
						String angleOfRotation = "";
						angleOfRotation = jTextRotateAngle.getText();
						
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
						
						jTextCommandLog.setText(jTextCommandLog.getText() + command 
								+ " -- Rotate left " + Integer.parseInt(angleOfRotation) + " degrees\n\n");
						
						try {
							sendCommand(command);
						} catch (NXTCommException e1) {
							e1.printStackTrace();
						}
					}
				});     
			}
			{
				jButtonRight = new JButton();
				getContentPane().add(jButtonRight);
				jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
				jButtonRight.setBounds(482, 92, 60, 73);
				jButtonRight.addKeyListener(this);
				jButtonRight.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						//Execute when button is pressed
						jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
						jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingRight.png")));
						jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
						jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
						
						String command = "20";
						
						String angleOfRotation = "";
						angleOfRotation = jTextRotateAngle.getText();
						
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
						
						jTextCommandLog.setText(jTextCommandLog.getText() + command
								+ " -- Rotate right " + Integer.parseInt(angleOfRotation) + " degrees\n\n");
						
						try {
							sendCommand(command);
						} catch (NXTCommException e1) {
							e1.printStackTrace();
						}
					}
				});     
			}
			{
				jButtonUp = new JButton();
				getContentPane().add(jButtonUp);
				jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
				jButtonUp.setBounds(405, 28, 77, 64);
				jButtonUp.addKeyListener(this);
				jButtonUp.addMouseListener(this);
//				jButtonUp.addActionListener(new ActionListener() {
//					 
//		            public void actionPerformed(ActionEvent e)
//		            {
//		        		//Execute when button is pressed
//						jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
//						jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
//						jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingUp.png")));
//						jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
//						
//						int speed = jSliderDrivingSpeed.getValue();
//
//						String command = "1";
//						
//						String hexSpeed = Integer.toHexString(speed);
//						
//						if(hexSpeed.length() == 1)
//						{
//							String temp = hexSpeed;
//							hexSpeed = "0" + temp;
//						}
//						
//						hexSpeed += "0";
//						
//						command += hexSpeed;
//						
//						jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Set speed to " + speed + "\n");
//
//						try {
//							sendCommand(command);
//						} catch (NXTCommException e1) {
//							e1.printStackTrace();
//						}
//						
//						command = "0000";
//						
//						jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Move forward\n\n");
//
//						try {
//							sendCommand(command);
//						} catch (NXTCommException e1) {
//							e1.printStackTrace();
//						}
//		            }
//		        });     
			}
			{
				jButtonDown = new JButton();
				getContentPane().add(jButtonDown);
				jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
				jButtonDown.setBounds(405, 165, 77, 61);
				jButtonDown.addKeyListener(this);
				jButtonDown.addMouseListener(this);
//				jButtonDown.addActionListener(new ActionListener() {
//					 
//		            public void actionPerformed(ActionEvent e)
//		            {
//		                //Execute when button is pressed
//						jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
//						jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
//						jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
//						jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingDown.png")));
//						
//						int speed = jSliderDrivingSpeed.getValue();
//
//						String command = "1";
//						
//						String hexSpeed = Integer.toHexString(speed);
//						
//						if(hexSpeed.length() == 1)
//						{
//							String temp = hexSpeed;
//							hexSpeed = "0" + temp;
//						}
//						
//						hexSpeed += "0";
//						
//						command += hexSpeed;
//						
//						jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Set speed to " + speed + "\n");
//
//						try {
//							sendCommand(command);
//						} catch (NXTCommException e1) {
//							e1.printStackTrace();
//						}
//						
//						command = "0100";
//						
//						jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Move backward\n\n");
//
//						try {
//							sendCommand(command);
//						} catch (NXTCommException e1) {
//							e1.printStackTrace();
//						}
//		            }
//		        });     
			}
			{
				jButtonStop = new JButton();
				getContentPane().add(jButtonStop);
				jButtonStop.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Stop.png")));
				jButtonStop.setBounds(410, 97, 67, 63);
				jButtonStop.addKeyListener(this);
				jButtonStop.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						//Execute when button is pressed
						jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
						jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
						jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
						jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
						
						String command = "6000";
						
						jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Stop movement\n\n");
						
						try {
							sendCommand(command);
						} catch (NXTCommException e1) {
							e1.printStackTrace();
						}
					}
				});    
			}
			{
				jLabelArmSpeed = new JLabel();
				getContentPane().add(jLabelArmSpeed);
				jLabelArmSpeed.setText("Arm Speed:");
				jLabelArmSpeed.setBounds(759, 122, 75, 16);
			}
			{
				jButtonArmUp = new JButton();
				getContentPane().add(jButtonArmUp);
				jButtonArmUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
				jButtonArmUp.setBounds(753, 52, 79, 63);
				jButtonArmUp.addKeyListener(this);
				jButtonArmUp.addMouseListener(this);
			}
			{
				jButtonArmDown = new JButton();
				getContentPane().add(jButtonArmDown);
				jButtonArmDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
				jButtonArmDown.setBounds(755, 166, 75, 61);
				jButtonArmDown.addKeyListener(this);
				jButtonArmDown.addMouseListener(this);
			}
			{
				jLabelBattery = new JLabel();
				getContentPane().add(jLabelBattery);
				jLabelBattery.setBounds(745, 311, 87, 16);
			}
			{
				jLabelBatteryIcon = new JLabel();
				getContentPane().add(jLabelBatteryIcon);
				
				jLabelBattery.setText("Battery " + BATTERY_PERCENT + "%");
				jLabelBattery.setHorizontalTextPosition(SwingConstants.CENTER);
				jLabelBattery.setHorizontalAlignment(SwingConstants.CENTER);
				jLabelBatteryIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Battery0%.png")));

//				int closest = 100;
//				
//				if(Math.abs((batteryPercent - 75)) < Math.abs((batteryPercent - closest)))
//					closest = 75;
//				
//				if(Math.abs((batteryPercent - 50)) < Math.abs((batteryPercent - closest)))
//					closest = 50;
//				
//				if(Math.abs((batteryPercent - 25)) < Math.abs((batteryPercent - closest)))
//					closest = 25;
//				
//				if(Math.abs((batteryPercent - 0)) < Math.abs((batteryPercent - closest)))
//					closest = 0;
//				
//				if(closest == 100)
//					jLabelBatteryIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Battery100%.png")));
//				
//				if(closest == 75)
//					jLabelBatteryIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Battery75%.png")));
//				
//				if(closest == 50)
//					jLabelBatteryIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Battery50%.png")));
//				
//				if(closest == 25)
//					jLabelBatteryIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Battery25%.png")));
//				
//				if(closest == 0)
//					jLabelBatteryIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Battery0%.png")));
				
				jLabelBatteryIcon.setBounds(743, 334, 87, 157);
			}
			{
				jTextRotateAngle = new JTextField();
				jTextRotateAngle.setHorizontalAlignment(SwingConstants.CENTER);
				jTextRotateAngle.setText("25");
				getContentPane().add(jTextRotateAngle);
				jTextRotateAngle.setBounds(652, 165, 48, 20);
				jTextRotateAngle.addKeyListener(this);
			}
			{
				jTextDistance = new JTextField();
				jTextDistance.setHorizontalAlignment(SwingConstants.CENTER);
				jTextDistance.setText("0");
				getContentPane().add(jTextDistance);
				jTextDistance.setBounds(652, 193, 49, 20);
				jTextDistance.addKeyListener(this);
			}
			{
				jLabelDrivingSpeed = new JLabel();
				getContentPane().add(jLabelDrivingSpeed);
				jLabelDrivingSpeed.setText("Driving Speed:");
				jLabelDrivingSpeed.setBounds(606, 18, 87, 16);
			}
			{
				jLabelRotateAngle = new JLabel();
				getContentPane().add(jLabelRotateAngle);
				jLabelRotateAngle.setText("Rotate Angle:");
				jLabelRotateAngle.setBounds(565, 167, 80, 16);
			}
			{
				jLabelDistance = new JLabel();
				getContentPane().add(jLabelDistance);
				jLabelDistance.setText("Distance:");
				jLabelDistance.setBounds(587, 195, 53, 16);
			}
			{
				jSliderDrivingSpeed = new JSlider();
				getContentPane().add(jSliderDrivingSpeed);
				jSliderDrivingSpeed.setBounds(558, 39, 178, 16);
				jSliderDrivingSpeed.addKeyListener(this);
			}
			{
				jLabelForward = new JLabel();
				getContentPane().add(jLabelForward);
				jLabelForward.setText("Forward");
				jLabelForward.setBounds(420, 7, 47, 16);
			}
			{
				jLabelBackward = new JLabel();
				getContentPane().add(jLabelBackward);
				jLabelBackward.setText("Backward");
				jLabelBackward.setBounds(413, 229, 58, 16);
			}
			{
				jLabelLeft = new JLabel();
				getContentPane().add(jLabelLeft);
				jLabelLeft.setText("Turn Left");
				jLabelLeft.setBounds(345, 73, 62, 16);
			}
			{
				jLabelRight = new JLabel();
				getContentPane().add(jLabelRight);
				jLabelRight.setText("Turn Right");
				jLabelRight.setBounds(483, 74, 69, 16);
			}
			{
				jLabelTurnRadius = new JLabel();
				getContentPane().add(jLabelTurnRadius);
				jLabelTurnRadius.setText("Turning Radius:");
				jLabelTurnRadius.setBounds(602, 72, 88, 16);
			}
			{
				jSliderTurnRadius = new JSlider();
				getContentPane().add(jSliderTurnRadius);
				jSliderTurnRadius.setBounds(559, 92, 177, 16);
				jSliderTurnRadius.addKeyListener(this);
			}
			{
				jSeparator1 = new JSeparator(SwingConstants.VERTICAL);
				getContentPane().add(jSeparator1);
				jSeparator1.setBounds(554, 14, 6, 256);
			}
			{
				jSeparator2 = new JSeparator();
				getContentPane().add(jSeparator2);
				jSeparator2.setOrientation(SwingConstants.VERTICAL);
				jSeparator2.setBounds(738, 14, 6, 255);
			}
			{
				jSeparator3 = new JSeparator();
				getContentPane().add(jSeparator3);
				jSeparator3.setBounds(554, 14, 186, 10);
			}
			{
				jSeparator4 = new JSeparator();
				getContentPane().add(jSeparator4);
				jSeparator4.setBounds(554, 220, 185, 10);
			}
			{
				jButtonCircleLeft = new JButton();
				jButtonCircleLeft.setMargin(new Insets(0,0,0,0));
				getContentPane().add(jButtonCircleLeft);
				jButtonCircleLeft.setText("Circle Left");
				jButtonCircleLeft.setBounds(566, 117, 80, 26);
				jButtonCircleLeft.addKeyListener(this);
				jButtonCircleLeft.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{		            	
						//		            	double arcRadius = (double)jSlider2.getValue();
						//		            	
						//		        		double wheelDiameter = 2.5;
						//		        		double trackWidth = 5; // distance between center of two wheels
						//		        		
						//		        		DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, Motor.A, Motor.C);
						//		        		pilot.arc(arcRadius, 360);
						
						//Execute when button is pressed
						jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
						jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
						jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
						jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
						
						String hexRadius = Integer.toHexString(jSliderTurnRadius.getValue());
						
						if(hexRadius.length() == 1)
						{
							String temp = hexRadius;
							hexRadius = "0" + temp;
						}
						
						String command = "71";
						
						command += hexRadius;
						
						jTextCommandLog.setText(jTextCommandLog.getText() + command 
								+ " -- Circle left with radius " + jSliderTurnRadius.getValue() + "\n\n");
						
						try {
							sendCommand(command);
						} catch (NXTCommException e1) {
							e1.printStackTrace();
						}
					}
				}); 
			}
			{
				jSeparator5 = new JSeparator();
				getContentPane().add(jSeparator5);
				jSeparator5.setBounds(554, 66, 185, 10);
			}
			{
				jSeparator6 = new JSeparator();
				getContentPane().add(jSeparator6);
				jSeparator6.setBounds(554, 154, 185, 10);
			}
			{
				jButtonCircleRight = new JButton();
				jButtonCircleRight.setMargin(new Insets(0,0,0,0));
				getContentPane().add(jButtonCircleRight);
				jButtonCircleRight.setText("Circle Right");
				jButtonCircleRight.setBounds(651, 117, 76, 26);
				jButtonCircleRight.addKeyListener(this);
				jButtonCircleRight.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{		            	
						//		            	double arcRadius = (double)jSlider2.getValue();
						//		            	
						//		        		double wheelDiameter = 2.5;
						//		        		double trackWidth = 5; // distance between center of two wheels
						//		        		
						//		        		DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, Motor.A, Motor.C);
						//		        		pilot.arc(arcRadius * -1, 360);
						
						//Execute when button is pressed
						jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
						jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
						jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
						jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
						
						String hexRadius = Integer.toHexString(jSliderTurnRadius.getValue());
						
						if(hexRadius.length() == 1)
						{
							String temp = hexRadius;
							hexRadius = "0" + temp;
						}
						
						String command = "70";
						
						command += hexRadius;
						
						jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Circle right with radius " + jSliderTurnRadius.getValue() + "\n\n");
						
						try {
							sendCommand(command);
						} catch (NXTCommException e1) {
							e1.printStackTrace();
						}
					}
				}); 
			}
			{
				jLabelBluetooth = new JLabel();
				getContentPane().add(jLabelBluetooth);
				jLabelBluetooth.setText("Bluetooth Connection");
				jLabelBluetooth.setBounds(344, 331, 127, 16);
			}
			{
				boolean connected = true;
				
				jButtonBluetooth = new JButton();
				getContentPane().add(jButtonBluetooth);
				
				if(connected)
					jButtonBluetooth.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Connected.png")));
				else
					jButtonBluetooth.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/NotConnected.png")));

				jButtonBluetooth.setBounds(342, 354, 127, 127);
			}
			{
				jLabelSensors = new JLabel();
				getContentPane().add(jLabelSensors);
				jLabelSensors.setText("SENSORS");
				jLabelSensors.setBounds(577, 319, 63, 16);
			}
			{
				jLabelSound = new JLabel();
				getContentPane().add(jLabelSound);
				jLabelSound.setText("Sound");
				jLabelSound.setBounds(486, 346, 48, 16);
			}
			{
				jLabelLight = new JLabel();
				getContentPane().add(jLabelLight);
				jLabelLight.setText("Light");
				jLabelLight.setBounds(531, 346, 48, 16);
			}
			{
				jLabelUltrasonic = new JLabel();
				getContentPane().add(jLabelUltrasonic);
				jLabelUltrasonic.setText("Ultrasonic");
				jLabelUltrasonic.setBounds(570, 346, 60, 16);
			}
			{
				jLabelTouch = new JLabel();
				getContentPane().add(jLabelTouch);
				jLabelTouch.setText("Touch Sensor");
				jLabelTouch.setBounds(640, 346, 85, 16);
			}
			{
				jLabelDecibels = new JLabel();
				getContentPane().add(jLabelDecibels);
				jLabelDecibels.setText(DECIBELS + " dB");
				jLabelDecibels.setBounds(489, 401, 32, 16);
			}
			{
				jLabelLightLevel = new JLabel();
				getContentPane().add(jLabelLightLevel);
				jLabelLightLevel.setText(LIGHT_PERCENT + "%");
				jLabelLightLevel.setBounds(534, 401, 23, 16);
			}
			{
				jSeparator7 = new JSeparator();
				getContentPane().add(jSeparator7);
				jSeparator7.setOrientation(SwingConstants.VERTICAL);
				jSeparator7.setBounds(526, 343, 5, 115);
			}
			{
				jSeparator8 = new JSeparator();
				getContentPane().add(jSeparator8);
				jSeparator8.setOrientation(SwingConstants.VERTICAL);
				jSeparator8.setBounds(564, 343, 9, 115);
			}
			{
				jLabelCentimeters = new JLabel();
				getContentPane().add(jLabelCentimeters);
				jLabelCentimeters.setText(ULTRASONIC + " cm");
				jLabelCentimeters.setBounds(581, 401, 35, 16);
			}
			{
				jSeparator9 = new JSeparator();
				getContentPane().add(jSeparator9);
				jSeparator9.setBounds(483, 342, 245, 10);
			}
			{
				jSeparator10 = new JSeparator();
				getContentPane().add(jSeparator10);
				jSeparator10.setBounds(483, 458, 245, 8);
			}
			{
				jSeparator11 = new JSeparator();
				getContentPane().add(jSeparator11);
				jSeparator11.setOrientation(SwingConstants.VERTICAL);
				jSeparator11.setBounds(483, 342, 11, 117);
			}
			{
				jSeparator12 = new JSeparator();
				getContentPane().add(jSeparator12);
				jSeparator12.setOrientation(SwingConstants.VERTICAL);
				jSeparator12.setBounds(632, 342, 10, 116);
			}
			{
				jSeparator13 = new JSeparator();
				getContentPane().add(jSeparator13);
				jSeparator13.setBounds(483, 367, 245, 9);
			}
			{
				jSeparator14 = new JSeparator();
				getContentPane().add(jSeparator14);
				jSeparator14.setOrientation(SwingConstants.VERTICAL);
				jSeparator14.setBounds(727, 342, 10, 116);
			}
			{
				jLabelTouchIcon = new JLabel();
				getContentPane().add(jLabelTouchIcon);
				if(TOUCH)
					jLabelTouchIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Touch.png")));
				else
					jLabelTouchIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/NoTouch.png")));
				jLabelTouchIcon.setBounds(639, 372, 82, 82);
			}
			{
				jLabelCamera = new JLabel();
				getContentPane().add(jLabelCamera);
				jLabelCamera.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/CameraView.png")));
				jLabelCamera.setBounds(16, 15, 311, 248);
				jLabelCamera.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			}
			{
				jLabelArmDown = new JLabel();
				getContentPane().add(jLabelArmDown);
				jLabelArmDown.setText("Arm Down");
				jLabelArmDown.setBounds(761, 230, 59, 16);
			}
			{
				jLabelArmUp = new JLabel();
				getContentPane().add(jLabelArmUp);
				jLabelArmUp.setText("Arm Up");
				jLabelArmUp.setBounds(771, 31, 42, 16);
			}
			{
				jButtonLaunchBluetooth = new JButton();
				getContentPane().add(jButtonLaunchBluetooth);
				jButtonLaunchBluetooth.setText("Launch Bluetooth");
				jButtonLaunchBluetooth.setBounds(563, 229, 169, 33);
				jButtonLaunchBluetooth.addKeyListener(this);
				jButtonLaunchBluetooth.addActionListener(new ActionListener() {
				
		            public void actionPerformed(ActionEvent e)
		            {		           
		        		jTextCommandLog.setText(jTextCommandLog.getText() + "Trying to connect...\n");

		        		NXTComm nxtComm;	

		        		try {
		        			nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);

		        			NXTInfo info = new NXTInfo(NXTCommFactory.BLUETOOTH, "DATA_Robotics", "00:16:53:13:93:08");
//		        			NXTInfo info = new NXTInfo(NXTCommFactory.BLUETOOTH, "7a", "00:16:53:13:f6:a4");

		        			nxtComm.open(info);
		        			
		        			os = new DataOutputStream(nxtComm.getOutputStream());
		        			is = new DataInputStream(nxtComm.getInputStream());
		        			
		        			jTextCommandLog.setText(jTextCommandLog.getText() + "Connection established\n\n");
		        			
		        		} catch (Exception e1) {
		        			jTextCommandLog.setText(jTextCommandLog.getText() + "Unable to establish connection\n\n");
		        			e1.printStackTrace();
		        			return;
		        		}
		            }
		        }); 
			}
			{
				jSeparator15 = new JSeparator();
				getContentPane().add(jSeparator15);
				jSeparator15.setBounds(555, 269, 183, 9);
			}
			{
				jButtonRefresh = new JButton();
				getContentPane().add(jButtonRefresh);
				jButtonRefresh.setText("Refresh Sensors");
				jButtonRefresh.setBounds(544, 470, 130, 26);
				jButtonRefresh.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
						TOUCH = false;
						LIGHT_PERCENT = 20;
						ULTRASONIC = 93;
						DECIBELS = 30;
						BATTERY_PERCENT = 74;
						
						if(TOUCH)
							jLabelTouchIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Touch.png")));
						else
							jLabelTouchIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/NoTouch.png")));
						
						jLabelLightLevel.setText(LIGHT_PERCENT + "%");
						
						jLabelCentimeters.setText(ULTRASONIC + " cm");

						jLabelDecibels.setText(DECIBELS + " dB");

						jLabelBattery.setText("Battery " + BATTERY_PERCENT + "%");

						repaint();
		            }
				});
			}
			{
				jSliderArmSpeed = new JSlider();
				getContentPane().add(jSliderArmSpeed);
				jSliderArmSpeed.setBounds(751, 142, 80, 16);
				jSliderArmSpeed.addKeyListener(this);
			}
			{
				jLabelAngleUnits = new JLabel();
				getContentPane().add(jLabelAngleUnits);
				jLabelAngleUnits.setText("deg");
				jLabelAngleUnits.setBounds(708, 167, 28, 16);
			}
			{
				jLabelDistanceUnits = new JLabel();
				getContentPane().add(jLabelDistanceUnits);
				jLabelDistanceUnits.setText("cm");
				jLabelDistanceUnits.setBounds(709, 195, 18, 16);
			}
			{
				jTextAreaInstructions = new JTextArea();
				getContentPane().add(jTextAreaInstructions);
				jTextAreaInstructions.setText("----------Keyboard Instructions----------"
						+ "\nUp/Down arrows: forward/backward" + "\n   Left/Right arrows: rotate left/right"
						+ "\n                  Space bar: stop");
				jTextAreaInstructions.setAlignmentX(SwingConstants.CENTER);
				jTextAreaInstructions.setBounds(342, 248, 201, 71);
				jTextAreaInstructions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				jTextAreaInstructions.setLineWrap(true);
				jTextAreaInstructions.setFocusable(false);
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
        
		if(arg0.getComponent() == jButtonUp)
		{
			jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingUp.png")));
			jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
			jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
			jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
			
			int speed = jSliderDrivingSpeed.getValue();

			String command = "1";
			
			String hexSpeed = Integer.toHexString(speed);
			
			if(hexSpeed.length() == 1)
			{
				String temp = hexSpeed;
				hexSpeed = "0" + temp;
			}
			
			hexSpeed += "0";
			
			command += hexSpeed;
			
			jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Set speed to " + speed + "\n");

			try {
				sendCommand(command);
			} catch (NXTCommException e1) {
				e1.printStackTrace();
			}
			
			command = "0000";
			
			jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Move forward\n\n");

			try {
				sendCommand(command);
			} catch (NXTCommException e1) {
				e1.printStackTrace();
			}
		}
		
		if(arg0.getComponent() == jButtonDown)
		{
			jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingDown.png")));
			jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
			jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
			jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
			
			int speed = jSliderDrivingSpeed.getValue();

			String command = "1";
			
			String hexSpeed = Integer.toHexString(speed);
			
			if(hexSpeed.length() == 1)
			{
				String temp = hexSpeed;
				hexSpeed = "0" + temp;
			}
			
			hexSpeed += "0";
			
			command += hexSpeed;
			
			jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Set speed to " + speed + "\n");

			try {
				sendCommand(command);
			} catch (NXTCommException e1) {
				e1.printStackTrace();
			}
			
			command = "0100";
			
			jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Move backward\n\n");

			try {
				sendCommand(command);
			} catch (NXTCommException e1) {
				e1.printStackTrace();
			}
        }

		if(arg0.getComponent() == jButtonArmUp)
		{
			jButtonArmUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingUp.png")));
			
			String command = "80";
			
			String hexSpeed = Integer.toHexString(jSliderArmSpeed.getValue());
			
			if(hexSpeed.length() == 1)
			{
				String temp = hexSpeed;
				hexSpeed = "0" + temp;
			}

			command += hexSpeed;
			
			jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Start rotating arm up at speed " + jSliderArmSpeed.getValue() + "\n");
			
			try {
				sendCommand(command);
			} catch (NXTCommException e) {
				e.printStackTrace();
			}
		}
		
		if(arg0.getComponent() == jButtonArmDown)
		{
			jButtonArmDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingDown.png")));
			
			String command = "81";
			
			String hexSpeed = Integer.toHexString(jSliderArmSpeed.getValue());
			
			if(hexSpeed.length() == 1)
			{
				String temp = hexSpeed;
				hexSpeed = "0" + temp;
			}
			
			command += hexSpeed;
			
			jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Start rotating arm down at speed " + jSliderArmSpeed.getValue() + "\n");

			try {
				sendCommand(command);
			} catch (NXTCommException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(arg0.getComponent() == jButtonUp || arg0.getComponent() == jButtonDown 
				|| arg0.getComponent() == jButtonLeft || arg0.getComponent() == jButtonRight)
		{
			jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
			jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
			jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
			jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));

			String command = "6000";
			
			jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Stop movement\n\n");

			try {
				sendCommand(command);
			} catch (NXTCommException e1) {
				e1.printStackTrace();
			}
		}
		
		if(arg0.getComponent() == jButtonArmUp || arg0.getComponent() == jButtonArmDown)
		{
			if(arg0.getComponent() == jButtonArmUp)
				jButtonArmUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
			
			if(arg0.getComponent() == jButtonArmDown)
				jButtonArmDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
			
			String command = "9000";
			
			jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Stop rotating arm\n\n");
			
			try {
				sendCommand(command);
			} catch (NXTCommException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(firstPressed)
		{
			firstPressed = false;

			if(arg0.getKeyCode() == KeyEvent.VK_UP || arg0.getKeyCode() == KeyEvent.VK_DOWN)
			{
	    		//Execute when button is pressed
				if(arg0.getKeyCode() == KeyEvent.VK_UP)
				{
					jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
					jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
					jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingUp.png")));
					jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
				}
				
				if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
				{
					jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
					jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
					jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
					jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingDown.png")));
				}
				
				int speed = jSliderDrivingSpeed.getValue();
	
				String command = "1";
				
				String hexSpeed = Integer.toHexString(speed);
				
				if(hexSpeed.length() == 1)
				{
					String temp = hexSpeed;
					hexSpeed = "0" + temp;
				}
				
				hexSpeed += "0";
				
				command += hexSpeed;
				
				jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Set speed to " + speed + "\n");
	
				try {
					sendCommand(command);
				} catch (NXTCommException e1) {
					e1.printStackTrace();
				}
				
				if(arg0.getKeyCode() == KeyEvent.VK_UP)
				{
					command = "0000";
					jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Move forward\n\n");
				}
				
				if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
				{
					command = "0100";
					jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Move backward\n\n");
				}
				
				try {
					sendCommand(command);
				} catch (NXTCommException e1) {
					e1.printStackTrace();
				}			
						
				try {
					sendCommand(command);
				} catch (NXTCommException e) {
					e.printStackTrace();
				}
			}
		
			if(arg0.getKeyCode() == KeyEvent.VK_LEFT || arg0.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					//Execute when button is pressed
					jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
					jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingRight.png")));
					jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
					jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
					
					String command = "20";
					
					String angleOfRotation = "";
					angleOfRotation = jTextRotateAngle.getText();
					
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
					
					jTextCommandLog.setText(jTextCommandLog.getText() + command
							+ " -- Rotate right " + Integer.parseInt(angleOfRotation) + " degrees\n\n");
					
					try {
						sendCommand(command);
					} catch (NXTCommException e1) {
						e1.printStackTrace();
					}
				}
				
				if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
				{
					//Execute when button is pressed
					jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingLeft.png")));
					jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
					jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
					jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
					
					String command = "21";
					
					String angleOfRotation = "";
					angleOfRotation = jTextRotateAngle.getText();
					
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
					
					jTextCommandLog.setText(jTextCommandLog.getText() + command 
							+ " -- Rotate left " + Integer.parseInt(angleOfRotation) + " degrees\n\n");
					
					try {
						sendCommand(command);
					} catch (NXTCommException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_SPACE)
			{
				//Execute when button is pressed
				jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
				jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
				jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
				jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
				
				String command = "6000";
				
				jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Stop movement\n\n");
				
				try {
					sendCommand(command);
				} catch (NXTCommException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		firstPressed = true;

		if(arg0.getKeyCode() == KeyEvent.VK_UP || arg0.getKeyCode() == KeyEvent.VK_DOWN)
		{
			jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
			jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
			jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
			jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));		
			
			String command = "6000";
			
			jTextCommandLog.setText(jTextCommandLog.getText() + command + " -- Stop movement\n\n");
	
			try {
				sendCommand(command);
			} catch (NXTCommException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}