package com.maxgfr.music_player.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maxgfr.music_player.R;
import com.maxgfr.music_player.model.Artiste;

import java.util.List;

/**
 * Created by maxime on 16/03/2017.
 */

public class ArtisteAdapter extends ArrayAdapter<Artiste> {

    int resource;
    String response;
    Context context;

    public ArtisteAdapter(Context context, int textViewResourceId, List<Artiste> artists) {
        super(context, textViewResourceId, artists);
        this.resource = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout artistListItem;
        // Get current artist object
        Artiste artist = getItem(position);

        // Inflate the view
        if (convertView == null) {
            artistListItem = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, artistListItem, true);
        } else {
            artistListItem = (LinearLayout) convertView;
        }

        // Get the text boxes from the list_item.xml file
        TextView artistName = (TextView) artistListItem.findViewById(R.id.contentArtiste);

        // Assign the appropriate data from artist object
        artistName.setText(artist.getTitle());

        return artistListItem;
    }
}