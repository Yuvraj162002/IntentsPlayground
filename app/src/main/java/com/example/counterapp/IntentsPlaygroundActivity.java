package com.example.counterapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.counterapp.databinding.ActivityIntentsPlaygroundBinding;

public class IntentsPlaygroundActivity extends AppCompatActivity {

    private static final int REQUEST_COUNT = 0 ;
    ActivityIntentsPlaygroundBinding b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityIntentsPlaygroundBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setupHideErrorForEditText();
    }

    private void setupHideErrorForEditText() {
        TextWatcher myText = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                   hideError();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        b.Data.getEditText().addTextChangedListener(myText);
        b.InitialCount.getEditText().addTextChangedListener(myText);
    }

    public void OpenMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void SendImplictIntent(View view) {
        String input = b.Data.getEditText().getText().toString().trim();
        if (input.isEmpty()) {
            b.Data.setError("Please enter something");
            return;
        }

        int type = b.intentTypeRadioGroup.getCheckedRadioButtonId();

        if (type == R.id.OpenWebpageRadioBut) {
            OpenWebpage(input);
        }
        else if (type == R.id.DialButtonRadioBut){
            DialButton(input);
        }
        else if ( type == R.id.ShareTextRadioBut){
          ShareText(input);
    }
        else {
            Toast.makeText(this, "Please select one button", Toast.LENGTH_SHORT).show();
        }
    }

    public void SendData(View view) {
      String input = b.InitialCount.getEditText().getText().toString().trim();
      if (input.isEmpty()){
          b.InitialCount.setError("Please Enter Number");
          return;
      }
      int initialcount = Integer.parseInt(input);

      Intent intent = new Intent(this,MainActivity.class);
      intent.putExtra("initialcount",initialcount);
      startActivityForResult(intent,REQUEST_COUNT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_COUNT && resultCode == RESULT_OK){
            int count = data.getIntExtra("initialcount",0);
            b.result.setText("Final count received"+count);
        }
    }


    //// Implict Intent...
    private void OpenWebpage(String url) {
        if (!url.matches("^((https?|ftp|file):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$")){
            b.Data.setError("Please enter valid URL!");
            return;
        }
        Uri uri = Uri.parse(url);
        Intent intent1 = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent1);

        hideError();
    }
    private void DialButton(String number) {
        if(!number.matches("\\d{10}$")){
            b.Data.setError("Please enter valid Number!");
            return;
        }

        Uri uri = Uri.parse("tell:"+number);
        Intent intent2 = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent2);
        hideError();
    }

   private void ShareText(String text) {
       Intent intent2 = new Intent(); intent2.setAction(Intent.ACTION_SEND);
       intent2.setType("text/plain");
       intent2.putExtra(Intent.EXTRA_TEXT, "Your text here" );
       startActivity(Intent.createChooser(intent2, "Share via"));

    }

    ///Use to hide error...
    private void hideError() {


        b.Data.setError(null);
    }


}