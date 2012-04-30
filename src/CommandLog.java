import java.util.ArrayList;

public class CommandLog {

	private static ArrayList<String> allCommands = new ArrayList<String>();
	private static ArrayList<String> sentCommands = new ArrayList<String>();
	private static ArrayList<String> receivedCommands = new ArrayList<String>();
	
	public static void addSentCommand(String command)
	{
		allCommands.add(command);
		sentCommands.add(command);
	}
	
	public static void addReceivedCommand(String command)
	{
		allCommands.add(command);
		receivedCommands.add(command);
	}
	
	public static String getLastSentCommand()
	{
		return sentCommands.get(sentCommands.size() - 1);
	}
}