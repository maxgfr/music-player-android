package com.maxgfr.music_player.service;

/**
 * Created by maxime on 16/03/2017.
 */


import android.app.Service;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import com.maxgfr.music_player.model.Titre;

import java.io.IOException;
import java.util.ArrayList;

import static android.R.attr.id;

public class MusicService extends Service {

    private final IBinder mBinder = new LocalBinder();

    private ArrayList<Titre> songs;

    public class LocalBinder extends Binder {
        public MusicService getService(){
            //Retourne le this du local service pour que les clients puissent appeler ses méthodes publiques
            return MusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent){
        return mBinder;
    }

    //pass song list
    public void setList(ArrayList<Titre> theSongs){
        songs=theSongs;
    }

    public void playMedia(MediaPlayer mediaPlayer) {
        /*Uri contentUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(getApplicationContext(), contentUri);
        }
        catch (IOException e){
            e.getMessage();
        }*/
        mediaPlayer.start();
    }
}
