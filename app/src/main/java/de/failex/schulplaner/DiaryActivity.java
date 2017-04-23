package de.failex.schulplaner;

import android.content.Context;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiaryActivity extends AppCompatActivity {

    Tagebuch lib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        this.setTitle("Lerntagebuch");

        Button addtodiary = (Button) findViewById(R.id.adddiarybutton);
        final EditText fach = (EditText) findViewById(R.id.fachdiary);
        final EditText msg = (EditText) findViewById(R.id.msgdiary);

        File file = new File(this.getFilesDir(), "tagebuch.json");
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
        }
        catch(IOException e) {
            e.printStackTrace();

        }

        lib = gson.fromJson(end.toString(), Tagebuch.class);

        addtodiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (msg.getText().toString().isEmpty() || fach.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Bitte alle Felder ausfüllen!", Toast.LENGTH_LONG).show();
               } else {
                    lib.fach.add(fach.getText().toString());
                lib.text.add(msg.getText().toString());
                lib.datum.add(new Date());
                writeChanges();
                finish();
            }
            }
        });
    }

    public void writeChanges() {
        Gson gson = new Gson();
        String string = gson.toJson(lib);
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
