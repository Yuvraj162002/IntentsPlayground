package com.example.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        getInitialCount();
    }

    private void getInitialCount() {
         qty = getIntent().getIntExtra("initialcount",0);
        b.qty.setText(String.valueOf(qty));
    }

    public void deqty(View view) {
        //--qty;
        Toast.makeText(this, "Decrement  by 1", Toast.LENGTH_SHORT).show();

        b.qty.setText("" + --qty);


    }

    public void inqty(View view) {
        //qty++;
        Toast.makeText(this, "increment by 1", Toast.LENGTH_SHORT).show();
        b.qty.setText("" + ++qty);


    }

    public void SendBack(View view) {

        // Send The Data;
        Intent intent = new Intent();
        intent.putExtra("initialcount",qty);
        setResult(RESULT_OK,intent);

        //Close The Activity
        finish();
    }


}


