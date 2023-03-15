package com.myrza.calculatordata;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Holidays extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holidays);

        getSupportActionBar().setTitle(getResources().getString(R.string.b_holidays));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_to_main2:
                Intent intent_main = new Intent(this, MainActivity.class);
                startActivity(intent_main);
                break;
            case R.id.kazakhstan:
                Intent intent_kazakhstan = new Intent(this, Kazakhstan_activity.class);
                startActivity(intent_kazakhstan);
                break;
            case R.id.russia:
                Intent intent_russia = new Intent(this, Russia_activity.class);
                startActivity(intent_russia);
                break;
            /*case R.id.america:
                Intent intent_america = new Intent(this, America_activity.class);
                startActivity(intent_america);
                break;
            case R.id.worldHolidays:
                Intent intent_world_holidays = new Intent(this, World_holidays.class);
                startActivity(intent_world_holidays);
                break;*/
            default:
                break;
        }
    }
}