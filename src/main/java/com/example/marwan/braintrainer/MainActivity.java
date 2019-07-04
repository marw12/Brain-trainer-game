package com.example.marwan.braintrainer;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView questionTextView;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    int correctAnswerLocation;
    TextView resultTextView;
    int score=0;
    int numberOfQ=0;
    TextView scoreTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    public void playAgain(View view){
        score=0;
        numberOfQ=0;
        timerTextView.setText("0:30");
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQ));

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long i) {
                timerTextView.setText(String.valueOf((i/1000)+"s"));

            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                mplayer.start();
                playAgainButton.setVisibility(View.VISIBLE);


            }
        }.start();


    }

    public void chooseAnswer(View view){

        if(Integer.toString(correctAnswerLocation).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;

        }else{
            resultTextView.setText("Wrong");
        }
        numberOfQ++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQ));
        newQuestion();

    }

    public void newQuestion(){
        Random r=new Random();
        int a=r.nextInt(51);
        int b=r.nextInt(51);

        questionTextView.setText(Integer.toString(a)+ " + " + Integer.toString(b));

        correctAnswerLocation=r.nextInt(4);

        answers.clear();

        for (int i=0;i<=4;i++){

            if(i==correctAnswerLocation){
                answers.add(a+b);
            }else{
                int wrongAnswer=r.nextInt(100);

                while(wrongAnswer==a+b){
                    wrongAnswer=r.nextInt(100);
                }
                answers.add(wrongAnswer);
            }

        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }



    public void start(View view){
        goButton.setVisibility(view.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
        gameLayout.setVisibility(View.VISIBLE);

    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton=findViewById(R.id.goButton);
        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        button3= (Button) findViewById(R.id.button3);
        button4= (Button) findViewById(R.id.button4);
        resultTextView=(TextView) findViewById(R.id.resultTextView);
        scoreTextView= (TextView) findViewById(R.id.scoreTextView);
        questionTextView=(TextView) findViewById(R.id.questionTextView);
        timerTextView= (TextView) findViewById(R.id.timerTextView);
        playAgainButton= (Button) findViewById(R.id.playAgainButton);
        gameLayout=(ConstraintLayout) findViewById(R.id.gameLayout);


        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);



    }
}
