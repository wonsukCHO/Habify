package ph.edu.dlsu.ian_ona.habify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private ArrayList<Model> tasks;
    private ArrayList<Model> tempMind;
    private ArrayList<Model> tempSoul;
    private ArrayList<Model> tempPhysical;
    private Model task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        manager = new LinearLayoutManager(this);
        adapter = new Adapter(this);

        recyclerView = findViewById(R.id.view);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        tasks = new ArrayList<>();
        tempMind = new ArrayList<>();
        tempSoul = new ArrayList<>();
        tempPhysical = new ArrayList<>();
    }

    public void generateTasks(View view){
        populateMind(5);
        adapter.refreshTasks(tasks);
    }

    public void generateOne(View view){
        Random rand = new Random();
        int randNum = rand.nextInt((5 - 0) + 1) + 0;
        int choice = rand.nextInt((3 - 1) + 1) + 1;
        switch (choice){
            case 1:
                task = tempMind.get(randNum);
                break;
            case 2:
                task = tempSoul.get(randNum);
                break;
            case 3:
                task = tempPhysical.get(randNum);
                break;
        }
//        task = new Model(temp);
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

        for(int i = 0; i < limit; i++){
            tasks.add(tempMind.get(i));
        }

    }

    public void populateSoul(int limit){
        Model s1 = new Model("S", "Meditate for 5 minutes", "02/28/2019");
        Model s2 = new Model("S", "List down the things you are thankful dfr", "02/28/2019");
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

        for(int i = 0; i < limit; i++){
            tasks.add(tempMind.get(i));
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

        for(int i = 0; i < limit; i++){
            tasks.add(tempPhysical.get(i));
        }
    }
}
