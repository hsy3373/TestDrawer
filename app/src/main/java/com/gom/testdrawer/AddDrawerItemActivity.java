package com.gom.testdrawer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddDrawerItemActivity extends AppCompatActivity {

    EditText addTitle, addContent, addDate;
    Button addNoticeBtn;
    String title, content, date;
    Chip leadingChip, noticeChip;
    TextView addDateText;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_notice);

        addContent = findViewById(R.id.add_content);
        addTitle = findViewById(R.id.add_title);
        addDate = findViewById(R.id.add_date);
        addDateText = findViewById(R.id.add_date_text_view);

        leadingChip = findViewById(R.id.chip_leading_btn);
        noticeChip = findViewById(R.id.chip_notice_btn);

        addNoticeBtn= findViewById(R.id.add_notice_btn);

        addDate.setVisibility(View.GONE);
        addDateText.setVisibility(View.GONE);


        addNoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef;

                if(leadingChip.isChecked()){
                    myRef = FirebaseDatabase.getInstance().getReference("leadingList");

                }else{
                    myRef = FirebaseDatabase.getInstance().getReference("noticeList");
                }


                title = addTitle.getText().toString();
                content = addContent.getText().toString();
                date = CurrentDateTime.getCurrentDateTime();

                String nodeKey = myRef.push().getKey();

                final HashMap<String, Object> hashMap = new HashMap<>();

                hashMap.put("title", title);
                hashMap.put("content", content);
                hashMap.put("date", date);
                hashMap.put("id", nodeKey);

                myRef.child(nodeKey).setValue(hashMap);


            }
        });









    }


    public void addNotice(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("noticeList");



    }



}


