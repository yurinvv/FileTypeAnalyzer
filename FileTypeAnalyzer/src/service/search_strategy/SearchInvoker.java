package service.search_strategy;

import java.io.*;

public class SearchInvoker {
    private SearchMethod searchMethod;

    public void setMethod(SearchMethod searchMethod) {
        this.searchMethod = searchMethod;
    }

    public int search(String text, String pattern) {
        return searchMethod.search(text, pattern);
    }

    public int search(File file, String pattern) {

        int result = -1;

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[pattern.length()];
            byte[] tail = new byte[pattern.length()];

            while (inputStream.read(buffer) != -1) {
                String text = String.format("%s%s", new String(tail), new String(buffer));
                tail = buffer.clone();
                result = searchMethod.search(text, pattern);
                if (result > 0) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
