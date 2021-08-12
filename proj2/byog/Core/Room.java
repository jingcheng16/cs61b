package byog.Core;

import java.util.HashSet;
import java.util.Set;

public class Room {
    Position startingPoint;
    int width;
    int height;
    Set<Position> perimeter = new HashSet<>();
    Set<Position> inner = new HashSet<>();
    Set<Position> entireRoom = new HashSet<>();

    /**
     * Room constructor
     * @param point the lower left corner point
     * @param wid the width of the rectangle room
     * @param hei the height of the rectangle room
     */
    public Room(Position point, int wid, int hei, Position[][] coordinate) {
        this.startingPoint = point;
        this.width = wid;
        this.height = hei;
        //define the boundary
        int lowerY = point.y;
        int upperY = lowerY + height - 1;
        int leftX = point.x;
        int rightX = point.x + wid - 1;

        //include all points on perimeter
        for (int i = leftX; i <= rightX; i++) {
            perimeter.add(coordinate[i][lowerY]);
            perimeter.add(coordinate[i][upperY]);
        }
        for (int i = lowerY + 1; i < upperY; i++) {
            perimeter.add(coordinate[leftX][i]);
            perimeter.add(coordinate[rightX][i]);
        }

        //include all points inner
        for (int i = leftX + 1; i < rightX; i++) {
            for (int j = lowerY + 1; j < upperY; j++) {
                inner.add(coordinate[i][j]);
            }
        }

        entireRoom.addAll(perimeter);
        entireRoom.addAll(inner);
    }

}
