package com.example.a46066294p.magiccardsapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

import java.util.Set;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Cards> items;
    private CardsAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*
        FragmentMainBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);//Data Binding Layout Files --> ver https://developer.android.com/topic/libraries/data-binding/index.html
        View view = binding.getRoot();*/

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ListView lvCards = (ListView)view.findViewById(R.id.lvCards);

        //String[] data = {"Loading..."};

        items = new ArrayList<>();
        adapter = new CardsAdapter(
                getContext(),
                R.layout.lv_cards_row,
                items
        );
        lvCards.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cards_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Refresh
        if (id == R.id.action_refresh) {
            filter();
            return true;
        }

        //Settings
        if (id == R.id.action_settings) {
            Intent i = new Intent(getContext(), SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refresh() {

        RefreshDataTask task = new RefreshDataTask();
        task.execute();

    }


    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Cards>> {
        @Override
        protected ArrayList<Cards> doInBackground(Void... voids) {

            DataAccesObject dao = new DataAccesObject();
            ArrayList<Cards> result = dao.getCards();

            Log.d("DEBUG", result != null ? result.toString() : null);

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Cards> cardsApi) {
            adapter.clear();
            for (Cards cards : cardsApi) {
                adapter.add(cards);
            }
        }
    }



    private void filter() {

        FilterDataTask task = new FilterDataTask();
        task.execute();

    }


    private class FilterDataTask extends AsyncTask<Void, Void, ArrayList<Cards>> {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected ArrayList<Cards> doInBackground(Void... voids) {

            //String color = preferences.getStringSet("color")
            ///////////////////////////////////////////////////////////////////////////////////////////////////////
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            Set<String> selections = preferences.getStringSet("multi_select_list_preference_1" , null);
            String[] selected= selections.toArray(new String[] {});
            for (int i = 0; i < selected.length ; i++){
                System.out.println("\ntest" + i +" : " + selected[i]);
            }

            Log.d("TESTING" , " multiselect");

            /*
            <string-array name="http_rarity_cards">
                <item>basic_land</item>
                <item>common</item>
                <item>uncommon</item>
                <item>rare</item>
                <item>mythic_rare</item>
                <item>special</item>
            </string-array>
             */

            DataAccesObject dao = new DataAccesObject();
            ArrayList<Cards> result = dao.getCards(selections);

            Log.d("DEBUG", result != null ? result.toString() : null);

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Cards> cardsApi) {
            adapter.clear();
            for (Cards cards : cardsApi) {
                adapter.add(cards);
            }
        }
    }

}
