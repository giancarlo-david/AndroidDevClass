package edu.txstate.gsd26.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class UpdateActivity extends AppCompatActivity {

    int intIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        TextView carID = findViewById(R.id.txtID);
        TextView carBrand = findViewById(R.id.txtBrand);
        TextView carName = findViewById(R.id.txtName);
        final EditText newCost = findViewById(R.id.txtNewCost);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(UpdateActivity.this);

        final Car selectedCar = new Car(pref.getInt("KeyID", 0),
                pref.getString("KeyBrand", null),
                pref.getString("KeyName", null),
                pref.getFloat("KeyCost", 0),
                pref.getString("KeyURL", null));

        intIndex = pref.getInt("KeyPosition", 0);

        DecimalFormat f = new DecimalFormat("###,###.##");

        carID.setText("ID: " + String.valueOf(selectedCar.getId()));
        carBrand.setText("Brand: " + selectedCar.getBrand());
        carName.setText("Name: " + selectedCar.getName());

        Button update = findViewById(R.id.btnUpdate);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNewCost = newCost.getText().toString();
                StringEntity entity = null;

                try{
                    entity = new StringEntity(strNewCost);
                    entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/text"));
                    String url = "Car/" + (intIndex + 1) + "/Cost.json";
                    RestClient.put(UpdateActivity.this, url, entity, "application/text", new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            Toast.makeText(UpdateActivity.this, "Failure!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {
                            Toast.makeText(UpdateActivity.this, "Success!", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button home = findViewById(R.id.btnHome);

        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
            }
        });

    }
}