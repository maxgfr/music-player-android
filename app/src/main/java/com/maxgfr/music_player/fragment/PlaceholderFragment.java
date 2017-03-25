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
import com.maxgfr.music_player.model.Album;
import com.maxgfr.music_player.model.MusicLab;
import com.maxgfr.music_player.model.Titre;
import com.maxgfr.music_player.service.MusicService;
import com.maxgfr.music_player.service.SingletonMediaPlayer;

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
    private MainActivity activite;

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber, MainActivity activity) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        fragment.setMainActivity(activity);
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        selection = sectionNumber;
        return fragment;
    }

    public void setMainActivity(MainActivity activite){
        this.activite = activite;
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listeView = (ListView) rootView.findViewById(R.id.content);
        musicLab = new MusicLab(getContext());

        switch (selection) {
            case 1:
                TitreAdapter adapter = new TitreAdapter(getActivity(), musicLab.getTitres());
                listeView.setAdapter(adapter);
                activite.getServicePlayer().setList(musicLab.getTitres());
                listeView.setOnItemClickListener(onClickMusic);
                break;

            case 2:
                AlbumAdapter adapter2 = new AlbumAdapter(getActivity(), musicLab.getAlbums());
                listeView.setAdapter(adapter2);
                listeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Album album = (Album) parent.getItemAtPosition(position);
                        TitreAdapter ad2 = new TitreAdapter(getActivity(), musicLab.getTitresFromAlbum(album.getTitle()));
                        listeView.setAdapter(ad2);
                        activite.getServicePlayer().setList(musicLab.getTitresFromAlbum(album.getTitle()));
                        listeView.setOnItemClickListener(onClickMusic);

                    }
                });
                break;

            case 3:
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, android.R.id.text1, musicLab.getArtists());
                listeView.setAdapter(adapter3);
                listeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String artiste = (String) parent.getItemAtPosition(position);
                        TitreAdapter ad3 = new TitreAdapter(getActivity(), musicLab.getTitresFromArtist(artiste));
                        listeView.setAdapter(ad3);
                        activite.getServicePlayer().setList(musicLab.getTitresFromArtist(artiste));
                        listeView.setOnItemClickListener(onClickMusic);
                    }
                });
                break;
        }
        return rootView;
    }

    private AdapterView.OnItemClickListener onClickMusic = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Titre titre = (Titre) parent.getItemAtPosition(position);
            activite.getServicePlayer().playMedia(titre);
        }
    };
}

