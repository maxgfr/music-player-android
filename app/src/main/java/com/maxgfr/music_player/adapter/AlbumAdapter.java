package com.maxgfr.music_player.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.maxgfr.music_player.R;
import com.maxgfr.music_player.model.Album;

import java.util.ArrayList;

/**
 * Created by maxime on 22/03/2017. 
 */

public class AlbumAdapter extends ArrayAdapter<Album> {

    public AlbumAdapter(Context context, ArrayList<Album> albums) {
        super(context, 0, albums);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater albumInflater = LayoutInflater.from(getContext());
        View customView = albumInflater.inflate(R.layout.fragment_album, parent, false);

        String album_name = getItem(position).getTitle();
        TextView album_text = (TextView) customView.findViewById(R.id.albumView);
        album_text.setText(album_name);

        return customView;
    }
}
