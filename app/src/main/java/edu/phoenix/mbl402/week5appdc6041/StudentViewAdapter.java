package edu.phoenix.mbl402.week5appdc6041;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StudentViewAdapter extends RecyclerView.Adapter<StudentViewAdapter.MyViewHolder>{


        private List<Student> studentList;
        private Context mContext;

        public StudentViewAdapter (Context context, SsgContainer student){
            this.studentList = student.getSsgList();
            mContext = context;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder  {

            public TextView firstName, lastName, studentID;
            LinearLayout parentLayout;

            public MyViewHolder(View itemView) {
                super(itemView);
                firstName = (TextView) itemView.findViewById(R.id.st_firstName);
                lastName = (TextView) itemView.findViewById(R.id.st_lastName);
                studentID = (TextView) itemView.findViewById(R.id.st_sid);
                parentLayout = itemView.findViewById(R.id.student_parent_layout);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.student_row, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.firstName.setText(studentList.get(position).getFirstName());
            holder.lastName.setText(studentList.get(position).getLastName());
            holder.studentID.setText(String.valueOf(studentList.get(position).getStudentID()));
            holder.parentLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent intent = new Intent(mContext, EditStudentActivity.class);
                    intent.putExtra("id", studentList.get(position).getStudentID());
                    Toast.makeText(mContext, "Opening file for " + studentList.get(position).getFullName(), Toast.LENGTH_SHORT).show();
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return this.studentList.size();
        }
}



