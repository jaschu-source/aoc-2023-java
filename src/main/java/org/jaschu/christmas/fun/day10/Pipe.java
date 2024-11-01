package org.jaschu.christmas.fun.day10;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pipe {
    private String value;
    private boolean openToNorth;
    private boolean openToEast;
    private boolean openToSouth;
    private boolean openToWest;
}
