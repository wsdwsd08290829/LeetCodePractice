package leet;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
	public List<String> fullJustify(String[] words, int L) {
		List<String> result = new ArrayList<String>();
		if (words == null)
			return result;

		int lineLen = 0;
		int start = 0;
		for (int i = 0; i <= words.length; i++) {
			StringBuilder line = new StringBuilder();
			if (i < words.length && lineLen + words[i].length() <= L) {
				lineLen += words[i].length() + 1; // 1 is space
			} else if ((i <= words.length - 1 && lineLen + words[i].length() > L)
					|| i == words.length) { // if newLine reached or !!!end of
											// words wordReached
				// length of words no spaces
				int wordLength = lineLen - (i - start);
				int gaps = i - start - 1;
				// onle one words in line
				if (gaps == 0) {
					line.append(words[start]);
					appendSpaces(line, L - words[start].length());
				}
				// two or more words in line
				if (gaps > 0) {
					// special for last line: "shall    be." =>"shall be.   "
					if (words.length == i) {
						for (int k = start; k < i; k++) {
							line.append(words[k]);
							line.append(" ");
						}
						appendSpaces(line, L - lineLen);
						result.add(line.toString());
						return result;
					}
					// assign extra to each spaces when there is extra
					int spacesNum = (L - wordLength) / gaps;
					int extraNum = (L - wordLength) % gaps;
					for (int k = start; k < i; k++) {
						line.append(words[k]);
						if (k < i - 1) {
							appendSpaces(line, spacesNum);
							if (extraNum > 0) {
								extraNum--;
								line.append(" ");
							}
						}
					}
				}
				if (i < words.length) {
					lineLen = words[i].length() + 1;
					start = i;
				}

				result.add(line.toString());
			}
		} // end for

		return result;
	}

	public void appendSpaces(StringBuilder sb, int count) {
		while (count > 0) {
			sb.append(" ");
			count--;
		}
	}

	// same idea, clear code
	// http://blog.csdn.net/linhuanmars/article/details/24063271
	public ArrayList<String> fullJustify1(String[] words, int L) {
		ArrayList<String> res = new ArrayList<String>();
		if (words == null || words.length == 0)
			return res;
		int count = 0; // all word length sum, no space
		int last = 0;
		for (int i = 0; i < words.length; i++) {
			if (count + words[i].length() + (i - last) > L) {
				int spaceNum = 0;
				int extraNum = 0;
				if (i - last - 1 > 0) {
					spaceNum = (L - count) / (i - last - 1);
					extraNum = (L - count) % (i - last - 1);
				}
				StringBuilder str = new StringBuilder();
				for (int j = last; j < i; j++) {
					str.append(words[j]);
					if (j < i - 1) {
						for (int k = 0; k < spaceNum; k++) {
							str.append(" ");
						}
						if (extraNum > 0) {
							str.append(" ");
						}
						extraNum--;
					}
				}
				for (int j = str.length(); j < L; j++) {
					str.append(" ");
				}
				res.add(str.toString());
				count = 0;
				last = i;
			}
			count += words[i].length();
		}
		// !!!for last line
		StringBuilder str = new StringBuilder();
		for (int i = last; i < words.length; i++) {
			str.append(words[i]);
			if (str.length() < L)
				str.append(" ");
		}
		for (int i = str.length(); i < L; i++) {
			str.append(" ");
		}
		res.add(str.toString());
		return res;
	}

	public static void main(String[] args) {
		String[] str = { "" };
		TextJustification tj = new TextJustification();
		System.out.println(tj.fullJustify(str, 0));
		String[] str1 = { "Don't", "go", "around", "saying", "the", "world",
				"owes", "you", "a", "living;", "the", "world", "owes", "you",
				"nothing;", "it", "was", "here", "first." };
		System.out.println(tj.fullJustify(str1, 30));
	}
}
