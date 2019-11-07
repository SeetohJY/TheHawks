package com.example.the_hawks.Search;

import android.app.SearchManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.provider.BaseColumns;

import org.json.JSONArray;

public class FetchSearchTermSuggestions extends AsyncTask<String, Void, Cursor> {

    private static final String[] sAutocompleteColNames = new String[] {
            BaseColumns._ID,                         // necessary for adapter
            SearchManager.SUGGEST_COLUMN_TEXT_1      // the full search term
    };
    private JSONArray searchTerms = new JSONArray();

    protected Cursor doInBackground(String... params) {

        MatrixCursor cursor = new MatrixCursor(sAutocompleteColNames);

        // get your search terms from the server here, ex:


        // parse your search terms into the MatrixCursor
        for (int index = 0; index < searchTerms.length(); index++) {
            try {
                String term = searchTerms.getString(index);
            } catch (Exception e) {

            }

            Object[] row = new Object[] { index,searchTerms };
            cursor.addRow(row);
        }

        return cursor;
    }

//    @Override
//    protected void onPostExecute(Cursor result) {
//        searchView.getSuggestionsAdapter().changeCursor(result);
//    }

}