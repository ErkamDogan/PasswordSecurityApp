package com.example.secapp;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secapp.Engine.DatabaseHelper;
import com.example.secapp.Engine.PasswordEntry;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class PasswordListAdapter extends RecyclerView.Adapter<PasswordListAdapter.PasswordListViewHolder> {

    private ArrayList<PasswordEntry> passwordList;
    private Context context;

    public PasswordListAdapter(ArrayList<PasswordEntry> passwordList, Context context) {
        this.passwordList = passwordList;
        this.context = context;
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

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
                View popUpView = layoutInflater.inflate(R.layout.password_edit_popup, null);

                PopupWindow popupWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

                EditText inputAppName = popUpView.findViewById(R.id.popUpEditApp);
                EditText inputPasssword = popUpView.findViewById(R.id.popUpEditPass);
                Button editButton = popUpView.findViewById(R.id.popUpEditEdit);

                inputAppName.setText(passwordList.get(holder.getAdapterPosition()).getAppName());
                inputPasssword.setText(passwordList.get(holder.getAdapterPosition()).getPassword());

                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (inputAppName.getText().length() > 0 && inputPasssword.getText().length() > 0) {
                            passwordList.set(holder.getAdapterPosition(),
                                    new PasswordEntry(inputAppName.getText().toString(), inputPasssword.getText().toString(),"0"));
                            notifyDataSetChanged();
                            DatabaseHelper db = new DatabaseHelper(context);
                            db.updatePassword(passwordEntry.getId(), inputAppName.getText().toString(), inputPasssword.getText().toString());
                            db.close();
                            popupWindow.dismiss();
                        }
                    }
                });

                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
                View popUpView = layoutInflater.inflate(R.layout.confirmation_popup_yes_no, null);

                PopupWindow popupWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

                Button yesButton = popUpView.findViewById(R.id.yesButton);
                Button noButton = popUpView.findViewById(R.id.noButton);

                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeItem(holder.getAdapterPosition());
                        DatabaseHelper db = new DatabaseHelper(context);
                        db.deletePassword(passwordEntry.getId());
                        db.close();
                        popupWindow.dismiss();
                    }
                });

                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });


                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
            }
        });

        holder.seeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Input Type", String.valueOf(holder.passwordView.getInputType()));
                if (holder.passwordView.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    holder.passwordView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    holder.passwordView.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return passwordList.size();
    }

    private void removeItem(int position) {
        passwordList.remove(position);
        notifyItemRemoved(position);
    }

    public class PasswordListViewHolder extends RecyclerView.ViewHolder {
        TextView appNameView;
        TextView passwordView;
        ImageButton editButton;
        ImageButton deleteButton;
        ImageButton seeButton;

        public PasswordListViewHolder(@NonNull View itemView) {
            super(itemView);
            appNameView = (TextView) itemView.findViewById(R.id.appNameView);
            passwordView = (TextView) itemView.findViewById(R.id.passwordView);
            editButton = (ImageButton) itemView.findViewById(R.id.editButton);
            deleteButton = (ImageButton) itemView.findViewById(R.id.deleteButton);
            seeButton = (ImageButton) itemView.findViewById(R.id.seeButton);

            passwordView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }
}
