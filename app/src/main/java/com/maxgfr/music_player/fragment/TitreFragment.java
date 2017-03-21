package com.maxgfr.music_player.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import com.maxgfr.music_player.model.MusicLab;
import com.maxgfr.music_player.adapter.TitreAdapter;
import com.maxgfr.music_player.model.Titre;
import com.maxgfr.music_player.service.MusicService;

/**
 * Created by maxime on 20/03/2017.
 */

public class TitreFragment extends ListFragment {

    private ArrayList<Titre> mTitres;
    private boolean fromMainActivity;
    private boolean fromAlbumList = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mTitres = MusicLab.get(getActivity()).getTitres();
        fromMainActivity = true;

        Collections.sort(mTitres, new Comparator<Titre>(){
            public int compare(Titre a, Titre b){
                return a.getTitre().compareTo(b.getTitre());
            }
        });

        TitreAdapter adapter = new TitreAdapter(getContext(),Adapter.IGNORE_ITEM_VIEW_TYPE, mTitres);
        setListAdapter(adapter);
    }

    
    public void onListItemClick (ListView l, View v, int position, long id) {
        // Get selected song, put in bundle and send
        Titre s = ((TitreAdapter) getListAdapter()).getItem(position);
        Bundle bundle = new Bundle();
        Intent i = new Intent(getActivity(), MusicService.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((TitreAdapter)getListAdapter()).notifyDataSetChanged();
    }

}