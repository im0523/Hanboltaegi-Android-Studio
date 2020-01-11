package com.example.han2.Dto;

public class WeekChefDTO {
     String id, nickname, good, recipe;

    public WeekChefDTO(String id, String nickname, String good, String recipe) {
        this.id = id;
        this.nickname = nickname;
        this.good = good;
        this.recipe = recipe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}
