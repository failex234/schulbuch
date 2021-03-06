package de.failex.schulplaner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
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
    Tagebuch tb;
    Referate refe;

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
        } catch (IOException e) {
            e.printStackTrace();

        }
        lib = gson.fromJson(end.toString(), Library.class);
        switch (message) {
            case "Noten":
                this.setTitle("Notenübersicht");
                ArrayList<String> aktuell = lib.inhalt.get(0);
                ArrayList<String> ziel = lib.inhalt.get(1);
                for (int i = 1; i < aktuell.size(); i++) {
                    if (i == 1) {
                        endliste.add("Religion: " + aktuell.get(1) + "/" + ziel.get(1));
                    } else if (i == 2) {
                        endliste.add("Pädagogik: " + aktuell.get(2) + "/" + ziel.get(2));
                    } else if (i == 3) {
                        endliste.add("Sport: " + aktuell.get(3) + "/" + ziel.get(3));
                    } else if (i == 4) {
                        endliste.add("Englisch: " + aktuell.get(4) + "/" + ziel.get(4));
                    } else if (i == 5) {
                        endliste.add("Informatik: " + aktuell.get(5) + "/" + ziel.get(5));
                    } else if (i == 6) {
                        endliste.add("Geschichte: " + aktuell.get(6) + "/" + ziel.get(6));
                    } else if (i == 7) {
                        endliste.add("Mathe: " + aktuell.get(7) + "/" + ziel.get(7));
                    } else if (i == 8) {
                        endliste.add("Deutsch: " + aktuell.get(8) + "/" + ziel.get(8));
                    } else if (i == 9) {
                        endliste.add("Musik: " + aktuell.get(9) + "/" + ziel.get(9));
                    } else if (i == 10) {
                        endliste.add("Bio: " + aktuell.get(10) + "/" + ziel.get(10));
                    } else if (i == 11) {
                        endliste.add("SoWi: " + aktuell.get(11) + "/" + ziel.get(11));
                    }
                }
                break;
            case "Stundenplan":
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                ArrayList<String> plan = lib.inhalt.get(2);
                switch (day) {
                    case Calendar.SUNDAY:
                        this.setTitle("Stundenplan - Sonntag");
                        dow = "Sonntag";
                        break;
                    case Calendar.MONDAY:
                        this.setTitle("Stundenplan - Montag");
                        dow = "Montag";
                        break;
                    case Calendar.TUESDAY:
                        this.setTitle("Stundenplan - Dienstag");
                        dow = "Dienstag";
                        break;
                    case Calendar.WEDNESDAY:
                        this.setTitle("Stundenplan - Mittwoch");
                        dow = "Mittwoch";
                        break;
                    case Calendar.THURSDAY:
                        this.setTitle("Stundenplan - Donnerstag");
                        dow = "Donnerstag";
                        break;
                    case Calendar.FRIDAY:
                        this.setTitle("Stundenplan - Freitag");
                        dow = "Freitag";
                        break;
                    case Calendar.SATURDAY:
                        this.setTitle("Stundenplan - Samstag");
                        dow = "Samstag";
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
                int currentclass = ClassTimeHandler.getCurrentClass();
                if (currentclass == 1) {
                    endliste.set(0, endliste.get(0) + "   <------");
                } else if (currentclass == 2) {
                    endliste.set(1, endliste.get(1) + "   <------");
                } else if (currentclass == 3) {
                    endliste.set(2, endliste.get(2) + "   <------");
                } else if (currentclass == 4) {
                    endliste.set(3, endliste.get(3) + "   <------");
                } else if (currentclass == 5) {
                    endliste.set(4, endliste.get(4) + "   <------");
                } else if (currentclass == 6) {
                    endliste.set(5, endliste.get(5) + "   <------");
                } else if (currentclass == 7) {
                    endliste.set(6, endliste.get(6) + "   <------");
                } else if (currentclass == 8) {
                    endliste.set(7, endliste.get(7) + "   <------");
                } else if (currentclass == 9) {
                    endliste.set(8, endliste.get(8) + "   <------");
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
                    Toast.makeText(getApplicationContext(), "Klausuren: " + has, Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getApplicationContext(), "Hausaufgaben: " + has, Toast.LENGTH_LONG).show();
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
                } catch (IOException e) {
                    e.printStackTrace();

                }
                tb = gs.fromJson(en.toString(), Tagebuch.class);
                ArrayList<String> fach = null;
                boolean fachempty = false;
                ArrayList<Date> datum = null;
                boolean datumempty = false;
                ArrayList<String> text = null;
                boolean textempty = false;
                try {
                    fach = tb.fach;
                    fachempty = false;
                    datum = tb.datum;
                    datumempty = false;
                    text = tb.text;
                    textempty = false;
                } catch (NullPointerException e) {
                    endliste.add("Kein Eintrag!");
                    break;
                }


                if (fach.size() == datum.size() && datum.size() == text.size()) {
                    for (int i = 0; i < fach.size(); i++) {
                        if (datum.get(i) == null) datumempty = true;
                        if (fach.get(i).isEmpty()) fachempty = true;
                        if (text.get(i).isEmpty()) textempty = true;
                    }

                    if (fachempty || datumempty || textempty) {
                        Toast.makeText(getApplicationContext(), "FEHLER! Es befindet sich ein leeres Element in einer Liste!", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }

                    for (int i = 0; i < fach.size(); i++) {
                        DateFormat dateFormat = new SimpleDateFormat("dd.MM HH:mm");
                        Date date = datum.get(i);
                        endliste.add(fach.get(i) + " vom " + dateFormat.format(date) + ": " + text.get(i));
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "FEHLER! Die Listen sind unterschiedlich groß!", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            case "Referate":
                this.setTitle("Referatsübersicht");
                File ref = new File(this.getFilesDir(), "referate.json");
                Gson gso = new Gson();
                StringBuilder sb = new StringBuilder();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(ref));
                    String line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        line = br.readLine();
                    }
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
                refe = gso.fromJson(sb.toString(), Referate.class);
                ArrayList<String> reffach = null;
                ArrayList<Date> refdatum = null;
                ArrayList<String> refthema = null;
                try {
                    reffach = refe.fach;
                    refdatum = refe.datum;
                    refthema = refe.thema;

                    for (int i = 1; i < reffach.size(); i++) {
                        endliste.add(reffach.get(i) + ": " + refthema.get(i) + " für den  " + refdatum.get(i));
                    }
                } catch (NullPointerException e) {
                    endliste.add("Kein Eintrag!");
                    break;
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
                    for (int i = 0; i < liste.size(); i++) {
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

        if (this.getTitle().equals("Kommende Klausuren") || this.getTitle().equals("Hausaufgabenübersicht") || this.getTitle().equals("Referatsübersicht")) {
            if (this.lview.getCount() == 1 && this.lview.getItemAtPosition(0).toString().equals("Kein Eintrag!")) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.option_menu_no_edit, menu);
            } else {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.option_menu, menu);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        try {
            if (item.getTitle() == null) return false;
        } catch (NullPointerException e) {
            return false;
        }
        if (this.getTitle().toString().contains("Stundenplan")) return false;
        if (this.getTitle().equals("Lerntagebuch") && item.getTitle().toString().equals("Hinzufügen")) {
            if (java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.SUNDAY || java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.SATURDAY) {
                Toast.makeText(this, "Da heute kein Schultag ist, kannst du keinen Eintrag hinzufügen!", Toast.LENGTH_LONG).show();
                return false;
            }
            final String currentclass = ClassTimeHandler.getClassName(ClassTimeHandler.getCurrentClass(), java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK));
            if (currentclass.equals("none")) {
                Toast.makeText(this, "Da du gerade keinen Unterricht hast, kannst du auch keinen Eintrag verfassen!", Toast.LENGTH_LONG).show();
                return false;
            }
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            final EditText edittext = new EditText(this);
            alert.setMessage("Dein Eintrag für die " + ClassTimeHandler.getCurrentClass() + ". Stunde (" + currentclass + ")");
            alert.setTitle("Eintrag hinzufügen");

            alert.setView(edittext);

            alert.setPositiveButton("Hinzufügen", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String msg = edittext.getText().toString();
                    if (msg.isEmpty()) return;
                    DateFormat dateFormat = new SimpleDateFormat("dd.MM HH:mm");
                    Date date = new Date();
                    Gson gson = new Gson();
                    try {
                        tb.fach.add(currentclass);
                    } catch (NullPointerException e) {
                        tb = new Tagebuch();
                        tb.fach.add(currentclass);
                    }

                    try {
                        tb.datum.add(dateFormat.parse(dateFormat.format(date)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    tb.text.add(msg);
                    String string = gson.toJson(tb);
                    FileOutputStream outputStream;
                    try {
                        outputStream = openFileOutput("tagebuch.json", Context.MODE_PRIVATE);
                        outputStream.write(string.getBytes());
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finish();
                }

            });

            alert.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.cancel();
                }
            });

            alert.show();
            //Intent intent = new Intent(getApplicationContext(),DiaryActivity.class);
            //intent.putExtra(EXTRA_MESSAGE, this.getTitle());
            //startActivity(intent);
            //finish();
            return false;
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
                                if (currentitem.contains(lib.inhalt.get(3).get(j)) && currentitem.contains(lib.inhalt.get(6).get(j)) && currentitem.contains(lib.inhalt.get(5).get(j))) {
                                    lib.inhalt.get(3).remove(j);
                                    lib.inhalt.get(6).remove(j);
                                    lib.inhalt.get(5).remove(j);
                                }
                            }
                        } else if (this.getTitle().equals("Referatsübersicht")) {
                            for (int j = 0; j < refe.thema.size(); j++) {
                                String currentitem = lview.getItemAtPosition(i).toString();
                                if (currentitem.contains(refe.thema.get(j)) && currentitem.contains(refe.datum.get(j).toString()) && currentitem.contains(refe.fach.get(j))) {
                                    refe.thema.remove(j);
                                    refe.datum.remove(j);
                                    refe.fach.remove(j);
                                }
                            }
                            writeChanges();
                        }
                    }
                }
                    for (int i = checkedElements.size() - 1; i >= 0; i--) {
                        endliste.remove(checkedElements.get(i));
                    }
                    if (checkedElements.size() > 0 && endliste.size() == 0) {
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
                Intent intentm = new Intent(getApplicationContext(), EditActivity.class);
                intentm.putExtra(EXTRA_MESSAGE, this.getTitle());
                startActivity(intentm);
                finish();
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

    @Override
    protected void onResume() {
        super.onResume();
        if (endliste != null) {
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
            } catch (IOException e) {
                e.printStackTrace();

            }
            lib = gson.fromJson(end.toString(), Library.class);
            switch (getTitle().toString()) {
                case "Kommende Klausuren":
                    ArrayList<String> klausuren = lib.inhalt.get(3);
                    ArrayList<String> datenklausuren = lib.inhalt.get(6);
                    ArrayList<String> datumklausuren = lib.inhalt.get(5);
                    if (klausuren.size() == datenklausuren.size() && datenklausuren.size() == datumklausuren.size()) {
                        endliste = new ArrayList<>();
                        for (int i = 1; i < klausuren.size(); i++) {
                            endliste.add(klausuren.get(i) + " am " + datumklausuren.get(i) + " in " + datenklausuren.get(i));
                        }
                        adapter.notifyDataSetChanged();
                    }
                    break;
                case "Hausaufgabenübersicht":
                    ArrayList<String> hausaufgaben = lib.inhalt.get(4);
                    ArrayList<String> datenhausaufgaben = lib.inhalt.get(8);
                    ArrayList<String> datumhausaufgaben = lib.inhalt.get(7);
                    if (hausaufgaben.size() == datenhausaufgaben.size() && datenhausaufgaben.size() == datumhausaufgaben.size()) {
                        endliste = new ArrayList<>();
                    }
                        if (hausaufgaben.size() > 1) {
                            for (int i = 1; i < hausaufgaben.size(); i++) {
                                endliste.add(hausaufgaben.get(i) + " für den " + datumhausaufgaben.get(i) + ": " + datenhausaufgaben.get(i));
                            }
                        } else {
                            endliste.add("Kein Eintrag!");
                        }
                    adapter.notifyDataSetChanged();
                    break;

            }
        }
    }
}
