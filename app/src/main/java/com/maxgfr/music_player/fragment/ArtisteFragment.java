package com.maxgfr.music_player.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import com.maxgfr.music_player.adapter.ArtisteAdapter;
import com.maxgfr.music_player.model.MusicLab;
import com.maxgfr.music_player.service.MusicService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by maxime on 20/03/2017.
 */

public class ArtisteFragment extends ListFragment {

    private ArrayList<String> mArtistes;
    private boolean fromMainActivity;
    private boolean fromAlbumList = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mArtistes = MusicLab.get(getActivity()).getArtists();
        fromMainActivity = true;

        ArtisteAdapter adapter = new ArtisteAdapter(getContext(), Adapter.IGNORE_ITEM_VIEW_TYPE, mArtistes);
        setListAdapter(adapter);
    }


    public void onListItemClick (ListView l, View v, int position, long id) {
        // Get selected song, put in bundle and send
        String s = ((ArtisteAdapter) getListAdapter()).getItem(position);
        Bundle bundle = new Bundle();
        Intent i = new Intent(getActivity(), MusicService.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((ArtisteAdapter)getListAdapter()).notifyDataSetChanged();
    }

}