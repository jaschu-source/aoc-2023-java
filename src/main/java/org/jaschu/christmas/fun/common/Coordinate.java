package org.jaschu.christmas.fun.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinate {
    private int x;
    private int y;
    private Coordinate previousCoordinate;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Method to update position
    public void updatePosition(int x, int y) {
        previousCoordinate = new Coordinate(this.x, this.y);
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public void resetToPrevious() {
        this.x = previousCoordinate.x;
        this.y = previousCoordinate.y;
    }

    public boolean equals(Coordinate o) {
        return this.x == o.x && this.y == o.y;
    }
}
