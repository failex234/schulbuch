package de.failex.schulplaner;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ItemActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "de.failex.felixdemo.MESSAGE";
    Library lib;
    String dow = "";
    ArrayList<String> endliste;
    boolean inEditMode;
    ArrayAdapter<String> adapter;
    ListView lview;
    MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        endliste = new ArrayList<>();

        inEditMode = false;

        File file = new File(this.getFilesDir(), "config.json");
        Gson gson = new Gson();
        StringBuilder end = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while (line != null) {
                end.append(line);
                line = br.readLine();
            }
            br.close();
        }
        catch(IOException e) {
            e.printStackTrace();

        }
        lib = gson.fromJson(end.toString(), Library.class);
        switch(message) {
            case "Noten":
                this.setTitle("Notenübersicht");
                ArrayList<String> aktuell = lib.inhalt.get(0);
                ArrayList<String> ziel = lib.inhalt.get(1);
                for (int i = 1; i < aktuell.size(); i++) {
                    if (i == 1) {
                        endliste.add("Religion: " + aktuell.get(1) + "/" + ziel.get(1));
                    } else if (i == 2) {
                        endliste.add("Pädagogik: " + aktuell.get(2) + "/" + ziel.get(2));
                    }else if (i == 3) {
                        endliste.add("Sport: " + aktuell.get(3) + "/" + ziel.get(3));
                    }else if (i == 4) {
                        endliste.add("Englisch: " + aktuell.get(4) + "/" + ziel.get(4));
                    }else if (i == 5) {
                        endliste.add("Informatik: " + aktuell.get(5) + "/" + ziel.get(5));
                    }else if (i == 6) {
                        endliste.add("Geschichte: " + aktuell.get(6) + "/" + ziel.get(6));
                    }else if (i == 7) {
                        endliste.add("Mathe: " + aktuell.get(7) + "/" + ziel.get(7));
                    }else if (i == 8) {
                        endliste.add("Deutsch: " + aktuell.get(8) + "/" + ziel.get(8));
                    }else if (i == 9) {
                        endliste.add("Musik: " + aktuell.get(9) + "/" + ziel.get(9));
                    }else if (i == 10) {
                        endliste.add("Bio: " + aktuell.get(10) + "/" + ziel.get(10));
                    }else if (i == 11) {
                        endliste.add("SoWi: " + aktuell.get(11) + "/" + ziel.get(11));
                    }
                }
                break;
            case "Stundenplan":
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                ArrayList<String> plan = lib.inhalt.get(2);
                switch(day) {
                    case Calendar.SUNDAY:
                        this.setTitle("Stundenplan - Sonntag");
                        dow ="Sonntag";
                        break;
                    case Calendar.MONDAY:
                        this.setTitle("Stundenplan - Montag");
                        dow ="Montag";
                        break;
                    case Calendar.TUESDAY:
                        this.setTitle("Stundenplan - Dienstag");
                        dow ="Dienstag";
                        break;
                    case Calendar.WEDNESDAY:
                        this.setTitle("Stundenplan - Mittwoch");
                        dow ="Mittwoch";
                        break;
                    case Calendar.THURSDAY:
                        this.setTitle("Stundenplan - Donnerstag");
                        dow ="Donnerstag";
                        break;
                    case Calendar.FRIDAY:
                        this.setTitle("Stundenplan - Freitag");
                        dow ="Freitag";
                        break;
                    case Calendar.SATURDAY:
                        this.setTitle("Stundenplan - Samstag");
                        dow ="Samstag";
                        break;
                }
                for (int i = 0; i < plan.size(); i++) {
                    if (dow == "Sonntag") {
                        endliste.add("Kein Unterricht heute :(");
                        dow = "undefined";
                    } else if (dow == "Montag") {
                        if (i >= 2 && i <= 10) {
                            if (plan.get(i).equals("Frei")) {
                                endliste.add(" ");
                            } else if (plan.get(i).equals("Freistunde")) {
                                endliste.add("----------------");
                            } else {
                                endliste.add(plan.get(i));
                            }
                        }
                    } else if (dow == "Dienstag") {
                        if (i >= 12 && i <= 20) {
                            if (plan.get(i).equals("Frei")) {
                                endliste.add(" ");
                            } else if (plan.get(i).equals("Freistunde")) {
                                endliste.add("----------------");
                            } else {
                                endliste.add(plan.get(i));
                            }
                        }
                    } else if (dow == "Mittwoch") {
                        if (i >= 22 && i <= 30) {
                            if (plan.get(i).equals("Frei")) {
                                endliste.add(" ");
                            } else if (plan.get(i).equals("Freistunde")) {
                                endliste.add("----------------");
                            } else {
                                endliste.add(plan.get(i));
                            }
                        }
                    } else if (dow == "Donnerstag") {
                        if (i >= 32 && i <= 40) {
                            if (plan.get(i).equals("Frei")) {
                                endliste.add(" ");
                            } else if (plan.get(i).equals("Freistunde")) {
                                endliste.add("----------------");
                            } else {
                                endliste.add(plan.get(i));
                            }
                        }
                    } else if (dow == "Freitag") {
                        if (i >= 42 && i <= 50) {
                            if (plan.get(i).equals("Frei")) {
                                endliste.add(" ");
                            } else if (plan.get(i).equals("Freistunde")) {
                                endliste.add("----------------");
                            } else {
                                endliste.add(plan.get(i));
                            }
                        }
                    } else if (dow == "Samstag") {
                        endliste.add("Kein Unterricht heute :(");
                        dow = "undefined";
                    }
                }
                break;
            case "Klausuren":
                this.setTitle("Kommende Klausuren");
                ArrayList<String> klausuren = lib.inhalt.get(3);
                ArrayList<String> datenklausuren = lib.inhalt.get(6);
                ArrayList<String> datumklausuren = lib.inhalt.get(5);
                if (klausuren.size() == datenklausuren.size() && datenklausuren.size() == datumklausuren.size()) {
                    if (klausuren.size() > 1) {
                        for (int i = 1; i < klausuren.size(); i++) {
                            endliste.add(klausuren.get(i) + " am " + datumklausuren.get(i) + " in " + datenklausuren.get(i));
                        }
                    } else {
                        endliste.add("Kein Eintrag!");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Die Listen sind verschieden groß!", Toast.LENGTH_LONG).show();
                    String has = "";
                    String dnhas = "";
                    String dmhas = "";
                    for (int i = 0; i < klausuren.size(); i++) {
                        has += klausuren.get(i);
                    }
                    for (int i = 0; i < datenklausuren.size(); i++) {
                        dnhas += datenklausuren.get(i);
                    }
                    for (int i = 0; i < datumklausuren.size(); i++) {
                        dmhas += datumklausuren.get(i);
                    }
                    Toast.makeText(getApplicationContext(),"Klausuren: " + has, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Daten " + dnhas, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Datum" + dmhas, Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            case "Hausaufgaben":
                this.setTitle("Hausaufgabenübersicht");
                ArrayList<String> hausaufgaben = lib.inhalt.get(4);
                ArrayList<String> datenhausaufgaben = lib.inhalt.get(8);
                ArrayList<String> datumhausaufgaben = lib.inhalt.get(7);
                if (hausaufgaben.size() == datenhausaufgaben.size() && datenhausaufgaben.size() == datumhausaufgaben.size()) {
                    if (hausaufgaben.size() > 1) {
                        for (int i = 1; i < hausaufgaben.size(); i++) {
                            endliste.add(hausaufgaben.get(i) + " für den " + datumhausaufgaben.get(i) + ": " + datenhausaufgaben.get(i));
                        }
                    } else {
                        endliste.add("Kein Eintrag!");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Die Listen sind verschieden groß!", Toast.LENGTH_LONG).show();
                    String has = "";
                    String dnhas = "";
                    String dmhas = "";
                    for (int i = 0; i < hausaufgaben.size(); i++) {
                        has += hausaufgaben.get(i);
                    }
                    for (int i = 0; i < datenhausaufgaben.size(); i++) {
                        dnhas += datenhausaufgaben.get(i);
                    }
                    for (int i = 0; i < datumhausaufgaben.size(); i++) {
                        dmhas += hausaufgaben.get(i);
                    }
                    Toast.makeText(getApplicationContext(),"Hausaufgaben: " + has, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Daten " + dnhas, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Datum" + dmhas, Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            case "Lerntagebuch":
                this.setTitle("Lerntagebuch");
                File tag = new File(this.getFilesDir(), "tagebuch.json");
                Gson gs = new Gson();
                StringBuilder en = new StringBuilder();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(tag));
                    String line = br.readLine();

                    while (line != null) {
                        en.append(line);
                        line = br.readLine();
                    }
                    br.close();
                }
                catch(IOException e) {
                    e.printStackTrace();

                }
                Tagebuch tb = gs.fromJson(en.toString(), Tagebuch.class);


                ArrayList<String> fach = tb.fach;
                ArrayList<Date> datum = tb.datum;
                ArrayList<String> text = tb.text;

                if (fach.size() == datum.size() && datum.size() == text.size()) {
                    for (int i = 0; i < fach.size(); i++) {
                        DateFormat dateFormat = new SimpleDateFormat("dd.MM HH:mm");
                        Date date = datum.get(i);
                        endliste.add(fach.get(i) + " vom " + dateFormat.format(date) + ": " + text.get(i));
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "FEHLER! Die Listen sind unterschiedlich groß!", Toast.LENGTH_LONG).show();
                }
                break;
        }

        lview = (ListView) findViewById(R.id.listView);

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SparseBooleanArray liste = lview.getCheckedItemPositions();
                int counter = 0;
                if (liste != null) {
                    for (int i = 0; i  < liste.size(); i++) {
                        if (liste.get(liste.keyAt(i))) {
                            counter++;
                        }
                    }
                    if (counter > 0) {
                        item.setTitle("Löschen");
                    } else {
                        item.setTitle("Abbrechen");
                    }
                }
            }
        });
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, endliste);
        lview.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.getTitle().toString().contains("Stundenplan")) return false;

        if (this.getTitle().toString().contains("Lerntagebuch")) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.option_menu_tagebuch, menu);
            return super.onCreateOptionsMenu(menu);
        }
        if (this.getTitle().equals("Notenübersicht")) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.option_menu_noten, menu);
            return super.onCreateOptionsMenu(menu);
        }
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        try {
            if (item.getTitle() == null) return false;
        }
        catch (NullPointerException e) {
            return false;
        }
        if (this.getTitle().toString().contains("Stundenplan")) return false;
        if (this.getTitle().equals("Lerntagebuch") && item.getTitle().toString().equals("Hinzufügen")) {
            Intent intent = new Intent(getApplicationContext(), EditActivity.class);
            intent.putExtra(EXTRA_MESSAGE, this.getTitle());
            startActivity(intent);
        }
        if (item.getTitle().toString().equals("Bearbeiten") || item.getTitle().toString().equals("Löschen") || item.getTitle().toString().equals("Abbrechen")) {
            if (inEditMode) {
                item.setTitle("Bearbeiten");
                SparseBooleanArray checked = lview.getCheckedItemPositions();
                ArrayList<String> checkedElements = new ArrayList<>();
                for (int i = 0; i < lview.getCount(); i++) {
                    if (checked.get(i)) {
                        checkedElements.add(lview.getItemAtPosition(i).toString());
                        if (lview.getItemAtPosition(i).toString().equals("Kein Eintrag!")) {
                            lview.clearChoices();
                            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, endliste);
                            lview.setItemsCanFocus(true);
                            lview.setAdapter(adapter);
                            inEditMode = false;
                            return super.onOptionsItemSelected(item);
                        }
                        if (this.getTitle().equals("Hausaufgabenübersicht")) {
                            for (int j = 0; j < lib.inhalt.get(4).size(); j++) {
                                String currentitem = lview.getItemAtPosition(i).toString();
                                if (currentitem.contains(lib.inhalt.get(4).get(j)) && currentitem.contains(lib.inhalt.get(8).get(j)) && currentitem.contains(lib.inhalt.get(7).get(j))) {
                                    lib.inhalt.get(4).remove(j);
                                    lib.inhalt.get(8).remove(j);
                                    lib.inhalt.get(7).remove(j);
                                }
                            }
                        } else if (this.getTitle().equals("Kommende Klausuren")) {
                            String currentitem = lview.getItemAtPosition(i).toString();
                            for (int j = 0; j < lib.inhalt.get(3).size(); j++) {
                                if (currentitem.contains(lib.inhalt.get(3).get(j)) && currentitem.contains(lib.inhalt.get(6).get(j)) && currentitem.contains(lib.inhalt.get(5).get(j))){
                                    lib.inhalt.get(3).remove(j);
                                    lib.inhalt.get(6).remove(j);
                                    lib.inhalt.get(5).remove(j);
                                }
                            }
                        }
                        writeChanges();
                    }
                }
                for (int i = checkedElements.size() - 1; i >= 0; i--) {
                    endliste.remove(checkedElements.get(i));
                }
                if (checkedElements.size() > 0) {
                    endliste.add("Kein Eintrag!");
                }
                adapter.notifyDataSetChanged();
                lview.clearChoices();
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, endliste);
                lview.setItemsCanFocus(true);
                lview.setAdapter(adapter);
                inEditMode = false;
            } else {
                item.setTitle("Abbrechen");
                this.item = item;
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, endliste);
                lview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lview.setItemsCanFocus(false);
                lview.setAdapter(adapter);
                inEditMode = true;
            }
        } else if (item.getTitle().toString().equals("Hinzufügen")) {
            Intent intent = new Intent(getApplicationContext(), EditActivity.class);
            intent.putExtra(EXTRA_MESSAGE, this.getTitle());
            startActivity(intent);
        } else if (item.getTitle().toString().equals("Zielpunktzahl bearbeiten")) {
            Toast.makeText(getApplicationContext(), "Du möchtest also deine Ziele anpassen?", Toast.LENGTH_LONG).show();

        } else if (item.getTitle().toString().equals("Aktuelle Punktzahl bearbeiten")) {
            Toast.makeText(getApplicationContext(), "Du möchtest also deine aktuelle Punktzahl anpassen?", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void writeChanges() {
        Gson gson = new Gson();
        String string = gson.toJson(lib);
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput("config.json", Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
