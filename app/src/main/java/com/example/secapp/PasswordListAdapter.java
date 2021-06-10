package com.example.secapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secapp.Engine.PasswordEntry;

import java.util.ArrayList;

public class PasswordListAdapter extends RecyclerView.Adapter<PasswordListAdapter.PasswordListViewHolder> {

    private ArrayList<PasswordEntry> passwordList;

    public PasswordListAdapter(ArrayList<PasswordEntry> passwordList) {
        this.passwordList = passwordList;
    }

    @NonNull
    @Override
    public PasswordListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.password_item_view, parent, false);
        return new PasswordListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordListViewHolder holder, int position) {
        PasswordEntry passwordEntry = passwordList.get(position);
        holder.appNameView.setText(passwordEntry.getAppName());
        holder.passwordView.setText(passwordEntry.getPassword());
        //Log.i("position:"," "+ position);
    }

    @Override
    public int getItemCount() {
        return passwordList.size();
    }

    public class PasswordListViewHolder extends RecyclerView.ViewHolder {
        TextView appNameView;
        TextView passwordView;

        public PasswordListViewHolder(@NonNull View itemView) {
            super(itemView);
            appNameView = (TextView) itemView.findViewById(R.id.appNameView);
            passwordView = (TextView) itemView.findViewById(R.id.passwordView);
        }
    }
}
