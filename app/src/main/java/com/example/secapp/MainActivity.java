package com.example.secapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText pinField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin_view);
        pinField = findViewById(R.id.pinField);
        pinField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        pinField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            if (pinField.getText().length() != 6) {
                                //TODO
                                //print an error message
                            } else {
                                pinAction();
                            }
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        int textCount = 0;
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    private void pinAction() {
        int rawPin = Integer.parseInt(pinField.getText().toString());
        //Log.i("PIN", "getPin: " + rawPin);
        Intent intent = new Intent(MainActivity.this, PasswordsActivity.class);
        startActivity(intent);
    }
}