package com.gom.testdrawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModifyDrawerListAdapter extends RecyclerView.Adapter<ModifyDrawerListAdapter.ViewHolder> {

    private List<DrawerItem> drawerItemList;

    public ModifyDrawerListAdapter() {
        this.drawerItemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ModifyDrawerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_notice_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModifyDrawerListAdapter.ViewHolder holder, int position) {
        holder.onBind(drawerItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return drawerItemList.size();
    }

    public void setDrawerItemList(List<DrawerItem> drawerItemList){
        this.drawerItemList = drawerItemList;
        notifyDataSetChanged();
    }

    public void sortByDate() {
        Collections.sort(this.drawerItemList, (o1, o2) -> o2.getDate().compareTo(o1.getDate()) );

        notifyDataSetChanged();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView title, content, date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.drawer_item_title);
//            content = itemView.findViewById(R.id.drawer_item_content);
            date = itemView.findViewById(R.id.drawer_item_date);

        }


        void onBind(DrawerItem item) {
            title.setText(item.getTitle());
//            content.setText(item.getContent());
            String[] dateArray = String.valueOf(item.getDate()).split("");
            String dateValue = "";
            for( int i=2; i<10; i++){
                dateValue += dateArray[i];
            }
            date.setText(dateValue);

        }

    }
}
