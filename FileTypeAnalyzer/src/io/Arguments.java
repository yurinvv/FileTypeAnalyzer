package io;

import lombok.Data;

@Data
public class Arguments {
    private String filesPath;
    private String patternsPath;

    public Arguments(String[] args) {
        filesPath = args[0];
        patternsPath = args[1];
    }
}
