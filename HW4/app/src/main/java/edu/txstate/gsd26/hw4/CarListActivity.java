package edu.txstate.gsd26.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class CarListActivity extends ListActivity {

    List<Car> carList = new ArrayList<Car>();

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Car selectedCar = carList.get(position);

        DecimalFormat f = new DecimalFormat("###,###.##");
        Toast.makeText(CarListActivity.this, "Cost per day is: $" +
                f.format(selectedCar.getCostPerDay()), Toast.LENGTH_SHORT).show();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(CarListActivity.this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("KeyID", selectedCar.getId());
        editor.putString("KeyBrand", selectedCar.getBrand());
        editor.putString("KeyName", selectedCar.getName());
        editor.putFloat("KeyCost", (float) selectedCar.getCostPerDay());
        editor.putString("KeyURL", selectedCar.getUrl());
        editor.putInt("KeyPosition", position);

        editor.commit();

        startActivity(new Intent(CarListActivity.this, CarDetailsActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));
        RestClient.get(CarListActivity.this, "Car.json", headers.toArray(new Header[headers.size()]),
                null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        //super.onSuccess(statusCode, headers, response);

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                Car bean = new Car(response.getJSONObject(i));
                                carList.add(bean);
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }

                        }

                        setListAdapter(new ArrayAdapter<Car>(CarListActivity.this, R.layout.activity_car_list2, R.id.txtList, carList));
                    }
                });
    }
}