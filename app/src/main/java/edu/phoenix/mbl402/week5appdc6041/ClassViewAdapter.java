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

public class ClassViewAdapter extends RecyclerView.Adapter<ClassViewAdapter.MyViewHolder>{


        private List<Subjects> classList;
        private Context mContext;

        public ClassViewAdapter(Context context, SsgContainer subjects){
            this.classList = subjects.getSsgList();
            mContext = context;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder  {

            public TextView className, classID;
            LinearLayout parentLayout;

            public MyViewHolder(View itemView) {
                super(itemView);
                className = (TextView) itemView.findViewById(R.id.sub_subject);
                classID = (TextView) itemView.findViewById(R.id.sub_sid);
                parentLayout = itemView.findViewById(R.id.class_parent_layout);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.class_row, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.className.setText(classList.get(position).getClassName());
            holder.classID.setText(String.valueOf(classList.get(position).getClassID()));
            holder.parentLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent intent = new Intent(mContext, EditSubjectActivity.class);
                    intent.putExtra("id", classList.get(position).getClassID());
                    Toast.makeText(mContext, "Opening file for " + classList.get(position).getClassName(), Toast.LENGTH_SHORT).show();
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return this.classList.size();
        }
}



