package com.maxgfr.music_player.model;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;

/**
 * Created by maxime on 16/03/2017.
 */

public class Titre extends MediaPlayer{

    private String nomMusique;
    private String nomArtiste;
    private String nomAlbum;
    private long idTitre;
    private Uri uri;

    public Titre() {super();}

    public Titre(long id, String songTitle, String songArtist, String songAlbum, Uri uri) {
        super();
        idTitre = id;
        nomMusique = songTitle;
        nomArtiste = songArtist;
        nomAlbum = songAlbum;
        this.uri = uri;
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

    public Uri getUri() {
        return uri;
    }

    public String getAlbum() {
        return nomAlbum;
    }

    public boolean equals(Titre t) {
        if(nomMusique.equals(t.getTitre()) && nomArtiste.equals(t.getArtiste()))
            return true;
        else return false;
    }
}

