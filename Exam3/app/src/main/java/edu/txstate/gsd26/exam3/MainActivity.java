package edu.txstate.gsd26.exam3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;


public class MainActivity extends ListActivity {

    List<Game> gameList = new ArrayList<Game>();


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Game selectedGame = gameList.get(position);

        int updatedSales = selectedGame.getSales() + 100;

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt("KeyID", selectedGame.getId());
        editor.putString("KeyTitle", selectedGame.getTitle());
        editor.putInt("KeySales", updatedSales);
        editor.putInt("KeyPosition", position);

        editor.commit();

        selectedGame.setSales(updatedSales);
        gameList.set(position, selectedGame);


        startActivity(new Intent(MainActivity.this, UpdateActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));
        RestClient.get(MainActivity.this, "games_gsd26.json", headers.toArray(new Header[headers.size()]),
                null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        //super.onSuccess(statusCode, headers, response);

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                Game bean = new Game(response.getJSONObject(i));
                                gameList.add(bean);
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }

                        }

                        setListAdapter(new ArrayAdapter<Game>(MainActivity.this, R.layout.activity_main, R.id.txtList, gameList));
                    }
                });
    }

}