package de.failex.schulplaner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "de.failex.felixdemo.MESSAGE";
    Library lib;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView lview = (ListView) findViewById(R.id.lview);
        gson = new Gson();
        File file = new File(this.getFilesDir(), "config.json");
        File tbuch = new File(this.getFilesDir(), "tagebuch.json");

        if (!tbuch.exists()) {
            createTagebuch();
        }

        if (!file.exists()) {
            this.createConfig();
        } else {
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
            System.out.println(lib.name);

        }
        ArrayList<String> themen = new ArrayList<>();
        for (int i = 0; i < lib.inhalt.size(); i++) {
            if (!themen.contains(lib.inhalt.get(i).get(0))) {
                if (lib.inhalt.get(i).get(0).equals("Datum-Klausuren") || lib.inhalt.get(i).get(0).equals("Daten-Klausuren") || lib.inhalt.get(i).get(0).equals("Datum-Hausaufgaben") || lib.inhalt.get(i).get(0).equals("Daten-Hausaufgaben")) {
                } else {
                    themen.add(lib.inhalt.get(i).get(0));
                }
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, themen);
        lview.setAdapter(adapter);
        this.setTitle(R.string.title);


        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View v, int arg2,
                                    long arg3) {
                Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
                String message = lview.getItemAtPosition(arg2).toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);

            }
        });

    }

    public void createConfig() {
        lib = new Library();
        lib.name = "Schule";
        List<ArrayList<String>> list1 = new ArrayList<>();
        ArrayList<String> notenaktuell = new ArrayList<>();
        notenaktuell.add("Noten");
        notenaktuell.add("6"); //Religion
        notenaktuell.add("8"); //Päda
        notenaktuell.add("10"); //Sport
        notenaktuell.add("7"); //Englisch
        notenaktuell.add("12"); //Informatik
        notenaktuell.add("5"); //Geschichte
        notenaktuell.add("5"); //Mathe
        notenaktuell.add("6"); //Deutsch
        notenaktuell.add("11"); //Musik
        notenaktuell.add("7"); //Bio
        notenaktuell.add("9"); //sowi
        ArrayList<String> notenziel = new ArrayList<>();
        notenziel.add("Noten");
        notenziel.add("15"); //Religion
        notenziel.add("15"); //Päda
        notenziel.add("15"); //Sport
        notenziel.add("15"); //Englisch
        notenziel.add("15"); //Informatik
        notenziel.add("15"); //Geschichte
        notenziel.add("15"); //Mathe
        notenziel.add("15"); //Deutsch
        notenziel.add("15"); //Musik
        notenziel.add("15"); //Bio
        notenziel.add("15"); //sowi
        ArrayList<String> stundenplan = new ArrayList<>();
        stundenplan.add("Stundenplan");
        stundenplan.add("Montag");
        stundenplan.add("Religion");
        stundenplan.add("Religion");
        stundenplan.add("Pädagogik");
        stundenplan.add("Pädagogik");
        stundenplan.add("Sport");
        stundenplan.add("Sport");
        stundenplan.add("Englisch");
        stundenplan.add("Informatik");
        stundenplan.add("Geschichte");
        stundenplan.add("Dienstag");
        stundenplan.add("Mathematik");
        stundenplan.add("Mathematik");
        stundenplan.add("Englisch");
        stundenplan.add("Englisch");
        stundenplan.add("Deutsch");
        stundenplan.add("Deutsch");
        stundenplan.add("Freistunde");
        stundenplan.add("Musik");
        stundenplan.add("Frei");
        stundenplan.add("Mittwoch");
        stundenplan.add("Musik");
        stundenplan.add("Musik");
        stundenplan.add("Geschichte");
        stundenplan.add("Geschichte");
        stundenplan.add("Biologie");
        stundenplan.add("Biologie");
        stundenplan.add("Freistunde");
        stundenplan.add("Mathematik");
        stundenplan.add("Pädagogik");
        stundenplan.add("Donnerstag");
        stundenplan.add("Informatik");
        stundenplan.add("Informatik");
        stundenplan.add("Geschichte");
        stundenplan.add("Geschichte");
        stundenplan.add("Sport");
        stundenplan.add("SoWi");
        stundenplan.add("Deutsch");
        stundenplan.add("Freistunde");
        stundenplan.add("Biologie");
        stundenplan.add("Freitag");
        stundenplan.add("Frei");
        stundenplan.add("SoWi");
        stundenplan.add("SoWi");
        stundenplan.add("Englisch");
        stundenplan.add("Englisch");
        stundenplan.add("Freistunde");
        stundenplan.add("Religion");
        stundenplan.add("Frei");
        stundenplan.add("Frei");

        ArrayList<String> klausuren = new ArrayList<>();
        klausuren.add("Klausuren");
        ArrayList<String> hausaufgaben = new ArrayList<>();
        hausaufgaben.add("Hausaufgaben");

        ArrayList<String> datumklausuren = new ArrayList<>();
        datumklausuren.add("Datum-Klausuren");
        ArrayList<String> datenklausuren = new ArrayList<>();
        datenklausuren.add("Daten-Klausuren");

        ArrayList<String> datumhausaufgaben = new ArrayList<>();
        datumhausaufgaben.add("Datum-Hausaufgaben");
        ArrayList<String> datenhausuafgaben = new ArrayList<>();
        datenhausuafgaben.add("Daten-Hausaufgaben");

        ArrayList<String> tagebuch = new ArrayList<>();
        tagebuch.add("Lerntagebuch");


        lib.inhalt = list1;
        lib.inhalt.add(notenaktuell); //Index 0
        lib.inhalt.add(notenziel); //Index 1
        lib.inhalt.add(stundenplan); //Index 2
        lib.inhalt.add(klausuren); //Index 3
        lib.inhalt.add(hausaufgaben); //Index 4
        lib.inhalt.add(datumklausuren); //Index 5
        lib.inhalt.add(datenklausuren); //Index 6
        lib.inhalt.add(datumhausaufgaben); //Index 7
        lib.inhalt.add(datenhausuafgaben); //Index 8
        lib.inhalt.add(tagebuch); //Index 9
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
        createTagebuch();
    }

    public void createTagebuch() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM HH:mm");
        Date date = new Date();
        Tagebuch tb = new Tagebuch();
        Gson gson = new Gson();

        tb.fach.add("Mathe");
        try {
            tb.datum.add(dateFormat.parse(dateFormat.format(date)));
        }
        catch(ParseException e) {
            e.printStackTrace();
        }
        tb.text.add("Heute haben wir über Ortsvektoren gesprochen.");
        String string = gson.toJson(tb);
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput("tagebuch.json", Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
