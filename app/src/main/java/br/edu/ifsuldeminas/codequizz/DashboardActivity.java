package br.edu.ifsuldeminas.codequizz;

import static br.edu.ifsuldeminas.codequizz.MainActivity.listOfQuestions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

    Button nextQuestion;

    int correctCount=0;
    int wrongCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Hooks();

        allQuestionsList=listOfQuestions;
        Collections.shuffle(allQuestionsList);
        modelClass=listOfQuestions.get(index);

        nextQuestion.setClickable(false);

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

        nextQuestion=findViewById(R.id.nextQuestion);

    }

    private void Correct(CardView cardOption){
        cardOption.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green_500));
        disableButton();
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctCount++;
                if(index<listOfQuestions.size()-1){
                    index++;
                    modelClass=listOfQuestions.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                }else{
                    GameWon();
                }
            }
        });



    }
    private void Wrong(CardView cardOption){
        cardOption.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red_500));
        disableButton();
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrongCount++;
                if(index<listOfQuestions.size()-1){
                    index++;
                    modelClass=listOfQuestions.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                }else{
                    GameWon();
                }
            }
        });

    }

    private void GameWon(){
        Intent intent=new Intent(DashboardActivity.this, WonActivity.class);
        intent.putExtra("correct", correctCount);
        intent.putExtra("wrong",wrongCount);
        startActivity(intent);
    }

    public void enableButton(){
        card_option_a.setClickable(true);
        card_option_b.setClickable(true);
        card_option_c.setClickable(true);
        card_option_d.setClickable(true);
    }
    public void disableButton(){
        card_option_a.setClickable(false);
        card_option_b.setClickable(false);
        card_option_c.setClickable(false);
        card_option_d.setClickable(false);
    }

    public void resetColor(){
        card_option_a.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        card_option_b.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        card_option_c.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        card_option_d.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        nextQuestion.setClickable(false);
    }

    public void OptionAClick(View view){
        if (modelClass.getoA().equals(modelClass.getAns())){
            Correct(card_option_a);
        }else{
            Wrong(card_option_a);
        }
    }
    public void OptionBClick(View view){
        if (modelClass.getoB().equals(modelClass.getAns())){
            Correct(card_option_b);
        }else{
            Wrong(card_option_b);
        }
    }

    public void OptionCClick(View view){
        if (modelClass.getoC().equals(modelClass.getAns())){
            Correct(card_option_c);
        }else{
            Wrong(card_option_c);
        }
    }

    public void OptionDClick(View view){

        if (modelClass.getoD().equals(modelClass.getAns())){
            Correct(card_option_d);
        }else{
            Wrong(card_option_d);
        }
    }




}