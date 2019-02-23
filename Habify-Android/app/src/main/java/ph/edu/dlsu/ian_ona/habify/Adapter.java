package ph.edu.dlsu.ian_ona.habify;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Holder> {


    private ArrayList<Model> list;
    private Context context;

    public Adapter(Context context){
        list = new ArrayList<Model>();
        list.add(new Model("TODO #1"));
        list.add(new Model("TODO #2"));

        this.context = context;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.row, viewGroup, false);
        Holder holder = new Holder(view);
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
}
