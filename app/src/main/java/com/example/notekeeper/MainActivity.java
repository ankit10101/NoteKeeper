package com.example.notekeeper;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText etTitle, etDesc;
    FloatingActionButton button;
    ArrayList<Note> notes = new ArrayList<Note>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.fabADD);
        final NoteAdapter noteAdapter = new NoteAdapter(notes, MainActivity.this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomAlert(noteAdapter);

//                String title = etTitle.getText().toString();
//                String description = etDesc.getText().toString();
//                Note note = new Note(title, description, String.format("dd-mm-yyyy", new Date()), 0);
//                notes.add(note);
//                noteAdapter.notifyItemInserted(notes.size() - 1);
//                recyclerView.scrollToPosition(notes.size() - 1);
//                Toast.makeText(MainActivity.this, "NOTE ADDED!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showCustomAlert(final NoteAdapter noteAdapter) {
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.view_dialog, null, false);
        AlertDialog customAlertDialog = new AlertDialog.Builder(this)
                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etTitle = dialogView.findViewById(R.id.etTitle);
                        etDesc = dialogView.findViewById(R.id.etDesc);
                        String title = etTitle.getText().toString();
                        String description = etDesc.getText().toString();
                        Note note = new Note(title, description, String.format("dd-mm-yyyy", new Date()), 0);
                        notes.add(note);
                        noteAdapter.notifyItemInserted(notes.size() - 1);
                        recyclerView.scrollToPosition(notes.size() - 1);
                        Toast.makeText(MainActivity.this, "NOTE ADDED!", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setView(dialogView).show();
        customAlertDialog.show();
    }
}
