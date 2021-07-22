import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne() {
        assertTrue("'a' and 'b' are different by exactly one", offByOne.equalChars('a', 'b'));
        assertTrue("'r' and 'q' are different by exactly one", offByOne.equalChars('r', 'q'));
        assertTrue("'&' and '%' are different by exactly one", offByOne.equalChars('&', '%'));
        assertFalse("'a' and 'b' are not different by exactly one", offByOne.equalChars('a', 'e'));
        assertFalse("'r' and 'q' are not different by exactly one", offByOne.equalChars('z', 'a'));
        assertFalse("'r' and 'q' are not different by exactly one", offByOne.equalChars('a', 'a'));
    }
}
