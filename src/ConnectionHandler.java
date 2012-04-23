import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class ConnectionHandler {
	
	// INITIATES BLUETOOTH CONNECTION WITH NXT (called when "Launch Bluetooth" button is pressed)
	public static void launchBluetooth()
	{
		ControllerGUI.jTextCommandLog.setText(ControllerGUI.jTextCommandLog.getText() + "Trying to connect...\n");
		
		NXTComm nxtComm;
		
		try {
			nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			
			NXTInfo info = new NXTInfo(NXTCommFactory.BLUETOOTH, "DATA_Robotics", "00:16:53:13:93:08");
			
			nxtComm.open(info);
			
			ControllerGUI.os = new DataOutputStream(nxtComm.getOutputStream());
			ControllerGUI.is = new DataInputStream(nxtComm.getInputStream());
			
			ControllerGUI.jTextCommandLog.setText(ControllerGUI.jTextCommandLog.getText() + "Connection established\n\n");
			
		} catch (Exception e1) {
			ControllerGUI.jTextCommandLog.setText(ControllerGUI.jTextCommandLog.getText() + "Unable to establish connection\n\n");
			e1.printStackTrace();
			return;
		}
	}
	
	// SENDS COMMAND TO ROBOT (called whenever a GUI action occurs)
	public static void sendCommand(String input) throws NXTCommException
	{	
		int message = Integer.parseInt(input, 16);
		try {
			int command = (message << 16) + message;
			ControllerGUI.os.writeInt(command);
			ControllerGUI.os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

			try {
				ControllerGUI.os.close();
				ControllerGUI.is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
	}
}