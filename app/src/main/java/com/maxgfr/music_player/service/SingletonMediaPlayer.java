package com.maxgfr.music_player.service;

import android.media.MediaPlayer;

/**
 * Created by maxime on 22/03/2017.
 */

public class SingletonMediaPlayer extends MediaPlayer {

    private static volatile SingletonMediaPlayer instance = null;

    private SingletonMediaPlayer(){
        super();
    }

    public static SingletonMediaPlayer getInstance(){
        if(SingletonMediaPlayer.instance==null){
            synchronized (SingletonMediaPlayer.class){
                if(SingletonMediaPlayer.instance==null){
                    SingletonMediaPlayer.instance=new SingletonMediaPlayer();
                }
            }
        }
        return SingletonMediaPlayer.instance;
    }
}
