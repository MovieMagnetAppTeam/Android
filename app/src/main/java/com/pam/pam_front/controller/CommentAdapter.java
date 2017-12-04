package com.pam.pam_front.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pam.pam_front.R;
import com.pam.pam_front.model.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<Comment> commentList;

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.comment_movie_card, viewGroup, false);

        return new CommentAdapter.CommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder commentViewHolder, int position) {
        Comment comment = commentList.get(position);
        commentViewHolder.commentAuthorNameTextView.setText(comment.author_name);
        commentViewHolder.commentDescriptionTextView.setText(String.valueOf(comment.review_id));

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView commentAuthorNameTextView;
        TextView commentDescriptionTextView;
        ImageView authorImage;

        CommentViewHolder(View v) {
            super(v);
            commentAuthorNameTextView =  (TextView) v.findViewById(R.id.authorName);
            commentDescriptionTextView = (TextView)  v.findViewById(R.id.commentDescription);
            authorImage = (ImageView)  v.findViewById(R.id.authorPhoto);
        }
    }
}
