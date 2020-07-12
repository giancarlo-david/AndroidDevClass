package edu.txstate.gsd26.exam3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        TextView gameID = findViewById(R.id.txtID);
        TextView gameTitle = findViewById(R.id.txtTitle);
        TextView gameSales = findViewById(R.id.txtSales);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(UpdateActivity.this);

        Game selectedGame = new Game(pref.getInt("KeyID", 0), pref.getString("KeyTitle", null), pref.getInt("KeySales", 0));

        int intIndex = pref.getInt("KeyPosition", 0);

        gameID.setText("ID: " + selectedGame.getId());
        gameTitle.setText("Title: " + String.valueOf(selectedGame.getTitle()));
        gameSales.setText("Sales: " + selectedGame.getSales());

        String strNewCost = String.valueOf(selectedGame.getSales());
        StringEntity entity = null;

        try{
            entity = new StringEntity(strNewCost);
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/text"));
            String url = "games_gsd26/" + intIndex + "/Sales.json";
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}