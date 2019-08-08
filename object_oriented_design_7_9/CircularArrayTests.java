package object_oriented_design_7_9;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CircularArrayTests {
	
	private static final int CIRC_ARRAY_INITIAL_SIZE = 4;
	
	private CircularArray<Character> myCircArray;
	
	@Before
	public void setup() {
		myCircArray = new CircularArray<Character>(CIRC_ARRAY_INITIAL_SIZE);
		myCircArray.add(0, 'A');
		myCircArray.add(1, 'B');
		myCircArray.add(2, 'C');
	}

	@Test
	public void get_GetBackProperResult_True() {
		assertSame('A', myCircArray.get(0));
	}
	
	@Test
	public void get_GetBackProperResultAfterARotation_True() {
		myCircArray.rotate(2);
		
		assertSame('A', myCircArray.get(2));
	}
	
	@Test
	public void get_GetBackProperResultAfterARotation_False() {
		myCircArray.rotate(2);
		
		assertNotSame('A', myCircArray.get(0));
	}
	
	@Test
	public void get_SeeingWhatExistsAtAPlaceUndefinedYet_Null() {
		assertNull(myCircArray.get(3));
	}
}