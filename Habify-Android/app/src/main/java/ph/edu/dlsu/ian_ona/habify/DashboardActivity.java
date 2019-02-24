package ph.edu.dlsu.ian_ona.habify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView dateTimeText;
    private Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private ArrayList<Model> tasks;
    private ArrayList<Model> tempMind;
    private ArrayList<Model> tempSoul;
    private ArrayList<Model> tempPhysical;
//    private Model task;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().hide();

        manager = new LinearLayoutManager(this);
        adapter = new Adapter(this);

        recyclerView = findViewById(R.id.view);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        tasks = new ArrayList<>();
        tempMind = new ArrayList<>();
        tempSoul = new ArrayList<>();
        tempPhysical = new ArrayList<>();

        generateNow();
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);

        textView = findViewById(R.id.textView);
        String name = sharedPref.getString(getString(R.string.userPref), null);
        textView.setText("Welcome back, " + name);

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String dateString = sdf.format(date);
        dateTimeText = findViewById(R.id.dateTimeText);
        dateTimeText.setText(dateString);

    }

    public void generateTasks(View view){
       generateNow();
       adapter.refreshTasks(tasks);
    }

    public void generateNow(){
        tempMind = new ArrayList<>();
        tempPhysical = new ArrayList<>();
        tempSoul = new ArrayList<>();
        tasks = new ArrayList<>();

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);

        int userScore = sharedPref.getInt(getString(R.string.userScorePref), 0);
        int mindScore = sharedPref.getInt(getString(R.string.mindScorePref), 0);
        int bodyScore = sharedPref.getInt(getString(R.string.bodyScorePref), 0);
        int spiritScore = sharedPref.getInt(getString(R.string.spiritScorePref), 0);

        if(mindScore > bodyScore && mindScore > spiritScore){
            populatePhysical(3);
            populateSoul(3);
        } else if (bodyScore > mindScore && mindScore > spiritScore){
            populateMind(3);
            populateSoul(3);
        } else if (spiritScore > mindScore && spiritScore > bodyScore){
            populateMind(3);
            populatePhysical(3);
        } else {
            populateMind(4);
            populatePhysical(4);
            populateSoul(4);
        }
        adapter.refreshTasks(tasks);
    }

    public void generateOne(View view){
        populateAll();
        Model task = null;
        int randomNum = ThreadLocalRandom.current().nextInt(0, 4 + 1);
        int choice = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        switch (choice){
            case 1:
                task = tempMind.get(randomNum);
                break;
            case 2:
                task = tempSoul.get(randomNum);
                break;
            case 3:
                task = tempPhysical.get(randomNum);
                break;
        }
//        task = new Model(temp);
//        Model task = new Model("M", "Single", "02/24/2019");
        adapter.refreshOne(task);
    }

    public void populateMind(int limit){
        Model t1 = new Model("M", "Read 20 pages of a book you want to read", "02/28/2019");
        Model t2 = new Model("M", "Do some advanced studying for one lesson in one of your classes", "02/28/2019");
        Model t3 = new Model("M", "Read an article about something your interested in", "02/28/2019");
        Model t4 = new Model("M", "Memorize a fact on something you're interested in", "02/28/2019");
        Model t5 = new Model("M", "Review 1 lesson in one of your classes", "02/28/2019");

        tempMind.add(t1);
        tempMind.add(t2);
        tempMind.add(t3);
        tempMind.add(t4);
        tempMind.add(t5);

//        tasks.add(t1);
//        tasks.add(t2);
//        tasks.add(t3);

        for(int i = 0; i < limit-1; i++){
            tasks.add(tempMind.get(i));
            Log.d("ADDING MIND", "here" +tempMind.get(i));

        }

    }

    public void populateSoul(int limit){
        Model s1 = new Model("S", "Meditate for 5 minutes", "02/28/2019");
        Model s2 = new Model("S", "List down the things you are thankful for", "02/28/2019");
        Model s3 = new Model("S", "Give someone a hug", "02/28/2019");
        Model s4 = new Model("S", "Make someone smile today", "02/28/2019");
        Model s5 = new Model("S", "Hang out with a friend today", "02/28/2019");

        tempSoul.add(s1);
        tempSoul.add(s2);
        tempSoul.add(s3);
        tempSoul.add(s4);
        tempSoul.add(s5);

//        tasks.add(s1);
//        tasks.add(s2);
//        tasks.add(s3);

        for(int i = 0; i < limit-1; i++){
            tasks.add(tempSoul.get(i));
            Log.d("ADDING SOUL", "here" +tempSoul.get(i));
        }
    }

    public void populatePhysical(int limit){
        Model p1 = new Model("P", "Do 30 Sit-ups", "02/28/2019");
        Model p2 = new Model("P", "Take a 30 Minute Jog", "02/28/2019");
        Model p3 = new Model("P", "Do 50 Jumping Jacks", "02/28/2019");
        Model p4 = new Model("P", "Do 30 Push Ups", "02/28/2019");
        Model p5 = new Model("P", "Do 50 30 Squats", "02/28/2019");

        tempPhysical.add(p1);
        tempPhysical.add(p2);
        tempPhysical.add(p3);
        tempPhysical.add(p4);
        tempPhysical.add(p5);

//        tasks.add(p1);
//        tasks.add(p2);
//        tasks.add(p3);

        for(int i = 0; i < limit-1; i++){
            tasks.add(tempPhysical.get(i));
            Log.d("ADDING BODY", "here" +tempPhysical.get(i));

        }
    }

    public void populateAll(){
        Model t1 = new Model("M", "Read 20 pages of a book you want to read", "02/28/2019");
        Model t2 = new Model("M", "Do some advanced studying for one lesson in one of your classes", "02/28/2019");
        Model t3 = new Model("M", "Read an article about something your interested in", "02/28/2019");
        Model t4 = new Model("M", "Memorize a fact on something you're interested in", "02/28/2019");
        Model t5 = new Model("M", "Review 1 lesson in one of your classes", "02/28/2019");

        tempMind.add(t1);
        tempMind.add(t2);
        tempMind.add(t3);
        tempMind.add(t4);
        tempMind.add(t5);

        Model p1 = new Model("P", "Do 30 Sit-ups", "02/28/2019");
        Model p2 = new Model("P", "Take a 30 Minute Jog", "02/28/2019");
        Model p3 = new Model("P", "Do 50 Jumping Jacks", "02/28/2019");
        Model p4 = new Model("P", "Do 30 Push Ups", "02/28/2019");
        Model p5 = new Model("P", "Do 50 30 Squats", "02/28/2019");

        tempPhysical.add(p1);
        tempPhysical.add(p2);
        tempPhysical.add(p3);
        tempPhysical.add(p4);
        tempPhysical.add(p5);

        Model s1 = new Model("S", "Meditate for 5 minutes", "02/28/2019");
        Model s2 = new Model("S", "List down the things you are thankful for", "02/28/2019");
        Model s3 = new Model("S", "Give someone a hug", "02/28/2019");
        Model s4 = new Model("S", "Make someone smile today", "02/28/2019");
        Model s5 = new Model("S", "Hang out with a friend today", "02/28/2019");

        tempSoul.add(s1);
        tempSoul.add(s2);
        tempSoul.add(s3);
        tempSoul.add(s4);
        tempSoul.add(s5);
    }

    public void viewProgress(View view){
        Intent intent = new Intent(DashboardActivity.this, ViewProgressActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void retakeQuiz(View view){
        Intent intent = new Intent(DashboardActivity.this, FormActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

}
