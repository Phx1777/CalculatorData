package com.myrza.calculatordata.ru_holl_all;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myrza.calculatordata.MainActivity;
import com.myrza.calculatordata.R;
import com.myrza.calculatordata.Russia_activity;
import com.myrza.calculatordata.holidays.Russia;

public class Ru_hol_den_narodnogo_edinstva extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private String showResult;

    public String sec = null;
    public String min = null;
    public String hour = null;
    public String day = null;
    public String week = null;
    public String month = null;
    public String year = null;

    public String doDenNarodEdinstv = null;
    public String ifNow = null;

    public String sunday = null;
    public String monday = null;
    public String tuesday = null;
    public String wednesday = null;
    public String thursday = null;
    public String friday = null;
    public String saturday = null;

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_back_to_holidays_activity_ru:
                Intent intent = new Intent(this, Russia_activity.class);
                startActivity(intent);
                break;
            case R.id.b_to_main2:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    class MyTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return showResult;
        }

        @Override
        protected void onPostExecute(String response) {
            textView.setText(response);
        }

        @Override
        protected void onPreExecute() {
            sec = getResources().getString(R.string.sec);
            min = getResources().getString(R.string.min);
            hour = getResources().getString(R.string.hour);
            day = getResources().getString(R.string.day);
            week = getResources().getString(R.string.week);
            month = getResources().getString(R.string.month);
            year = getResources().getString(R.string.year);

            ifNow = getResources().getString(R.string.res_ifNowDenNarodEdinstva_ru);
            doDenNarodEdinstv = getResources().getString(R.string.res_den_narod_edinstva_ru);

            sunday = getResources().getString(R.string.sunday);
            monday = getResources().getString(R.string.monday);
            tuesday = getResources().getString(R.string.tuesday);
            wednesday = getResources().getString(R.string.wednesday);
            thursday = getResources().getString(R.string.thursday);
            friday = getResources().getString(R.string.friday);
            saturday = getResources().getString(R.string.saturday);

            showResult = Russia.den_narodnogo_edinstva(sec, min, hour, day, week, month, year,
                    doDenNarodEdinstv, ifNow, monday, tuesday, wednesday, thursday, friday,
                    saturday, sunday);

        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ru_hol_den_narodnogo_edinstva);

        getSupportActionBar().setTitle(getResources().getString(R.string.b_den_narod_edinstva));

        Button b_den_narod_edinstva_ru = findViewById(R.id.b_get_info_about_den_narod_edinstva_ru);

        textView = findViewById(R.id.data_result);
        textView = findViewById(R.id.info_result_date);

        View.OnClickListener onClickListener = view -> new MyTask().execute(showResult);

        b_den_narod_edinstva_ru.setOnClickListener(onClickListener);

        if (Russia_activity.bol_to_activate_den_narod_edinstva_ru) {
            b_den_narod_edinstva_ru.callOnClick();
        }

        b_den_narod_edinstva_ru.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.res_b_updated),
                                Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

    }

}