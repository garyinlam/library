package com.nology.library;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.init();
        library.run();
        library.shutdown();
    }
}
