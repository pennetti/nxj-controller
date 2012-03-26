import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class ControllerMain {
	private static boolean USBtest = false;
	static long start = 0, latency = 0;
	static boolean readFlag = true;
	static Object lock = new Object();

	public static void main(String[] args) throws NXTCommException {

		start = System.currentTimeMillis();
		final NXTComm connection;
		NXTInfo[] info;

		if (USBtest) {
			connection = NXTCommFactory.createNXTComm(NXTCommFactory.USB);
			info = connection.search(null, 0); // no need for name if it is conn
												// via USB
		} else {
			connection = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			info = connection.search("NXT", 1234); // your robot's name must be NXT and the code is 1234
		}

		if (info.length == 0) {
			System.out.println("Cant find any device to connect");
			return;
		}
		// open connections and data streams
		connection.open(info[0]);
		
		OutputStream os = connection.getOutputStream();
		InputStream is = connection.getInputStream();

		final DataOutputStream oHandle = new DataOutputStream(os);
		final DataInputStream iHandle = new DataInputStream(is);
		latency = System.currentTimeMillis() - start;
		System.out.printf("Connection is established [%dms]\n", latency);

		String input = "Initiate.";

		Scanner scanner = new Scanner(System.in);

		// Start a reader thread and readFlag is our barrier (i.e. set readFlag to false to terminate threads)
		Thread PCreceiver = new Thread() {
			public void run() {
				while (readFlag) {
					try {
						start = System.currentTimeMillis();
						byte[] buffer = new byte[256];
						int count = iHandle.read(buffer); // might wnt to check ack later
						if (count>0){
						String ret = (new String(buffer)).trim();
						long l = System.currentTimeMillis() - start;
						System.out.printf("NXJ: %s [%dms]\n", ret, l);
						}
						Thread.sleep(10);
					} catch (IOException e) {
						System.out.println("Fail to read from iHandle bc "
								+ e.toString());
						return;
					} catch (InterruptedException e){
						
					}

				}
			}
		};
		PCreceiver.start();
		
		System.out.println("\nPC: CONNECTED...");
		
		do{
			try {
				input = scanner.nextLine();
				start = System.currentTimeMillis();
				
				oHandle.write(input.getBytes());
				oHandle.flush();
				latency = System.currentTimeMillis() - start;
				
				System.out.println("\nPC: " + input + " [" + latency + "ms]");

//				 String ack = iHandle.readUTF();
//				 System.out.printf("NXJ: %s\n", ack);

				 
			} catch (IOException e) {
				System.out.println("Fail to write to oHandle bc "
						+ e.toString());
				return;
			}
		} while (!input.equalsIgnoreCase("exit"));
		
		
		try {
			connection.close();
			readFlag=false; // stop reading threads
			// stop all threads as well 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ending session...");
	}
}
