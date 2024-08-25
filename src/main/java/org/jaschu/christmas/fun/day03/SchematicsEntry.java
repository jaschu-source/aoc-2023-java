package org.jaschu.christmas.fun.day03;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchematicsEntry {
    private Character characterValue;
    private SchematicsType schematicsType;

    // only necessary if schematicsType is NUMBER
    private boolean alreadyCounted = false;
    private int numericValue = 0;
}
