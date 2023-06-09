package com.example.ranga.main.comixInfo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ranga.App;
import com.example.ranga.R;
import com.example.ranga.database.Comix;
import com.example.ranga.database.Comment;
import com.example.ranga.database.User;
import com.example.ranga.database.UsersTableQueriesHelper;
import com.example.ranga.main.ParcebleComix;
import com.example.ranga.main.ParcebleUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComixCommentsFragment extends Fragment
{

    private TextView commentInput;
    private ImageView sendBtn;
    private RecyclerView list;
    private TextView blankListMsg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.comments_selection, container, false);

        commentInput = view.findViewById(R.id.comments_selection_add_comment_text);
        sendBtn = view.findViewById(R.id.comments_selection_add_comment_btn);
        list = view.findViewById(R.id.comments_selection_comments);
        blankListMsg = view.findViewById(R.id.comments_selection_blank_list);

        Comix comix = ((ParcebleComix)getArguments().getParcelable(ParcebleComix.class.getSimpleName())).getComix();
        Log.d("TEST", comix.id + "");
        CommentListAdapter adapter = new CommentListAdapter(getContext(), new ArrayList<Comment>(), new CommentListAdapter.OnCommentAvatarClickedListener() {
            @Override
            public void OnClicked(User user)
            {
                if (user.id != App.getInstance().getUser().id)
                {
                    Bundle args = new Bundle();
                    User usr = UsersTableQueriesHelper.GetUserFromBd(user.login);
                    args.putParcelable(ParcebleUser.class.getSimpleName(),new ParcebleUser(usr));
                    Navigation.findNavController(view).navigate(R.id.action_comixInfoFragment_to_profileFragment, args);
                }
                else
                {
                    Navigation.findNavController(view).navigate(R.id.action_comixInfoFragment_to_profileFragment);
                }
            }
        });
        ComixCommentsViewModelFactory factory = new ComixCommentsViewModelFactory(comix);
        ComixCommentsViewModel model = new ViewModelProvider(this, factory).get(ComixCommentsViewModel.class);

        model.getComments().observe(getViewLifecycleOwner(), new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments)
            {
                Collections.reverse(comments);
                adapter.setComments(comments);
                adapter.notifyDataSetChanged();
                if (comments.size() != 0 && blankListMsg.getVisibility() == View.VISIBLE)
                {
                    blankListMsg.setVisibility(View.INVISIBLE);
                }
                else if (comments.size() == 0 && blankListMsg.getVisibility() == View.INVISIBLE)
                {
                    blankListMsg.setVisibility(View.VISIBLE);
                }
            }
        });

        list.setAdapter(adapter);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = commentInput.getText().toString();
                if (text.length() > 0)
                    model.OnSendBtnCLicked(commentInput.getText().toString());
                commentInput.setText("");
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        return view;
    }
}
