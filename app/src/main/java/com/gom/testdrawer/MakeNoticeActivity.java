package com.gom.testdrawer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MakeNoticeActivity extends AppCompatActivity {
    RecyclerView noticeRecyclerView;
    DrawerListAdapter drawerListAdapter;
    List<DrawerItem> noticeList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_notice_activity);

        Button btn = findViewById(R.id.go_notice_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MakeNoticeActivity.this, AddDrawerItemActivity.class);
                startActivity(intent);
            }
        });


    }

    public void settingAdapter(Context context){
        drawerListAdapter = new DrawerListAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        noticeRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        noticeRecyclerView.setLayoutManager(layoutManager);
        noticeRecyclerView.setAdapter(drawerListAdapter = new DrawerListAdapter(context));
    }

    private void loadDrawerNoticeItem(){
        noticeList = new ArrayList<>();
        DatabaseReference noticeReference =  FirebaseDatabase.getInstance().getReference("noticeList");

        noticeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    noticeList.clear();
                    Log.d("gogogo", "1");


                    if(dataSnapshot.hasChildren()) {
                        Log.d("gogogo", "2");
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Log.d("gogogo", "3");
                            DrawerItem drawerItem = snapshot.getValue(DrawerItem.class);
                            noticeList.add(drawerItem);//어레이리스트에 추가
                            drawerListAdapter.setDrawerItemList(noticeList); // 어뎁터에 변경사항 요청
                            drawerListAdapter.sortByDate();

                            Log.d("gogogo", "4");
                            Log.d("gogogo", noticeList.get(0).getDate());

                        }

//                        noticeDrawerAdapter.sortByDate();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    Log.d("gogogo", ex.getMessage());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
