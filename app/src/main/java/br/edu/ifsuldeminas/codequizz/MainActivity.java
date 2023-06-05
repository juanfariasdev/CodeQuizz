package br.edu.ifsuldeminas.codequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ModelClass> listOfQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfQuestions = new ArrayList<>();

        listOfQuestions.add(new ModelClass("questaoD","a","b", "c","d", "d"));
        listOfQuestions.add(new ModelClass("questaoB","a","b", "c","d", "b"));
        listOfQuestions.add(new ModelClass("questaoA","a","b", "c","d", "a"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        }, 1500);
    }
}