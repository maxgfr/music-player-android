package com.maxgfr.music_player.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.maxgfr.music_player.adapter.TitreAdapter;
import com.maxgfr.music_player.model.Titre;

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

        // Check if accessed from album list or main menu
        if(getArguments() == null) {
            mTitres = TitreLab.get(getActivity()).getTitres();
            fromMainActivity = true;
        }
        else {
            String artist = (String) getArguments().getSerializable(EXTRA_ARTIST_ID);

            // Check if selected from artist or album
            if(artist == null){
                String album = (String) getArguments().getSerializable(EXTRA_ALBUM_ID);
                mTitres = TitreLab.get(getActivity()).getAlbumTitres(album);
                fromAlbumList = true;
            }
            else {
                mTitres = new ArrayList<Titre>();
                mTitres = TitreLab.get(getActivity()).getTitresFromArtist(artist);
            }
            fromMainActivity = false;
        }

        Collections.sort(mTitres, new Comparator<Titre>(){
            public int compare(Titre a, Titre b){
                return a.getTitle().compareTo(b.getTitle());
            }
        });

        TitreAdapter adapter = new TitreAdapter(mTitres);
        setListAdapter(adapter);
    }

    public static TitreFragment newInstance(String album) {
        // Initialize with String argument
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ALBUM_ID, album);

        TitreListFragment fragment = new TitreListFragment();
        fragment.setArguments(args);

        return fragment;
    }
    
    public void onListItemClick (ListView l, View v, int position, long id) {
        // Get selected song, put in bundle and send
        Titre s = ((TitreAdapter) getListAdapter()).getItem(position);
        Bundle bundle = new Bundle();
        Intent i = new Intent(getActivity(), TitrePagerActivity.class);
        bundle.putSerializable(TitreFragment.EXTRA_SONG_ID, s.getId());
        bundle.putBoolean(TitreFragment.EXTRA_FROM_ID, fromMainActivity);
        bundle.putBoolean(TitreFragment.EXTRA_FROM_ALBUMLIST_ID, fromAlbumList);
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((TitreAdapter)getListAdapter()).notifyDataSetChanged();
    }

}