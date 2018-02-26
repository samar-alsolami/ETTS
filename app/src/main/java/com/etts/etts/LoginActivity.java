package com.etts.etts;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity{
DB_Connection connect=new DB_Connection(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
    //move to main page
    public void back_to_main(View v){
        ImageButton back_butt=(ImageButton)v;
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
    //
    public void login(View v){

        if(v.getId()==R.id.login_butt){
            EditText username= (EditText)findViewById(R.id.username);
            String namestr=username.getText().toString();

            EditText password = (EditText)findViewById(R.id.password);
            String passstr=password.getText().toString();

            String passwordd =connect.searchpass(namestr);//check valid
            if(namestr.equals("administrator") && passstr.equals("123456")){
                Intent i =new Intent(LoginActivity.this,SemesterInformation.class);
                startActivity(i);
            }
            if(passstr.equals(passwordd)){
                Intent i =new Intent(LoginActivity.this,AddSchedule.class);
                i.putExtra("Username",namestr);
                startActivity(i);
            }else{
                Toast pass=Toast.makeText(LoginActivity.this,"username or password not correct , try again",Toast.LENGTH_SHORT);
                pass.show();
            }


        }
    }

}
