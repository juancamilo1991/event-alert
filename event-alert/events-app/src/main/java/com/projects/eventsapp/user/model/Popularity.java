package com.projects.eventsapp.user.model;

public enum Popularity {

    HIGH(100),
    MODERATE(25),
    LOW(10);

    private int levelOfPop;

    Popularity(int level) {
        this.levelOfPop = level;
    }

    public int getLevelOfPop() {
        return levelOfPop;
    }
}
