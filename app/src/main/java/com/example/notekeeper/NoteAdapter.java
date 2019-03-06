package com.example.notekeeper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private ArrayList<Note> notesArrayList = new ArrayList<>();
    private Context context;
    private NoteDatabase db;

    public NoteAdapter(ArrayList<Note> objectsArrayList, Context context, NoteDatabase db) {
        this.notesArrayList = objectsArrayList;
        this.context = context;
        this.db = db;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_row, viewGroup, false);
        return new NoteHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull NoteHolder viewHolder, int position) {

        Note currentNote = notesArrayList.get(position);
        viewHolder.tvTitle.setText(notesArrayList.get(position).getTitle());
        viewHolder.tvDesc.setText(notesArrayList.get(position).getDescription());
        viewHolder.tvTime.setText(notesArrayList.get(position).getTime());
        //To give random color to each note

        if (currentNote.getColor() == 0) {
            Random r = new Random();
            int red = r.nextInt(256);
            int blue = r.nextInt(256);
            int green = r.nextInt(256);
            viewHolder.linearLayout.setBackgroundColor(Color.argb(255, red, blue, green));
            currentNote.setColor(Color.argb(255, red, blue, green));
        } else {
            viewHolder.linearLayout.setBackgroundColor(currentNote.getColor());
        }
    }

    @Override
    public int getItemCount() {
        return notesArrayList.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc, tvTime;
        LinearLayout linearLayout;

        public NoteHolder(@NonNull final View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvTime = itemView.findViewById(R.id.tvTime);

            linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    Note itemToBeRemoved = notesArrayList.get(position);
                    notesArrayList.remove(position);
                    db.noteDao().deleteNote(itemToBeRemoved);
                    return true;
                }
            });

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, OpenedNote.class);
                    int colorId = notesArrayList.get(getAdapterPosition()).getColor();
                    Bundle extras = new Bundle();
                    extras.putString("Title", tvTitle.getText().toString());
                    extras.putString("Description", tvDesc.getText().toString());
                    extras.putInt("Color", colorId);
                    i.putExtras(extras);
                    context.startActivity(i);
                }
            });
        }
    }
}
