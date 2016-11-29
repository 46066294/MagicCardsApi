package com.example.a46066294p.magiccardsapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.Set;

import com.example.a46066294p.magiccardsapi.databinding.FragmentMainBinding;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    //private ArrayList<Cards> items;
    private CardsCursorAdapter adapter;
    private ProgressDialog dialog;

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

        FragmentMainBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);//Data Binding Layout Files --> ver https://developer.android.com/topic/libraries/data-binding/index.html
        View view = binding.getRoot();

        adapter = new CardsCursorAdapter(getContext(), Cards.class);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading data...");

        binding.lvCards.setAdapter(adapter);
        binding.lvCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cards card = (Cards)adapterView.getItemAtPosition(i);

                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("card" , card);
                startActivity(intent);
            }
        });

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //refresh();
            }
        });

        getLoaderManager().initLoader(0, null, this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //refresh();
        filter();
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

////////////////////////////////////////////////////////////////////////////////////////////////////
    private void refresh() {

        RefreshDataTask task = new RefreshDataTask();
        task.execute();


    }




    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ArrayList<Cards> result = DataAccesObject.getCards();

            Log.d("DEBUG", result != null ? result.toString() : null);

            DataManager.deleteCards(getContext());
            DataManager.saveCards(result, getContext());

            return null;
        }
    }


    private void filter() {
        FilterDataTask task = new FilterDataTask();
        task.execute();

    }


    private class FilterDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }


        @Override
        protected Void doInBackground(Void... voids) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            Set<String> selections = preferences.getStringSet("multi_select_list_preference_1" , null);
            String[] selectedColor = selections.toArray(new String[] {});

            for (int i = 0; i < selectedColor.length ; i++){
                System.out.println("\ntestColor" + i +" : " + selectedColor[i]);
            }

            String selectedRarity = preferences.getString("list_preference_1" , null);
            System.out.println("\ntestRariry : " + selectedRarity);

            //DataAccesObject dao = new DataAccesObject();
            ArrayList<Cards> result = DataAccesObject.getCards(selections, selectedRarity);

            Log.d("DEBUG", result != null ? result.toString() : null);

            DataManager.deleteCards(getContext());
            DataManager.saveCards(result, getContext());

            return null;
        }
    }

    //Interface methods
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

}
