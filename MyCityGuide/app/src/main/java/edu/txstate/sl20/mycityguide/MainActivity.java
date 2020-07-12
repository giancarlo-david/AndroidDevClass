package edu.txstate.sl20.mycityguide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class MainActivity extends ListActivity {
    //String[] strAttractions = {"An Institute of Chicago", "Willis Tower", "Navy Pier"};

    List<Attraction> listAttraction = new ArrayList<Attraction>() ;

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        Attraction selectedAttrcation = listAttraction.get(position);
        DecimalFormat df = new DecimalFormat("###,###.##");
        Toast.makeText(MainActivity.this,
                    "The cost: " + df.format(selectedAttrcation.getCost()), Toast.LENGTH_LONG).show();

        //String url = selectedAttrcation.getUrl();
        //if (url == null) {
        //    url = "http://google.com";
        //}
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt("keyPosition", position);
        ed.commit();

        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        intent.putExtra("keyPosition", position);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));
        RestClient.get(MainActivity.this, "attractions.json",headers.toArray(new Header[headers.size()]),
                null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        //super.onSuccess(statusCode, headers, response);

                        for (int i=0; i < response.length(); i++){
                            try {
                                Attraction bean = new Attraction(response.getJSONObject(i));
                                listAttraction.add(bean);
                            } catch (JSONException ex){
                                ex.printStackTrace();
                            }

                        }

                        setListAdapter(new ArrayAdapter<Attraction>(MainActivity.this,R.layout.layout_attrcation, R.id.txtAttraction, listAttraction));
                    }
                }
                );



//        Attraction a1 = new Attraction();
//        a1.setId(101);
//        a1.setName("An Institute of Chicago");
//        a1.setCost(20.99);
//        a1.setUrl("http://artic.edu");
//        listAttraction.add(a1);
//
//        Attraction a2 = new Attraction();
//        a2.setId(102);
//        a2.setName("Willis Tower");
//        a2.setCost(30.25);
//        a2.setUrl("http://willistower.com");
//        listAttraction.add(a2);
//
//        Attraction a3 = new Attraction();
//        a3.setId(103);
//        a3.setName("Navy Pier");
//        a3.setCost(25.80);
//        //url is null
//        listAttraction.add(a3);

        //setContentView(R.layout.activity_main);
        //setListAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, strAttractions));
        //setListAdapter(new ArrayAdapter<String>(MainActivity.this,R.layout.layout_attrcation, R.id.txtAttraction,strAttractions));
        //setListAdapter(new ArrayAdapter<Attraction>(MainActivity.this,R.layout.layout_attrcation, R.id.txtAttraction, listAttraction));
    }
}