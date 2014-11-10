package leet;

public class ZigZagConversion {
	public String convert(String s, int nRows) {
		if (nRows <= 1)
			return s;
		if (s == null)
			return s;
		StringBuilder result = new StringBuilder("");
		int gap = (nRows - 1) * 2;
		// i means init index of that row, each row to string
		for (int i = 0; i < nRows; i++) {
			int index = i;
			while (index < s.length()) {
				result.append(s.charAt(index));
				// add zag of that row (moving upwards between two column) if
				// possible
				if (i > 0 && i < nRows - 1) {
					int midIndex = index + (nRows - 1 - i) * 2;
					if (midIndex < s.length()) {
						result.append(s.charAt(midIndex));
					}
				}
				// to next column
				index += gap;
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		ZigZagConversion zz = new ZigZagConversion();
		System.out.println(zz.convert("PAYPALISHIRING", 2));
	}
}
