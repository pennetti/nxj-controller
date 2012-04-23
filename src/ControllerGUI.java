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

import lejos.pc.comm.NXTCommException;

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
	
	private JLabel jLabelDrivingSpeed;
	private JLabel jLabelCentimeters;
	private JLabel jLabelTouchIcon;
	private JLabel jLabelArmClockwise;
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
	private JLabel jLabelDistanceUnits;
	private JLabel jLabelAngleUnits;
	private JLabel jLabelCameraDisplay;

	private JButton jButtonLaunchBluetooth;
	private JButton jButtonBluetooth;
	private JButton jButtonCircleRight;
	private JButton jButtonCircleLeft;
	private JButton jButtonArmCounterclockwise;
	private JButton jButtonArmClockwise;
	private JButton jButtonStop;
	private JButton jButtonLeft;
	private JButton jButtonDown;
	private JButton jButtonUp;
	private JButton jButtonRight;
	private JButton jButtonRefresh;
	private JButton jButtonMoveForward;
	private JButton jButtonMoveBackward;
	private JButton jButtonTurnLeftDegrees;
	private JButton jButtonTurnRightDegrees;
	
	private JScrollPane jScrollCommandLog;
	private JScrollPane jScrollReceivedCommands;
	private JScrollPane jScrollPane4;
	private JScrollPane jScrollSentCommands;
	
	static JTextField jTextDistance;
	static JTextField jTextRotateAngle;

	private JTextArea jTextAreaInstructions;

	static JSlider jSliderTurnRadius;
	static JSlider jSliderDrivingSpeed;
	static JSlider jSliderArmSpeed;

	private JTextPane jTextReceivedCommands;
	private JTextPane jTextSentCommands;
	static JTextPane jTextCommandLog;
	
	private JTabbedPane jTabCommands;
	
	private JSeparator jSeparator16;
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
	
	static DataOutputStream os;
	static DataInputStream is;
	
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

	boolean firstPressed = true;
	
  //Used to display battery percent level
  public void paint(Graphics g2) {
	  super.paint(g2);
	  
	  int MAX_BATTERY_LEVEL = 137;
	  int BASE = 555;
	  int yCoordinate = BASE - MAX_BATTERY_LEVEL * BATTERY_PERCENT / 100;
	  
	  Graphics2D g = (Graphics2D) g2;
	  
	  if(BATTERY_PERCENT > 75)
		  g.setPaint(Color.blue);
	  else if(BATTERY_PERCENT > 50)
		  g.setPaint(Color.green);
	  else if(BATTERY_PERCENT > 25)
		  g.setPaint(Color.yellow);
	  else
		  g.setPaint(Color.red);
	  
	  g.fill(new Rectangle2D.Double(754, yCoordinate, 82, MAX_BATTERY_LEVEL * BATTERY_PERCENT / 100));
}
    
	// Runs the GUI
	public static void main(String[] args) throws NXTCommException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ControllerGUI inst = new ControllerGUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
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
				jTabCommands.setBounds(14, 323, 317, 236);
				jTabCommands.addKeyListener(this);
				jTabCommands.addMouseListener(this);
				{
					jScrollCommandLog = new JScrollPane();
					jTabCommands.addTab("Command Log", null, jScrollCommandLog, null);
					{
						jTextCommandLog = new JTextPane();
						jScrollCommandLog.setViewportView(jTextCommandLog);
						jTextCommandLog.setEditable(false);
						jTextCommandLog.setPreferredSize(new java.awt.Dimension(309, 205));
						jTextCommandLog.addMouseListener(this);
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
				jButtonLeft.setBounds(341, 84, 59, 73);
				jButtonLeft.addMouseListener(this);
				jButtonLeft.addKeyListener(this);
			}
			{
				jButtonRight = new JButton();
				getContentPane().add(jButtonRight);
				jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
				jButtonRight.setBounds(477, 84, 60, 73);
				jButtonRight.addMouseListener(this);
				jButtonRight.addKeyListener(this);
			}
			{
				jButtonUp = new JButton();
				getContentPane().add(jButtonUp);
				jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
				jButtonUp.setBounds(400, 21, 77, 64);
				jButtonUp.addKeyListener(this);
				jButtonUp.addMouseListener(this);
			}
			{
				jButtonDown = new JButton();
				getContentPane().add(jButtonDown);
				jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
				jButtonDown.setBounds(400, 157, 77, 61);
				jButtonDown.addKeyListener(this);
				jButtonDown.addMouseListener(this);
			}
			{
				jButtonStop = new JButton();
				getContentPane().add(jButtonStop);
				jButtonStop.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Stop.png")));
				jButtonStop.setBounds(405, 89, 67, 63);
				jButtonStop.addMouseListener(this);
				jButtonStop.addKeyListener(this);
				jButtonStop.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
						jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
						jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
						jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
						
						Command.stop(0);
					}
				});    
			}
			{
				jLabelArmSpeed = new JLabel();
				getContentPane().add(jLabelArmSpeed);
				jLabelArmSpeed.setText("Arm Speed:");
				jLabelArmSpeed.setBounds(761, 139, 75, 16);
				jLabelArmSpeed.addKeyListener(this);
				jLabelArmSpeed.addMouseListener(this);
			}
			{
				jButtonArmClockwise = new JButton();
				getContentPane().add(jButtonArmClockwise);
				jButtonArmClockwise.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Clockwise.png")));
				jButtonArmClockwise.setBounds(756, 50, 79, 81);
				jButtonArmClockwise.addKeyListener(this);
				jButtonArmClockwise.addMouseListener(this);
			}
			{
				jButtonArmCounterclockwise = new JButton();
				getContentPane().add(jButtonArmCounterclockwise);
				jButtonArmCounterclockwise.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Counterclockwise.png")));
				jButtonArmCounterclockwise.setBounds(756, 182, 79, 81);
				jButtonArmCounterclockwise.addKeyListener(this);
				jButtonArmCounterclockwise.addMouseListener(this);
			}
			{
				jLabelBattery = new JLabel();
				getContentPane().add(jLabelBattery);
				jLabelBattery.setBounds(751, 355, 87, 16);
			}
			{
				jLabelBatteryIcon = new JLabel();
				getContentPane().add(jLabelBatteryIcon);
				
				jLabelBattery.setText("Battery " + BATTERY_PERCENT + "%");
				jLabelBattery.setHorizontalTextPosition(SwingConstants.CENTER);
				jLabelBattery.setHorizontalAlignment(SwingConstants.CENTER);
				jLabelBattery.addKeyListener(this);
				jLabelBattery.addMouseListener(this);
				jLabelBatteryIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Battery0%.png")));
				jLabelBatteryIcon.setBounds(749, 377, 87, 157);
				jLabelBatteryIcon.addKeyListener(this);
				jLabelBatteryIcon.addMouseListener(this);
			}
			{
				jTextRotateAngle = new JTextField();
				jTextRotateAngle.setHorizontalAlignment(SwingConstants.CENTER);
				jTextRotateAngle.setText("25");
				getContentPane().add(jTextRotateAngle);
				jTextRotateAngle.setBounds(661, 142, 44, 20);
				jTextRotateAngle.addMouseListener(this);
				jTextRotateAngle.addKeyListener(this);
			}
			{
				jTextDistance = new JTextField();
				jTextDistance.setHorizontalAlignment(SwingConstants.CENTER);
				jTextDistance.setText("10");
				getContentPane().add(jTextDistance);
				jTextDistance.setBounds(661, 213, 44, 20);
				jTextDistance.addMouseListener(this);
				jTextDistance.addKeyListener(this);
			}
			{
				jLabelDrivingSpeed = new JLabel();
				getContentPane().add(jLabelDrivingSpeed);
				jLabelDrivingSpeed.setText("Driving Speed:");
				jLabelDrivingSpeed.setBounds(602, 9, 87, 16);
				jLabelDrivingSpeed.addKeyListener(this);
				jLabelDrivingSpeed.addMouseListener(this);
			}
			{
				jSliderDrivingSpeed = new JSlider();
				getContentPane().add(jSliderDrivingSpeed);
				jSliderDrivingSpeed.setBounds(554, 27, 178, 16);
				jSliderDrivingSpeed.addMouseListener(this);
				jSliderDrivingSpeed.addKeyListener(this);
			}
			{
				jLabelForward = new JLabel();
				getContentPane().add(jLabelForward);
				jLabelForward.setText("Forward");
				jLabelForward.setBounds(415, 4, 47, 16);
				jLabelForward.addKeyListener(this);
				jLabelForward.addMouseListener(this);
			}
			{
				jLabelBackward = new JLabel();
				getContentPane().add(jLabelBackward);
				jLabelBackward.setText("Backward");
				jLabelBackward.setBounds(409, 218, 58, 16);
				jLabelBackward.addKeyListener(this);
				jLabelBackward.addMouseListener(this);
			}
			{
				jLabelLeft = new JLabel();
				getContentPane().add(jLabelLeft);
				jLabelLeft.setText("Turn Left");
				jLabelLeft.setBounds(345, 67, 62, 16);
				jLabelLeft.addKeyListener(this);
				jLabelLeft.addMouseListener(this);
			}
			{
				jLabelRight = new JLabel();
				getContentPane().add(jLabelRight);
				jLabelRight.setText("Turn Right");
				jLabelRight.setBounds(478, 67, 69, 16);
				jLabelRight.addKeyListener(this);
				jLabelRight.addMouseListener(this);
			}
			{
				jLabelTurnRadius = new JLabel();
				getContentPane().add(jLabelTurnRadius);
				jLabelTurnRadius.setText("Turning Radius:");
				jLabelTurnRadius.setBounds(599, 50, 88, 16);
				jLabelTurnRadius.addKeyListener(this);
				jLabelTurnRadius.addMouseListener(this);
			}
			{
				jSliderTurnRadius = new JSlider();
				getContentPane().add(jSliderTurnRadius);
				jSliderTurnRadius.setBounds(555, 69, 177, 16);
				jSliderTurnRadius.addMouseListener(this);
				jSliderTurnRadius.addKeyListener(this);
			}
			{
				jSeparator1 = new JSeparator(SwingConstants.VERTICAL);
				getContentPane().add(jSeparator1);
				jSeparator1.setBounds(550, 9, 6, 298);
				jSeparator1.addKeyListener(this);
				jSeparator1.addMouseListener(this);
			}
			{
				jSeparator2 = new JSeparator();
				getContentPane().add(jSeparator2);
				jSeparator2.setOrientation(SwingConstants.VERTICAL);
				jSeparator2.setBounds(734, 9, 6, 298);
				jSeparator2.addKeyListener(this);
				jSeparator2.addMouseListener(this);
			}
			{
				jSeparator3 = new JSeparator();
				getContentPane().add(jSeparator3);
				jSeparator3.setBounds(550, 8, 185, 10);
				jSeparator3.addKeyListener(this);
				jSeparator3.addMouseListener(this);
			}
			{
				jSeparator4 = new JSeparator();
				getContentPane().add(jSeparator4);
				jSeparator4.setBounds(550, 258, 185, 10);
				jSeparator4.addKeyListener(this);
				jSeparator4.addMouseListener(this);
			}
			{
				jButtonCircleLeft = new JButton();
				jButtonCircleLeft.setMargin(new Insets(0,0,0,0));
				getContentPane().add(jButtonCircleLeft);
				jButtonCircleLeft.setText("Circle Left");
				jButtonCircleLeft.setBounds(562, 88, 80, 26);
				jButtonCircleLeft.addMouseListener(this);
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
						
						Command.circle(1);
					}
				}); 
			}
			{
				jSeparator5 = new JSeparator();
				getContentPane().add(jSeparator5);
				jSeparator5.setBounds(550, 46, 185, 10);
				jSeparator5.addKeyListener(this);
				jSeparator5.addMouseListener(this);
			}
			{
				jSeparator6 = new JSeparator();
				getContentPane().add(jSeparator6);
				jSeparator6.setBounds(550, 119, 185, 10);
				jSeparator6.addKeyListener(this);
				jSeparator6.addMouseListener(this);
			}
			{
				jButtonCircleRight = new JButton();
				jButtonCircleRight.setMargin(new Insets(0,0,0,0));
				getContentPane().add(jButtonCircleRight);
				jButtonCircleRight.setText("Circle Right");
				jButtonCircleRight.setBounds(647, 88, 76, 26);
				jButtonCircleRight.addMouseListener(this);
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
						
						Command.circle(0);
					}
				}); 
			}
			{
				jLabelBluetooth = new JLabel();
				getContentPane().add(jLabelBluetooth);
				jLabelBluetooth.setText("Bluetooth Connection");
				jLabelBluetooth.setBounds(350, 372, 127, 16);
				jLabelBluetooth.addKeyListener(this);
				jLabelBluetooth.addMouseListener(this);
			}
			{
				boolean connected = true;
				
				jButtonBluetooth = new JButton();
				getContentPane().add(jButtonBluetooth);
				
				if(connected)
					jButtonBluetooth.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Connected.png")));
				else
					jButtonBluetooth.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/NotConnected.png")));

				jButtonBluetooth.setBounds(348, 395, 127, 127);
				jButtonBluetooth.addKeyListener(this);
				jButtonBluetooth.addMouseListener(this);
			}
			{
				jLabelSensors = new JLabel();
				getContentPane().add(jLabelSensors);
				jLabelSensors.setText("SENSORS");
				jLabelSensors.setBounds(583, 368, 63, 16);
				jLabelSensors.addKeyListener(this);
				jLabelSensors.addMouseListener(this);
			}
			{
				jLabelSound = new JLabel();
				getContentPane().add(jLabelSound);
				jLabelSound.setText("Sound");
				jLabelSound.setBounds(492, 395, 48, 16);
				jLabelSound.addKeyListener(this);
				jLabelSound.addMouseListener(this);
			}
			{
				jLabelLight = new JLabel();
				getContentPane().add(jLabelLight);
				jLabelLight.setText("Light");
				jLabelLight.setBounds(537, 395, 48, 16);
				jLabelLight.addKeyListener(this);
				jLabelLight.addMouseListener(this);
			}
			{
				jLabelUltrasonic = new JLabel();
				getContentPane().add(jLabelUltrasonic);
				jLabelUltrasonic.setText("Ultrasonic");
				jLabelUltrasonic.setBounds(576, 395, 60, 16);
				jLabelUltrasonic.addKeyListener(this);
				jLabelUltrasonic.addMouseListener(this);
			}
			{
				jLabelTouch = new JLabel();
				getContentPane().add(jLabelTouch);
				jLabelTouch.setText("Touch Sensor");
				jLabelTouch.setBounds(646, 395, 85, 16);
				jLabelTouch.addKeyListener(this);
				jLabelTouch.addMouseListener(this);
			}
			{
				jLabelDecibels = new JLabel();
				getContentPane().add(jLabelDecibels);
				jLabelDecibels.setText(DECIBELS + " dB");
				jLabelDecibels.setBounds(495, 450, 32, 16);
				jLabelDecibels.addKeyListener(this);
				jLabelDecibels.addMouseListener(this);
			}
			{
				jLabelLightLevel = new JLabel();
				getContentPane().add(jLabelLightLevel);
				jLabelLightLevel.setText(LIGHT_PERCENT + "%");
				jLabelLightLevel.setBounds(540, 450, 23, 16);
				jLabelLightLevel.addKeyListener(this);
				jLabelLightLevel.addMouseListener(this);
			}
			{
				jSeparator7 = new JSeparator();
				getContentPane().add(jSeparator7);
				jSeparator7.setOrientation(SwingConstants.VERTICAL);
				jSeparator7.setBounds(532, 392, 5, 115);
				jSeparator7.addKeyListener(this);
				jSeparator7.addMouseListener(this);
			}
			{
				jSeparator8 = new JSeparator();
				getContentPane().add(jSeparator8);
				jSeparator8.setOrientation(SwingConstants.VERTICAL);
				jSeparator8.setBounds(570, 392, 9, 115);
				jSeparator8.addKeyListener(this);
				jSeparator8.addMouseListener(this);
			}
			{
				jLabelCentimeters = new JLabel();
				getContentPane().add(jLabelCentimeters);
				jLabelCentimeters.setText(ULTRASONIC + " cm");
				jLabelCentimeters.setBounds(587, 450, 35, 16);
				jLabelCentimeters.addKeyListener(this);
				jLabelCentimeters.addMouseListener(this);
			}
			{
				jSeparator9 = new JSeparator();
				getContentPane().add(jSeparator9);
				jSeparator9.setBounds(489, 391, 245, 10);
				jSeparator9.addKeyListener(this);
				jSeparator9.addMouseListener(this);
			}
			{
				jSeparator10 = new JSeparator();
				getContentPane().add(jSeparator10);
				jSeparator10.setBounds(489, 507, 245, 8);
				jSeparator10.addKeyListener(this);
				jSeparator10.addMouseListener(this);
			}
			{
				jSeparator11 = new JSeparator();
				getContentPane().add(jSeparator11);
				jSeparator11.setOrientation(SwingConstants.VERTICAL);
				jSeparator11.setBounds(489, 391, 11, 117);
				jSeparator11.addKeyListener(this);
				jSeparator11.addMouseListener(this);
			}
			{
				jSeparator12 = new JSeparator();
				getContentPane().add(jSeparator12);
				jSeparator12.setOrientation(SwingConstants.VERTICAL);
				jSeparator12.setBounds(638, 391, 10, 116);
				jSeparator12.addKeyListener(this);
				jSeparator12.addMouseListener(this);
			}
			{
				jSeparator13 = new JSeparator();
				getContentPane().add(jSeparator13);
				jSeparator13.setBounds(489, 416, 245, 9);
				jSeparator13.addKeyListener(this);
				jSeparator13.addMouseListener(this);
			}
			{
				jSeparator14 = new JSeparator();
				getContentPane().add(jSeparator14);
				jSeparator14.setOrientation(SwingConstants.VERTICAL);
				jSeparator14.setBounds(733, 391, 10, 116);
				jSeparator14.addKeyListener(this);
				jSeparator14.addMouseListener(this);
			}
			{
				jLabelTouchIcon = new JLabel();
				getContentPane().add(jLabelTouchIcon);
				if(TOUCH)
					jLabelTouchIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Touch.png")));
				else
					jLabelTouchIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/NoTouch.png")));
				jLabelTouchIcon.setBounds(645, 421, 82, 82);
				jLabelTouchIcon.addKeyListener(this);
				jLabelTouchIcon.addMouseListener(this);
			}
			{
				jLabelCamera = new JLabel();
				getContentPane().add(jLabelCamera);
				jLabelCamera.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/CameraView.png")));
				jLabelCamera.setBounds(15, 41, 311, 248);
				jLabelCamera.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jLabelCamera.addKeyListener(this);
				jLabelCamera.addMouseListener(this);
			}
			{
				jLabelArmDown = new JLabel();
				getContentPane().add(jLabelArmDown);
				jLabelArmDown.setText("<html>&#160&#160&#160&#160&#160&#160&#160Rotate Arm<br>Counterclockwise</html>");
				jLabelArmDown.setBounds(742, 264, 103, 32);
				jLabelArmDown.addKeyListener(this);
				jLabelArmDown.addMouseListener(this);
			}
			{
				jLabelArmClockwise = new JLabel();
				getContentPane().add(jLabelArmClockwise);
				jLabelArmClockwise.setText("<html>Rotate Arm<br>&#160Clockwise</html>");
				jLabelArmClockwise.setBounds(760, 13, 69, 38);
				jLabelArmClockwise.setHorizontalAlignment(SwingConstants.CENTER);
				jLabelArmClockwise.addKeyListener(this);
				jLabelArmClockwise.addMouseListener(this);
			}
			{
				jButtonLaunchBluetooth = new JButton();
				getContentPane().add(jButtonLaunchBluetooth);
				jButtonLaunchBluetooth.setText("Launch Bluetooth");
				jButtonLaunchBluetooth.setBounds(559, 267, 169, 33);
				jButtonLaunchBluetooth.addMouseListener(this);
				jButtonLaunchBluetooth.addKeyListener(this);
				jButtonLaunchBluetooth.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{	
						ConnectionHandler.launchBluetooth();
					}
				}); 
			}
			{
				jSeparator15 = new JSeparator();
				getContentPane().add(jSeparator15);
				jSeparator15.setBounds(551, 307, 183, 9);
				jSeparator15.addKeyListener(this);
				jSeparator15.addMouseListener(this);
			}
			{
				jButtonRefresh = new JButton();
				getContentPane().add(jButtonRefresh);
				jButtonRefresh.setText("Refresh Sensors");
				jButtonRefresh.setBounds(550, 519, 130, 26);
				jButtonRefresh.addKeyListener(this);
				jButtonRefresh.addMouseListener(this);
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
				jSliderArmSpeed.setBounds(748, 157, 94, 16);
				jSliderArmSpeed.addMouseListener(this);
				jSliderArmSpeed.addKeyListener(this);
			}
			{
				jLabelAngleUnits = new JLabel();
				getContentPane().add(jLabelAngleUnits);
				jLabelAngleUnits.setText("deg");
				jLabelAngleUnits.setBounds(708, 143, 28, 16);
				jLabelAngleUnits.addKeyListener(this);
				jLabelAngleUnits.addMouseListener(this);
			}
			{
				jLabelDistanceUnits = new JLabel();
				getContentPane().add(jLabelDistanceUnits);
				jLabelDistanceUnits.setText("cm");
				jLabelDistanceUnits.setBounds(709, 215, 18, 16);
				jLabelDistanceUnits.addKeyListener(this);
				jLabelDistanceUnits.addMouseListener(this);
			}
			{
				jTextAreaInstructions = new JTextArea();
				getContentPane().add(jTextAreaInstructions);
				jTextAreaInstructions.setText("----------Keyboard Instructions----------"
						+ "\nUp/Down arrows: forward/backward" + "\n   Left/Right arrows: rotate left/right"
						+ "\n                  Space bar: stop");
				jTextAreaInstructions.setAlignmentX(SwingConstants.CENTER);
				jTextAreaInstructions.setBounds(338, 237, 201, 71);
				jTextAreaInstructions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				jTextAreaInstructions.setLineWrap(true);
				jTextAreaInstructions.setFocusable(false);
				jTextAreaInstructions.addKeyListener(this);
				jTextAreaInstructions.addMouseListener(this);
			}
			{
				jButtonMoveForward = new JButton();
				getContentPane().add(jButtonMoveForward);
				jButtonMoveForward.setText("Go Forward:");
				jButtonMoveForward.setBounds(557, 194, 99, 26);
				jButtonMoveForward.setMargin(new Insets(0,0,0,0));
				jButtonMoveForward.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						Command.setTravelSpeed();
						
						Command.moveDistance(0);
					}
				});
			}
			{
				jButtonMoveBackward = new JButton();
				getContentPane().add(jButtonMoveBackward);
				jButtonMoveBackward.setText("Go Backward:");
				jButtonMoveBackward.setBounds(558, 227, 98, 26);
				jButtonMoveBackward.setMargin(new Insets(0,0,0,0));
				jButtonMoveBackward.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						Command.setTravelSpeed();
						
						Command.moveDistance(1);
					}
				});
			}
			{
				jSeparator16 = new JSeparator();
				getContentPane().add(jSeparator16);
				jSeparator16.setBounds(550, 187, 184, 10);
			}
			{
				jButtonTurnRightDegrees = new JButton();
				getContentPane().add(jButtonTurnRightDegrees);
				jButtonTurnRightDegrees.setText("Turn Right:");
				jButtonTurnRightDegrees.setBounds(556, 125, 100, 26);
				jButtonTurnRightDegrees.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						Command.turnDegrees(0);
					}
				});

			}
			{
				jButtonTurnLeftDegrees = new JButton();
				getContentPane().add(jButtonTurnLeftDegrees);
				jButtonTurnLeftDegrees.setText("Turn Left:");
				jButtonTurnLeftDegrees.setBounds(556, 156, 100, 26);
				jButtonTurnLeftDegrees.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						Command.turnDegrees(1);
					}
				});     
			}
			{
				jLabelCameraDisplay = new JLabel();
				getContentPane().add(jLabelCameraDisplay);
				jLabelCameraDisplay.setText("CAMERA DISPLAY");
				jLabelCameraDisplay.setBounds(118, 20, 101, 16);
			}
			pack();
			this.setSize(860, 600);
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

			Command.setTravelSpeed();
			
			Command.startMove(0);
		}
		
		if(arg0.getComponent() == jButtonDown)
		{
			jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingDown.png")));

			Command.setTravelSpeed();
			
			Command.startMove(1);
        }

		if(arg0.getComponent() == jButtonRight)
		{
			jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingRight.png")));
			
			Command.startRotate(0);		
        }
		
		if(arg0.getComponent() == jButtonLeft)
		{
			jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingLeft.png")));
			
			Command.startRotate(1);		
        }
		
		if(arg0.getComponent() == jButtonArmClockwise)
		{
			jButtonArmClockwise.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingClockwise.png")));
			
			Command.startRotateArm(0);
		}
		
		if(arg0.getComponent() == jButtonArmCounterclockwise)
		{
			jButtonArmCounterclockwise.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingCounterclockwise.png")));
			
			Command.startRotateArm(1);
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

			Command.stop(0);
		}
		
		if(arg0.getComponent() == jButtonArmClockwise || arg0.getComponent() == jButtonArmCounterclockwise)
		{
			if(arg0.getComponent() == jButtonArmClockwise)
				jButtonArmClockwise.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Clockwise.png")));
			
			if(arg0.getComponent() == jButtonArmCounterclockwise)
				jButtonArmCounterclockwise.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Counterclockwise.png")));
			
			Command.stop(1);
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
					jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingUp.png")));
				}
				
				if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
				{
					jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingDown.png")));
				}
				
				Command.setTravelSpeed();
				
				if(arg0.getKeyCode() == KeyEvent.VK_UP)
				{
					Command.startMove(0);
				}
				
				if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
				{
					Command.startMove(1);
				}
			}
		
			if(arg0.getKeyCode() == KeyEvent.VK_LEFT || arg0.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingRight.png")));
					
					Command.startRotate(0);	
				}
				
				if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
				{
					jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingLeft.png")));
					
					Command.startRotate(1);
				}
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_SPACE)
			{
				//Execute when button is pressed
				jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
				jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
				jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
				jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
				
				Command.stop(0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		firstPressed = true;

		if(arg0.getKeyCode() == KeyEvent.VK_UP || arg0.getKeyCode() == KeyEvent.VK_DOWN
				|| arg0.getKeyCode() == KeyEvent.VK_LEFT || arg0.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
			jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
			jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
			jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));		
			
			Command.stop(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}