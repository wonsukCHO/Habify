package ph.edu.dlsu.ian_ona.habify;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

public class Holder extends RecyclerView.ViewHolder{

    private CheckBox checkBox;

    public Holder(View view){
        super(view);
        checkBox = view.findViewById(R.id.checkBox);
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(String task) {
        checkBox.setText(task);
    }
}
