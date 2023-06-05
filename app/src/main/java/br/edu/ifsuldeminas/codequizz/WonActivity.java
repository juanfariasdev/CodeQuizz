package br.edu.ifsuldeminas.codequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WonActivity extends AppCompatActivity {

    int correct, wrong;
    TextView correctValue, wrongValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        correctValue = findViewById(R.id.correctValue);
        wrongValue = findViewById(R.id.wrongValue);

        correct = getIntent().getIntExtra("correct", 0);
        wrong = getIntent().getIntExtra("wrong", 0);

        correctValue.setText(String.valueOf(correct));
        wrongValue.setText(String.valueOf(wrong));
    }
}
