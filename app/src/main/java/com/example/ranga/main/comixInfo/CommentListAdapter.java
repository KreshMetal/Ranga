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
import com.example.ranga.database.User;

import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder>
{
    private final LayoutInflater inflater;

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
    }

    private List<Comment> comments;
    private final OnCommentAvatarClickedListener listener;

    public CommentListAdapter(Context context, List<Comment> comments, OnCommentAvatarClickedListener listener)
    {
        inflater = LayoutInflater.from(context);
        this.comments = comments;
        this.listener = listener;
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

        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnClicked(comment.user);
            }
        });
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

    public interface OnCommentAvatarClickedListener
    {
        void OnClicked(User user);
    }
}
