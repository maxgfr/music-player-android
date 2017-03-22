package com.maxgfr.music_player.fragment;

/**
 * Created by maxime on 21/03/2017.
 */

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.maxgfr.music_player.R;
import com.maxgfr.music_player.activity.MainActivity;
import com.maxgfr.music_player.adapter.AlbumAdapter;
import com.maxgfr.music_player.adapter.TitreAdapter;
import com.maxgfr.music_player.model.MusicLab;
import com.maxgfr.music_player.model.Titre;
import com.maxgfr.music_player.service.MusicService;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static int selection;
    private ListView listeView;
    private MusicLab musicLab;

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        selection = sectionNumber;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container,false);
        this.listeView = (ListView) rootView.findViewById(R.id.content);
        musicLab = new MusicLab(getContext());


        switch (selection) {
            case 1:
                //TitreAdapter adapter = new TitreAdapter(getActivity(),musicLab.getTitres());
                //this.listeView.setAdapter(adapter);
                break;

            case 2:
                //AlbumAdapter adapter2 = new AlbumAdapter(getActivity(),musicLab.getAlbums());
                //this.listeView.setAdapter(adapter2);
                break;

            case 3:
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1, android.R.id.text1,musicLab.getArtists());
                this.listeView.setAdapter(adapter3);
                break;
        }
        return rootView;
    }


}
