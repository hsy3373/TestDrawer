package com.gom.testdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

public class ModifyDrawerItemActivity extends AppCompatActivity {

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



        leadingChip.setVisibility(View.GONE);
        noticeChip.setVisibility(View.GONE);


        Intent intent = getIntent();

        addTitle.setText(intent.getStringExtra("title"));
        addDate.setText(intent.getStringExtra("date"));
        addContent.setText(intent.getStringExtra("content"));

    }
}
