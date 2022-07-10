package com.company;
enum taste{
    sweet,
    salty,
    bitter,
    sour,
    neutral
}

public class Ingredient {
    private String name;
    private taste taste;
    public Ingredient(String name,taste taste){
        this.name = name;
        this.taste = taste;
    }

    public String getName() {
        return name;
    }

    public com.company.taste getTaste() {
        return taste;
    }
}
