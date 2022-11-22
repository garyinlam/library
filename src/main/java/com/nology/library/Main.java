package com.nology.library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            List<String[]> list = LibraryUtils.csvToList("files/library.csv");
            List<Book> b = new ArrayList<>();
            for (String[] s: list) {
                b.add(LibraryUtils.stringArrToBook(s));
            }
            LibraryUtils.toJson(b,"files/books.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
