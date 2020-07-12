package edu.txstate.sl20.attractionwithimages;

import androidx.annotation.NonNull;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    List<Attraction> list = new ArrayList<Attraction>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        TextView tv = new TextView(getApplicationContext());
        tv.setText("Icon made by Pixel perfect from www.flaticon.com");
        listView = getListView();
        listView.addFooterView(tv);

        Attraction a1 = null;
        a1 = new Attraction();
        a1.setId(100);
        a1.setName("Mayan");
        a1.setImage(R.drawable.mayan);

        Attraction a2 = null;
        a2 = new Attraction();
        a2.setId(107);
        a2.setName("Forbidden City");
        a2.setImage(R.drawable.forbidden_city);

        Attraction a3 = null;
        a3 = new Attraction();
        a3.setId(109);
        a3.setName("Pyramids");
        a3.setImage(R.drawable.pyramids);


        list.add(a1);
        list.add(a2);
        list.add(a3);


        setListAdapter( new AttractionAdaptor(this, R.layout.attraction_row,list));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        Toast.makeText(this, list.get(position).getName(), Toast.LENGTH_LONG).show();
    }
}
