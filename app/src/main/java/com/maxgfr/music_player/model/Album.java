package com.maxgfr.music_player.model;

/**
 * Created by maxime on 16/03/2017.
 */

public class Album {

    private String nom;
    private String artiste;

    public Album() {
    }

    public Album(String title, String artist) {
        nom = title;
        artiste = artist;
    }

    public String getTitle() {
        return nom;
    }

    public void setTitle(String title) {
        nom = title;
    }

    public String getFromArtist() {
        return artiste;
    }

    public void setFromArtist(String artiste) {
        this.artiste = artiste;
    }

    public boolean equals(Album album) {
        if(nom.equals(album.getTitle()) && artiste.equals(album.getFromArtist()))
            return true;
        else return false;
    }
}
