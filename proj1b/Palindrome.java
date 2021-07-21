public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        //TODO:
        Deque<Character> result = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++){
            result.addLast(word.charAt(i));
        }
        return result;
    }
}
