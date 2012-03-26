import lejos.pc.comm.*;


public class ConnectionHandler {
	
	NXTCommFactory connection;
	private int connectionSignalStrength;
	
	public static void main (String [] args) {
		
	}
	
	/**
	 * Attempts to connect to the NXT brick via USB and if this 
	 * fails it attempts to connect with Bluetooth
	 * 
	 * @param nxtCommFac
	 */
	public void connect (NXTCommFactory nxtCommFac) {
		
	}
	
	/**
	 * Disconnects from any current connections whether they are
	 * USB and Bluetooth
	 * 
	 * @param nxtCommFac
	 */
	public void disconnect (NXTCommFactory nxtCommFac) {
		
	}
	
	/**
	 * Returns the connection strength so it can be reported to 
	 * the GUI for the user
	 * 
	 * @return
	 */
	public int getConnectionSignalStrength () {
		return connectionSignalStrength;
	}
	
}
