import lejos.pc.comm.NXTCommException;

public class Command {

	public static void stop(int type)
	{
		String command = "600";
		
		if(type == 0)
		{
			command += "0";
			CommandLog.addSentCommand(command + " -- Stop movement\n\n");
		}
		else
		{
			command += "1";
			CommandLog.addSentCommand(command + " -- Stop rotating arm\n\n");
		}
		
		try {
			ConnectionHandler.sendCommand(command);
		} catch (NXTCommException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void setTravelSpeed(int type)
	{
		int speed = 50;
		
		if(type == 1)
			speed = ControllerGUI.jSliderDrivingSpeed.getValue();
		if(type == 8)
			speed = ControllerGUI.jSliderArmSpeed.getValue();

		String command = "1" + type;
		
		String hexSpeed = Integer.toHexString(speed);
		
		if(hexSpeed.length() == 1)
		{
			String temp = hexSpeed;
			hexSpeed = "0" + temp;
		}

		command += hexSpeed;
		
		CommandLog.addSentCommand(command + " -- Set speed to " + speed + "\n");

		try {
			ConnectionHandler.sendCommand(command);
		} catch (NXTCommException e1) {
			e1.printStackTrace();
		}
	}

	public static void startMove(int direction)
	{
		String command = "0";
		
		if(direction == 0)
		{
			command += "000";
			CommandLog.addSentCommand(command + " -- Move forward\n\n");
		}
		else
		{
			command += "100";
			CommandLog.addSentCommand(command + " -- Move backward\n\n");
		}

		try {
			ConnectionHandler.sendCommand(command);
		} catch (NXTCommException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void startRotate(int direction)
	{
		String command = "2";
		
		if(direction == 0)
		{
			command += "000";
			CommandLog.addSentCommand(command + " -- Start rotating right\n");
		}
		else
		{
			command += "100";
			CommandLog.addSentCommand(command + " -- Start rotating left\n");
		}
		
		try {
			ConnectionHandler.sendCommand(command);
		} catch (NXTCommException e1) {
			e1.printStackTrace();
		}		
	}
	
	public static void startRotateArm(int direction)
	{
		String command = "8";
		
		if(direction == 0)
			command += "000";
		else
			command += "001";
		
		if(direction == 0)
		{
			CommandLog.addSentCommand(command + " -- Start rotating arm clockwise at speed " + ControllerGUI.jSliderArmSpeed.getValue() + "\n");
		}
		else
		{
			CommandLog.addSentCommand(command + " -- Start rotating arm counterclockwise at speed " + ControllerGUI.jSliderArmSpeed.getValue() + "\n");
		}
		
		try {
			ConnectionHandler.sendCommand(command);
		} catch (NXTCommException e) {
			e.printStackTrace();
		}	
	}
	
	public static void circle(int direction)
	{
		String hexRadius = Integer.toHexString(ControllerGUI.jSliderTurnRadius.getValue());
		
		if(hexRadius.length() == 1)
		{
			String temp = hexRadius;
			hexRadius = "0" + temp;
		}
		
		String command = "7";
		
		if(direction == 0)
			command += "0";
		else
			command += "1";
		
		command += hexRadius;
		
		if(direction == 0)
		{
			CommandLog.addSentCommand(command + " -- Circle right with radius " + ControllerGUI.jSliderTurnRadius.getValue() + "\n\n");
		}
		else
		{
			CommandLog.addSentCommand(command + " -- Circle left with radius " + ControllerGUI.jSliderTurnRadius.getValue() + "\n\n");
		}

		try {
			ConnectionHandler.sendCommand(command);
		} catch (NXTCommException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void moveDistance(int direction)
	{
		String command = "0";
		
		if(direction == 0)
			command += "0";
		else
			command += "1";
		
		String distance = ControllerGUI.jTextDistance.getText();
		String hexDistnace = Integer.toHexString(Integer.parseInt(distance));
		
		if(hexDistnace.length() == 1)
		{
			String temp = hexDistnace;
			hexDistnace = "0" + temp;
		}
		
		command += hexDistnace;
		
		if(direction == 0)
		{
			CommandLog.addSentCommand(command + " -- Move forward " + distance + " cm\n\n");
		}
		else
		{
			CommandLog.addSentCommand(command + " -- Move backward " + distance + " cm\n\n");
		}

		try {
			ConnectionHandler.sendCommand(command);
		} catch (NXTCommException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void turnDegrees(int direction)
	{
		String command = "2";
		
		if(direction == 0)
			command += "0";
		else
			command += "1";
		
		String angleOfRotation = "";
		angleOfRotation = ControllerGUI.jTextRotateAngle.getText();
		
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
		
		if(direction == 0)
		{
			CommandLog.addSentCommand(command + " -- Rotate right " + Integer.parseInt(angleOfRotation) + " degrees\n\n");
		}
		else
		{
			CommandLog.addSentCommand(command + " -- Rotate left " + Integer.parseInt(angleOfRotation) + " degrees\n\n");
		}
		
		try {
			ConnectionHandler.sendCommand(command);
		} catch (NXTCommException e1) {
			e1.printStackTrace();
		}
	}
}