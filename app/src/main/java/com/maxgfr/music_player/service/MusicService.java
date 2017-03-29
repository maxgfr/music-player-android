package com.maxgfr.music_player.service;

/**
 * Created by maxime on 16/03/2017.
 */


import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.maxgfr.music_player.model.Titre;

import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private MediaPlayer mediaPlayer = null;

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

    public void playMedia(Titre titre) {
        if (mediaPlayer != null) {
            stopMedia();
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(getApplicationContext(), titre.getUri());
        } catch (IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (IllegalStateException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IllegalStateException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        }
       mediaPlayer.start();
    }

    public void stopMedia() {
        mediaPlayer.stop();
    }
}
