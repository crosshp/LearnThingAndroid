package com.learn_thing.learnthingandroid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.learn_thing.learnthingandroid.DataBase.TestAdapter;
import com.learn_thing.learnthingandroid.Entity.TestQuestion;
import com.learn_thing.learnthingandroid.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 20.02.2016.
 */
public class TestActivity extends AppCompatActivity {
    Button nextButton = null;
    RadioGroup radioGroup = null;
    TestActivity activity = this;
    Map<Integer, String> answersMap = new HashMap<>();
    List<TestQuestion> testQuestions = null;
    EditText editText = null;
    int currentQuestion = 1;
    TextView questionText = null;
    RadioButton answer1Button = null;
    RadioButton answer2Button = null;
    RadioButton answer3Button = null;
    RadioButton answer4Button = null;
    TestAdapter testAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        nextButton = (Button) findViewById(R.id.action_button);
        radioGroup = (RadioGroup) findViewById(R.id.groupButton);
        editText = (EditText) findViewById(R.id.responseText);
        questionText = (TextView) findViewById(R.id.questionText);
        answer1Button = (RadioButton) findViewById(R.id.answer1Button);
        answer2Button = (RadioButton) findViewById(R.id.answer2Button);
        answer3Button = (RadioButton) findViewById(R.id.answer3Button);
        answer4Button = (RadioButton) findViewById(R.id.answer4Button);
        testQuestions = getQuestions();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        checkNextQuestion(currentQuestion);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Откритий вопрос
                if (testQuestions.get(currentQuestion - 1).isOpenQuestion()) {
                    String text = editText.getText().toString();
                    if (text.length() != 0) {
                        answersMap.put(currentQuestion, text);
                        editText.setText("");
                        editText.setVisibility(View.INVISIBLE);
                        currentQuestion++;
                        checkNextQuestion(currentQuestion);
                    } else {
                        Toast.makeText(activity, "Заповніть поле!", Toast.LENGTH_LONG).show();
                    }
                    // Вопрос с вибором
                } else {
                    int id = radioGroup.getCheckedRadioButtonId();
                    switch (id) {
                        case -1: {
                            Toast.makeText(activity, "Оберіть один із варіантів!", Toast.LENGTH_LONG).show();
                            break;
                        }
                        case R.id.answer1Button: {
                            answersMap.put(currentQuestion, "1");
                            break;
                        }
                        case R.id.answer2Button: {
                            answersMap.put(currentQuestion, "2");
                            break;
                        }
                        case R.id.answer3Button: {
                            answersMap.put(currentQuestion, "3");
                            break;
                        }
                        case R.id.answer4Button: {
                            answersMap.put(currentQuestion, "4");
                            break;
                        }
                    }
                    if (id != -1) {
                        radioGroup.clearCheck();
                        currentQuestion++;
                        checkNextQuestion(currentQuestion);
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(activity, "Тест не пройдено!", Toast.LENGTH_LONG).show();
        testAdapter.close();
        finish();
    }

    public void checkNextQuestion(int position) {
        // End of Test
        if (position == testQuestions.size()) {
            Toast.makeText(activity, "Тест успішно пройдений!", Toast.LENGTH_LONG).show();
            testAdapter.close();
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            showMap();
            // Обработка следющего вопроса
        } else {
            TestQuestion testQuestion = testQuestions.get(position - 1);
            questionText.setText(testQuestion.getQuestion());
            if (testQuestion.isOpenQuestion()) {
                editText.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.INVISIBLE);
            } else {
                editText.setVisibility(View.INVISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                answer1Button.setText(testQuestion.getAnswer1());
                answer2Button.setText(testQuestion.getAnswer2());
                answer3Button.setText(testQuestion.getAnswer3());
                answer4Button.setText(testQuestion.getAnswer4());
            }
        }
    }

    public void showMap() {
        String s = "";
        for (String values : answersMap.values()) {
            s += values + "\n";
            System.out.println(values);
        }
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<TestQuestion> getQuestions() {
        testAdapter = new TestAdapter(activity);
        return testAdapter.getTestTable();
    }
}
