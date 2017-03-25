package com.maxgfr.music_player.adapter;

/**
 * Created by maxime on 20/03/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maxgfr.music_player.activity.MainActivity;
import com.maxgfr.music_player.fragment.PlaceholderFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private MainActivity activite;

    public SectionsPagerAdapter(FragmentManager fm, MainActivity activite) {
        super(fm);
        this.activite = activite;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1,activite);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Titres";
            case 1:
                return "Album";
            case 2:
                return "Artistes";
        }
        return null;
    }
}
