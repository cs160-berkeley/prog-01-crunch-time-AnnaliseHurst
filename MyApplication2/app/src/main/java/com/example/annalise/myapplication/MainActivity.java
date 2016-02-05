package com.example.annalise.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;


public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    private EditText num;
    private EditText cal_view;
    private Bundle b = new Bundle();
    private String st;
    private Double entered = 0.0;
    private String onTextChanged;
    private Boolean focus = true;
    private Boolean focus2 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.activies,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                st = parent.getItemAtPosition(position).toString();
                TextView tv1 = (TextView) findViewById(R.id.nums);
                tv1.setText(b.getStringArray(st)[2]);
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                focus2 = false;
                onTextChanged = num.getText().toString();
                update();
            }
        });

        cal_view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                onTextChanged = cal_view.getText().toString();
                focus = false;
                if (focus2) {
                    TextView tv = (TextView) findViewById(R.id.editText);
                    double cal = Integer.parseInt(b.getStringArray(st)[1]);
                    double meas = Integer.parseInt(b.getStringArray(st)[0]);
                    double newanswer = 0.0;
                    if (!(onTextChanged == null)) {
                        if (!(onTextChanged.isEmpty())) {
                            entered = Double.parseDouble(onTextChanged);
                            newanswer = (meas * entered) / cal;
                        }
                    }
                    String ans = Integer.toString((int) newanswer);
                    tv.setText(ans);
                }
                focus2 = true;

            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void init() {
        num = (EditText)findViewById(R.id.editText);
        cal_view = (EditText)findViewById(R.id.answer);
        String[] s1 = {"350","100","Reps"}; b.putStringArray("Pushups",s1);
        String[] s2 = {"200","100","Reps"}; b.putStringArray("Situps",s2);
        String[] s3 = {"225","100","Reps"}; b.putStringArray("Squats",s3);
        String[] s4 = {"25","100","Minutes"}; b.putStringArray("Leg-Lifts",s4);
        String[] s5 = {"25","100","Minutes"}; b.putStringArray("Planks",s5);
        String[] s6 = {"10","100","Minutes"}; b.putStringArray("Jumping Jacks",s6);
        String[] s7 = {"100","100","Reps"}; b.putStringArray("Pullups",s7);
        String[] s8 = {"12","100","Minutes"}; b.putStringArray("Cycling",s8);
        String[] s9 = {"20","100","Minutes"}; b.putStringArray("Walking",s9);
        String[] s10 = {"12","100","Minutes"}; b.putStringArray("Jogging",s10);
        String[] s11 = {"13","100","Minutes"}; b.putStringArray("Swimming",s11);
        String[] s12 = {"15","100","Minutes"}; b.putStringArray("Stair-Climbing",s12);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void update() {
        TextView tv  = (TextView)findViewById(R.id.answer);
        double cal = Integer.parseInt(b.getStringArray(st)[1]);
        double meas = Integer.parseInt(b.getStringArray(st)[0]);
        double answer = 0.0;
        if (!(onTextChanged == null)) {
            if (!(onTextChanged.isEmpty())) {
                entered = Double.parseDouble(onTextChanged);
                answer = (cal * entered) / meas;
            }
        }
//        String ans = (String) tv.getText();
        String ans = "";
        if (focus) {
            ans = Integer.toString((int) answer);
            tv.setText(ans);
        }

        int [] l_ids = new int [] {R.id.list1,
                R.id.list2,
                R.id.list3,
                R.id.list4,
                R.id.list5,
                R.id.list6,
                R.id.list7,
                R.id.list8,
                R.id.list9,
                R.id.list10,
                R.id.list11,
                R.id.list12};
        int [] a_ids = new int [] {R.id.a_num1,
                R.id.a_num2,
                R.id.a_num3,
                R.id.a_num4,
                R.id.a_num5,
                R.id.a_num6,
                R.id.a_num7,
                R.id.a_num8,
                R.id.a_num9,
                R.id.a_num10,
                R.id.a_num11,
                R.id.a_num12};
        TextView textView;
        String[] keyset = b.keySet().toArray(new String[b.keySet().size()]);
        Arrays.sort(keyset);
        for (int l=1; l<=12; l++) {
            String[] sa = b.getStringArray(keyset[l-1]);

            textView = (TextView) findViewById(l_ids[l - 1]);
            String calvs = sa[1];
            double calv = Double.parseDouble(calvs);
            String measvs = sa[0];
            double measv = Double.parseDouble(measvs);
            double newmeas = 0.0;
            if (!(ans == null)) {
                newmeas = (answer * measv) / calv;
                String newm = Integer.toString((int) newmeas);
                textView.setText(sa[2] + " of " + keyset[l - 1]);
                textView = (TextView) findViewById(a_ids[l - 1]);
                textView.setText(newm);
            }
        }
        focus = true;
    }

}
