package com.example.ranga.main.comixInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ranga.App;
import com.example.ranga.database.Comix;
import com.example.ranga.database.Comment;
import com.example.ranga.database.CommentTableQuariesHelper;

import java.util.List;

public class ComixCommentsViewModel extends ViewModel
{
    private Comix comix;

    public LiveData<List<Comment>> getComments()
    {
        return comments;
    }

    private LiveData<List<Comment>> comments;

    public ComixCommentsViewModel(Comix comix)
    {
        this.comix = comix;
        comments = CommentTableQuariesHelper.GetByIdComix(comix.id);
    }

    public void OnSendBtnCLicked(String text)
    {
        Comment comment = new Comment();
        comment.comix = comix;
        comment.user = App.getInstance().getUser();
        comment.text = text;
        CommentTableQuariesHelper.AddComment(comment);
    }
}
