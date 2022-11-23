package com.nology.library;

public class User {
    private int id;
    private String name;
    private boolean isAdmin;

    private static int idCounter = 0;

    public User() {
        this.id = idCounter++;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.isAdmin = false;
    }

    public User(int id, String name, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public static void setIdCounter(int idCounter) {
        User.idCounter = idCounter;
    }
}
