package org.jaschu.christmas.fun.day10;

import lombok.Getter;
import org.jaschu.christmas.fun.common.Coordinate;

@Getter
public class CoordinateWithSteps extends Coordinate {
    private int steps;

    public CoordinateWithSteps(int x, int y) {
        super(x, y);
        steps = 0;
    }

    @Override
    public void updatePosition(int x, int y) {
        super.updatePosition(x, y);
        steps++;
    }

    @Override
    public void resetToPrevious() {
        super.resetToPrevious();
        steps--;
    }


}

