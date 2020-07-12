package edu.txstate.gsd26.exam2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        TextView id = findViewById(R.id.txtID);
        TextView title = findViewById(R.id.txtTitle);
        TextView author = findViewById(R.id.txtAuthor);
        TextView price = findViewById(R.id.txtPrice);
        TextView sales = findViewById(R.id.txtSales);
        TextView loyalties = findViewById(R.id.txtLoyalties);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(BookDetailsActivity.this);

        Book selectedBook = new Book(
                pref.getInt("KeyID", 0),
                pref.getFloat("KeyPrice", 0),
                pref.getString("KeyTitle", null),
                pref.getString("KeyAuthor", null),
                pref.getInt("KeySales", 0));

        double totalLoyalties = selectedBook.getSales() * selectedBook.getPrice() * 0.12;

        DecimalFormat f = new DecimalFormat("###,###.##");

        id.setText("ID: " + selectedBook.getId());
        title.setText("Title: " + selectedBook.getTitle());
        author.setText("Author: " + selectedBook.getAuthor());
        price.setText("Price: $" + f.format(selectedBook.getPrice()));
        sales.setText("Sales: " + f.format(selectedBook.getSales()));
        loyalties.setText("Loyalties: $" + f.format(totalLoyalties));

}
}