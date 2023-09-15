package com.example.mydailywork;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.badge.BadgeUtils;

public class MainActivity extends AppCompatActivity {
    private CheckBox[] newBoxes = new CheckBox[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button saveBtn = (Button) findViewById(R.id.save);
        Button resetAndresult = (Button) findViewById(R.id.reset);
        Button addbtn = (Button) findViewById(R.id.addBtn);
        LinearLayout scroll = (LinearLayout) findViewById(R.id.Linear);
        TextView workT = (TextView) findViewById(R.id.work);


        //기본 데이터 유무
        SharedPreferences db = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = db.edit();
        String a;
        int i;
        for(i = 0; !(a = db.getString(""+i, "unknown")).equals("unknown"); i++){
            int ik = i+50;
            Boolean check = db.getBoolean(""+ik, false);
            CheckBox nowbox= new CheckBox(MainActivity.this);
            newBoxes[i] = nowbox;
            nowbox.setText(a);
            scroll.addView(nowbox);
            if(check){
                nowbox.setChecked(true);
            }

        }
        final int[] i2 = {i};
        addbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                CheckBox newbox= new CheckBox(MainActivity.this);
                if(workT.getText().toString().equals("")) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setMessage("내용이 없습니다.");
                    alert.show();

                }else {
                    newBoxes[i2[0]] = newbox;
                    newbox.setText(""+workT.getText());
                    scroll.addView(newbox);
                    //데이터 저장하기
                    editor.putString(""+ i2[0],workT.getText().toString());
                    editor.apply();
                    workT.setText("");
                }
                i2[0]++;

            }


        });





        resetAndresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent goResult = new Intent(v.getContext(), Result.class);


                startActivity(goResult);
            }

        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; newBoxes[i] != null; i++){
                    int i2 = i+50;

                    if(newBoxes[i].isChecked()){
                        editor.putBoolean(""+i2, true);
                    }else {
                        editor.putBoolean(""+i2, false);
                    }

                    editor.apply();
                }
            }
        });





    }
}