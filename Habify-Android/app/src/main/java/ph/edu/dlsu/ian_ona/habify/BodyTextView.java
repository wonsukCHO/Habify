package ph.edu.dlsu.ian_ona.habify;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class BodyTextView extends AppCompatTextView {
    public BodyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public BodyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BodyTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/latolight.ttf");
        setTypeface(tf ,1);
    }

}
