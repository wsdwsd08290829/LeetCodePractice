package leet;

import java.util.LinkedList;
import java.util.Stack;

public class SimplifyPath {
	// maintain stack for each section of path, pop when ../,
	// continue when ./
	// !!always check i < path length; !! consider ..abcd as dir name
	public String simplifyPath(String path) {
		if (path == null || path.length() == 0) {
			return "";
		}
		Stack<String> stack = new Stack<String>();
		int i = 0;
		StringBuilder sect = new StringBuilder();
		int dotCount = 0;
		while (i < path.length()) {
			while (i < path.length() && path.charAt(i) == '/') {
				if (sect.length() > 0) {
					stack.push(sect.toString());
					sect = new StringBuilder();
				}
				i++;
			}// now i point to first sect

			// count dot
			while (i < path.length() && path.charAt(i) == '.') {
				dotCount++;
				i++;
			}

			// condition for go to upper dir
			if (dotCount > 0
					&& ((i < path.length() && path.charAt(i) == '/') || i == path
							.length())) {
				if (dotCount == 2 && !stack.isEmpty()) {
					stack.pop();
					dotCount = 0;
				}
			}
			// as directory
			if (dotCount > 0 && (i < path.length() && path.charAt(i) != '/')
					|| dotCount > 2) {
				while (dotCount > 0) {
					sect.append('.');
					dotCount--;
				}
			}
			dotCount = 0;
			// for other char add to sect as name of dir
			if (i < path.length() && path.charAt(i) != '/'
					&& path.charAt(i) != '.') {
				sect.append(path.charAt(i));
			}

			i++;
		}
		if (sect.length() > 0) {
			stack.push(sect.toString());
			sect = new StringBuilder();
		}
		while (!stack.empty()) {
			sect.insert(0, stack.pop());
			sect.insert(0, "/");
		}
		// only "/"
		if (sect.length() == 0) {
			sect.append("/");
		}
		return sect.toString();
	}

	/******* method2 *******/
	public String simplifyPath1(String path) {
		if (path == null || path.length() == 0) {
			return "";
		}
		LinkedList<String> stack = new LinkedList<String>();
		StringBuilder res = new StringBuilder();
		int i = 0;

		while (i < path.length()) {
			int index = i;
			StringBuilder temp = new StringBuilder();
			// get string between tow "/"
			while (i < path.length() && path.charAt(i) != '/') {
				temp.append(path.charAt(i));
				i++;
			}
			// if has string
			if (index != i) {
				String str = temp.toString();
				if (str.equals("..")) {
					if (!stack.isEmpty())
						stack.pop();
				} else if (!str.equals(".")) {
					stack.push(str);
				}
			} // other wise is '/', keep looping
			i++;
		}
		// save result use toArray
		if (!stack.isEmpty()) {
			String[] strs = stack.toArray(new String[stack.size()]);
			for (int j = strs.length - 1; j >= 0; j--) {
				res.append("/" + strs[j]);
			}
		}
		if (res.length() == 0)
			return "/";
		return res.toString();
	}
}
