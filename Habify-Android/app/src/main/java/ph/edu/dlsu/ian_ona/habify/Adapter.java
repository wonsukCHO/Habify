package ph.edu.dlsu.ian_ona.habify;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Holder> {

    private ArrayList<Model> list;
    private Context context;

    public Adapter(Context context){
        list = new ArrayList<Model>();
//        list.add(new Model("TODO #1"));
//        list.add(new Model("TODO #2"));
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.row, viewGroup, false);
        Holder holder = new Holder(view);

        holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preferences), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                int temp = sharedPref.getInt(context.getString(R.string.userScorePref), 0);
                Log.d("OLD", "old is " +temp);
                if(isChecked){
                    temp++;
                } else {
                    temp--;
                }
                Log.d("NEW", "new is " +temp);
                editor.putInt(context.getString(R.string.userScorePref), temp);
                editor.commit();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.setCheckBox(list.get(i).getTask());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refreshTasks(ArrayList<Model> tasks){
        Log.d("TASK ARRIVES", "here" +tasks.toString());
        list = new ArrayList<>();
        list = tasks;
        Log.d("ADAPTER", "lists is " +list);
        notifyDataSetChanged();
    }

    public void refreshOne(Model task){
        list.add(task);
        notifyItemInserted(list.size()-1);
    }
}
