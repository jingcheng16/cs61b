public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    public boolean isPalindrome(String word) {
        if (word.equals("")) {
            return true;
        }
        return isPalindrome(word, 0, word.length() - 1);
    }

    private boolean isPalindrome(String word, int start, int end) {
        word = word.toLowerCase();
        if (word.charAt(start) == word.charAt(end)) {
            if (start == end || start + 1 == end) {
                return true;
            } else {
                return isPalindrome(word, start + 1, end - 1);
            }
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.equals("")) {
            return true;
        }
        return isPalindrome(word, cc, 0, word.length() - 1);
    }

    private boolean isPalindrome(String word, CharacterComparator cc, int start, int end) {
        word = word.toLowerCase();
        if (cc.equalChars(word.charAt(start), word.charAt(end))) {
            if (start + 1 == end) {
                return true;
            } else {
                return isPalindrome(word, cc, start + 1, end - 1);
            }
        } else {
            return (start == end);
        }
    }
}
