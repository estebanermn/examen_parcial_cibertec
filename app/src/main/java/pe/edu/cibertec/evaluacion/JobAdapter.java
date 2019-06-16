package pe.edu.cibertec.evaluacion;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.LayoutJob> {

    static final String url_logo = "https://jobs.github.com/rails/active_storage/blobs/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBaWRoIiwiZXhwIjpudWxsLCJwdXIiOiJibG9iX2lkIn19--60b93fbdfaefa103c133026d0c07507614c1033f/Kingdotcom.png";
    List<Job> items;

    public JobAdapter(List<Job> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public LayoutJob onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.job_layout, viewGroup, false);

        LayoutJob layoutJob = new LayoutJob(view);
        return layoutJob;
    }

    @Override
    public void onBindViewHolder(@NonNull LayoutJob layoutJob, int position) {
        layoutJob.tvTitle.setText(items.get(position).getTitle());

        layoutJob.tvCompany.setText(items.get(position).getCompany());

        layoutJob.tvDescription.setText(items.get(position).getDescription());


        Glide.with(layoutJob.itemView).load(url_logo).into(layoutJob.ivLogo);
        //   + jobs.get(position).getCompany_logo()).into(layoutJob.ivLogo);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class LayoutJob extends RecyclerView.ViewHolder {

        TextView tvTitle, tvCompany, tvDescription;


        ImageView ivLogo;

        public LayoutJob(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCompany = itemView.findViewById(R.id.tvCompany);

            tvDescription = itemView.findViewById(R.id.tvDescription);

            ivLogo = itemView.findViewById(R.id.imageView);
        }
    }
}
