/**
 * This class is needed to parse the arguments passed to the program.
 *
 * The program takes two arguments: the path to the files to analyze and
 * the path to the templates by which the program will determine file types
 */

/**
 * Explanation of some constructs in the code.
 *
 * @Data annotation will create for this class getters and setters for each field, a readable toString() method,
 * and custom hashCode() and equals() methods
 *
 */

package ru.somesite.fileTypeAnalyzer.io;

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
