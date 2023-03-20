package com.myrza.calculatordata.kz_hol_all;

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

import com.myrza.calculatordata.Kazakhstan_activity;
import com.myrza.calculatordata.MainActivity;
import com.myrza.calculatordata.R;
import com.myrza.calculatordata.holidays.Kazakhstan;

public class Kz_hol_kurban_ait extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private String showResult;

    public String sec = null;
    public String min = null;
    public String hour = null;
    public String day = null;
    public String week = null;
    public String month = null;
    public String year = null;

    public String doKurbanAitOstalos = null;
    public String ifNowKurbanAit = null;

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
            case R.id.b_back_to_holidays_activityy:
                Intent intent = new Intent(this, Kazakhstan_activity.class);
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

            doKurbanAitOstalos = getResources().getString(R.string.do_kurban_ait_ostalos);
            ifNowKurbanAit = getResources().getString(R.string.ifNowKurbanAit);

            sunday = getResources().getString(R.string.sunday);
            monday = getResources().getString(R.string.monday);
            tuesday = getResources().getString(R.string.tuesday);
            wednesday = getResources().getString(R.string.wednesday);
            thursday = getResources().getString(R.string.thursday);
            friday = getResources().getString(R.string.friday);
            saturday = getResources().getString(R.string.saturday);

            showResult = Kazakhstan.kurban_ait(sec, min, hour, day, week, month, year,
                    doKurbanAitOstalos, ifNowKurbanAit, monday, tuesday, wednesday, thursday, friday,
                    saturday, sunday);

        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kz_hol_kurban_ait);

        getSupportActionBar().setTitle(getResources().getString(R.string.kurban_ait));

        Button get_info_about_kurban_ait = findViewById(R.id.b_get_info_about_kurban_ait);

        textView = findViewById(R.id.info_result_date);

        View.OnClickListener onClickListener = view -> new MyTask().execute(showResult);

        get_info_about_kurban_ait.setOnClickListener(onClickListener);

        if (Kazakhstan_activity.bol_to_activate_kurban_ait) {
            get_info_about_kurban_ait.callOnClick();
        }

        get_info_about_kurban_ait.setOnTouchListener(new View.OnTouchListener() {
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