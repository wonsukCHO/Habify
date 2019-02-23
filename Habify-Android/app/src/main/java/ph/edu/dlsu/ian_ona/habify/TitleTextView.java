package ph.edu.dlsu.ian_ona.habify;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class TitleTextView extends AppCompatTextView {
    public TitleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TitleTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/moonbold.otf");
        setTypeface(tf ,1);
    }

}
