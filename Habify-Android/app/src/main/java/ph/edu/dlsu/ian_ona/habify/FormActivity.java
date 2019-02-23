package ph.edu.dlsu.ian_ona.habify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {
    private List<Question> questions;
    private int current;
    private TextView question;
    private Spinner choices;
    private Button previousBtn, nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        question = findViewById(R.id.questionTextView);
        choices = findViewById(R.id.choicesSpinner);
        previousBtn = findViewById(R.id.previousBtn);
        nextBtn = findViewById(R.id.nextBtn);

        questions = new ArrayList<>();
        initializeQuestions();
        current = 0;

        updateQuestion();
    }

    public void updateQuestion(){
        question.setText(questions.get(current).getQuestion());
        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, questions.get(current).getChoices());
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        choices.setAdapter(spinnerArrayAdapter);

        if (current == 0) {
            previousBtn.setVisibility(View.INVISIBLE);
        } else {
            previousBtn.setVisibility(View.VISIBLE);
        }

        if (current == questions.size()-1){
            nextBtn.setText("Submit");
        } else {
            nextBtn.setText("Next");
        }
    }

    public void next(View view){
        Question cur = questions.get(current);
        cur.setAnswer(choices.getSelectedItem().toString());
        int index = -1;
        for (String ans:cur.getChoices()) {
            if (ans.equalsIgnoreCase(cur.getAnswer()))
                index = cur.getChoices().indexOf(ans);
        }
        cur.setScore(cur.getScores().get(index));

        if (current == questions.size()-1){
            Intent intent = new Intent(FormActivity.this, DashboardActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
            return;
        }

        current++;
        updateQuestion();

    }

    public void previous(View view){
        current--;
        updateQuestion();
    }

    public void initializeQuestions(){
        List<String> choices = new ArrayList<>();
        List<Integer> scores = new ArrayList<>();
        choices.add("Body");
        choices.add("Mind");
        choices.add("Soul");
        scores.add(10);
        scores.add(10);
        scores.add(10);
        Question q1 = new Question("What do you value the most?",choices,scores,"GENERAL");

        choices = new ArrayList<>();
        scores = new ArrayList<>();
        choices.add("Outdoor activities");
        choices.add("Indoor activities");
        choices.add("A bit of both");
        scores.add(3);
        scores.add(1);
        scores.add(2);
        Question q2 = new Question("How would you describe the things you do in your free time?",choices,scores,"B");

        questions.add(q1);
        questions.add(q2);
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
    }


}
