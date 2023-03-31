package prem.world.demo2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    List<UserData> list = new ArrayList<>();
    ItemClickListener itemClickListener;
    public MyAdapter(List<UserData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyHolder holder, int position) {
        position=holder.getAdapterPosition();
        final UserData userData = list.get(position);

        holder.tv_name.setText(userData.getName());
        holder.tv_email.setText(userData.getEmail());

        int finalPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.OnItemClick(finalPosition,userData);
            }
        });

        int finalPosition1 = position;
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(finalPosition1);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        TextView tv_name,tv_email,tv_delete;

        public MyHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name_item);
            tv_email = itemView.findViewById(R.id.tv_email_item);
            tv_delete = itemView.findViewById(R.id.tv_delete_item);
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void UpdateData(int position,UserData userData){

        list.remove(position);
        list.add(userData);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }
}
