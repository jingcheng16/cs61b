/** Performs some basic array test. */
public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out return checks. */
    public static boolean checkReturn(Integer expected, int actual) {
        if (expected != actual) {
            System.out.println("returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }


    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, ad1.size()) && passed;
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.addLast("middle");
        passed = checkSize(2, ad1.size()) && passed;

        ad1.addLast("back");
        passed = checkSize(3, ad1.size()) && passed;

        System.out.println("Printing out deque: ");
        ad1.printDeque();

        printTestStatus(passed);

    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.removeFirst();
        // should be empty
        passed = checkEmpty(true, ad1.isEmpty()) && passed;

        printTestStatus(passed);

    }

    public static void gradescopeTest003() {
        System.out.println("Running Gradescope Test 003. ");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        // should be empty
        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst(0);
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addFirst(4);
        ad1.addFirst(5);
        ad1.addFirst(6);
        ad1.addFirst(7);
        ad1.addFirst(8);
        ad1.addFirst(9);

        // should not be empty
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.removeFirst();
        int first = ad1.removeFirst();
        // should not be empty
        passed = checkEmpty(false, ad1.isEmpty()) && passed;
        passed = checkReturn(8, first) && passed;

        printTestStatus(passed);

    }

    public static void gradescopeTest004() {
        System.out.println("Running Gradescope Test 004. ");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        // should be empty
        boolean passed = checkEmpty(true, ad1.isEmpty());
        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addLast(2);

        int last = ad1.removeLast();
        passed = checkReturn(2, last) && passed;

        ad1.addLast(4);
        ad1.addLast(5);

        passed = checkSize(4, ad1.size()) && passed;

        printTestStatus(passed);
    }

    public static void gradescopeTest005() {
        System.out.println("Running Gradescope Test 005. ");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        // should be empty
        boolean passed = checkEmpty(true, ad1.isEmpty());
        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addLast(2);
        passed = checkEmpty(false, ad1.isEmpty());
        ad1.addLast(4);
        passed = checkEmpty(false, ad1.isEmpty());
        printTestStatus(passed);
    }

    public static void gradescopeTest011() {
        System.out.println("Running Gradescope Test 011. ");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        // should be empty
        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addLast(0);
        ad1.addLast(1);
        ad1.removeFirst();
        //==> 0
        ad1.get(0);
        //==> 1
        ad1.addFirst(4);
        ad1.addFirst(5);
        ad1.removeLast();
        //==> 1
        ad1.addFirst(7);
        ad1.addFirst(8);
        ad1.addLast(9);
        ad1.removeLast();
        //==> 9
        ad1.removeLast();
        //==> 4
        ad1.addFirst(12);
        ad1.removeLast();
        //==> 5
        ad1.removeLast();
        //==> 7
        ad1.addFirst(15);
        ad1.get(1);
        //==> 12
        ad1.addFirst(17);
        int returnValue = ad1.get(2);
        //==> 12
        passed = checkReturn(12, returnValue) && passed;

        printTestStatus(passed);
    }

    public static void gradescopeTest012() {
        System.out.println("Running Gradescope Test 012. ");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        // should be empty
        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.removeFirst();
        passed = checkEmpty(true, ad1.isEmpty()) && passed;
        passed = checkSize(0, ad1.size()) && passed;


        printTestStatus(passed);
    }

    private static void gradescopeTest008() {
        System.out.println("Running Gradescope Test 008. ");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        // should be empty
        boolean passed = checkEmpty(true, ad1.isEmpty());

        for (int i = 0; i < 100; i++) {
            ad1.addFirst(0);
            ad1.addFirst(1);
            ad1.addFirst(2);
            ad1.addFirst(3);
            ad1.addFirst(4);
            ad1.addFirst(5);
            ad1.addFirst(6);
            ad1.addFirst(7);
            ad1.addFirst(8);
            ad1.addFirst(9);
            ad1.addFirst(10);
            ad1.addFirst(11);
            ad1.addFirst(12);
            ad1.addFirst(13);
            ad1.addFirst(14);
            ad1.addFirst(15);
            ad1.addFirst(16);
            ad1.removeFirst();
            ad1.removeFirst();
            ad1.removeFirst();
            ad1.removeFirst();
            ad1.removeFirst();
            ad1.removeFirst();
            ad1.removeFirst();
            ad1.removeFirst();
            ad1.removeFirst();
            ad1.removeFirst();
            ad1.removeFirst();
            ad1.removeFirst();
            ad1.removeFirst();
            ad1.removeFirst();
            ad1.removeFirst();
            ad1.removeFirst();
            int returnValue = ad1.removeFirst();
            passed = checkReturn(0, returnValue) && passed;
            passed = checkEmpty(true, ad1.isEmpty()) && passed;
        }

        printTestStatus(passed);
    }


    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        gradescopeTest003();
        gradescopeTest004();
        gradescopeTest005();
        gradescopeTest011();
        gradescopeTest012();
        gradescopeTest008();
    }
}
