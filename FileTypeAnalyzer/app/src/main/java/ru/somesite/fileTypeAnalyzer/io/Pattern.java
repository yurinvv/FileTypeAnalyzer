/**
 * The object representation of the pattern
 *
 * Description of fields:
 *
 * 1) A pattern priority.
 *     A higher value means higher priority.
 *     In this example, the "Zip archive" is a container for multiple files.
 *     However, Microsoft Office files are also stored as Zip archives,
 *     you can clearly see this if you rename file "file.docx" to "file.zip".
 *     If you unzip it, you'll see that it contains a bunch of folders and a
 *     bunch of XMLs. So, a Word file contains both "PK" indicating that this is
 *     a Zip archive and "word/_rels" indicating that this is a Word document.
 *     In this situation, you should choose a pattern with higher priority,
 *     which is "MS Office Word 2007+".
 *
 * 2) A document indicator.
 *     For example:
 *       %PDF- is the PDF document indicator;
 *       word/_rels is the MS Office Word 2007+ document indicator
 *
 * 3) A name of document type
 */

/**
 * Explanation of some constructs in the code.
 *
 * @Data annotation will create for this class getters and setters for each field, a readable toString() method,
 * and custom hashCode() and equals() methods
 *
 * @AllArgsConstructor annotation will create the all-arguments constructor
 *
 * The class implements the Comparable interface which is used for sorting operations
 * Comparable provides the compareTo() method which allows comparing an object with other objects of the same type.
 * The method returns:
 *   - A positive integer (for example, 1), if the current object is greater;
 *   - A negative integer (for example, -1), if the current object is less;
 *   - Zero, if they are equal.
 */

package ru.somesite.fileTypeAnalyzer.io;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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
