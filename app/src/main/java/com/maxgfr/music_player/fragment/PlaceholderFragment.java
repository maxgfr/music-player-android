package com.maxgfr.music_player.fragment;

/**
 * Created by maxime on 21/03/2017.
 */

import android.app.Activity;
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
    private MusicLab labo;
    private ListView listeView;
    private Activity activite;
    private ArrayList listeCourante;
    private MusicService service;

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
        return fragment;
    }

    public void setMainActivity(MainActivity activite){
        this.activite = activite;
    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        this.listeView = (ListView) rootView.findViewById(R.id.content);

        int numero = this.getArguments().getInt(ARG_SECTION_NUMBER);

        if(numero == 1){
            replaceListView(labo.getTitres(), onTitre);
        }
        else if(numero == 2){
            replaceListView(labo.getAlbums(), onAlbum);
        }
        else if(numero == 3) {
            replaceListView(labo.getArtists(), onArtiste);
        }

        return rootView;
    }

    //Quand on clique sur un morceau, le joue par le biais du service
    private AdapterView.OnItemClickListener onTitre = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //service.playSong();
        }
    };

    private AdapterView.OnItemClickListener onAlbum = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String nomAlbum = generateArrayListAlbum().get(position);
            replaceListView(labo.getAlbumTitres(nomAlbum), onTitre);
        }
    };

    private AdapterView.OnItemClickListener onArtiste = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String monArtiste = generateArrayListAlbum().get(position);
            replaceListView(labo.getAlbumsFromArtist(monArtiste), onTitre);
        }
    };

    private void replaceListView(ArrayList<?> liste, AdapterView.OnItemClickListener event){
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, android.R.id.text1, liste);
        this.listeView.setAdapter(adapter);
        this.listeView.setOnItemClickListener(event);

        this.listeCourante = liste;
    }

    private ArrayList<String> generateArrayListAlbum(){
        ArrayList<String> listeAlbums = new ArrayList<String>();
        for(Titre p : labo.getTitres()){
            if(!listeAlbums.contains(p.getAlbum())){
                listeAlbums.add(p.getAlbum());
            }
        }
        return listeAlbums;
    }

    private ArrayList<String> generateArrayListArtiste(){
        ArrayList<String> listeAlbums = new ArrayList<String>();
        for(Titre p : labo.getTitres()){
            if(!listeAlbums.contains(p.getArtiste())){
                listeAlbums.add(p.getArtiste());
            }
        }
        return listeAlbums;
    }

}
