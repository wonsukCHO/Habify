package ph.edu.dlsu.ian_ona.habify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        manager = new LinearLayoutManager(this);
        adapter = new Adapter(this);

        recyclerView = findViewById(R.id.view);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
