package com.rxjava_operators_java.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.rxjava_operators_java.R;
import com.rxjava_operators_java.model.Post;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private List<Post> posts;

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_item, parent, false);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.PostsViewHolder holder, int position) {

        holder.tvTitle.setText(posts.get(position).getTitle());
        if (posts.get(position).getComments() == null) {
            holder.tvProgress.setVisibility(View.GONE);
            holder.progress_circular.setVisibility(View.VISIBLE);
        } else {
            holder.tvProgress.setText(String.valueOf(posts.get(position).getComments().size()));
            holder.tvProgress.setVisibility(View.VISIBLE);
            holder.progress_circular.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return posts == null ? 0 : posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public void updatePosts(Post posts) {
        int position = this.posts.indexOf(posts);
        this.posts.set(position, posts);
        notifyItemChanged(position);
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView tvTitle;
        private AppCompatTextView tvProgress;
        private ProgressBar progress_circular;

        public PostsViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvProgress = itemView.findViewById(R.id.tvProgress);
            progress_circular = itemView.findViewById(R.id.progress_circular);
        }
    }

}
