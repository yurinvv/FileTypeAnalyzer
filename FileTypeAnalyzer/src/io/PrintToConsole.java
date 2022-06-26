package io;

public class PrintToConsole implements IPrinter {
    @Override
    public void print(String text) {
        System.out.println(text);
    }
}
