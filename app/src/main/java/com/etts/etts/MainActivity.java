package com.etts.etts;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void login(View v){
        Button button2 =(Button) v;
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
    public void signup(View v){
        Button button2 =(Button) v;
        startActivity(new Intent(MainActivity.this, SignupActivity.class));
    }
}
