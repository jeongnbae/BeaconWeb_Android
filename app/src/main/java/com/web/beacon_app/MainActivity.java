package com.web.beacon_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inputName, inputBirth, inputTel;
    private RadioButton selectGender1, selectGender2, radioGroup;

    private String gender;

    private ArrayList memberList;

    private TextView mainLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memberList = new ArrayList();
        mainLogo = (TextView) findViewById(R.id.mainLogo);
        addButtonListener();
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void login(View view){

        if(inputName.getText().length() == 0 || inputBirth.getText().length() == 0 || inputTel.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "전부 입력해 주세요.",Toast.LENGTH_LONG).show();
            return;
        }else{
            memberList.add(inputName.getText());
            memberList.add(inputBirth.getText());
            if(gender == null){
                memberList.add("남성");
            }else{
                memberList.add(gender);
            }
        }

        if(inputTel.getText().length() >=9 && inputTel.getText().length() <=11){
            memberList.add(inputTel.getText());
        }else{
            inputTel.setText("");
            Toast.makeText(getApplicationContext(), "올바른 전화번호 입력하세요.",Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(getApplicationContext(), memberList.toString(),Toast.LENGTH_LONG).show();

        String name = memberList.get(0).toString();
        String birth = memberList.get(1).toString();
        String gender = memberList.get(2).toString();
        String tel = memberList.get(3).toString();

        Log.d("name",name);
        Log.d("birth",birth);
        Log.d("gender",gender);
        Log.d("tel",tel);

        new SigninActivity(this).execute(name, birth, gender, tel);
    }

    public void addButtonListener(){

        /*이름, 생년월일, 전화번호 입력*/
        inputName = (EditText) findViewById(R.id.inputName);
        inputBirth = (EditText) findViewById(R.id.inputBirth);
        inputTel = (EditText) findViewById(R.id.inputTel);

        /*이름 체크*/
        inputName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    if (inputName.getText().length() == 1 || inputName.getText().length() >= 10) {
                        inputName.setText("");
                        Toast.makeText(getApplicationContext(), "올바른 이름을 입력해주세요.", Toast.LENGTH_LONG).show();
                    }else if(inputName.getText().length() == 0 ){
                        inputName.setText("");
                        Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        /*생년월일 체크*/
        inputBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    if(inputBirth.getText().length() <= 5){
                        inputBirth.setText("");
                        Toast.makeText(getApplicationContext(), "올바른 생년월일(6자리)을 입력해주세요.",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        /*남성 및 여성 선택*/

        selectGender1 = (RadioButton) findViewById(R.id.selectGender1);
        selectGender2 = (RadioButton) findViewById(R.id.selectGender2);

        selectGender1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "남성";
                setGender(gender);
            }
        });

        selectGender2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "여성";
                setGender(gender);
            }
        });
    }
}
