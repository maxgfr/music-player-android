package com.maxgfr.music_player.service;

/**
 * Created by maxime on 16/03/2017.
 */


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
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
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.maxgfr.music_player.R;
import com.maxgfr.music_player.activity.MainActivity;
import com.maxgfr.music_player.model.Titre;

import java.util.ArrayList;

public class MusicService extends Service {

    private final IBinder mBinder = new LocalBinder();

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

    public void playMedia(MediaPlayer media){
        media.start();
    }

}
