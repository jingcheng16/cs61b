import static org.junit.Assert.*;
import org.junit.Test;

import java.sql.Array;

public class TestArrayDequeGold {
    @Test
    // @source: StudentArrayDequeLauncher
    public void testArrayDeque() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        String message = "";

        for (int i = 0; i < 20; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {
                sad1.addLast(i);
                ads1.addLast(i);
                message += "addLast(" + i + ")\n";
                assertEquals(message, ads1.size(), sad1.size());
            } else if (numberBetweenZeroAndOne < 0.5) {
                sad1.addFirst(i);
                ads1.addFirst(i);
                message += "addFirst(" + i + ")\n";
                assertEquals(message, ads1.size(), sad1.size());
            } else if (numberBetweenZeroAndOne < 0.75) {
                if (sad1.size() == 0) {
                    continue;
                } else {
                    Integer expected = ads1.removeFirst();
                    Integer actual = sad1.removeFirst();
                    message += "removeFirst()\n";
                    assertEquals(message, expected, actual);
                }
            } else {
                if (sad1.size() == 0) {
                    continue;
                } else {
                    Integer expected = ads1.removeLast();
                    Integer actual = sad1.removeLast();
                    message += "removeLast()\n";
                    assertEquals(message, expected, actual);
                }
            }
        }
    }
}
