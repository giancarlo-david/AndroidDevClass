package edu.txstate.gsd26.exam2;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    List<Book> bookList = new ArrayList<Book>();

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        Book selectedBook = bookList.get(position);

        if (selectedBook.getSales() == 0){
            Toast.makeText(MainActivity.this, "This book has no sales!", Toast.LENGTH_LONG).show();
        }

        else{
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("KeyID", selectedBook.getId());
            editor.putFloat("KeyPrice", (float) selectedBook.getPrice());
            editor.putString("KeyTitle", selectedBook.getTitle());
            editor.putString("KeyAuthor", selectedBook.getAuthor());
            editor.putInt("KeySales", selectedBook.getSales());

            editor.commit();

            startActivity(new Intent(MainActivity.this, BookDetailsActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Book book1 = new Book(101,15.00, "A Book of Life", "Gian David", 0);
        Book book2 = new Book(102, 12, "The Great Gatsby", "F. Scott Fitzgerald", 30000000);
        Book book3 = new Book(103, 14, "The Lightning Thief", "Rick Riordan", 2000000);
        Book book4 = new Book(104, 12, "Legend", "Marie Lu", 1500000);
        Book book5 = new Book(105, 14, "The Hunger Games", "Suzanne Collins", 3000000);

        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);

        setListAdapter(new ArrayAdapter<Book>(MainActivity.this, R.layout.activity_main, R.id.txtList, bookList));

    }
}