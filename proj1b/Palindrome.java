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


        /*
        if (word.equals("")) {
            return true;
        } else if (!word.equalsIgnoreCase(word)) {
            return false;
        } else {
            return isPalindrome(word, 0, word.length() - 1);
        }*/
    }

    private boolean isPalindrome(Deque d) {
        if (d.size() < 2) {
            return true;
        } else if (d.removeFirst() == d.removeLast()) {
            return isPalindrome(d);
        } else {
            return false;
        }

        /*
        if (word.charAt(start) == word.charAt(end)) {
            if (start == end || start + 1 == end) {
                return true;
            } else {
                return isPalindrome(word, start + 1, end - 1);
            }
        } else {
            return false;
        }*/
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        return isPalindrome(d, cc);
        /*
        if (word.equals("")) {
            return true;
        } else if (!word.equalsIgnoreCase(word)) {
            return false;
        } else {
            return isPalindrome(word, cc, 0, word.length() - 1);
        }*/
    }

    private boolean isPalindrome(Deque d, CharacterComparator cc) {
        if (d.size() < 2) {
            return true;
        } else if (cc.equalChars((char) d.removeFirst(), (char) d.removeLast())) {
            return isPalindrome(d, cc);
        } else {
            return false;
        }

        /*
        if (cc.equalChars(word.charAt(start), word.charAt(end))) {
            if (start + 1 == end) {
                return true;
            } else {
                return isPalindrome(word, cc, start + 1, end - 1);
            }
        } else {
            return (start == end);
        }*/
    }
}
