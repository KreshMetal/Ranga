package com.example.ranga.main.comixInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ranga.R;
import com.example.ranga.database.Comment;

import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder>
{
    private final LayoutInflater inflater;

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
    }

    private List<Comment> comments;

    public CommentListAdapter(Context context, List<Comment> comments)
    {
        inflater = LayoutInflater.from(context);
        this.comments = comments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = inflater.inflate(R.layout.comment, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Comment comment = comments.get(position);
        holder.avatar.setImageResource((int)comment.user.avatar);
        holder.nickname.setText(comment.user.login);
        holder.text.setText(comment.text);

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView avatar;
        private TextView nickname;
        private TextView text;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            avatar = itemView.findViewById(R.id.comment_avatar);
            nickname = itemView.findViewById(R.id.comment_nickname);
            text = itemView.findViewById(R.id.comment_text);
        }
    }
}
