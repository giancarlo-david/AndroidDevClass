package edu.txstate.gsd26.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CarWash extends AppCompatActivity {

    Double dblTotalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_wash);

        final RadioButton exteriorWash = findViewById(R.id.radioBtnExteriorOnly);
        final RadioButton exteriorInteriorWash = findViewById(R.id.radioBtnExteriorInterior);
        final EditText numOfWashes = findViewById(R.id.txtNumWashes);
        Button Calculate = findViewById(R.id.btnCalculate);
        final TextView results = findViewById(R.id.txtResults);

        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String strNumWashes = numOfWashes.getText().toString();
                    Integer intNumWashes = Integer.parseInt(strNumWashes);

                    if (exteriorWash.isChecked()){
                        dblTotalCost = 8.5 * intNumWashes;
                    }
                    else if (exteriorInteriorWash.isChecked()) {
                        if (intNumWashes < 10){
                            dblTotalCost = 15.5 * intNumWashes;
                        }
                        else{
                            dblTotalCost = 12.5 * intNumWashes;
                        }
                    }
                    else {
                        Toast.makeText(CarWash.this, "Please select a type of wash.", Toast.LENGTH_LONG).show();
                    }

                    DecimalFormat f = new DecimalFormat("$###,###.##");
                    results.setText("The cost is: " + f.format(dblTotalCost) + " for " + strNumWashes + " washes.");
                    // results.setText("The cost is: " + f.format(dblTotalCost) + " for " + strNumWashes + " washes.");
                }
                catch (Exception ex) {
                    Toast.makeText(CarWash.this, "The input is not valid!", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

    }


}