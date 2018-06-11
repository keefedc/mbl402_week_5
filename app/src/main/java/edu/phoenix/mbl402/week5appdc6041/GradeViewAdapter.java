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

public class GradeViewAdapter extends RecyclerView.Adapter<GradeViewAdapter.MyViewHolder>{

        private List<Student> studentList;
        private List<Subjects> classList;
        private List<Grades> gradeList;
        private Context mContext;

        public GradeViewAdapter(Context context, List<SsgContainer> inData){
            this.studentList = inData.get(0).getSsgList();
            this.classList = inData.get(1).getSsgList();
            this.gradeList = inData.get(2).getSsgList();
            this.mContext = context;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder  {

            public TextView gradeID, studentID, studentName, subject, score, grade;
            LinearLayout parentLayout;

            public MyViewHolder(View itemView) {
                super(itemView);
                gradeID = (TextView) itemView.findViewById(R.id.gr_id);
                studentID = (TextView) itemView.findViewById(R.id.gr_sid);
                studentName = (TextView) itemView.findViewById(R.id.gr_name);
                subject = (TextView) itemView.findViewById(R.id.gr_subject);
                score = (TextView) itemView.findViewById(R.id.gr_score);
                grade = (TextView) itemView.findViewById(R.id.gr_grade);
                parentLayout = itemView.findViewById(R.id.grade_parent_layout);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.grade_row, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.gradeID.setText(String.valueOf(gradeList.get(position).getGradeID()));
            holder.studentID.setText(String.valueOf(gradeList.get(position).getStudentID()));

            int a = gradeList.get(position).getStudentID();
            int b = 0;
            int i = 0;
            do {
                if (studentList.get(i).getStudentID() == a) {
                    b = i;
                    break;
                }

                i++;
            } while (i < classList.size());
            holder.studentName.setText(studentList.get(b).getFullName());

            a = gradeList.get(position).getClassID();
            b = 0;
            i = 0;
            do {
                if (classList.get(i).getClassID() == a) {
                    b = i;
                    break;
                }

                i++;
            } while (i < classList.size());
            holder.subject.setText(classList.get(b).getClassName());

            holder.score.setText(String.valueOf(gradeList.get(position).getScore()));
            holder.grade.setText(gradeList.get(position).getGrade());
            holder.parentLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent intent = new Intent(mContext, EditGradeActivity.class);
                    intent.putExtra("id", gradeList.get(position).getGradeID());
                    Toast.makeText(mContext, "Opening file for grade transaction #" + gradeList.get(position).getGradeID(), Toast.LENGTH_SHORT).show();
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return this.gradeList.size();
        }
}