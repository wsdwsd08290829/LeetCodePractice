package leet;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//Only one letter can be changed at a time
//dict = ["hot","dot","dog","lot","log"];
//"hit" -> "hot" -> "dot" -> "dog" -> "cog"
public class WordLadder {
	public int ladderLength(String start, String end, Set<String> dict) {
		if (start == null || end == null || start.length() == 0
				|| end.length() == 0 || start.length() != end.length())
			return 0;
		Set<String> visited = new HashSet<String>();
		Queue<String> q = new LinkedList<String>();
		q.add(start);
		visited.add(start);
		int level = 1;
		int levelEnd = 1;
		int levelCount = 0;
		while (!q.isEmpty()) {
			String cur = q.remove();
			levelEnd--; // cur is last node of current level
			// add neighbor to q, mark visited, levelcount++,
			for (int i = 0; i < cur.length(); i++) {
				char[] ch = cur.toCharArray();
				for (int j = 'a'; j <= 'z'; j++) {
					ch[i] = (char) j;
					String temp = new String(ch);
					// if found return level+1
					if (temp.equals(end)) {
						return level + 1;
					}
					if (!visited.contains(temp) && dict.contains(temp)) {
						q.add(temp);
						visited.add(temp);
						levelCount++;
					}
				}
			}
			// deal with level related vars
			if (levelEnd == 0) {
				levelEnd = levelCount; // update new level count for next level
				levelCount = 0;
				level++; // next level to be visited
			}
		}
		return 0;
	}

	public static void main(String[] args) {

	}
}

// TODO word ladderII find all shortest ladders
// http://blog.csdn.net/linhuanmars/article/details/23071455