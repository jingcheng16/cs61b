public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        //TODO:
        Deque<Character> result = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++){
            result.addLast(word.charAt(i));
        }
        return result;
    }

    public boolean isPalindrome(String word) {
        if (word == "") {
            return true;
        }
        return isPalindrome(word, 0, word.length() - 1);
    }

    public boolean isPalindrome(String word, int start, int end) {
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
}
