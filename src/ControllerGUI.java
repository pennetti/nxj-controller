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
import java.util.Timer;
import java.util.TimerTask;

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
	
	private JLabel jLabelDrivingSpeed;
	private static JLabel jLabelCentimeters;
	private static JLabel jLabelTouchIcon;
	private JLabel jLabelArmClockwise;
	private JLabel jLabelArmDown;
	private JLabel jLabelCamera;
	private static JLabel jLabelLightLevel;
	private static JLabel jLabelDecibels;
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
	private static JLabel jLabelBattery;
	private JLabel jLabelArmSpeed;
	private JLabel jLabelDistanceUnits;
	private JLabel jLabelAngleUnits;
	private JLabel jLabelCameraDisplay;
	private JLabel jLabelArmBounds;
	private JLabel jLabelGray3;
	private JLabel jLabelGray2;
	private JLabel jLabelGray1;
	static JLabel jLabelBluetoothImage;
	static JLabel jLabelTouchSensor;
	private static JLabel jLabelBluetoothConnection;

	private JButton jButtonKeyboardInstructions;
	private JButton jButtonLaunchBluetooth;
	private JButton jButtonCircleRight;
	private JButton jButtonCircleLeft;
	private JButton jButtonArmCounterclockwise;
	private JButton jButtonArmClockwise;
	private JButton jButtonStop;
	private JButton jButtonLeft;
	private JButton jButtonDown;
	private JButton jButtonUp;
	private JButton jButtonRight;
	static JButton jButtonRefresh;
	private JButton jButtonMoveForward;
	private JButton jButtonMoveBackward;
	private JButton jButtonTurnLeftDegrees;
	private JButton jButtonTurnRightDegrees;
	
	private JScrollPane jScrollCommandLog;
	private JScrollPane jScrollReceivedCommands;
	private JScrollPane jScrollSentCommands;
	
	static JTextField jTextDistance;
	static JTextField jTextRotateAngle;

	private JTextArea jTextAreaInstructions;

	static JSlider jSliderTurnRadius;
	static JSlider jSliderDrivingSpeed;
	static JSlider jSliderArmSpeed;

	static JTextPane jTextReceivedCommands;
	static JTextPane jTextSentCommands;
	static JTextPane jTextCommandLog;
	
	private JTabbedPane jTabCommands;
	
	private JSeparator jSeparator20;
	private JSeparator jSeparator19;
	private JSeparator jSeparator18;
	private JSeparator jSeparator17;
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
	boolean TOUCH = false;
	int LIGHT_PERCENT = 0;
	int ULTRASONIC = 0;
	int DECIBELS = 0;
	static int BATTERY_PERCENT = 0;

	//Used for mouse held down
	boolean firstPressed = true;
    		
	public static void main(String[] args) throws NXTCommException {
		// Run the GUI
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ControllerGUI inst = new ControllerGUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
		
		// Establish Bluetooth connection
		NXTComm nxtComm;
		
		try {
			
			nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			
			NXTInfo info = new NXTInfo(NXTCommFactory.BLUETOOTH, "DATA_Robotics", "00:16:53:13:93:08");

			nxtComm.open(info);
			
			os = new DataOutputStream(nxtComm.getOutputStream());
			is = new DataInputStream(nxtComm.getInputStream());
			
			jTextCommandLog.setText(jTextCommandLog.getText() + "Trying to connect...\n");
			jTextCommandLog.setText(jTextCommandLog.getText() + "       NXT Name -- \"" + ConnectionHandler.NXT_NAME
					+ "\"\n       NXT Address -- \"" + ConnectionHandler.NXT_ADDRESS + "\"\n");
			
			jTextCommandLog.setText(jTextCommandLog.getText() + "Connection established\n\n");
			
			jLabelBluetoothConnection.setText("Connected");

		} catch (Exception e1) {
			jTextCommandLog.setText(jTextCommandLog.getText() + "Unable to establish connection\n\n");
			e1.printStackTrace();
			return;
		}
		
		// Send robot status update
		Timer timer = new Timer(true);
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				try {
					ConnectionHandler.sendStatusCheck("F000");
				} catch (NXTCommException e) {
					e.printStackTrace();
				}
			}
		}, 0, 500);
		
		// Receive and decode sensor values
		while(true)
		{									
			char code = 'x';
			String valueHex = "xx";
			int mess = 0;
			
			try {
				byte[] buffer = new byte[4];

				int count = is.read(buffer); // pass the buffer to the input handle to read
				if (count>0){    			
					mess = ((0xFF & buffer[0]) << 24 | (0xFF & buffer[1]) << 16 | (0xFF & buffer[2]) << 8 | (0xFF & buffer[3]));
				}	
			} catch (Exception e) {
				//e.printStackTrace();
			}
			
			String message = Integer.toString(mess, 16);
			
			code = message.charAt(0);
			valueHex = message.substring(1, 4);
			int value = Integer.parseInt(valueHex, 16);
						
			switch(code){
			// Light Sensor
			case '1':		
				jLabelLightLevel.setText(value + "%");
				break;
				
			// Ultrasonic Sensor	
			case '2':
				jLabelCentimeters.setText(value + " cm");
				break;
				
			// Touch Sensor
			case '3':
				if(value == 1)
					jLabelTouchSensor.setText("Touch");
				else
					jLabelTouchSensor.setText("No Touch");
				break;
				
			// Sound Sensor
			case '4':
				jLabelDecibels.setText(value + " dB");
				break;
				
			// Battery Level	
			case '5':
				double level = ((double)value - 630) / (850 - 630) * 100;
				BATTERY_PERCENT = (int)level;
				jLabelBattery.setText("Battery " + BATTERY_PERCENT + "%");
				break;
				
			default:
				break;
			}
		}
	}
	
	  //Used to display battery percent level
	  public void paint(Graphics g2) {
		  super.paint(g2);
		  
		  //Aligns paint function with Battery Icon Image
		  int MAX_BATTERY_LEVEL = 137;
		  int x_COORDINATE = jLabelBatteryIcon.getX() + 5;
		  int y_COORDINATE = (jLabelBatteryIcon.getY() + jLabelBatteryIcon.getHeight() + 22) - MAX_BATTERY_LEVEL * BATTERY_PERCENT / 100;
		  int WIDTH = 82;
		  int HEIGHT = MAX_BATTERY_LEVEL * BATTERY_PERCENT / 100;
		  
		  Graphics2D g = (Graphics2D) g2;
		  
		  if(BATTERY_PERCENT > 75)
			  g.setPaint(Color.blue);
		  else if(BATTERY_PERCENT > 50)
			  g.setPaint(Color.green);
		  else if(BATTERY_PERCENT > 25)
			  g.setPaint(Color.yellow);
		  else
			  g.setPaint(Color.red);
		  
		  g.fill(new Rectangle2D.Double(x_COORDINATE, y_COORDINATE, WIDTH, HEIGHT));
	}
	  
	public static void setBluetoothImage(boolean connected) {
//		if(connected)
//			jLabelBluetoothImage.setIcon(new ImageIcon(jLabelBluetoothImage.getClass().getClassLoader().getResource("Images/Connected.png")));
//		else
//			jLabelBluetoothImage.setIcon(new ImageIcon(jLabelBluetoothImage.getClass().getClassLoader().getResource("Images/NotConnected.png")));
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
				jTabCommands.setBounds(14, 312, 317, 236);
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
						jTextCommandLog.addKeyListener(this);
					}
				}
				{
					jScrollSentCommands = new JScrollPane();
					jTabCommands.addTab("Sent", null, jScrollSentCommands, null);
					{
						jTextSentCommands = new JTextPane();
						jScrollSentCommands.setViewportView(jTextSentCommands);
						jTextSentCommands.setEditable(false);
						jTextSentCommands.setPreferredSize(new java.awt.Dimension(309, 205));
						jTextSentCommands.addMouseListener(this);
						jTextSentCommands.addKeyListener(this);
					}
				}
				{
					jScrollReceivedCommands = new JScrollPane();
					jTabCommands.addTab("Received", null, jScrollReceivedCommands, null);
					{
						jTextReceivedCommands = new JTextPane();
						jScrollReceivedCommands.setViewportView(jTextReceivedCommands);
						jTextReceivedCommands.setEditable(false);
						jTextReceivedCommands.setPreferredSize(new java.awt.Dimension(309, 205));
						jTextReceivedCommands.addMouseListener(this);
						jTextReceivedCommands.addKeyListener(this);
					}
				}
			}
			{
				jButtonLeft = new JButton();
				getContentPane().add(jButtonLeft);
				jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
				jButtonLeft.setBounds(339, 106, 59, 73);
				jButtonLeft.addMouseListener(this);
				jButtonLeft.addKeyListener(this);
			}
			{
				jButtonRight = new JButton();
				getContentPane().add(jButtonRight);
				jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
				jButtonRight.setBounds(475, 106, 60, 73);
				jButtonRight.addMouseListener(this);
				jButtonRight.addKeyListener(this);
			}
			{
				jButtonUp = new JButton();
				getContentPane().add(jButtonUp);
				jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
				jButtonUp.setBounds(398, 43, 77, 64);
				jButtonUp.addKeyListener(this);
				jButtonUp.addMouseListener(this);
			}
			{
				jButtonDown = new JButton();
				getContentPane().add(jButtonDown);
				jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
				jButtonDown.setBounds(398, 179, 77, 61);
				jButtonDown.addKeyListener(this);
				jButtonDown.addMouseListener(this);
			}
			{
				jButtonStop = new JButton();
				getContentPane().add(jButtonStop);
				jButtonStop.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Stop.png")));
				jButtonStop.setBounds(403, 111, 67, 63);
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
				jLabelArmSpeed.setText("Arm Speed (deg/s):");
				jLabelArmSpeed.setBounds(748, 134, 113, 16);
				jLabelArmSpeed.addKeyListener(this);
				jLabelArmSpeed.addMouseListener(this);
			}
			{
				jButtonArmClockwise = new JButton();
				getContentPane().add(jButtonArmClockwise);
				jButtonArmClockwise.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Clockwise.png")));
				jButtonArmClockwise.setBounds(763, 47, 79, 81);
				jButtonArmClockwise.addKeyListener(this);
				jButtonArmClockwise.addMouseListener(this);
			}
			{
				jButtonArmCounterclockwise = new JButton();
				getContentPane().add(jButtonArmCounterclockwise);
				jButtonArmCounterclockwise.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Counterclockwise.png")));
				jButtonArmCounterclockwise.setBounds(763, 190, 79, 81);
				jButtonArmCounterclockwise.addKeyListener(this);
				jButtonArmCounterclockwise.addMouseListener(this);
			}
			{
				jLabelBattery = new JLabel();
				getContentPane().add(jLabelBattery);
				jLabelBattery.setBounds(765, 348, 87, 16);
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
				jLabelBatteryIcon.setBounds(765, 369, 87, 157);
				jLabelBatteryIcon.addKeyListener(this);
				jLabelBatteryIcon.addMouseListener(this);
			}
			{
				jTextRotateAngle = new JTextField();
				jTextRotateAngle.setHorizontalAlignment(SwingConstants.CENTER);
				jTextRotateAngle.setText("25");
				getContentPane().add(jTextRotateAngle);
				jTextRotateAngle.setBounds(621, 138, 44, 20);
				jTextRotateAngle.addMouseListener(this);
				jTextRotateAngle.addKeyListener(this);
			}
			{
				jTextDistance = new JTextField();
				jTextDistance.setHorizontalAlignment(SwingConstants.CENTER);
				jTextDistance.setText("10");
				getContentPane().add(jTextDistance);
				jTextDistance.setBounds(613, 220, 44, 19);
				jTextDistance.addMouseListener(this);
				jTextDistance.addKeyListener(this);
			}
			{
				jLabelDrivingSpeed = new JLabel();
				getContentPane().add(jLabelDrivingSpeed);
				jLabelDrivingSpeed.setText("0       Drive Speed (cm/s):    100");
				jLabelDrivingSpeed.setBounds(559, 11, 173, 16);
				jLabelDrivingSpeed.addKeyListener(this);
				jLabelDrivingSpeed.addMouseListener(this);
			}
			{
				jSliderDrivingSpeed = new JSlider();
				getContentPane().add(jSliderDrivingSpeed);
				jSliderDrivingSpeed.setBounds(554, 27, 178, 16);
				jSliderDrivingSpeed.setBackground(Color.getHSBColor(0, 0, .78f));
				jSliderDrivingSpeed.setFocusable(false);
				jSliderDrivingSpeed.addMouseListener(this);
				jSliderDrivingSpeed.addKeyListener(this);
			}
			{
				jLabelForward = new JLabel();
				getContentPane().add(jLabelForward);
				jLabelForward.setText("Forward");
				jLabelForward.setBounds(413, 26, 47, 16);
				jLabelForward.addKeyListener(this);
				jLabelForward.addMouseListener(this);
			}
			{
				jLabelBackward = new JLabel();
				getContentPane().add(jLabelBackward);
				jLabelBackward.setText("Backward");
				jLabelBackward.setBounds(407, 240, 58, 16);
				jLabelBackward.addKeyListener(this);
				jLabelBackward.addMouseListener(this);
			}
			{
				jLabelLeft = new JLabel();
				getContentPane().add(jLabelLeft);
				jLabelLeft.setText("Turn Left");
				jLabelLeft.setBounds(343, 89, 62, 16);
				jLabelLeft.addKeyListener(this);
				jLabelLeft.addMouseListener(this);
			}
			{
				jLabelRight = new JLabel();
				getContentPane().add(jLabelRight);
				jLabelRight.setText("Turn Right");
				jLabelRight.setBounds(476, 89, 69, 16);
				jLabelRight.addKeyListener(this);
				jLabelRight.addMouseListener(this);
			}
			{
				jLabelTurnRadius = new JLabel();
				getContentPane().add(jLabelTurnRadius);
				jLabelTurnRadius.setText("0         Turn Radius (cm):     100");
				jLabelTurnRadius.setBounds(559, 52, 174, 16);
				jLabelTurnRadius.addKeyListener(this);
				jLabelTurnRadius.addMouseListener(this);
			}
			{
				jSliderTurnRadius = new JSlider();
				getContentPane().add(jSliderTurnRadius);
				jSliderTurnRadius.setBounds(555, 69, 177, 16);
				jSliderTurnRadius.setFocusable(false);
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
				jSeparator4.setBounds(550, 270, 185, 10);
				jSeparator4.addKeyListener(this);
				jSeparator4.addMouseListener(this);
			}
			{
				jButtonCircleLeft = new JButton();
				jButtonCircleLeft.setMargin(new Insets(0,0,0,0));
				getContentPane().add(jButtonCircleLeft);
				jButtonCircleLeft.setText("Circle Left");
				jButtonCircleLeft.setBounds(562, 88, 80, 26);
				jButtonCircleLeft.setFocusable(false);
				jButtonCircleLeft.addMouseListener(this);
				jButtonCircleLeft.addKeyListener(this);
				jButtonCircleLeft.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{		            						
						//Execute when button is pressed
						jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
						jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
						jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
						jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
						
						Command.setTravelSpeed(1);
						
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
				jButtonCircleRight.setFocusable(false);
				jButtonCircleRight.addMouseListener(this);
				jButtonCircleRight.addKeyListener(this);
				jButtonCircleRight.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{		            	
						//Execute when button is pressed
						jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Left.png")));
						jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Right.png")));
						jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Up.png")));
						jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Down.png")));
						
						Command.setTravelSpeed(1);
						
						Command.circle(0);
					}
				}); 
			}
			{
				jLabelBluetooth = new JLabel();
				getContentPane().add(jLabelBluetooth);
				jLabelBluetooth.setText("BLUETOOTH");
				jLabelBluetooth.setBounds(352, 366, 126, 16);
				jLabelBluetooth.setHorizontalAlignment(SwingConstants.CENTER);
				jLabelBluetooth.addKeyListener(this);
				jLabelBluetooth.addMouseListener(this);
			}
			{
				jLabelSensors = new JLabel();
				getContentPane().add(jLabelSensors);
				jLabelSensors.setText("SENSORS");
				jLabelSensors.setBounds(499, 366, 245, 16);
				jLabelSensors.setHorizontalAlignment(SwingConstants.CENTER);
				jLabelSensors.addKeyListener(this);
				jLabelSensors.addMouseListener(this);
			}
			{
				jLabelSound = new JLabel();
				getContentPane().add(jLabelSound);
				jLabelSound.setText("Sound");
				jLabelSound.setBounds(502, 393, 48, 16);
				jLabelSound.addKeyListener(this);
				jLabelSound.addMouseListener(this);
			}
			{
				jLabelLight = new JLabel();
				getContentPane().add(jLabelLight);
				jLabelLight.setText("Light");
				jLabelLight.setBounds(547, 393, 48, 16);
				jLabelLight.addKeyListener(this);
				jLabelLight.addMouseListener(this);
			}
			{
				jLabelUltrasonic = new JLabel();
				getContentPane().add(jLabelUltrasonic);
				jLabelUltrasonic.setText("Ultrasonic");
				jLabelUltrasonic.setBounds(586, 393, 60, 16);
				jLabelUltrasonic.addKeyListener(this);
				jLabelUltrasonic.addMouseListener(this);
			}
			{
				jLabelTouch = new JLabel();
				getContentPane().add(jLabelTouch);
				jLabelTouch.setText("Touch Sensor");
				jLabelTouch.setBounds(656, 393, 85, 16);
				jLabelTouch.addKeyListener(this);
				jLabelTouch.addMouseListener(this);
			}
			{
				jLabelDecibels = new JLabel();
				getContentPane().add(jLabelDecibels);
				jLabelDecibels.setText(DECIBELS + " dB");
				jLabelDecibels.setBounds(502, 448, 39, 16);
				jLabelDecibels.setHorizontalAlignment(SwingConstants.CENTER);
				jLabelDecibels.addKeyListener(this);
				jLabelDecibels.addMouseListener(this);
			}
			{
				jLabelLightLevel = new JLabel();
				getContentPane().add(jLabelLightLevel);
				jLabelLightLevel.setText(LIGHT_PERCENT + "%");
				jLabelLightLevel.setBounds(545, 448, 34, 16);
				jLabelLightLevel.setHorizontalAlignment(SwingConstants.CENTER);
				jLabelLightLevel.addKeyListener(this);
				jLabelLightLevel.addMouseListener(this);
			}
			{
				jSeparator7 = new JSeparator();
				getContentPane().add(jSeparator7);
				jSeparator7.setOrientation(SwingConstants.VERTICAL);
				jSeparator7.setBounds(542, 390, 5, 115);
				jSeparator7.addKeyListener(this);
				jSeparator7.addMouseListener(this);
			}
			{
				jSeparator8 = new JSeparator();
				getContentPane().add(jSeparator8);
				jSeparator8.setOrientation(SwingConstants.VERTICAL);
				jSeparator8.setBounds(580, 390, 9, 115);
				jSeparator8.addKeyListener(this);
				jSeparator8.addMouseListener(this);
			}
			{
				jLabelCentimeters = new JLabel();
				getContentPane().add(jLabelCentimeters);
				jLabelCentimeters.setText(ULTRASONIC + " cm");
				jLabelCentimeters.setBounds(583, 448, 64, 16);
				jLabelCentimeters.setHorizontalAlignment(SwingConstants.CENTER);
				jLabelCentimeters.addKeyListener(this);
				jLabelCentimeters.addMouseListener(this);
			}
			{
				jSeparator9 = new JSeparator();
				getContentPane().add(jSeparator9);
				jSeparator9.setBounds(499, 389, 245, 10);
				jSeparator9.addKeyListener(this);
				jSeparator9.addMouseListener(this);
			}
			{
				jSeparator10 = new JSeparator();
				getContentPane().add(jSeparator10);
				jSeparator10.setBounds(499, 505, 245, 8);
				jSeparator10.addKeyListener(this);
				jSeparator10.addMouseListener(this);
			}
			{
				jSeparator11 = new JSeparator();
				getContentPane().add(jSeparator11);
				jSeparator11.setOrientation(SwingConstants.VERTICAL);
				jSeparator11.setBounds(499, 389, 11, 117);
				jSeparator11.addKeyListener(this);
				jSeparator11.addMouseListener(this);
			}
			{
				jSeparator12 = new JSeparator();
				getContentPane().add(jSeparator12);
				jSeparator12.setOrientation(SwingConstants.VERTICAL);
				jSeparator12.setBounds(648, 389, 10, 116);
				jSeparator12.addKeyListener(this);
				jSeparator12.addMouseListener(this);
			}
			{
				jSeparator13 = new JSeparator();
				getContentPane().add(jSeparator13);
				jSeparator13.setBounds(499, 414, 245, 9);
				jSeparator13.addKeyListener(this);
				jSeparator13.addMouseListener(this);
			}
			{
				jSeparator14 = new JSeparator();
				getContentPane().add(jSeparator14);
				jSeparator14.setOrientation(SwingConstants.VERTICAL);
				jSeparator14.setBounds(743, 389, 10, 116);
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
				jLabelTouchIcon.setVisible(false);
				jLabelTouchIcon.setBounds(655, 419, 82, 82);
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
				jLabelArmDown.setBounds(749, 272, 103, 32);
				jLabelArmDown.addKeyListener(this);
				jLabelArmDown.addMouseListener(this);
			}
			{
				jLabelArmClockwise = new JLabel();
				getContentPane().add(jLabelArmClockwise);
				jLabelArmClockwise.setText("<html>Rotate Arm<br>&#160Clockwise</html>");
				jLabelArmClockwise.setBounds(767, 10, 69, 38);
				jLabelArmClockwise.setHorizontalAlignment(SwingConstants.CENTER);
				jLabelArmClockwise.addKeyListener(this);
				jLabelArmClockwise.addMouseListener(this);
			}
			{
				jButtonLaunchBluetooth = new JButton();
				getContentPane().add(jButtonLaunchBluetooth);
				jButtonLaunchBluetooth.setText("Launch Bluetooth");
				jButtonLaunchBluetooth.setBounds(556, 276, 173, 26);
				jButtonLaunchBluetooth.setFocusable(false);
				jButtonLaunchBluetooth.addMouseListener(this);
				jButtonLaunchBluetooth.addKeyListener(this);
				jButtonLaunchBluetooth.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{						
						NXTComm nxtComm;
						
						try {
							nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
							
							NXTInfo info = new NXTInfo(NXTCommFactory.BLUETOOTH, "DATA_Robotics", "00:16:53:13:93:08");
//							NXTInfo info = new NXTInfo(NXTCommFactory.BLUETOOTH, "7a", "00:16:53:13:f6:a4");

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
//				jButtonLaunchBluetooth.addActionListener(new ActionListener() {
//					
//					public void actionPerformed(ActionEvent e)
//					{	
//						ConnectionHandler.launchBluetooth();
//					}
//				}); 
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
				jButtonRefresh.setBounds(560, 517, 130, 26);
				jButtonRefresh.addKeyListener(this);
				jButtonRefresh.addMouseListener(this);
				jButtonRefresh.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{	
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
				jSliderArmSpeed.setBounds(747, 155, 110, 16);
				jSliderArmSpeed.setFocusable(false);
				jSliderArmSpeed.addMouseListener(this);
				jSliderArmSpeed.addKeyListener(this);
			}
			{
				jLabelAngleUnits = new JLabel();
				getContentPane().add(jLabelAngleUnits);
				jLabelAngleUnits.setText("deg");
				jLabelAngleUnits.setBounds(631, 158, 28, 16);
				jLabelAngleUnits.addKeyListener(this);
				jLabelAngleUnits.addMouseListener(this);
			}
			{
				jLabelDistanceUnits = new JLabel();
				getContentPane().add(jLabelDistanceUnits);
				jLabelDistanceUnits.setText("cm");
				jLabelDistanceUnits.setBounds(658, 221, 18, 16);
				jLabelDistanceUnits.addKeyListener(this);
				jLabelDistanceUnits.addMouseListener(this);
			}
			{
				jTextAreaInstructions = new JTextArea();
				getContentPane().add(jTextAreaInstructions);
				jTextAreaInstructions.setText("Up/Down arrows: forward/backward" + "\n   Left/Right arrows: rotate left/right"
						+ "\n                  Space bar: stop"
						+ "\nD/A keys: arm clock/counterclockwise");
				jTextAreaInstructions.setAlignmentX(SwingConstants.CENTER);
				jTextAreaInstructions.setBounds(335, 294, 208, 70);
				jTextAreaInstructions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				jTextAreaInstructions.setLineWrap(true);
				jTextAreaInstructions.setFocusable(false);
				jTextAreaInstructions.addKeyListener(this);
				jTextAreaInstructions.addMouseListener(this);
				jTextAreaInstructions.setVisible(false);
			}
			{
				jButtonMoveForward = new JButton();
				getContentPane().add(jButtonMoveForward);
				jButtonMoveForward.setText("Go Forward");
				jButtonMoveForward.setBounds(571, 192, 143, 26);
				jButtonMoveForward.setMargin(new Insets(0,0,0,0));
				jButtonMoveForward.setFocusable(false);
				jButtonMoveForward.addKeyListener(this);
				jButtonMoveForward.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						Command.setTravelSpeed(1);
						
						Command.moveDistance(0);
					}
				});
			}
			{
				jButtonMoveBackward = new JButton();
				getContentPane().add(jButtonMoveBackward);
				jButtonMoveBackward.setText("Go Backward");
				jButtonMoveBackward.setBounds(571, 241, 143, 26);
				jButtonMoveBackward.setMargin(new Insets(0,0,0,0));
				jButtonMoveBackward.setFocusable(false);
				jButtonMoveBackward.addKeyListener(this);
				jButtonMoveBackward.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						Command.setTravelSpeed(1);
						
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
				jButtonTurnRightDegrees.setText("<html>Rotate<br>&#160Right</html>");
				jButtonTurnRightDegrees.setMargin(new Insets(5,4,5,4));
				jButtonTurnRightDegrees.setBounds(667, 127, 63, 55);
				jButtonTurnRightDegrees.addKeyListener(this);
				jButtonTurnRightDegrees.setFocusable(false);
				jButtonTurnRightDegrees.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						Command.setTravelSpeed(1);
						
						Command.turnDegrees(0);
					}
				});

			}
			{
				jButtonTurnLeftDegrees = new JButton();
				getContentPane().add(jButtonTurnLeftDegrees);
				jButtonTurnLeftDegrees.setText("<html>Rotate<br>&#160&#160Left</html>");
				jButtonTurnLeftDegrees.setMargin(new Insets(5,4,5,4));
				jButtonTurnLeftDegrees.setBounds(556, 127, 63, 55);
				jButtonTurnLeftDegrees.addKeyListener(this);
				jButtonTurnLeftDegrees.setFocusable(false);
				jButtonTurnLeftDegrees.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						Command.setTravelSpeed(1);
						
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
			{
				jButtonKeyboardInstructions = new JButton();
				getContentPane().add(jButtonKeyboardInstructions);
				jButtonKeyboardInstructions.setText("Show Keyboard Instructions");
				jButtonKeyboardInstructions.setBounds(341, 266, 194, 25);
				jButtonKeyboardInstructions.addKeyListener(this);
				jButtonKeyboardInstructions.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						if(!jTextAreaInstructions.isVisible())
						{
							jTextAreaInstructions.setVisible(true);
							jButtonKeyboardInstructions.setText("Hide Keyboard Instructions");
						}
						else
						{
							jTextAreaInstructions.setVisible(false);
							jButtonKeyboardInstructions.setText("Show Keyboard Instructions");
						}
					}
				});    
			}
			{
				jLabelArmBounds = new JLabel();
				getContentPane().add(jLabelArmBounds);
				jLabelArmBounds.setText("0                          100");
				jLabelArmBounds.setBounds(751, 167, 106, 16);
			}
			{
				jLabelGray1 = new JLabel();
				getContentPane().add(jLabelGray1);
				jLabelGray1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/gray.png")));
				jLabelGray1.setBounds(550, 10, 183, 35);
			}
			{
				jLabelGray2 = new JLabel();
				getContentPane().add(jLabelGray2);
				jLabelGray2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/gray.png")));
				jLabelGray2.setBounds(552, 121, 181, 65);
			}
			{
				jLabelGray3 = new JLabel();
				getContentPane().add(jLabelGray3);
				jLabelGray3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/gray.png")));
				jLabelGray3.setBounds(552, 270, 181, 36);
			}
			{
				jLabelBluetoothImage = new JLabel();
				getContentPane().add(jLabelBluetoothImage);
				jLabelBluetoothImage.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/NotConnected.png")));
				jLabelBluetoothImage.setBounds(354, 387, 120, 120);
				jLabelBluetoothImage.setVisible(false);
				jLabelBluetoothImage.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			}
			{
				jLabelTouchSensor = new JLabel();
				getContentPane().add(jLabelTouchSensor);
				jLabelTouchSensor.setText("No Touch");
				jLabelTouchSensor.setBounds(652, 448, 89, 16);
				jLabelTouchSensor.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jLabelBluetoothConnection = new JLabel();
				getContentPane().add(jLabelBluetoothConnection);
				jLabelBluetoothConnection.setText("Not Connected");
				jLabelBluetoothConnection.setBounds(352, 438, 125, 16);
				jLabelBluetoothConnection.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jSeparator17 = new JSeparator();
				getContentPane().add(jSeparator17);
				jSeparator17.setBounds(352, 390, 126, 10);
			}
			{
				jSeparator18 = new JSeparator();
				getContentPane().add(jSeparator18);
				jSeparator18.setBounds(352, 505, 126, 10);
			}
			{
				jSeparator19 = new JSeparator();
				getContentPane().add(jSeparator19);
				jSeparator19.setOrientation(SwingConstants.VERTICAL);
				jSeparator19.setBounds(478, 390, 10, 116);
			}
			{
				jSeparator20 = new JSeparator();
				getContentPane().add(jSeparator20);
				jSeparator20.setOrientation(SwingConstants.VERTICAL);
				jSeparator20.setBounds(351, 390, 10, 115);
			}
			pack();
			this.setSize(876, 593);
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
        
		if(arg0.getComponent() == jButtonLaunchBluetooth)
		{
			jTextCommandLog.setText(jTextCommandLog.getText() + "Trying to connect...\n");
			jTextCommandLog.setText(jTextCommandLog.getText() + "       NXT Name -- \"" + ConnectionHandler.NXT_NAME
					+ "\"\n       NXT Address -- \"" + ConnectionHandler.NXT_ADDRESS + "\"\n");
		}
		
		if(arg0.getComponent() == jButtonUp)
		{
			jButtonUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingUp.png")));

			Command.setTravelSpeed(1);
			
			Command.startMove(0);
		}
		
		if(arg0.getComponent() == jButtonDown)
		{
			jButtonDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingDown.png")));

			Command.setTravelSpeed(1);
			
			Command.startMove(1);
        }

		if(arg0.getComponent() == jButtonRight)
		{
			jButtonRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingRight.png")));
			
			Command.setTravelSpeed(1);
			
			Command.startRotate(0);		
        }
		
		if(arg0.getComponent() == jButtonLeft)
		{
			jButtonLeft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingLeft.png")));
			
			Command.setTravelSpeed(1);
			
			Command.startRotate(1);		
        }
		
		if(arg0.getComponent() == jButtonArmClockwise)
		{
			jButtonArmClockwise.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingClockwise.png")));
			
			Command.setTravelSpeed(8);
			
			Command.startRotateArm(0);
		}
		
		if(arg0.getComponent() == jButtonArmCounterclockwise)
		{
			jButtonArmCounterclockwise.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingCounterclockwise.png")));
			
			Command.setTravelSpeed(8);
			
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
				
				Command.setTravelSpeed(1);
				
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
				Command.setTravelSpeed(1);
				
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
			
			if(arg0.getKeyCode() == KeyEvent.VK_D)
			{
				jButtonArmClockwise.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingClockwise.png")));
				
				Command.setTravelSpeed(8);
				
				Command.startRotateArm(0);
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_A)
			{
				jButtonArmCounterclockwise.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/GoingCounterclockwise.png")));
				
				Command.setTravelSpeed(8);
				
				Command.startRotateArm(1);
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
		
		if(arg0.getKeyCode() == KeyEvent.VK_D || arg0.getKeyCode() == KeyEvent.VK_A)
		{
			if(arg0.getKeyCode() == KeyEvent.VK_D)
				jButtonArmClockwise.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Clockwise.png")));
			
			if(arg0.getKeyCode() == KeyEvent.VK_A)
				jButtonArmCounterclockwise.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Images/Counterclockwise.png")));
			
			Command.stop(1);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}