package com.meal;

public class Meal {

    private int    id;
    private String name;
    private String type;
    private String category;
    private String emoji;

    // Constructor WITH id
    public Meal(int id, String name, String type, String category, String emoji) {
        this.id       = id;
        this.name     = name;
        this.type     = type;
        this.category = category;
        this.emoji    = emoji;
    }

    // Empty constructor (needed for POST to work)
    public Meal() {}

    // Getters
    public int    getId()       { return id; }
    public String getName()     { return name; }
    public String getType()     { return type; }
    public String getCategory() { return category; }
    public String getEmoji()    { return emoji; }

    // Setters
    public void setId(int id)           { this.id = id; }
    public void setName(String name)    { this.name = name; }
    public void setType(String type)    { this.type = type; }
    public void setCategory(String cat) { this.category = cat; }
    public void setEmoji(String emoji)  { this.emoji = emoji; }
}