package com.nology.library;

public class Book {

    private int id;
    private String title;
    private String author;
    private String genre;
    private String subGenre;
    private String publisher;
    private int timesLoaned;
    private boolean isLoaned;
    private User currentLoaner;

    public Book() {
    }

    public Book(int id, String title, String author, String genre, String subGenre, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.subGenre = subGenre;
        this.publisher = publisher;
        this.timesLoaned = 0;
        this.isLoaned = false;
        this.currentLoaner = null;
    }

    public Book(int id, String title, String author, String genre, String subGenre, String publisher, int timesLoaned, boolean isLoaned, User currentLoaner) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.subGenre = subGenre;
        this.publisher = publisher;
        this.timesLoaned = timesLoaned;
        this.isLoaned = isLoaned;
        this.currentLoaner = currentLoaner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getTimesLoaned() {
        return timesLoaned;
    }

    public void setTimesLoaned(int timesLoaned) {
        this.timesLoaned = timesLoaned;
    }

    public boolean isLoaned() {
        return isLoaned;
    }

    public void setLoaned(boolean loaned) {
        isLoaned = loaned;
    }

    public User getCurrentLoaner() {
        return currentLoaner;
    }

    public void setCurrentLoaner(User currentLoaner) {
        this.currentLoaner = currentLoaner;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title=" + title +
                ", author=" + author +
                ", genre=" + genre +
                ", subGenre=" + subGenre +
                ", publisher=" + publisher +
                ", timesLoaned=" + timesLoaned +
                ", isLoaned=" + isLoaned +
                ", currentLoaner=" + currentLoaner +
                '}';
    }
}
