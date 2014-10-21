package basic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBasic {
	@Test
	public void runtest(){
		assertTrue(RegularExpDotStar.isMatch("aa","aa"));
		assertTrue(RegularExpDotStar.isMatch("aa", "a*"));
		assertTrue(RegularExpDotStar.isMatch("aa", ".*"));
		assertTrue(RegularExpDotStar.isMatch("ab", ".*"));
		assertTrue(RegularExpDotStar.isMatch("aab", "c*a*b"));
		
		assertFalse(RegularExpDotStar.isMatch("aa","a"));
		assertFalse(RegularExpDotStar.isMatch("aaa","aa"));
		
		assertFalse(RegularExpDotStar.isMatch("","aa"));
	}
	@Test
	public void testTwoSumRunner(){
		int [] res = TwoSum.twoSum(new int[]{2,7, 6,3}, 9);
		System.out.println("index : " + res[0] + " " + res[1]);
	}
}
