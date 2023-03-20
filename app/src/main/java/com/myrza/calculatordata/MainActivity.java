package com.myrza.calculatordata;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

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
                Intent intent1 = new Intent(this, Interval_date.class);
                startActivity(intent1);
                break;
            case R.id.l_info_about_data:
                Intent intent2 = new Intent(this, Get_date_info.class);
                startActivity(intent2);
                break;
            case R.id.l_holidays:
                Intent intent3 = new Intent(this, Holidays.class);
                startActivity(intent3);
                break;
            case R.id.l_count_work_days:
                Intent intent4 = new Intent(this, Count_work_and_hol_days.class);
                startActivity(intent4);
                break;
            case R.id.l_counting_time:
                Intent intent5 = new Intent(this, Counting_time.class);
                startActivity(intent5);
                break;
            case R.id.l_date_of_birth_info:
                Intent intent6 = new Intent(this, Info_day_of_birth.class);
                startActivity(intent6);
                break;
            default:
                break;
        }
    }
}