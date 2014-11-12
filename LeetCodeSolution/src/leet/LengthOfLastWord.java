package leet;

public class LengthOfLastWord {
	public int lengthOfLastWord(String s) {
		if (s == null || s.trim().length() == 0)
			return 0;
		s = s.trim();
		int count = 0;
		int endIndex = s.length() - 1;
		// !! endIndex>=0; boundary check
		while (endIndex >= 0 && s.charAt(endIndex) != ' ') {
			count++;
			endIndex--;
		}
		return count;
	}
}
