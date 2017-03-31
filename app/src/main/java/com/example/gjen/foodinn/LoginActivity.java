package com.example.gjen.foodinn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText etAcc, etPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findviews();
    }

    public void findviews(){
        etAcc = (EditText)findViewById(R.id.editText);
        etPw = (EditText) findViewById(R.id.editText2);
    }

    public void onEnter(View v){
        Intent it = new Intent(LoginActivity.this, Main2Activity.class);
        startActivity(it);
        LoginActivity.this.finish();
    }

    public void onCancel(View v){

    }

}
