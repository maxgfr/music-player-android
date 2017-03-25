package com.maxgfr.music_player.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by maxime on 20/03/2017.
 */

public class MusicLab {

    private ArrayList<Titre> mTitres;
    private ArrayList<String> mArtists;
    private ArrayList<Album> mAlbums;
    private Context mAppContext;

    public MusicLab(Context appContext) {
        mAppContext = appContext;
        search();
    }

    public void search () {
        // Query external audio
        ContentResolver musicResolver = mAppContext.getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        // Add songs
        mTitres = new ArrayList<Titre>();
        mArtists = new ArrayList<String>();
        mAlbums = new ArrayList<Album>();

        if (musicCursor == null) {
            // query failed, handle error.
        } else if (!musicCursor.moveToFirst()) {
            // no media on the device
        } else {
            int titleColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int albumColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            int albumIdColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);

            do {
                long thisId = musicCursor.getLong(idColumn);
                long thisIdAlbum = musicCursor.getLong(albumIdColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisAlbum = musicCursor.getString(albumColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                mTitres.add(new Titre(thisId, thisTitle, thisArtist, thisAlbum, null));
                mArtists.add(thisArtist);
                mAlbums.add(new Album(thisIdAlbum,thisAlbum,thisArtist));
            } while (musicCursor.moveToNext());

            mTitres = removeDuplicatesTitre(mTitres);
            mAlbums = removeDuplicatesAlbum(mAlbums);

        }
    }

    private ArrayList<Album> removeDuplicatesAlbum(ArrayList<Album> mAlbums) {
        ArrayList<Album> result = new ArrayList<Album>();
        Set<String> titles = new HashSet<String>();

        for( Album item : mAlbums ) {
            if(titles.add(item.getTitle())) {
                result.add( item );
            }
        }
        return  result;
    }

    private ArrayList<Titre> removeDuplicatesTitre(ArrayList<Titre> mTitres) {
        ArrayList<Titre> result = new ArrayList<Titre>();
        Set<Integer> titles = new HashSet<Integer>();

        for( Titre item : mTitres ) {
            if(titles.add((int) item.getId())) {
                result.add( item );
            }
        }
        return  result;

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

    public ArrayList<Titre> getTitresFromAlbum(String album) {
        ArrayList<Titre> list = new ArrayList<Titre>();
        for(Titre s: mTitres) {
            if(s.getAlbum().equals(album))
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

