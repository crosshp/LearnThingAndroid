package com.learn_thing.learnthingandroid.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    int idMethodic = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        nextButton = (Button) findViewById(R.id.action_button);
        radioGroup = (RadioGroup) findViewById(R.id.groupButton);
        questionText = (TextView) findViewById(R.id.questionText);
        answer1Button = (RadioButton) findViewById(R.id.answer1Button);
        answer2Button = (RadioButton) findViewById(R.id.answer2Button);
        answer3Button = (RadioButton) findViewById(R.id.answer3Button);
        answer4Button = (RadioButton) findViewById(R.id.answer4Button);
        idMethodic = getIntent().getIntExtra("id", 1);
        testQuestions = getQuestions();
        testQuestions = getQuestionsByID(idMethodic, testQuestions);
        Log.e("Size", String.valueOf(testQuestions.size()));
        // testQuestions = getQuestions();

        if (testQuestions == null) finish();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Test");


        checkNextQuestion(currentQuestion);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        if (position == testQuestions.size() + 1) {
            Toast.makeText(activity, "Тест успішно пройдений!", Toast.LENGTH_LONG).show();
            testAdapter.close();
            int result = calculateResult();
            SharedPreferences.Editor editor = getSharedPreferences("result", MODE_PRIVATE).edit();
            SharedPreferences sharedPreferences = getSharedPreferences("result", MODE_PRIVATE);
            int number = sharedPreferences.getInt("numberOfTry", 1);
            float resultPref = sharedPreferences.getFloat("result", 1);
            if (result > 5) {
                editor.putFloat("result", 5 + resultPref);
            } else {
                editor.putFloat("result", result + resultPref);
            }
            editor.putInt("numberOfTry", number + 1);
            editor.commit();
            Toast.makeText(activity,"Ваш результат:" +String.valueOf(result), Toast.LENGTH_LONG).show();
            finish();
            showMap();
            // Обработка следющего вопроса
        } else {
            TestQuestion testQuestion = testQuestions.get(position - 1);
            questionText.setText(testQuestion.getQuestion());
            radioGroup.setVisibility(View.VISIBLE);
            answer1Button.setText(testQuestion.getAnswer1());
            answer2Button.setText(testQuestion.getAnswer2());
            answer3Button.setText(testQuestion.getAnswer3());
            answer4Button.setText(testQuestion.getAnswer4());
        }
    }

    public int calculateResult() {
        int result = 0;
        for (int i = 0; i < testQuestions.size(); i++) {
            if (testQuestions.get(i).isOpenQuestion() == Integer.valueOf(answersMap.get(i + 1))) {
                result++;
            }
        }
        return result;
    }

    public void showMap() {
        String s = "";
        for (String values : answersMap.values()) {
            s += values + "\n";
            System.out.println(values);
        }
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

    public List<TestQuestion> getQuestionsByID(int id, List<TestQuestion> list) {
        List<TestQuestion> resultList = list;

        Log.e("ID", String.valueOf(id));
        switch (id) {
            case 1: {
                resultList = resultList.subList(0, 5);
                break;
            }
            case 2: {
                resultList = resultList.subList(5, 10);
                break;
            }
            case 3: {
                resultList = resultList.subList(10, 15);
                break;
            }
            case 4: {
                resultList = resultList.subList(15, 20);
                break;
            }
            case 5: {
                resultList = resultList.subList(20, 25);
                break;
            }
            case 6: {
                resultList = resultList.subList(20, 25);
                break;
            }
            case 7: {
                resultList = null;
                break;
            }
            default:
                break;
        }
        return resultList;
    }
}
