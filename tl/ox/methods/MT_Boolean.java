package tl.ox.methods;

public class MT_Boolean {
	
	public static boolean isOnOrOff(String s) {
		if (s.equalsIgnoreCase("on") || s.equalsIgnoreCase("off")) {
			return true;
		} else
			return false;
	}

	public static char booleanToOX(boolean b) {
		if (b) {
			return 'O';
		} else
			return 'X';
	}

}
