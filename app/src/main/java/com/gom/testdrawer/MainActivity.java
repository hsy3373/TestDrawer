package com.gom.testdrawer;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    AppCompatButton manageBtn;
    ImageButton emoji;
    View targetView, newChatView;
    EditText etSend;
    ImageView openChatDrawer;
    InputMethodManager inputMethodManager;
    RecyclerView noticeRecyclerView, leadingRecyclerView;
    DrawerListAdapter noticeListAdapter, leadingListAdapter;
    List<DrawerItem> noticeList;

    private boolean mIsOpen = true; // 플로팅 버튼이 작동중인지 체크
    private boolean emojiIsOpen = true; // 플로팅 버튼이 작동중인지 체크
    private Animation mFabOpenAnim, mFabCloseAnim; // 플로팅 버튼 애니메이션

    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_chat);

        mFabOpenAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fab_open);
        mFabCloseAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fab_close);

        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        emoji = findViewById(R.id.emoji);
        targetView = findViewById(R.id.test_layout);
        etSend = findViewById(R.id.activity_chat_etSend);
        newChatView = findViewById(R.id.new_chat_view);
        drawer = (DrawerLayout) findViewById(R.id.chat_drawer) ;
        openChatDrawer = findViewById(R.id.open_chat_drawer);
        manageBtn = findViewById(R.id.manage_btn);

        noticeRecyclerView = findViewById(R.id.notice_recycler);
        leadingRecyclerView = findViewById(R.id.leading_recycler);


        onFabButton();
        setClick();
//        getting();


        settingNoticeAdapter(MainActivity.this);
        settingLeadingAdapter(MainActivity.this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDrawerNoticeItem();
        loadDrawerLeadingItem();
    }

//
//    public void getting(){
//        DatabaseReference roomNameRef = FirebaseDatabase.getInstance().getReference()
//                .child("noticeList")
//                .child("-MbF5iz0B1r3fCmO43-G")
//                .child("title");
//
//        roomNameRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//                }
//                else {
//                    String toto = String.valueOf(task.getResult().getValue());
//                    Log.d("nonon", toto);
//                }
//            }
//        });
//
//    }
    public void settingNoticeAdapter(Context context){
        noticeListAdapter = new DrawerListAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        noticeRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        noticeRecyclerView.setLayoutManager(layoutManager);
        noticeRecyclerView.setAdapter(noticeListAdapter = new DrawerListAdapter(context));
    }

    public void settingLeadingAdapter(Context context){
        leadingListAdapter = new DrawerListAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        leadingRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        leadingRecyclerView.setLayoutManager(layoutManager);
        leadingRecyclerView.setAdapter(leadingListAdapter = new DrawerListAdapter(context));
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
                            noticeListAdapter.setDrawerItemList(noticeList); // 어뎁터에 변경사항 요청
                            noticeListAdapter.sortByDate();

                            Log.d("gogogo", "4");
                            Log.d("gogogo", noticeList.get(0).getDate());

                        }

//                        noticeDrawerAdapter.sortByDate();
                    } else {
                        noticeListAdapter.setDrawerItemList(noticeList);
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

    private void loadDrawerLeadingItem(){
        noticeList = new ArrayList<>();
        DatabaseReference noticeReference =  FirebaseDatabase.getInstance().getReference("leadingList");

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
                            leadingListAdapter.setDrawerItemList(noticeList); // 어뎁터에 변경사항 요청
                            leadingListAdapter.sortByDate();

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

    public void setClick(){

        newChatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emojiIsOpen){
                    targetView.setVisibility(View.GONE);
                    emojiIsOpen = !emojiIsOpen;

                }
                inputMethodManager.hideSoftInputFromWindow(newChatView.getWindowToken(), 0);
            }
        });

        etSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emojiIsOpen){
                    targetView.setVisibility(View.GONE);
                    emojiIsOpen = !emojiIsOpen;
                    inputMethodManager.showSoftInput(etSend,0);
                }
            }
        });

        emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator;//0.5초에 걸쳐 진행.
                if(emojiIsOpen){
                    targetView.setVisibility(View.GONE);
                }else{

                    targetView.setVisibility(View.VISIBLE);
                }
                emojiIsOpen = !emojiIsOpen;
            }
        });

        openChatDrawer.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawer.isDrawerOpen(Gravity.RIGHT)) {
                    drawer.openDrawer(Gravity.RIGHT) ;
                }else {
                    drawer.closeDrawer(Gravity.RIGHT);
                }
            }
        });

        manageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MakeNoticeActivity.class);
                startActivity(intent);
            }
        });

    }

    /*FAB BUTTON*/
    private void onFabButton() {
        FloatingActionButton fabMain;
        FloatingActionButton fabImage, fabVideo;

        fabMain = findViewById(R.id.activity_chat_fabImgBtn);
        fabImage = findViewById(R.id.activity_chat_fabImg);
        fabVideo = findViewById(R.id.activity_chat_fabVideo);

        mIsOpen = false;

        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsOpen) {
                    fabImage.setAnimation(mFabCloseAnim);
                    fabImage.setVisibility(View.GONE);
                    fabVideo.setVisibility(View.GONE);
                    mIsOpen = false;
                } else {
                    fabImage.setAnimation(mFabOpenAnim);
                    fabImage.setVisibility(View.VISIBLE);
                    fabVideo.setVisibility(View.VISIBLE);
                    mIsOpen = true;
                }
            }
        });

    }

    public void sendEmoji(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


}