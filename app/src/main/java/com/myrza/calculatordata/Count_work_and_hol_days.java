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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class Count_work_and_hol_days extends AppCompatActivity implements View.OnClickListener {
    private EditText editText1;
    private EditText editText2;
    private TextView textView;
    private String showResult;

    public String sunday = null;
    public String monday = null;
    public String tuesday = null;
    public String wednesday = null;
    public String thursday = null;
    public String friday = null;
    public String saturday = null;

    public String exceptionDatesEquals = null;
    public String exceptionDate2Later = null;
    public String exceptionFormatData = null;
    public String exceptionDataNotExist = null;

    public String working_days = null;
    public String weekend_days = null;


    public boolean bool_switch_button1;
    public boolean bool_switch_button2;

    public boolean bool_pon;
    public boolean bool_vtor;
    public boolean bool_sreda;
    public boolean bool_4etv;
    public boolean bool_pt;
    public boolean bool_sub;
    public boolean bool_voskr;

    private ProgressBar progressBar;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch b_switch1;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch b_switch2;
    private CheckBox b_checkBox_ponedelnik;
    private CheckBox b_checkBox_vtornik;
    private CheckBox b_checkBox_sreda;
    private CheckBox b_checkBox_4etverg;
    private CheckBox b_checkBox_pyatnica;
    private CheckBox b_checkBox_subbota;
    private CheckBox b_checkBox_voskresenie;

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
            case R.id.restart_button_work_activity:
                editText1.setText("");
                editText2.setText("");
                textView.setText(getResources().getString(R.string.res_textView_pods4et_rab_i_vyh_dney));

                b_switch1.setChecked(false);
                b_switch2.setChecked(false);

                b_checkBox_ponedelnik.setChecked(false);
                b_checkBox_vtornik.setChecked(false);
                b_checkBox_sreda.setChecked(false);
                b_checkBox_4etverg.setChecked(false);
                b_checkBox_pyatnica.setChecked(false);
                b_checkBox_subbota.setChecked(false);
                b_checkBox_voskresenie.setChecked(false);
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

            return showResult = CountWorkAndHolDays.calcWorkAndHolDaysMethod(editText1.getText().toString(),
                    editText2.getText().toString(), bool_pon, bool_vtor, bool_sreda,
                    bool_4etv, bool_pt, bool_sub, bool_voskr, bool_switch_button1, bool_switch_button2,
                    exceptionDatesEquals, exceptionDate2Later, exceptionFormatData, working_days, weekend_days,
                    monday, tuesday, wednesday, thursday, friday, saturday, sunday,
                    exceptionDataNotExist);

        }

        @Override
        protected void onPostExecute(String response) {
            textView.setText(response);
            progressBar.setVisibility(View.INVISIBLE);
        }

        @SuppressLint("ResourceType")
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);

            sunday = getResources().getString(R.string.sunday);
            monday = getResources().getString(R.string.monday);
            tuesday = getResources().getString(R.string.tuesday);
            wednesday = getResources().getString(R.string.wednesday);
            thursday = getResources().getString(R.string.thursday);
            friday = getResources().getString(R.string.friday);
            saturday = getResources().getString(R.string.saturday);

            exceptionDate2Later = getResources().getString(R.string.exceptionDate2Later);
            exceptionDatesEquals = getResources().getString(R.string.exceptionDatesEquals);
            exceptionFormatData = getResources().getString(R.string.exception_format_data_for_calc_workig_days);
            exceptionDataNotExist = getResources().getString(R.string.res_ex_not_exist_date);

            working_days = getResources().getString(R.string.working_days);
            weekend_days = getResources().getString(R.string.hol_days);


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_work_and_hol_days);

        /////new
        Context context = this;
        Resources resources = context.getResources();
        //////

        getSupportActionBar().setTitle(getResources().getString(R.string.res_b_count_work_and_hol_days));

        editText1 = findViewById(R.id.input_data1_calc_work_and_hol);
        editText2 = findViewById(R.id.input_data2_calc_work_and_hol);
        Button do_calc_button = findViewById(R.id.button_do_calc_work_and_hol);

        textView = findViewById(R.id.data_result_count_work_and_hol_days);

        progressBar = findViewById(R.id.progressBar_work_hol);


        /////new

        findViewById(R.id.restart_button_work_activity).setOnTouchListener(new View.OnTouchListener() {
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


        b_switch1 = (Switch) findViewById(R.id.b_switch_current_data1_calc_work_days);
        b_switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bool_switch_button1 = isChecked;

                if (!bool_clear_button_was_pressed_or_not1) {
                    if (isChecked) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.b_cur_data_on), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.b_cur_data_off), Toast.LENGTH_SHORT).show();
                    }
                }
                bool_clear_button_was_pressed_or_not1 = false;
            }
        });

        b_switch2 = (Switch) findViewById(R.id.b_switch_current_data2_calc_work_days);
        b_switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bool_switch_button2 = isChecked;

                if (!bool_clear_button_was_pressed_or_not2) {
                    if (isChecked) {
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.b_cur_data_on), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.b_cur_data_off), Toast.LENGTH_SHORT).show();
                    }
                }
                bool_clear_button_was_pressed_or_not2 = false;
            }
        });


        b_checkBox_ponedelnik = (CheckBox) findViewById(R.id.checkBox_ponedelnik);
        b_checkBox_ponedelnik.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bool_pon = isChecked;
            }
        });

        b_checkBox_vtornik = (CheckBox) findViewById(R.id.checkBox_vtornik);
        b_checkBox_vtornik.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bool_vtor = isChecked;
            }
        });

        b_checkBox_sreda = (CheckBox) findViewById(R.id.checkBox_sreda);
        b_checkBox_sreda.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bool_sreda = isChecked;
            }
        });

        b_checkBox_4etverg = (CheckBox) findViewById(R.id.checkBox_4etverg);
        b_checkBox_4etverg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bool_4etv = isChecked;
            }
        });

        b_checkBox_pyatnica = (CheckBox) findViewById(R.id.checkBox_pyatnica);
        b_checkBox_pyatnica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bool_pt = isChecked;
            }
        });

        b_checkBox_subbota = (CheckBox) findViewById(R.id.checkBox_subbota);
        b_checkBox_subbota.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bool_sub = isChecked;
            }
        });

        b_checkBox_voskresenie = (CheckBox) findViewById(R.id.checkBox_voskresenie);
        b_checkBox_voskresenie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bool_voskr = isChecked;
            }
        });



        ///////new*****
        String info_text = resources.getString(R.string.res_textView_pods4et_rab_i_vyh_dney);

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