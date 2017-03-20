package com.maxgfr.music_player.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxgfr.music_player.R;
import com.maxgfr.music_player.model.Album;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import java.util.List;

/**
 * Created by maxime on 16/03/2017.
 */

public class AlbumAdapter extends ArrayAdapter<Album> {

    int resource;
    String response;
    Context context;

    public AlbumAdapter(Context context, int textViewResourceId, List<Album> albums) {
        super(context, textViewResourceId, albums);
        this.resource = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout albumListItem;
        // Get current album object
        Album album = getItem(position);

        // Inflate the view
        if (convertView == null) {
            albumListItem = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, albumListItem, true);
        } else {
            albumListItem = (LinearLayout) convertView;
        }

        // Get the text boxes from the list_item.xml file
        TextView albumName = (TextView) albumListItem.findViewById(R.id.contentAlbum);

        // Assign the appropriate data from album object
        albumName.setText(album.getTitle());

        return albumListItem;
    }
}