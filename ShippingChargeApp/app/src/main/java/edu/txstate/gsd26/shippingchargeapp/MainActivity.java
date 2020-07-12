package edu.txstate.gsd26.shippingchargeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Double dblShippingCharge;
    String packageSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final RadioButton sizeLarge = findViewById(R.id.radioBtnLarge);
        final RadioButton sizeSmall = findViewById(R.id.radioBtnSmall);
        final Spinner products = findViewById(R.id.spinnerProducts);
        final EditText weight = findViewById(R.id.txtWeight);
        final TextView result = findViewById(R.id.txtResult);
        Button Calculate = findViewById(R.id.btnCalculate);

        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Double dblWeight = Double.parseDouble(weight.getText().toString());

                    if (dblWeight < 20) {
                        if (sizeSmall.isChecked()) {
                            dblShippingCharge = 2.5 * dblWeight;
                            packageSize = "Small";
                        } else if (sizeLarge.isChecked()) {
                            dblShippingCharge = 2.5 * dblWeight + 5;
                            packageSize = "Large";
                        } else {
                            Toast.makeText(MainActivity.this, "Please select a size", Toast.LENGTH_LONG).show();
                        }
                    }

                    else{
                        startActivity(new Intent(MainActivity.this, ErrorActivity.class));
                    }

                    String strProduct = products.getSelectedItem().toString();
                    DecimalFormat f = new DecimalFormat("$###,###.##");
                    result.setText("The shipping cost is: " + f.format(dblShippingCharge) + " for a " + strProduct + "\nshipping in a " + packageSize + " box size with a weight of " + dblWeight.toString() + " pounds.");
                }
                catch (Exception ex){
                    startActivity(new Intent(MainActivity.this, ErrorActivity.class));
                }

            }
        });
    }



}