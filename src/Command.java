
public class Command {
	
	private final boolean isValid = false;
	private int [] commandArray;
	
	public static void main (String [] args) {
		
	}
	
	public Command (int cmd) {
		
	}
	
	/**
	 * Checks the format of the command and ensures it has the correct
	 * number of parameters, ignores additional parameters
	 * 
	 * InfoHiding: this function doesn't know the actual commands
	 * and what they do to interface with the robot, it only checks 
	 * formatting
	 * 
	 * @param cmd
	 * @return
	 */
	public boolean checkCommand (int cmd) {
		return true;		
	}
	
	/**
	 * Returns the number of operands for a given command type and 
	 * ignores additional operands
	 * 
	 * @param cmd
	 * @return
	 */
	public int getNumberOfOperands (int cmd) {
		return 0;
	}
	
	/**
	 * Retrieves the command type (first 4 bits of the command)
	 * 
	 * InfoHiding: doesn't know actual function of command
	 * 
	 * @param cmd
	 * @return
	 */
	public int getCommandType (int cmd) {
		return 0;
	}

	/**
	 * Retrieves the operands and puts them into an array
	 * whose size depends on the number of possible operands for
	 * that command
	 * 
	 * InfoHiding: 
	 * 
	 * @param cmd
	 * @return
	 */
	public int [] getOperands (int cmd) {
		int [] arr = null;
		return arr;
		
	}
	
	/**
	 * Changes an operand given its number (0, 1, or 2)
	 * 
	 * InfoHiding: doesn't know the function of the instruction, 
	 * knows correct parameter format only
	 * 
	 * @param operand
	 * @param value
	 */
	public void setOperand (int operand, int value) {
		
	}
	
}
