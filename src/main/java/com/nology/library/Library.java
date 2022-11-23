package com.nology.library;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books;
    private List<User> users;

    private User current;

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

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

    public void run(){
        boolean finished = false;
        while (!finished){
            switch (Input.command()){
                case "exit":
                    System.out.println("Would you like to exit (y/n)");
                    finished = Input.confirm();
                    break;
                case "login":
                    login();
                    break;
                case "logout":
                    logout();
                    break;
                case "create":
                    createUser();
                    break;
                case "loans":
                    createLoansReport();
                    break;
                case "books":
                    displayAvailableBooks();
                    break;
                case "report":
                    createBookReport();
                    break;
                case "help":
                    help();
                    break;
                case "loan":
                    loan();
                    break;
                case "return":
                    returnBook();
                    break;
                default:
                    Output.error("unknown command");
            }
        }
    }

    public void shutdown(){
        try {
            LibraryUtils.toJson(books,"files/books.json");
            LibraryUtils.toJson(users,"files/users.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void login(){
        System.out.println("Enter in user id:");
        int userId = Input.getInt();

    }

    private void logout(){
        if (current != null){
            current = null;
        } else {
            Output.error("not logged in, cannot logout");
        }
    }

    private void createUser(){
    }

    private void createLoansReport(){

    }

    private void displayAvailableBooks(){

    }

    private void createBookReport(){

    }

    private void help(){

    }

    private void loan(){

    }

    private void returnBook(){

    }
}
