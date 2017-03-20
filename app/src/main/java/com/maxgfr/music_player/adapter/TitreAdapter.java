package com.maxgfr.music_player.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maxgfr.music_player.R;
import com.maxgfr.music_player.model.Titre;
import com.maxgfr.music_player.model.Titre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxime on 16/03/2017.
 */

public class TitreAdapter extends ArrayAdapter<Titre> {

    int resource;
    String response;
    Context context;

    public TitreAdapter(Context context, int textViewResourceId, List<Titre> titres) {
        super(context, textViewResourceId, titres);
        this.resource = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout titreListItem;
        // Get current titre object
        Titre titre = getItem(position);

        // Inflate the view
        if (convertView == null) {
            titreListItem = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, titreListItem, true);
        } else {
            titreListItem = (LinearLayout) convertView;
        }

        // Get the text boxes from the list_item.xml file
        TextView titreName = (TextView) titreListItem.findViewById(R.id.contentTitre);

        // Assign the appropriate data from titre object
        titreName.setText(titre.getTitre());

        return titreListItem;
    }
}