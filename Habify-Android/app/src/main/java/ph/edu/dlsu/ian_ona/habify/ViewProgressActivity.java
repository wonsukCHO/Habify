package ph.edu.dlsu.ian_ona.habify;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_progress);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
        int userScore = sharedPref.getInt(getString(R.string.userScorePref), 0);

        TextView textView = findViewById(R.id.textView3);
        textView.setText("Total score: " + userScore);
    }
}
