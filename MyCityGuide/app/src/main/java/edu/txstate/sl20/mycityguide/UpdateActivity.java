package edu.txstate.sl20.mycityguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class UpdateActivity extends AppCompatActivity {
    double dblCost;
    int intIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(UpdateActivity.this);
        intIndex = sp.getInt("keyPosition", 0);
        //
        intIndex = this.getIntent().getIntExtra("keyPosition", 0);

        final EditText cost = findViewById(R.id.txtCost);
        Button update = findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strCost = cost.getText().toString();
                StringEntity entity = null;
                try {
                    entity = new StringEntity(strCost);
                    entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/text"));
                    String url = "attractions/" + intIndex + "/Cost.json";
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

                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}