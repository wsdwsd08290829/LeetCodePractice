package test;

import leet.DecodeWays;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestLeet {
	@Test
	public void testDecodeWays(){
		assertThat(DecodeWays.numDecodings("12"), equalTo(2));
	}
}
