package com.company;

import java.util.ArrayList;

public class Recipe {
    private String Name;
    private ArrayList<Ingredient> Ingredients;
    private String opinion;
    int sweetNum = 0;
    int saltyNum = 0;
    int bitterNum = 0;
    int sourNum = 0;
    int neutralNum = 0;
    private String ingredientString;
    public Recipe(String Name, ArrayList<Ingredient> Ingredients) {
        this.Name = Name;
        this.Ingredients = Ingredients;
        this.ingredientString =getIngredients();
    }


    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getIngredientString(){
        return ingredientString;
    }


    public String getIngredients() {
        String result = "";
        int x = 0;
        for(Ingredient i : Ingredients){
            if(x == 0) {
                result += i.getName() + ", ";
                x++;
            }
            if(x == 1) {
                result += i.getName();
                x++;
            }
//            if(x == 3){
//                result +=  ", \n" + i.getName();
//                x++;
//            }
//            if(x == 6){
//                result +=  ", \n" + i.getName();
//                x++;
//            }
            else{
                result +=  ", " + i.getName();
                x++;
            }
        }
        return result;
    }
    public void setIngredients(ArrayList<Ingredient> newIngredients) {
        if(newIngredients.size() >=2) {
            this.Ingredients = newIngredients;
        }
    }
    public void addIngredient(Ingredient ingredient){
        this.Ingredients.add(ingredient);
    }

    public void setOpinion(){
        sweetNum = 0;
        saltyNum = 0;
        bitterNum = 0;
        sourNum = 0;
        neutralNum = 0;
        for(int i = 0; i < Ingredients.size();i++){
            if(Ingredients.get(i).getTaste()==taste.sweet){
                sweetNum++;
            }
            if(Ingredients.get(i).getTaste()==taste.salty){
                saltyNum++;
            }
            if(Ingredients.get(i).getTaste()==taste.bitter){
                bitterNum++;
            }
            if(Ingredients.get(i).getTaste()==taste.sour){
                sourNum++;
            }
            if(Ingredients.get(i).getTaste()==taste.neutral){
                neutralNum++;
            }
        }

        if(sweetNum!=0 && saltyNum!=0 && bitterNum!=0 && sourNum!=0 && neutralNum!=0){
            opinion = "This dish is so bad that it is hard to even look at it!";
        }
        else {
            int[] array = new int[5];
            array[0] = sweetNum;
            array[1] = saltyNum;
            array[2] = bitterNum;
            array[3] = sourNum;
            array[4] = neutralNum;
            int max = 0;
            int maxVal = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] > maxVal) {
                    maxVal = array[i];
                    max = i;
                }
            }
            if(max == 0){
                opinion= "This dish is so sweet that it is suitable to be a dessert!";
            }
            if(max == 1){
                opinion =  "This dish is quite salty!";
            }
            if(max == 2){
                opinion =  "This dish has a sharp and pungent taste - it is bitter!";
            }
            if(max == 3){
                opinion =  "This dish is sour - just like a lemon!";
            }
            if(max == 4){
                opinion =  "The taste of this dish is neutral.";
            }
        }
    }
    public String getOpinion(){
        return opinion;
    }
}
