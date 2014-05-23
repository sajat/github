package progkornybeadando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TextTest {
	Text text;
	@Before
	public void beforeTest(){
		text = new Text();
		text.setString("Ez lesz a szöveg\namit tesztelni fogunk\n minden jóra");
	}
	
	@Test
	public void test1() {
		assertEquals(3,text.getStringHeightSize());
	}
	
	@Test
	public void test2() {
		assertEquals(21,text.getStringWidthSize(text.getNextStringID(1)));
	}

}
