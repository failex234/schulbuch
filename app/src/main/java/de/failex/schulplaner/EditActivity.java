package de.failex.schulplaner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Locale;

import static de.failex.schulplaner.ItemActivity.EXTRA_MESSAGE;

public class EditActivity extends AppCompatActivity {

    boolean[] mItemState;
    Library lib;
    Referate ref;
    String title = "";
    ArrayList<String> endliste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        endliste = new ArrayList<>();

        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);

        final EditText feld = (EditText) findViewById(R.id.editText5);
        final EditText datum = (EditText) findViewById(R.id.editText7);
        final EditText fach = (EditText) findViewById(R.id.fachdiary);

        fach.requestFocus();
        if (message.equals("Hausaufgabenübersicht")) {
            this.setTitle("Hinzufügen - Hausaufgaben");
            feld.setHint("Aufgabe");
            datum.setHint("Fällig am");
            title = "HA";
        } else if (message.equals("Kommende Klausuren")) {
            this.setTitle("Hinzufügen - Klausuren");
            feld.setHint("Raum");
            datum.setHint("Datum");
            title = "KL";
        } else if (message.equals("Referatsübersicht")) {
            this.setTitle("Hinzufügen - Referat");
            feld.setHint("Aufgabe");
            datum.setHint("Fällig am");
            title = "RE";
        }

        Button add = (Button) findViewById(R.id.adddiarybutton);


        File file = new File(this.getFilesDir(), "config.json");
        Gson gson = new Gson();
        final StringBuilder end = new StringBuilder();
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

        File file1 = new File(this.getFilesDir(), "referate.json");
        Gson gson1 = new Gson();
        final StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file1));
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

        if (title.equals("KL") || title.equals("HA")) {
            lib = gson.fromJson(end.toString(), Library.class);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fach.getText().toString().isEmpty() || datum.getText().toString().isEmpty() || feld.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Bitte alle Felder ausfüllen!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    for (int i = 0; i < endliste.size(); i++) {
                        if (endliste.get(i).toString().toLowerCase().equals((fach.getText().toString() + " am " + datum.getText().toString() + " in " + feld.getText().toString()).toLowerCase()) || endliste.get(i).toString().toLowerCase().equals((datum.getText().toString() + " " + fach.getText().toString() + ": " + feld.getText().toString()).toLowerCase())) {
                            Toast.makeText(getApplicationContext(), "Dieses Element ist bereits vorhanden in der Liste!", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    if (title.equals("KL")) {
                        Date date;
                        DateFormat format;

                        String[] datecheck = datum.getText().toString().split(".");

                        if (datecheck.length != 2) {
                            Toast.makeText(getApplicationContext(), "Du hast ein ungültiges Datum angegeben!", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (Integer.parseInt(datecheck[1]) > 12) {
                            Toast.makeText(getApplicationContext(), "Diesen Monat gibt es nicht! Bitte überprüfe deine Eingabe", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (Integer.parseInt(datecheck[0]) > 31) {
                            Toast.makeText(getApplicationContext(), "Diesen Tag gibt es nicht! Bitte überprüfe deine Eingabe", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (Integer.parseInt(datecheck[0]) > 29 && Integer.parseInt(datecheck[1]) == 2) {
                            Toast.makeText(getApplicationContext(), "Diesen Tag gibt es nicht! Bitte überprüfe deine Eingabe", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            format = new SimpleDateFormat("dd.MM", Locale.GERMAN);
                            date = format.parse(datum.getText().toString());
                        } catch (ParseException e) {
                            Toast.makeText(getApplicationContext(), "Bitte Datum in Format TT.MM angeben!", Toast.LENGTH_LONG).show();
                            return;
                        }
                        lib.inhalt.get(3).add(fach.getText().toString());
                        lib.inhalt.get(5).add(format.format(date));
                        lib.inhalt.get(6).add(feld.getText().toString());
                    } else if (title.equals("HA")) {
                        Date date;
                        DateFormat format;
                        try {
                            format = new SimpleDateFormat("dd.MM", Locale.GERMAN);
                            date = format.parse(datum.getText().toString());
                        } catch (ParseException e) {
                            Toast.makeText(getApplicationContext(), "Bitte Datum in Format TT.MM angeben!", Toast.LENGTH_LONG).show();
                            return;
                        }
                        lib.inhalt.get(4).add(fach.getText().toString());
                        lib.inhalt.get(7).add(format.format(date));
                        lib.inhalt.get(8).add(feld.getText().toString());
                    }
                    fach.setText("");
                    datum.setText("");
                    feld.setText("");
                    fach.requestFocus();
                    writeChanges();

                }
            });
        } else if (title.equals("RE")) {
            ref = gson.fromJson(sb.toString(), Referate.class);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fach.getText().toString().isEmpty() || datum.getText().toString().isEmpty() || feld.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Bitte alle Felder ausfüllen!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    for (int i = 0; i < endliste.size(); i++) {
                        if (endliste.get(i).toString().toLowerCase().equals((fach.getText().toString() + " am " + datum.getText().toString() + " in " + feld.getText().toString()).toLowerCase()) || endliste.get(i).toString().toLowerCase().equals((datum.getText().toString() + " " + fach.getText().toString() + ": " + feld.getText().toString()).toLowerCase())) {
                            Toast.makeText(getApplicationContext(), "Dieses Element ist bereits vorhanden in der Liste!", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    Date date;
                    DateFormat format;
                    try {
                        format = new SimpleDateFormat("dd.MM", Locale.GERMAN);
                        date = format.parse(datum.getText().toString());
                    } catch (ParseException e) {
                        Toast.makeText(getApplicationContext(), "Bitte Datum in Format TT.MM angeben!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    ref.fach.add(fach.getText().toString());
                    ref.datum.add(date);
                    ref.thema.add(feld.getText().toString());
                    fach.setText("");
                    datum.setText("");
                    feld.setText("");
                    fach.requestFocus();
                    writeChanges();

                }
            });
        }
        fach.setFocusable(true);
    }

    public void writeChanges() {
        if (title.equals("HA") || title.equals("KL")) {
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
        } else if (title.equals("RE")) {
            Gson gson = new Gson();
            String string = gson.toJson(ref);
            FileOutputStream outputStream;
            try {
                outputStream = openFileOutput("referate.json", Context.MODE_PRIVATE);
                outputStream.write(string.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
