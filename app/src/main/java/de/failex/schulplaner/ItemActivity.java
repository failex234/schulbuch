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
                 try {
                     String currTime = new SimpleDateFormat("HH:mm").format(new Date());
                     String ersteStd = "08:00";
                     String ersteStdVrb = "08:45";
                     String zweiteStd = "08:50";
                     String zweiteStdVrb = "09:35";
                     String dritteStd = "09:40";
                     String dritteStdVrb = "10:25";
                     String vierteStd = "10:55";
                     String vierteStdVrb = "11:40";
                     String fuenfteStd = "11:45";
                     String fuenfteStdVrb = "12:30";
                     String sechsteStd = "12:35";
                     String sechsteStdVrb = "13:20";
                     String siebteStd = "13:40";
                     String siebteStdVrb = "14:25";
                     String achteStd = "14:30";
                     String achteStdVrb = "15:15";
                     String neunteStd = "15:15";
                     String neunteStdVrb = "16:00";

                     Date currTimed = new SimpleDateFormat("HH:mm").parse(currTime);
                     Date time1 = new SimpleDateFormat("HH:mm").parse(ersteStd);
                     Date time2 = new SimpleDateFormat("HH:mm").parse(ersteStdVrb);
                     Date timezweite = new SimpleDateFormat("HH:mm").parse(zweiteStd);
                     Date timezweitevrb = new SimpleDateFormat("HH:mm").parse(zweiteStdVrb);
                     Date timedritte = new SimpleDateFormat("HH:mm").parse(dritteStd);
                     Date timedrittevrb = new SimpleDateFormat("HH:mm").parse(dritteStdVrb);
                     Date timevierte = new SimpleDateFormat("HH:mm").parse(vierteStd);
                     Date timeviertevrb = new SimpleDateFormat("HH:mm").parse(vierteStdVrb);
                     Date timefuenfte = new SimpleDateFormat("HH:mm").parse(fuenfteStd);
                     Date timefuenftevrb = new SimpleDateFormat("HH:mm").parse(fuenfteStdVrb);
                     Date timesechste = new SimpleDateFormat("HH:mm").parse(sechsteStd);
                     Date timesechstevrb = new SimpleDateFormat("HH:mm").parse(sechsteStdVrb);
                     Date timesiebte = new SimpleDateFormat("HH:mm").parse(siebteStd);
                     Date timesiebtevrb = new SimpleDateFormat("HH:mm").parse(siebteStdVrb);
                     Date timeachte = new SimpleDateFormat("HH:mm").parse(achteStd);
                     Date timeachtevrb = new SimpleDateFormat("HH:mm").parse(achteStdVrb);
                     Date timeneunte = new SimpleDateFormat("HH:mm").parse(neunteStd);
                     Date timeneuntevrb = new SimpleDateFormat("HH:mm").parse(neunteStdVrb);



                     java.util.Calendar cal3 = java.util.Calendar.getInstance();
                     java.util.Calendar cal = java.util.Calendar.getInstance();
                     java.util.Calendar cal2 = java.util.Calendar.getInstance();
                     java.util.Calendar calzweite = java.util.Calendar.getInstance();
                     java.util.Calendar caldritte = java.util.Calendar.getInstance();
                     java.util.Calendar calvierte = java.util.Calendar.getInstance();
                     java.util.Calendar calfuenfte = java.util.Calendar.getInstance();
                     java.util.Calendar calsechste = java.util.Calendar.getInstance();
                     java.util.Calendar calsiebte = java.util.Calendar.getInstance();
                     java.util.Calendar calachte = java.util.Calendar.getInstance();
                     java.util.Calendar calneunte = java.util.Calendar.getInstance();
                     java.util.Calendar calzweitevrb = java.util.Calendar.getInstance();
                     java.util.Calendar caldrittevrb = java.util.Calendar.getInstance();
                     java.util.Calendar calviertevrb = java.util.Calendar.getInstance();
                     java.util.Calendar calfuenftevrb = java.util.Calendar.getInstance();
                     java.util.Calendar calsechstevrb = java.util.Calendar.getInstance();
                     java.util.Calendar calsiebtevrb = java.util.Calendar.getInstance();
                     java.util.Calendar calachtevrb = java.util.Calendar.getInstance();
                     java.util.Calendar calneuntevrb = java.util.Calendar.getInstance();



                     cal3.setTime(currTimed);
                     cal.setTime(time1);
                     cal2.setTime(time2);
                     calzweite.setTime(timezweite);
                     caldritte.setTime(timedritte);
                     calvierte.setTime(timevierte);
                     calfuenfte.setTime(timefuenfte);
                     calsechste.setTime(timesechste);
                     calsiebte.setTime(timesiebte);
                     calachte.setTime(timeachte);
                     calneunte.setTime(timeneunte);
                     calzweitevrb.setTime(timezweitevrb);
                     caldrittevrb.setTime(timedrittevrb);
                     calviertevrb.setTime(timeviertevrb);
                     calfuenftevrb.setTime(timefuenftevrb);
                     calsechstevrb.setTime(timesechstevrb);
                     calsiebtevrb.setTime(timesiebtevrb);
                     calachtevrb.setTime(timeachtevrb);
                     calneuntevrb.setTime(timeneuntevrb);



                     cal2.add(java.util.Calendar.DATE, 1);
                     cal3.add(java.util.Calendar.DATE, 1);
                     calzweite.add(java.util.Calendar.DATE, 1);
                     caldritte.add(java.util.Calendar.DATE, 1);
                     calvierte.add(java.util.Calendar.DATE, 1);
                     calfuenfte.add(java.util.Calendar.DATE, 1);
                     calsechste.add(java.util.Calendar.DATE, 1);
                     calsiebte.add(java.util.Calendar.DATE, 1);
                     calachte.add(java.util.Calendar.DATE, 1);
                     calneunte.add(java.util.Calendar.DATE, 1);
                     calzweitevrb.add(java.util.Calendar.DATE, 1);
                     caldrittevrb.add(java.util.Calendar.DATE, 1);
                     calviertevrb.add(java.util.Calendar.DATE, 1);
                     calfuenftevrb.add(java.util.Calendar.DATE, 1);
                     calsechstevrb.add(java.util.Calendar.DATE, 1);
                     calsiebtevrb.add(java.util.Calendar.DATE, 1);
                     calachtevrb.add(java.util.Calendar.DATE, 1);
                     calneuntevrb.add(java.util.Calendar.DATE, 1);


                     Date neu = cal3.getTime();

                     if (neu.after(cal.getTime()) && neu.before(cal2.getTime())) {
                         endliste.set(0, endliste.get(0) + "   <------");
                     } else if (neu.after(calzweite.getTime()) && neu.before(calzweitevrb.getTime())) {
                         endliste.set(1, endliste.get(1) + "   <------");
                     } else if (neu.after(caldritte.getTime()) && neu.before(caldrittevrb.getTime())) {
                         endliste.set(2, endliste.get(2) + "   <------");
                     } else if (neu.after(calvierte.getTime()) && neu.before(calviertevrb.getTime())) {
                         endliste.set(3, endliste.get(3) + "   <------");
                     } else if (neu.after(calfuenfte.getTime()) && neu.before(calfuenftevrb.getTime())) {
                         endliste.set(4, endliste.get(4) + "   <------");
                     } else if (neu.after(calsechste.getTime()) && neu.before(calsechstevrb.getTime())) {
                         endliste.set(5, endliste.get(5) + "   <------");
                     } else if (neu.after(calsiebte.getTime()) && neu.before(calsiebtevrb.getTime())) {
                         endliste.set(6, endliste.get(6) + "   <------");
                     } else if (neu.after(calachte.getTime()) && neu.before(calachtevrb.getTime())) {
                         endliste.set(7, endliste.get(7) + "   <------");
                     } else if (neu.after(calneunte.getTime()) && neu.before(calneuntevrb.getTime())) {
                         endliste.set(8, endliste.get(8) + "   <------");
                     }

                 }
                 catch(ParseException e) {
                     e.printStackTrace();
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
                boolean fachempty = false;
                ArrayList<Date> datum = tb.datum;
                boolean datumempty = false;
                ArrayList<String> text = tb.text;
                boolean textempty = false;

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

        if (this.getTitle().equals("Kommende Klausuren") || this.getTitle().equals("Hausaufgabenübersicht")) {
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
        }
        catch (NullPointerException e) {
            return false;
        }
        if (this.getTitle().toString().contains("Stundenplan")) return false;
        if (this.getTitle().equals("Lerntagebuch") && item.getTitle().toString().equals("Hinzufügen")) {
            Intent intent = new Intent(getApplicationContext(),DiaryActivity.class);
            intent.putExtra(EXTRA_MESSAGE, this.getTitle());
            startActivity(intent);
            finish();
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
}
