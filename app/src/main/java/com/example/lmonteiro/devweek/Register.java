package com.example.lmonteiro.devweek;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Register extends AppCompatActivity {
    private Button register;
    private EditText name;
    private EditText number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.register = (Button)findViewById(R.id.register);
        this.name = (EditText)findViewById(R.id.name);
        this.number = (EditText)findViewById(R.id.number);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser("user_data.dat");
                toastIt(getUser()[1]);
            }
        });
    }

    void registerUser(String filename){
        try {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + filename);
            file.createNewFile();
            if (file.exists()) {
                OutputStream fo = new FileOutputStream(file);
                fo.write(name.getText().toString().getBytes());
                fo.write("\n".getBytes());
                fo.write(number.getText().toString().getBytes());
                fo.close();
            }
        }catch (Exception e){

        }
    }

    void toastIt(String text){
        Toast.makeText(getApplication().getBaseContext(), text, Toast.LENGTH_LONG).show();
    }

    String[] getUser(){
        String[] user = new String[2];
        try {
            FileReader fr = new FileReader(Environment.getExternalStorageDirectory() + File.separator + "user_data.dat");
            BufferedReader br = new BufferedReader(fr);
            user[0] = br.readLine();
            user[1] = br.readLine();
        }catch(Exception e){

        }
        return user;
    }
}
