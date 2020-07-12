package edu.txstate.gsd26.ticketvault;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    int intNumberOfTickets;
    double dblCostPerTicket = 79.99;
    double dblTotalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText numberOfTicket = findViewById(R.id.txtNumOfTickets);
        final Spinner groups = findViewById(R.id.groupSpinner);
        Button findCost = findViewById(R.id.btnFindCost);
        final TextView results = findViewById(R.id.txtResults);

        findCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNumberOfTickets = numberOfTicket.getText().toString();
                try{
                    intNumberOfTickets = Integer.parseInt(strNumberOfTickets);
                }
                catch (Exception ex)
                {
                    Toast.makeText(MainActivity.this, "The input is not valid!", Toast.LENGTH_LONG).show();
                    return;
                }
                dblTotalCost = dblCostPerTicket * intNumberOfTickets;

                String strSelection = groups.getSelectedItem().toString();
                DecimalFormat f = new DecimalFormat("$###,###.##");
                results.setText("The cost is: " + f.format(dblTotalCost) + " and your selection is " + strSelection + ".");

            }
        });
    }
}