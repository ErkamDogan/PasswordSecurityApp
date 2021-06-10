package com.example.secapp;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secapp.Engine.PasswordEntry;

import java.util.ArrayList;

public class PasswordsActivity extends AppCompatActivity {

    protected ArrayList<PasswordEntry> passwords = new ArrayList<>();
    protected RecyclerView recyclerView;
    protected PasswordListAdapter passwordListAdapter;
    protected Button passwordAddButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwords_view);

        passwords.add(new PasswordEntry("Email", "emailpassword"));
        passwords.add(new PasswordEntry("MusicApp", "musicpassword"));
        passwords.add(new PasswordEntry("Teams", "teamspassword"));

        recyclerView = findViewById(R.id.recyclerView);
        passwordListAdapter = new PasswordListAdapter(passwords);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(passwordListAdapter);

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
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
            }
        });
    }

    public void addPassword(String appName, String password) {

    }
}
