public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        while (x < 10) {
            int result = 0;
            for(int i = 0; i <= x; i++){
                result += i;
            }
            System.out.println(result);
            x = x + 1;
        }
    }
}