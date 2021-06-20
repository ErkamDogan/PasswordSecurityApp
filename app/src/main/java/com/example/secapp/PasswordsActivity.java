package com.example.secapp;

import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secapp.Engine.DatabaseHelper;
import com.example.secapp.Engine.PasswordEntry;

import java.util.ArrayList;

public class PasswordsActivity extends AppCompatActivity {

    protected ArrayList<PasswordEntry> passwords = new ArrayList<>();
    protected RecyclerView recyclerView;
    protected PasswordListAdapter passwordListAdapter;
    protected Button passwordAddButton;
    protected Button changePinButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwords_view);


        recyclerView = findViewById(R.id.recyclerView);
        passwordListAdapter = new PasswordListAdapter(passwords, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(passwordListAdapter);

       /*addPassword("Email", "emailpassword");
        addPassword("MusicApp", "musicpassword");
        addPassword("Teams", "teamspassword");*/

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        passwords.addAll(db.getPaswordList());
        db.close();
        passwordListAdapter.notifyDataSetChanged();

        passwordAddButton = findViewById(R.id.addPasswordButton);
        passwordAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                LayoutInflater layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                View popUpView = layoutInflater.inflate(R.layout.password_input_popup, null);

                int widht = 350;
                int height = 270;

                PopupWindow popupWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

                EditText inputAppName = popUpView.findViewById(R.id.popUpInputApp);
                EditText inputPasssword = popUpView.findViewById(R.id.popUpInputPass);
                Button submitButton = popUpView.findViewById(R.id.popUpInputSubmit);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (inputAppName.getText().length() > 0 && inputPasssword.getText().length() > 0) {
                            addPassword(inputAppName.getText().toString(), inputPasssword.getText().toString());
                            popupWindow.dismiss();
                        }
                    }
                });

                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
            }
        });

        changePinButton = findViewById(R.id.changePinButton);
        changePinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                View popUpView = layoutInflater.inflate(R.layout.pin_edit_popup, null);

                int widht = 350;
                int height = 270;

                PopupWindow popupWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

                EditText pinInput = popUpView.findViewById(R.id.popUpPinInput);
                pinInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                EditText pinConfInput = popUpView.findViewById(R.id.popUpPinConfInput);
                pinConfInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                Button pinEditButton = popUpView.findViewById(R.id.popUpPinEditButton);

                pinEditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (pinInput.getText().length() == 6 && pinInput.getText().toString().equals(pinConfInput.getText().toString())) {
                            changePin(pinInput.getText().toString());
                            popupWindow.dismiss();
                        }
                    }
                });

                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
            }
        });
    }

    public void addPassword(String appName, String password) {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        long result = db.addPassword(appName,password);
        passwords.add(new PasswordEntry(appName, password,Long.toString(result)));
        db.close();
        passwordListAdapter.notifyDataSetChanged();
        Toast.makeText(PasswordsActivity.this, "Password Entry added!", Toast.LENGTH_LONG).show();

    }
    public void changePin(String pin) {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        long result = db.changePin(pin);
        db.close();
        //Log.i("Pin", "new pin" + db.getPin());
        Toast.makeText(PasswordsActivity.this, "PIN Updated!", Toast.LENGTH_LONG).show();

    }
}
