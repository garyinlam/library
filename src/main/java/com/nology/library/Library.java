package com.nology.library;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<Book> books;
    private List<User> users = new ArrayList<>();

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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        File userFile = new File("files/users.json");
        if(userFile.isFile()){
            try {
                users = LibraryUtils.jsonToUserList(userFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void run(){
        boolean finished = false;
        while (!finished){
            System.out.println("Enter in a command (type 'help' for commands)");
            switch (Input.command()){
                case "quit":
                    System.out.println("Would you like to quit (y/n)");
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
        System.out.println("Goodbye");
    }

    private void login(){
        System.out.println("Enter in user id:");
        int userId = Input.getInt();
        if (users
                .stream()
                .anyMatch(user -> user.getId() == userId)){
            current = users
                    .stream()
                    .filter(user -> user.getId() == userId)
                    .findFirst()
                    .get();
            String success = "Login successful as " + current.getName();
            if (current.isAdmin()){
                success += " (admin)";
            }
            System.out.println(success);
        } else {
            Output.error("id not found. Could not login");
        }
    }

    private void logout(){
        if (current != null){
            current = null;
            System.out.println("Logged out");
        } else {
            Output.error("not logged in, cannot logout");
        }
    }

    private void createUser(){
        int id = users.size();
        System.out.println("Enter user's name:");
        String name = Input.getInput();
        System.out.println("Is this user an admin? (y/n)");
        boolean isAdmin = Input.confirm();
        User newUser = new User(users.size(),name,isAdmin);
        System.out.println("Created new user. ID = " + newUser.getId());
        users.add(newUser);
    }

    private void createLoansReport(){
        if (current != null){
            if(current.isAdmin()) {
                try {
                    LibraryUtils.bookListCSV(books
                            .stream()
                            .filter(Book::isLoaned)
                            .collect(Collectors.toList()), "files/loanedReport.csv");
                    System.out.println("Success - created new report at files/loanedReport.csv");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Output.error("only admins can run reports");
            }
        } else {
            Output.error("not logged in, cannot run report");
        }
    }

    private void displayAvailableBooks(){
        books
                .stream()
                .filter(book -> !book.isLoaned())
                .forEach(System.out::println);
    }

    private void createBookReport(){
        if (current != null) {
            if (current.isAdmin()) {
                try {
                    LibraryUtils.bookListCSV(books, "files/booksReport.csv");
                    System.out.println("Success - created new report at files/booksReport.csv");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Output.error("only admins can run reports");
            }
        }else {
            Output.error("not logged in, cannot run report");
        }
    }

    private void help(){
        System.out.println("Available commands:");
        System.out.println("books - show currently available books");
        System.out.println("create - create a new user");
        System.out.println("help - show commands");
        System.out.println("loan - loan a book (must be logged in)");
        System.out.println("loans - create report for all books currently on loan (must be admin)");
        System.out.println("login - login to account using user id");
        System.out.println("logout - logout of account");
        System.out.println("quit - quit application");
        System.out.println("report - create report for all books (must be admin)");
        System.out.println("return - return a book using the book id");

    }

    private void loan(){
        if (current != null) {
            System.out.println("Enter in book id:");
            int bookId = Input.getInt();
            if (books
                    .stream()
                    .anyMatch(book -> book.getId() == bookId)){
                Book toLoan = books
                        .stream()
                        .filter(book -> book.getId()==bookId)
                        .findFirst()
                        .get();
                if (!toLoan.isLoaned()){
                    toLoan.loan(current);
                    System.out.println("Book is now loaned to " + current.getName() + " ID = " + current.getId());
                } else {
                    Output.error("book is already on loan, cannot loan");
                }
            } else {
                Output.error("book not found");
            }
        } else {
            Output.error("not logged in, cannot loan book");
        }
    }

    private void returnBook(){
        System.out.println("Enter in book id:");
        int bookId = Input.getInt();
        if (books
                .stream()
                .anyMatch(book -> book.getId() == bookId)) {
            Book toReturn = books
                    .stream()
                    .filter(book -> book.getId()==bookId)
                    .findFirst()
                    .get();
            if (toReturn.isLoaned()){
                toReturn.returnBook();
                System.out.println(toReturn.getTitle() + " has been returned");
            } else {
                Output.error("book is not on loan, cannot return");
            }
        } else {
            Output.error("book not found");
        }
    }
}
