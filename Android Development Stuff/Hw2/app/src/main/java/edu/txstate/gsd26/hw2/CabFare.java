package edu.txstate.gsd26.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CabFare extends AppCompatActivity {

    Double dblNumOfMiles;
    Double dblTotalCost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cab_fare);

        final EditText numOfMiles = findViewById(R.id.txtDistance);
        Button calculate = findViewById(R.id.btnCalculate);
        final Spinner cabs = findViewById(R.id.spinnerCab);
        final TextView results = findViewById(R.id.txtCabFareResult);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNumOfMiles = numOfMiles.getText().toString();
                try {
                    dblNumOfMiles = Double.parseDouble(strNumOfMiles);
                }
                catch(Exception ex){
                    Toast.makeText(CabFare.this, "The input is not valid!", Toast.LENGTH_LONG).show();
                    return;
                }
                dblTotalCost = 2 + 3.25 * dblNumOfMiles;
                String strCab = cabs.getSelectedItem().toString();
                DecimalFormat f = new DecimalFormat("$###,###.##");
                results.setText("The cost is: " + f.format(dblTotalCost) + " and your selected cab is " + strCab + ".");
            }
        });

    }
}