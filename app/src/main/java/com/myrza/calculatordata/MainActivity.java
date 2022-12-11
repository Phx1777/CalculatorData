package com.myrza.calculatordata;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.l_calculate_data:
                Intent intent = new Intent(this, Calculate_data.class);
                startActivity(intent);
                break;

            case R.id.l_info_about_data:
                Intent intent2 = new Intent(this, Get_date_info.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}