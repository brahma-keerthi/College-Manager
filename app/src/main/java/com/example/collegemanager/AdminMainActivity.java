package com.example.collegemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminMainActivity extends AppCompatActivity {

    Button add, update;
    AlertDialog dialog;
    LinearLayout layout;
    // Hashmap to push into firebase realtime database
    HashMap <String, Object> Slot = new HashMap<>();
    String lab ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lab = extras.getString("key");
        }

        add = findViewById(R.id.add);
        layout = findViewById(R.id.container);
        update = findViewById(R.id.update);

        buildDialog();

        // add button to add new items
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        // Update button to push the data to firebase
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("Database").child(lab).updateChildren(Slot).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if ( task.isSuccessful())
                            Toast.makeText(AdminMainActivity.this, "Updated Successfully !!!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    // Dialog of add button
    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.admin_dialog, null);

        final EditText name = view.findViewById(R.id.nameEdit);

        builder.setView(view);
        builder.setTitle("Enter Equipment/System/Tool Name : ")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String slotName = name.getText().toString();
                        Slot.put(slotName, 0);
                        addCard(slotName);
                        name.setText("");
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        dialog = builder.create();
    }

    // function of add new card
    private void addCard(String name) {
        final View view = getLayoutInflater().inflate(R.layout.admin_card, null);

        TextView nameView = view.findViewById(R.id.name);
        Switch changeOcc = view.findViewById(R.id.changeOcc);
        Switch changeStatus = view.findViewById(R.id.changeStatus);

        nameView.setText(name);

        // changeStatus gives the working
        changeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( changeStatus.isChecked() ){
                    Slot.put(name, 2);
                }
                else if ( changeOcc.isChecked() )
                    Slot.put(name, 1);
                else
                    Slot.put(name, 0);
                Toast.makeText(AdminMainActivity.this, "Status changed for " + name, Toast.LENGTH_SHORT).show();
            }
        });

        changeOcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( !changeStatus.isChecked() ) {
                    if (changeOcc.isChecked())
                        Slot.put(name, 1);
                    else
                        Slot.put(name, 0);

                    Toast.makeText(AdminMainActivity.this, "Status changed for " + name, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AdminMainActivity.this, "Can't alter status as " + name + " is not working...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        layout.addView(view);
    }
}