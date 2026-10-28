package com.example.midterm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    List<StudentModel> student;
    Context context;
    DBHelper dbHelper;

    public StudentAdapter(List<StudentModel> student, Context context) {
        this.student = student;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.student_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final StudentModel studentModel = student.get(position);
        holder.textViewID.setText(Integer.toString(studentModel.getId()));
        holder.editTextName.setText(studentModel.getName());
        holder.editTextStudentID.setText(studentModel.getStudentId());
        holder.seekBarAge.setProgress(studentModel.getAge() - 17);
        holder.textViewAge.setText("Age: " + Integer.toString(studentModel.getAge()));

        holder.seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                holder.textViewAge.setText("Age: " + Integer.toString(i + 17));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strName = holder.editTextName.getText().toString().trim();
                String strStudentId = holder.editTextStudentID.getText().toString().trim();
                int intAge = holder.seekBarAge.getProgress() + 17;

                if (strName.length() <= 0 || strStudentId.length() <= 0) {
                    Toast.makeText(context, "Enter data", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.updateStudent(new StudentModel(studentModel.getId(), strName, strStudentId, intAge));
                    notifyDataSetChanged();
                    ((Activity) context).finish();
                    context.startActivity(((Activity) context).getIntent());
                }
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteStudent(studentModel.getId());
                student.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return student.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewID, textViewAge;
        EditText editTextName, editTextStudentID;
        SeekBar seekBarAge;
        Button btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(R.id.textViewID);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            editTextName = itemView.findViewById(R.id.editTextName);
            editTextStudentID = itemView.findViewById(R.id.editTextStudentID);
            seekBarAge = itemView.findViewById(R.id.seekBarAge);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}