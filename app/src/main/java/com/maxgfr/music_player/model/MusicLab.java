package com.maxgfr.music_player.model;

import java.util.ArrayList;
import java.util.HashSet;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

/**
 * Created by maxime on 20/03/2017.
 */

public class MusicLab {

    private ArrayList<Titre> mTitres;
    private ArrayList<String> mArtists;
    private ArrayList<Album> mAlbums;

    private static MusicLab mTitreLab;

    private Context mAppContext;

    MusicLab(Context appContext) {
        mAppContext = appContext;

        // Query external audio
        ContentResolver musicResolver = mAppContext.getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
        boolean flag = false;

        // Add songs
        mTitres = new ArrayList<Titre>();
        mArtists = new ArrayList<String>();
        mAlbums = new ArrayList<Album>();

        if(musicCursor!=null && musicCursor.moveToFirst()){
            int titleColumn = musicCursor.getColumnIndexOrThrow
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndexOrThrow
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndexOrThrow
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            int albumColumn = musicCursor.getColumnIndexOrThrow
                    (android.provider.MediaStore.Audio.Media.ALBUM);
            int albumNb = musicCursor.getColumnCount();

            do {
                flag = false;
                long thisId = musicCursor.getLong(idColumn);
                String thisAlbum = musicCursor.getString(albumColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);

                mTitres.add(new Titre(thisId, thisTitle, thisArtist, thisAlbum, null));
                mArtists.add(thisArtist);

                // Check for Album duplicate
                if(mAlbums.size() > 0) {
                    for(Album a: mAlbums) {
                        if(a.getTitle().equals(thisAlbum) &&
                                a.getFromArtist().equals(thisArtist))
                            flag = true;
                    }
                }
                else
                    mAlbums.add(new Album(thisAlbum, thisArtist));

                if(flag == false)
                    mAlbums.add(new Album(thisAlbum, thisArtist));

            } while (musicCursor.moveToNext());

            if(mAlbums.size() > 0)
                mAlbums.remove(0);
        }
    }

    public static MusicLab get(Context c) {
        if (mTitreLab == null) {
            mTitreLab = new MusicLab(c.getApplicationContext());
        }

        return mTitreLab;
    }

    public Titre getTitre(long id) {
        for (Titre s : mTitres) {
            if (s.getId() == id)
                return s;
        }

        return null;
    }

    public ArrayList<Titre> getTitresFromArtist(String artist) {
        ArrayList<Titre> list = new ArrayList<Titre>();
        for(Titre s: mTitres) {
            if(s.getArtiste().equals(artist))
                list.add(s);
        }

        return list;
    }

    public void addTitre(Titre c) {
        mTitres.add(c);
    }

    public ArrayList<Titre> getTitres() {
        return mTitres;
    }

    public ArrayList<?> removeDuplicates(ArrayList<?> list) {
        HashSet hs = new HashSet();
        hs.addAll(list);
        list.clear();
        list.addAll(hs);

        return list;
    }

    public ArrayList<String> getArtists() {
        mArtists = (ArrayList<String>) removeDuplicates(mArtists);
        return mArtists;
    }

    public int getAlbumCount(String artist) {
        int count = 0;
        for(Album a: mAlbums) {
            if(a.getFromArtist().equals(artist))
                count++;
        }

        return count;
    }

    public ArrayList<Album> getAlbums() {
        return mAlbums;
    }

    public ArrayList<Album> getAlbumsFromArtist(String artist) {
        ArrayList<Album> list = new ArrayList<Album>();
        for(int i = 0; i < mAlbums.size(); i++) {
            if(mAlbums.get(i).getFromArtist().equals(artist))
                list.add(mAlbums.get(i));
        }

        return list;
    }

    public ArrayList<Titre> getAlbumTitres(String album) {
        ArrayList<Titre> list = new ArrayList<Titre>();
        for(int i = 0; i < mTitres.size(); i++) {
            if(mTitres.get(i).getAlbum().equals(album))
                list.add(mTitres.get(i));
        }

        return list;
    }

    public int getAlbumTitreCount(String album) {
        int count = 0;
        for(Titre s: mTitres) {
            if(s.getAlbum().equals(album))
                count++;
        }
        return count;
    }
}

