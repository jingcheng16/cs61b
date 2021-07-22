public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        return isPalindrome(d);
    }

    private boolean isPalindrome(Deque d) {
        if (d.size() < 2) {
            return true;
        } else if (d.removeFirst() == d.removeLast()) {
            return isPalindrome(d);
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        return isPalindrome(d, cc);
    }

    private boolean isPalindrome(Deque d, CharacterComparator cc) {
        if (d.size() < 2) {
            return true;
        } else if (cc.equalChars((char) d.removeFirst(), (char) d.removeLast())) {
            return isPalindrome(d, cc);
        } else {
            return false;
        }
    }
}
