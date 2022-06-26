package io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pattern implements Comparable<Pattern> {
    private String priority;
    private String indicator;
    private String docType;

    @Override
    public int compareTo(Pattern o) {
        int thisPriority = Integer.parseInt(this.getPriority());
        int anotherPriority = Integer.parseInt(o.getPriority());
        return Integer.compare(thisPriority, anotherPriority);
    }
}
