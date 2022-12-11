package com.myrza.calculatordata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Get_date_info extends AppCompatActivity implements View.OnClickListener {
    private EditText editText3;
    private TextView textView;
    private String showResult;

    Button restart_button;

    public String sunday = null;
    public String monday = null;
    public String tuesday = null;
    public String wednesday = null;
    public String thursday = null;
    public String friday = null;
    public String saturday = null;


    public String january = null;
    public String february = null;
    public String march = null;
    public String april = null;
    public String may = null;
    public String june = null;
    public String july = null;
    public String august = null;
    public String september = null;
    public String october = null;
    public String november = null;
    public String december = null;

    public String eto = null;
    public String denVGodu = null;
    public String exception = null;

    public String leapYear1 = null;
    public String leapYear2 = null;
    public String notLeapYear1 = null;
    public String notLeapYear2 = null;
    public String godu = null;


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.restart_button:
                Intent intent = new Intent(this, Get_date_info.class);
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
            sunday = getResources().getString(R.string.sunday);
            monday = getResources().getString(R.string.monday);
            tuesday = getResources().getString(R.string.tuesday);
            wednesday = getResources().getString(R.string.wednesday);
            thursday = getResources().getString(R.string.thursday);
            friday = getResources().getString(R.string.friday);
            saturday = getResources().getString(R.string.saturday);

            january = getResources().getString(R.string.january);
            february = getResources().getString(R.string.february);
            march = getResources().getString(R.string.march);
            april = getResources().getString(R.string.april);
            may = getResources().getString(R.string.may);
            june = getResources().getString(R.string.june);
            july = getResources().getString(R.string.july);
            august = getResources().getString(R.string.august);
            september = getResources().getString(R.string.september);
            october = getResources().getString(R.string.october);
            november = getResources().getString(R.string.november);
            december = getResources().getString(R.string.december);

            eto = getResources().getString(R.string.eto);
            denVGodu = getResources().getString(R.string.denVgodu);
            exception = getResources().getString(R.string.ex_info_data);

            leapYear1 = getResources().getString(R.string.leapYear1);
            leapYear2 = getResources().getString(R.string.leapYear2);
            notLeapYear1 = getResources().getString(R.string.notLeapYear1);
            notLeapYear2 = getResources().getString(R.string.notLeapYear2);
            godu = getResources().getString(R.string.godu);


            showResult = DateInfo.dateInformation(editText3.getText().toString(),
                    january, february, march, april, may, june, july, august, september, october,
                    november, december,
                    monday, tuesday, wednesday, thursday, friday, saturday, sunday, eto, denVGodu, exception,
                    leapYear1, leapYear2, notLeapYear1, notLeapYear2, godu);


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_date_info);

        editText3 = findViewById(R.id.date_info_edit);

        Button get_info_about_date = findViewById(R.id.b_get_info_about_date);

        textView = findViewById(R.id.data_result);
        textView = findViewById(R.id.info_result);

        restart_button = findViewById(R.id.restart_button);
        restart_button.setOnClickListener(this);

        View.OnClickListener onClickListener = view -> new MyTask().execute(showResult);

        get_info_about_date.setOnClickListener(onClickListener);
    }

}