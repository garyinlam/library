package com.nology.library;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books;
    private List<User> users;

    public void init(){
        File bookFile = new File("files/books.json");
        if(bookFile.isFile()){
            try {
                books = LibraryUtils.jsonToBookList(bookFile);
                System.out.println(books.get(0));
                System.out.println("From JSON");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                List<String[]> list = LibraryUtils.csvToList("files/library.csv");
                books = new ArrayList<>();
                for (String[] s: list) {
                    books.add(LibraryUtils.stringArrToBook(s));
                }
                LibraryUtils.toJson(books,"files/books.json");
                System.out.println(books.get(0));
                System.out.println("From CSV");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
