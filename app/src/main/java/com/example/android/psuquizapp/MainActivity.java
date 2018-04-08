package com.example.android.psuquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitAnswers(View view){
        /* Get Name */
        String name = getName(view);

        /* Calculate Score */
        Integer score = calculateScore(view);

        /* Create the result string */
        String resultString = getResultString(name, score);

        /* Show the toast with centered text*/
        Toast toast = Toast.makeText(this, resultString, Toast.LENGTH_LONG);
        TextView v = toast.getView().findViewById(android.R.id.message);
        if( v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }

    private String getName(View view) {
        /* Get Name from Text Box */
        EditText nameText = findViewById(R.id.name_field);
        String name = nameText.getText().toString();

        /* Replace with 'Anonymous' if no name was entered */
        if (Objects.equals(name, "")) {name = "Anonymous";}

        return name;
    }

    private Integer calculateScore(View view) {
        Integer score = 0;

        /* Question 1 */
        /* 1855 */
        EditText answerQ1 = findViewById(R.id.answer_q1);
        if(Objects.equals(answerQ1.getText().toString(), "1855")){ score += 1;}

        /* Question 2 */
        /* Ensure the first and last are the only ones checked for a correct answer */
        CheckBox answerQ2a = findViewById(R.id.answer_q2_a);
        CheckBox answerQ2b = findViewById(R.id.answer_q2_b);
        CheckBox answerQ2c = findViewById(R.id.answer_q2_c);
        CheckBox answerQ2d = findViewById(R.id.answer_q2_d);

        if (answerQ2a.isChecked() & !answerQ2b.isChecked() & !answerQ2c.isChecked() & answerQ2d.isChecked()) {
            score += 1;
        }

        /* Question 3 */
        /* Farmer's High School of Pennsylvania */
        RadioGroup answerQ3rbg = findViewById(R.id.answer_q3);
        RadioButton selected = answerQ3rbg.findViewById(answerQ3rbg.getCheckedRadioButtonId());
        if (selected != null) { // Selected id is null if no choice was made, so it isn't possible to get text
            String answerQ3 = (String) selected.getText();
            if (Objects.equals(answerQ3, "Farmer's High School of Pennsylvania")) {
                score += 1;
            }
        }

        /* Question 4 */


        return score;
    }

    private String getResultString(String name, Integer score) {
        String resultString =  name + ", your score is " + score + "!";

        /* Add a message based on the results */
        String comment;
        switch (score) {
            case 0:
                comment = "You're messing with me, right?";
                break;
            case 1:
                comment = "Eh, could be worse.";
                break;
            case 2:
                comment = "You must be from Pitt, I mean Akron.";
                break;
            case 3:
                comment = "You must be from Ohio.";
                break;
            case 4:
                comment = "You're only in it for the football.";
                break;
            case 5:
                comment = "You need to go study in the stacks.";
                break;
            case 6:
                comment = "You must be from a commonwealth campus.";
                break;
            case 7:
                comment = "The lion must be your BFF.";
                break;
            case 8:
                comment = "Are you in Schreyers?";
                break;
            case 9:
                comment = "You should apply to be a Lion Ambassador.";
                break;
            case 10:
                comment = "You must be in S&B.";
                break;
            default:
                comment = "Invalid score";
                break;
        }

        resultString += "\n" + comment;
        return resultString;
    }

}
