package ph.edu.dlsu.ian_ona.habify;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewProgressActivity extends AppCompatActivity {
    ImageView progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_progress);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
        int userScore = sharedPref.getInt(getString(R.string.userScorePref), 0);

        TextView textView = findViewById(R.id.textView3);
        textView.setText("Total progress: " + userScore + " development points");

        progress = findViewById(R.id.progressImg);
        if (userScore >= 1 && userScore < 4) {
            progress.setImageResource(R.drawable.checkpointone);
        } else if (userScore >= 4 && userScore < 7) {
            progress.setImageResource(R.drawable.checkpointtwo);
        } else if (userScore >= 7 && userScore < 10) {
            progress.setImageResource(R.drawable.checkpointthree);
        } else if (userScore >= 10 && userScore < 15) {
            progress.setImageResource(R.drawable.checkpointfour);
        } else if (userScore >= 15) {
            progress.setImageResource(R.drawable.checkpointfive);
        }
    }
}
