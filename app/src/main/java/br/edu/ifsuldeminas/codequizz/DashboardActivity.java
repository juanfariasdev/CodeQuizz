package br.edu.ifsuldeminas.codequizz;

import static br.edu.ifsuldeminas.codequizz.MainActivity.listOfQuestions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;

    int timerValue = 20;
    ProgressBar progressBar;

    List<ModelClass> allQuestionsList;

    ModelClass modelClass;

    int index=0;
    TextView card_question, option_a,option_b,option_c,option_d;
    CardView card_option_a,card_option_b,card_option_c,card_option_d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Hooks();



        allQuestionsList=listOfQuestions;
        Collections.shuffle(allQuestionsList);
        modelClass=listOfQuestions.get(index);

        setAllData();

        countDownTimer=new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long l) {
                timerValue=timerValue-1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                Dialog dialog=new Dialog(DashboardActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

                dialog.setContentView(R.layout.timer_out_dialog);

                dialog.findViewById(R.id.tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DashboardActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });



                dialog.show();
            }
        }.start();
    }

    private void setAllData() {
        card_question.setText(modelClass.getQuestion());
        option_a.setText(modelClass.getoA());
        option_b.setText(modelClass.getoB());
        option_c.setText(modelClass.getoC());
        option_d.setText(modelClass.getoD());
    }

    private void Hooks() {
        progressBar=findViewById(R.id.quiz_timer);
        card_question = findViewById(R.id.quiz_question);
        option_a = findViewById(R.id.quiz_option_a);
        option_b = findViewById(R.id.quiz_option_b);
        option_c = findViewById(R.id.quiz_option_c);
        option_d = findViewById(R.id.quiz_option_d);

        card_option_a = findViewById(R.id.card_option_a);
        card_option_b = findViewById(R.id.card_option_b);
        card_option_c = findViewById(R.id.card_option_c);
        card_option_d = findViewById(R.id.card_option_d);
    }
}