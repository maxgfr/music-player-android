package com.maxgfr.music_player.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.maxgfr.music_player.R;
import com.maxgfr.music_player.model.Titre;

import java.util.ArrayList;

/**
 * Created by maxime on 22/03/2017.
 */

public class TitreAdapter extends ArrayAdapter<Titre> {
    
    public TitreAdapter(Context context, ArrayList<Titre> titres) {
        super(context, 0, titres);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater albumInflater = LayoutInflater.from(getContext());
        View customView = albumInflater.inflate(R.layout.fragment_titre, parent, false);

        String name = getItem(position).getTitre();
        TextView txt = (TextView) customView.findViewById(R.id.titre);
        txt.setText(name);

        return customView;
    }

}
