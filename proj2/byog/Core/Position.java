package byog.Core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.abs;

public class Position {
    int x;
    int y;
    //status is 0 if the position is unoccupied, 1 vice versa.
    int status;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = 0;
    }

    public ArrayList<Position> availableHeight(Position[] line) {
        ArrayList<Position> result = new ArrayList<Position>();
        for (Position p2: line) {
            if (p2.status == 0) {
                result.add(p2);
            }
        }
        return result;
    }




}
