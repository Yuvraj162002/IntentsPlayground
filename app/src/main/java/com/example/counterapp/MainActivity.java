package com.example.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.counterapp.databinding.ActivityMainBinding;

import static com.example.counterapp.Constants.QUANTITY_KEY;

public class MainActivity extends AppCompatActivity {

    public int quantity = 0;
    private  ActivityMainBinding b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       b =  ActivityMainBinding.inflate(getLayoutInflater());

       // button = findViewById(R.id.button3);
        setContentView(b.getRoot());
        getInitialCount();
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        quantity= preferences.getInt(QUANTITY_KEY,0);

        b.qty.setText(String.valueOf(quantity));




    }

    private void getInitialCount() {
         quantity = getIntent().getIntExtra("initialcount",0);
        b.qty.setText(String.valueOf(quantity));
    }

    public void deqty(View view) {
        //--qty;
        Toast.makeText(this, "Decrement  by 1", Toast.LENGTH_SHORT).show();

        b.qty.setText("" + --quantity);


    }

    public void inqty(View view) {
        //qty++;
        Toast.makeText(this, "increment by 1", Toast.LENGTH_SHORT).show();
        b.qty.setText("" + ++quantity);


    }
    @Override
    protected void onPause() {
        super.onPause();
        ///  Create the shared prefernces object...
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        preferences.edit()
                .putInt(Constants.QUANTITY_KEY,quantity)
                .apply();
    }

    public void SendBack(View view) {

        // Send The Data;
        Intent intent = new Intent();
        intent.putExtra("initialcount",quantity);
        setResult(RESULT_OK,intent);

        //Close The Activity
        finish();
    }




}


