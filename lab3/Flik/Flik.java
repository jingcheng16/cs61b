/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        //return a.equals(b);
        return a == b;
    }

    public static void main(String[] args) {
        if(isSameNumber(128,128)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

}
