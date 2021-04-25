package com.example.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.counterapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
   // private TextView textview;
    //private Button button;
    private int qty = 0;
    private  ActivityMainBinding b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       b =  ActivityMainBinding.inflate(getLayoutInflater());

       // button = findViewById(R.id.button3);
        setContentView(b.getRoot());
    }

    public void deqty(View view) {
        qty--;

        b.qty.setText(" " +qty);

    }

    public void Inqty(View view) {
        qty++;

        b.qty.setText(" " +qty);

    }
}


