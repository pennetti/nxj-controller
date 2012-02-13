import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class Motion {
	
	public static void main(String[] args){
		
		InputStream is = null;
		DataInputStream ds = new DataInputStream(is);
		
		
		OutputStream os = null;
		PrintStream ps = new PrintStream(os);
		
		//Tests various function implementations
		Motion robot = new Motion();
		robot.accelerateToSpeed(250, 1000);
		robot.changeSpeedIncremental(200);
		robot.turnLeft(30);
		robot.goForward(100);
		robot.stopSlowly();
		if(robot.objectIsNear())
			robot.reverseAndGo(20);
	}
	
	public void accelerateToSpeed(int acceleration, int maxSpeed) //Accelerates robot to given maxSpeed, with given acceleration
	{
		if(Motor.A.getSpeed() < maxSpeed)
		{
			Motor.A.setAcceleration(acceleration);
			
			while(true)
			{
				if(Motor.A.getSpeed() == maxSpeed)
				{
					Motor.A.setAcceleration(0);
					break;
				}
			}
		}
	}
	
	public void stopSlowly() //Gradually slows down robot to complete stop
	{
		int DECELERATION = -250;
		Motor.A.setAcceleration(DECELERATION);
		while(true)
		{
			if(Motor.A.getSpeed() == 0)
			{
				Motor.A.setAcceleration(0);
				break;
			}
		}
	}
	
	public void changeSpeedIncremental(int value) //Immediate change in speed by given value (negative value for slow down)
	{
		int initSpeed = Motor.A.getSpeed();
		initSpeed += value;
		Motor.A.setSpeed(initSpeed);
	}
	
	public void turnLeft(int value) //Rotate robot left by given value of degrees
	{
		Motor.A.rotate(value * -1);
	}
	
	public void turnRight(int value) //Rotate robot right by given value of degrees
	{
		Motor.A.rotate(value);
	}
	
	public void goForward(int distance) //Advance robot forward by given distance
	{
		double wheelDiameter = 2.5;
		double trackWidth = 5; // distance between center of two wheels
		
		DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, Motor.A, Motor.C);
		pilot.travel(distance);
	}
	
	public void reverseAndGo(int distance) //Turns around robot and advances robot by given distance
	{
		Motor.A.rotate(180);
		
		double wheelDiameter = 2.5;
		double trackWidth = 5; // distance between center of two wheels
		
		DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, Motor.A, Motor.C);
		pilot.travel(distance);	}
	
	public boolean objectIsNear() //Returns true if object is within 20 cm of robot
	{
		int MIN_DISTANCE = 15;
		
		UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S1); //Assuming ultrasonic sensor is in port s1

		if(sonic.getDistance() < MIN_DISTANCE)
			return true;
		else
			return false;
	}
}