package com.example.mydailywork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView result = (TextView) findViewById(R.id.result);

        int all = 0;
        int ok = 0;

        SharedPreferences db = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = db.edit();
        String a;
        int i;
        for (i = 0; !(a = db.getString("" + i, "unknown")).equals("unknown"); i++) {
            int ik = i + 50;
            Boolean check = db.getBoolean("" + ik, false);
            all++;
            if (check) {
                ok++;
            }
        }

        Log.d("aaa", ""+ok + "   /  "  +""+all);

        float percent = ((float)ok/all) * 100;

        result.setText(""+percent);


        for(i = 0; !db.getString(""+i, "unknown").equals("unknown"); i++) {
            editor.remove(""+i);
            editor.apply();
        }



    }
}