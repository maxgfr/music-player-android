package com.maxgfr.music_player.model;
import android.graphics.Bitmap;

/**
 * Created by maxime on 16/03/2017.
 */

public class Titre {

    private String nomMusique;
    private String nomArtiste;
    private Bitmap cover;
    private String nomAlbum;
    private long idTitre;

    public Titre() {}

    public Titre(long id, String songTitle, String songArtist, String songAlbum, Bitmap cover) {
        idTitre = id;
        nomMusique = songTitle;
        nomArtiste = songArtist;
        nomAlbum = songAlbum;
        this.cover = cover;
    }

    public long getId() {
        return idTitre;
    }

    public String getTitre() {
        return nomMusique;
    }

    public String getArtiste() {
        return nomArtiste;
    }

    public String getAlbum() {
        return nomAlbum;
    }

    public Bitmap getCover() {
        return cover;
    }
}

