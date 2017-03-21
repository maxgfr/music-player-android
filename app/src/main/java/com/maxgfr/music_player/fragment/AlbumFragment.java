package com.maxgfr.music_player.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import com.maxgfr.music_player.adapter.AlbumAdapter;
import com.maxgfr.music_player.model.MusicLab;
import com.maxgfr.music_player.model.Album;
import com.maxgfr.music_player.service.MusicService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by maxime on 20/03/2017.
 */

public class AlbumFragment extends ListFragment {

    private ArrayList<Album> mAlbums;
    private boolean fromMainActivity;
    private boolean fromAlbumList = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mAlbums = MusicLab.get(getActivity()).getAlbums();
        fromMainActivity = true;

        Collections.sort(mAlbums, new Comparator<Album>(){
            public int compare(Album a, Album b){
                return a.getTitle().compareTo(b.getTitle());
            }
        });

        AlbumAdapter adapter = new AlbumAdapter(getContext(), Adapter.IGNORE_ITEM_VIEW_TYPE, mAlbums);
        setListAdapter(adapter);
    }


    public void onListItemClick (ListView l, View v, int position, long id) {
        // Get selected song, put in bundle and send
        Album s = ((AlbumAdapter) getListAdapter()).getItem(position);
        Bundle bundle = new Bundle();
        Intent i = new Intent(getActivity(), MusicService.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((AlbumAdapter)getListAdapter()).notifyDataSetChanged();
    }

}
