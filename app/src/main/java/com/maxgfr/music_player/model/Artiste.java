package com.maxgfr.music_player.model;

/**
 * Created by maxime on 16/03/2017.
 */

public class Artiste {

    private String nom;
    private long id;

    public Artiste() {
    }

    public Artiste(String title, long id) {
        nom = title;
        this.id = id;
    }

    public String getTitle() {
        return nom;
    }

    public long getId() {
        return id;
    }

    public void setTitle(String title) {
        nom = title;
    }


    public boolean equals(Artiste a) {
        if(nom.equals(a.getTitle()))
            return true;
        else return false;
    }
}
