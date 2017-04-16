package de.failex.schulplaner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class TestActivity extends AppCompatActivity {

    private boolean enabled = false;
    private ArrayAdapter<String> adapter;
    private ListView lview;
    private ArrayList<String> endliste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        endliste = new ArrayList<>();
        endliste.add("WERT");
        endliste.add("WERT");
        endliste.add("WERT");
        endliste.add("WERT");
        endliste.add("WERT");
        lview = (ListView) findViewById(R.id.lview1);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, endliste);
        lview.setAdapter(adapter);
        Toast.makeText(this, lview.getChoiceMode() + "", Toast.LENGTH_LONG);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_test, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (enabled) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, endliste);
            lview.setItemsCanFocus(true);
            lview.setAdapter(adapter);
            enabled = false;
        } else {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, endliste);
            lview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            lview.setItemsCanFocus(false);
            lview.setAdapter(adapter);
            enabled = true;
        }
        return super.onOptionsItemSelected(item);
    }
}
