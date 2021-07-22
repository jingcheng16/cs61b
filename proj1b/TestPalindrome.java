import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testIsPalindrome() {
        assertTrue("'a' is palindrome!", palindrome.isPalindrome("a"));
        assertTrue("'' is palindrome!", palindrome.isPalindrome(""));
        assertFalse("'RaCecar' isnot palindrome!", palindrome.isPalindrome("RaCecar"));
        assertTrue("'noon' is palindrome!", palindrome.isPalindrome("noon"));
        assertFalse("'aaAaab' is not palindrome!", palindrome.isPalindrome("aaAaab"));
        assertFalse("'rancor' is not palindrome!", palindrome.isPalindrome("rancor"));
        assertTrue("'a' is palindrome off by one!", palindrome.isPalindrome("a", offByOne));
        assertTrue("'' is palindrome off by one!", palindrome.isPalindrome("", offByOne));
        assertFalse("'racedBs' is palindrome obo!", palindrome.isPalindrome("racedBs", offByOne));
        assertFalse("'noPM' is palindrome obo!", palindrome.isPalindrome("noPM", offByOne));
        assertFalse("'aaaaab' is not palindrome obo!", palindrome.isPalindrome("aaaaab", offByOne));
        assertTrue("'flake' is palindrome off by one!", palindrome.isPalindrome("flake", offByOne));
    }

}
