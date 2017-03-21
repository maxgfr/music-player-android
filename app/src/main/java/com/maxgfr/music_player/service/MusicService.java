package com.maxgfr.music_player.service;

/**
 * Created by maxime on 16/03/2017.
 */

import java.util.ArrayList;
import java.util.UUID;
import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.maxgfr.music_player.model.Titre;

public class MusicService extends Service {

    private MediaPlayer player;
    private ArrayList<Titre> songs;
    private int songPosn;
    private final IBinder musicBind = new MusicBinder();
    private String songTitle="";
    private static final int NOTIFY_ID=1;

    public void onCreate(){
        super.onCreate();
        songPosn=0;
        player = new MediaPlayer();

        // Initialize player
        startForeground(songPosn, null);
        initMusicPlayer();
    }

    public void initMusicPlayer(){
        // Set player properties
        player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

    }

    // Pass song list
    public void setList(ArrayList<Titre> theTitres){
        songs=theTitres;
    }

    // Binder
    public class MusicBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    // Activity will bind to service
    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    // Release resources when unbind
    @Override
    public boolean onUnbind(Intent intent){
        player.stop();
        player.release();
        return false;
    }

    //play a song
    public void playTitre(){
        player.reset();
        Titre playTitre = songs.get(songPosn);
        songTitle=playTitre.getTitre();
        long currTitre = playTitre.getId();
        Uri trackUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                currTitre);

        // Set the data source
        try{
            player.setDataSource(getApplicationContext(), trackUri);
        }
        catch(Exception e){
            Log.e("MUSIC SERVICE", "Error setting data source", e);
        }

        player.prepareAsync();
    }

    public void setTitre(int songIndex){
        songPosn=songIndex;
    }

    public void onStart() {
        startForeground(1, null);
    }

    public int getPosn(){
        return player.getCurrentPosition();
    }

    public int getDur(){
        return player.getDuration();
    }

    public boolean isPng(){
        return player.isPlaying();
    }

    public String songPlaying() {
        return songTitle;
    }

    public void pausePlayer(){
        player.pause();
    }

    public void resumePlayer() {
        player.pause();
        int length = player.getCurrentPosition();
        player.seekTo(length);
        player.start();
    }

    public int getPlayingPosition(){
        return player.getCurrentPosition();
    }

    public void seek(int posn){
        player.seekTo(posn);
    }

    public void go(){
        player.start();
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
    }
}

