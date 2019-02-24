package ph.edu.dlsu.ian_ona.habify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {
    private List<Question> questions;
    private int current;
    private TextView question;
    private Spinner choices;
    private EditText textInput;
    private Button previousBtn, nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        question = findViewById(R.id.questionTextView);
        choices = findViewById(R.id.choicesSpinner);
        previousBtn = findViewById(R.id.previousBtn);
        nextBtn = findViewById(R.id.nextBtn);
        textInput = findViewById(R.id.plaintextInput);
        choices.setVisibility(View.INVISIBLE);
        textInput.setVisibility(View.INVISIBLE);

        choices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        questions = new ArrayList<>();
        initializeQuestions();
        current = 0;

        updateQuestion();
    }



    public void updateQuestion() {
        question.setText(questions.get(current).getQuestion());
        // Application of the Array to the Spinner
        choices.setAdapter(null);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, questions.get(current).getChoices());
        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spinner_item); // The drop down view
        choices.setAdapter(spinnerArrayAdapter);

        if (current == 0) {
            previousBtn.setVisibility(View.INVISIBLE);
        } else {
            previousBtn.setVisibility(View.VISIBLE);
        }

        if (current == questions.size() - 1) {
            nextBtn.setText("Submit");
        } else {
            nextBtn.setText("Next");
        }

        Question cur = questions.get(current);
        if (cur.getType().equalsIgnoreCase("INPUT") || cur.getType().equalsIgnoreCase("AGE")) {
            textInput.setVisibility(View.VISIBLE);
            choices.setVisibility(View.INVISIBLE);
            if (cur.getAnswer() != null) {
                textInput.setText(cur.getAnswer());
            } else {
                textInput.setText("");
            }
        } else {
            textInput.setVisibility(View.INVISIBLE);
            choices.setVisibility(View.VISIBLE);
            if (cur.getAnswer() != null) {
                int position = spinnerArrayAdapter.getPosition(cur.getAnswer());
                choices.setSelection(position);
            }
        }


    }

    public void next(View view) {
        Question cur = questions.get(current);
        if (cur.getType().equalsIgnoreCase("INPUT") || cur.getType().equalsIgnoreCase("AGE")) {
            cur.setAnswer(textInput.getText().toString());
        } else {
            cur.setAnswer(choices.getSelectedItem().toString());
            int index = -1;
            for (String ans : cur.getChoices()) {
                if (ans.equalsIgnoreCase(cur.getAnswer()))
                    index = cur.getChoices().indexOf(ans);
            }
            cur.setScore(cur.getScores().get(index));
        }

        if (current == questions.size() - 1) {
            SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            int mScore = 0;
            int sScore = 0;
            int bScore = 0;
            for (Question question : questions) {
                String type = question.getType();
                switch (type) {
                    case "INPUT":
                        editor.putString(getString(R.string.userPref), question.getAnswer());
                        break;
                    case "GENERAL":
                        if (question.getAnswer().equalsIgnoreCase("Body"))
                            bScore += 10;
                        else if (question.getAnswer().equalsIgnoreCase("Soul"))
                            sScore += 10;
                        else if (question.getAnswer().equalsIgnoreCase("Mind"))
                            mScore += 10;
                        break;
                    case "B":
                        bScore += question.getScore();
                        break;
                    case "M":
                        mScore += question.getScore();
                        break;
                    case "S":
                        sScore += question.getScore();
                        break;
                    default:
                        break;
                }
            }

            editor.putInt(getString(R.string.bodyScorePref), bScore);
            editor.putInt(getString(R.string.mindScorePref), mScore);
            editor.putInt(getString(R.string.spiritScorePref), sScore);
            editor.putInt(getString(R.string.userScorePref), 0);
            editor.commit();

            Intent intent = new Intent(FormActivity.this, DashboardActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
            return;
        }

        current++;
        updateQuestion();

    }

    public void previous(View view) {
        current--;
        updateQuestion();
    }

    public void initializeQuestions() {
        List choices = new ArrayList<>();
        List scores = new ArrayList<>();
        Question q0_1 = new Question("What is your name?", choices, scores, "INPUT");

        choices = new ArrayList<>();
        scores = new ArrayList<>();
        Question q0_2 = new Question("How old are you?", choices, scores, "AGE");

        choices = new ArrayList<>();
        scores = new ArrayList<>();
        choices.add("Body");
        choices.add("Mind");
        choices.add("Soul");
        scores.add(10);
        scores.add(10);
        scores.add(10);
        Question q1 = new Question("What do you value the most?", choices, scores, "GENERAL");

        // PHYSICAL WELLNESS
        choices = new ArrayList<>();
        scores = new ArrayList<>();
        choices.add("Outdoor activities");
        choices.add("Indoor activities");
        choices.add("A bit of both");
        scores.add(3);
        scores.add(1);
        scores.add(2);
        Question q2 = new Question("How would you describe the things you do in your free time?", choices, scores, "B");

        choices = new ArrayList<>();
        scores = new ArrayList<>();
        choices.add("I follow a strict diet");
        choices.add("I watch what I eat and try to eat as healthily as possible");
        choices.add("I eat whatever I want");
        scores.add(3);
        scores.add(2);
        scores.add(1);
        Question q3 = new Question("How would you define your eating habits?", choices, scores, "B");

        // SPIRITUAL WELLNESS
        choices = new ArrayList<>();
        scores = new ArrayList<>();
        choices.add("Yes");
        choices.add("Sometimes");
        choices.add("No");
        scores.add(1);
        scores.add(2);
        scores.add(3);
        Question q4 = new Question("Do you tend to overthink?", choices, scores, "S");

        choices = new ArrayList<>();
        scores = new ArrayList<>();
        choices.add("Yes");
        choices.add("Sometimes");
        choices.add("No");
        scores.add(1);
        scores.add(2);
        scores.add(3);
        Question q5 = new Question("Do you sometimes do something, and immediately regret it afterwards?", choices, scores, "S");

        choices = new ArrayList<>();
        scores = new ArrayList<>();
        choices.add("Yes");
        choices.add("Sometimes");
        choices.add("No");
        scores.add(1);
        scores.add(2);
        scores.add(3);
        Question q6 = new Question("Do you find yourself thinking about the “what-if’s” in life?", choices, scores, "S");

        choices = new ArrayList<>();
        scores = new ArrayList<>();
        choices.add("Alone with noone to bother me");
        choices.add("Working in a big group, the more the merrier");
        choices.add("Working in a small group, some extra hands to help out");
        scores.add(1);
        scores.add(3);
        scores.add(2);
        Question q7 = new Question("Describe your ideal work setting", choices, scores, "S");

        // INTELLECTUAL WELLNESS
        choices = new ArrayList<>();
        scores = new ArrayList<>();
        choices.add("Business oriented");
        choices.add("Technology oriented");
        choices.add("Art oriented");
        scores.add(2);
        scores.add(3);
        scores.add(1);
        Question q8 = new Question("How would you describe your interests?", choices, scores, "M");

        choices = new ArrayList<>();
        scores = new ArrayList<>();
        choices.add("Yes, I like to be prepared");
        choices.add("Sometimes, but I try not to dwell on it");
        choices.add("No, I live in the moment");
        scores.add(3);
        scores.add(2);
        scores.add(1);
        Question q9 = new Question("Do you think about your future often?", choices, scores, "M");

        choices = new ArrayList<>();
        scores = new ArrayList<>();
        choices.add("I am a fast learner");
        choices.add("I am a slow learner");
        scores.add(3);
        scores.add(1);
        Question q10 = new Question("How would you describe your learning process?", choices, scores, "M");

        questions.add(q0_1);
        questions.add(q0_2);
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);
        questions.add(q6);
        questions.add(q7);
        questions.add(q8);
        questions.add(q9);
        questions.add(q10);
    }

    class Question {
        String question;
        String answer;
        List<String> choices;
        List<Integer> scores;
        int score;
        String type;

        public Question(String question, List<String> choices, List<Integer> scores, String type) {
            this.question = question;
            this.choices = choices;
            this.scores = scores;
            this.type = type;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public List<String> getChoices() {
            return choices;
        }

        public void setChoices(List<String> choices) {
            this.choices = choices;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<Integer> getScores() {
            return scores;
        }

        public void setScores(List<Integer> scores) {
            this.scores = scores;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }




}
