package com.etts.etts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;



public class SignupActivity extends AppCompatActivity {
DB_Connection connect =new DB_Connection(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

    }
    //move to main page
    public void back_to_main(View v){
        ImageButton back_butt=(ImageButton)v;
        startActivity(new Intent(SignupActivity.this, MainActivity.class));
    }

    public void onsignupclick(View v) {
        if (v.getId() == R.id.sign_butt) {
            EditText username= (EditText)findViewById(R.id.username);
            EditText Email= (EditText)findViewById(R.id.Email);
            EditText Password= (EditText)findViewById(R.id.password);
            EditText conf_password= (EditText)findViewById(R.id.conf_password);
            String namestr=username.getText().toString();
            String emailstr=Email.getText().toString();
            String passstr=Password.getText().toString();
            String confstr=conf_password.getText().toString();
            //To ensure that all fields not empty
            if(namestr.equals("")|| emailstr.equals("") || passstr.equals("")||confstr.equals("") ){
                Toast pass=Toast.makeText(SignupActivity.this,"Please fill all fields",Toast.LENGTH_SHORT);
                pass.show();
            }
            //validate kau id
            else if(namestr.length()!=7 ){
                Toast pass=Toast.makeText(SignupActivity.this,"kau id must be seven digit",Toast.LENGTH_SHORT);
                pass.show();
            }
            //validate kau email
            else if(!emailstr.endsWith("@stu.kau.edu.sa") ){
                Toast pass=Toast.makeText(SignupActivity.this,"Please enter correct email",Toast.LENGTH_SHORT);
                pass.show();
            }
            //validate if password match confirm password
            else if(!passstr.equals(confstr)){
                Toast pass=Toast.makeText(SignupActivity.this,"Password don't match",Toast.LENGTH_SHORT);
                pass.show();
            }else{
                //insert data into database
                users_account u =new users_account();
                u.setId(namestr);
                u.setEmail(emailstr);
                u.setPassword(passstr);
                connect.insertaccount(u);
                Toast pass=Toast.makeText(SignupActivity.this,"Sign up done successfully",Toast.LENGTH_SHORT);
                pass.show();
                Intent i =new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
            }
        }
    }

}
