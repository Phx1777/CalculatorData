package com.myrza.calculatordata;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class Interval_date extends AppCompatActivity implements View.OnClickListener {
    private EditText editText1;
    private EditText editText2;
    private TextView textView;
    private String showResult;

    public Button clear_button_interval_date;

    public String sec = null;
    public String min = null;
    public String hour = null;
    public String day = null;
    public String week = null;
    public String month = null;
    public String year = null;

    public String interval = null;
    public String exceptionDate2Later = null;
    public String exceptionDatesEquals = null;
    public String exceptionFormatData = null;
    public String exceptionNonExistentDate = null;
    public String exceptionEmptyDate = null;

    public String sunday = null;
    public String monday = null;
    public String tuesday = null;
    public String wednesday = null;
    public String thursday = null;
    public String friday = null;
    public String saturday = null;

    private boolean bool_switch_button1;
    private boolean bool_switch_button2;

    private Switch b_switch1;
    private Switch b_switch2;

    private boolean bool_clear_button_was_pressed_or_not1 = false;
    private boolean bool_clear_button_was_pressed_or_not2 = false;

    ///////new*****
    private int index = 0;
    private final long delay = 35; // задержка между выводом символов, в миллисекундах
    ///////

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear_button_interval_date:
                editText1.setText("");
                editText2.setText("");
                textView.setText(getResources().getString(R.string.result));
                b_switch1.setChecked(false);
                b_switch2.setChecked(false);
                break;
            case R.id.b_to_main:
                Intent goBack = new Intent(this, MainActivity.class);
                startActivity(goBack);
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

        @SuppressLint("ResourceType")
        @Override
        protected void onPreExecute() {
            sec = getResources().getString(R.string.sec);
            min = getResources().getString(R.string.min);
            hour = getResources().getString(R.string.hour);
            day = getResources().getString(R.string.day);
            week = getResources().getString(R.string.week);
            month = getResources().getString(R.string.month);
            year = getResources().getString(R.string.year);

            interval = getResources().getString(R.string.interval);

            exceptionDate2Later = getResources().getString(R.string.exceptionDate2Later);
            exceptionDatesEquals = getResources().getString(R.string.exceptionDatesEquals);
            exceptionFormatData = getResources().getString(R.string.exceptionFormatData);
            exceptionNonExistentDate = getResources().getString(R.string.res_ex_not_exist_date);
            exceptionEmptyDate = getResources().getString(R.string.res_ex_empty_date);

            sunday = getResources().getString(R.string.sunday);
            monday = getResources().getString(R.string.monday);
            tuesday = getResources().getString(R.string.tuesday);
            wednesday = getResources().getString(R.string.wednesday);
            thursday = getResources().getString(R.string.thursday);
            friday = getResources().getString(R.string.friday);
            saturday = getResources().getString(R.string.saturday);


            try {
                showResult = IntervalDate.calcData(
                        editText1.getText().toString(),
                        editText2.getText().toString(), sec, min, hour, day, week, month, year,
                        exceptionDate2Later, exceptionDatesEquals, exceptionFormatData,
                        interval, bool_switch_button1, bool_switch_button2, monday, tuesday,
                        wednesday, thursday, friday, saturday, sunday, exceptionNonExistentDate,
                        exceptionEmptyDate);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval_date);

        /////new
        Context context = this;
        Resources resources = context.getResources();
        //////



        getSupportActionBar().setTitle(getResources().getString(R.string.res_date_interval));

        editText1 = findViewById(R.id.input_data1);
        editText2 = findViewById(R.id.input_data2);

        Button do_calc_button = findViewById(R.id.button_do_calc_data);

        textView = findViewById(R.id.data_result_interval);

        clear_button_interval_date = findViewById(R.id.clear_button_interval_date);
        clear_button_interval_date.setOnClickListener(this);

        /////new

        clear_button_interval_date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    bool_clear_button_was_pressed_or_not1 = true;
                    bool_clear_button_was_pressed_or_not2 = true;
                }
                return false;
            }
        });

        /////

        b_switch1 = (Switch) findViewById(R.id.b_switch_current_data1);
        b_switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bool_switch_button1 = isChecked;

                if (!bool_clear_button_was_pressed_or_not1) {
                    if (isChecked) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.switch_button_on), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.switch_button_off), Toast.LENGTH_SHORT).show();
                    }
                }
                bool_clear_button_was_pressed_or_not1 = false;
            }
        });

        b_switch2 = (Switch) findViewById(R.id.b_switch_current_data2);
        b_switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bool_switch_button2 = isChecked;

                if (!bool_clear_button_was_pressed_or_not2) {
                    if (isChecked) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.switch_button_on), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.switch_button_off), Toast.LENGTH_SHORT).show();
                    }
                }
                bool_clear_button_was_pressed_or_not2 = false;
            }
        });




        ///////new*****
        String info_text = resources.getString(R.string.result);

        // создаем Handler для задержки вывода каждого символа
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // выводим следующий символ
                textView.setText(info_text.substring(0, index++));
                // проверяем, достигли ли конца строки
                if (index <= info_text.length()) {
                    // постим задачу для следующего символа через delay миллисекунд
                    handler.postDelayed(this, delay);
                }
            }
        }, delay);
        /////////////************

        View.OnClickListener onClickListener = view -> new MyTask().execute(showResult);
        do_calc_button.setOnClickListener(onClickListener);

    }
}