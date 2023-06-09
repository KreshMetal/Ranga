package com.example.ranga.main.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentController;

import com.example.ranga.App;
import com.example.ranga.R;
import com.example.ranga.database.User;
import com.example.ranga.database.UsersTableQueriesHelper;
import com.example.ranga.main.ParcebleUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment
{
    private CircleImageView avatar;
    private TextView name;
    private TextView status;
    private Button editStatusBtn;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.profile_layout, container, false);
        avatar = view.findViewById(R.id.profile_avatar);
        name = view.findViewById(R.id.profile_name);
        status = view.findViewById(R.id.profile_status);
        editStatusBtn = view.findViewById(R.id.profile_edit_status_btn);

        if (getArguments() != null)
        {
            editStatusBtn.setVisibility(View.GONE);
            user = ((ParcebleUser)getArguments().getParcelable(ParcebleUser.class.getSimpleName())).getUser();
        }
        else
        {
            user = App.getInstance().getUser();
        }

        avatar.setImageResource((int)user.avatar);
        name.setText(user.login);
        if (!user.status.equals(""))
            status.setText(user.status);

        editStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View v = inflater.inflate(R.layout.edit_status_dialog_layout, null);
                builder.setView(v);

                EditText textEdit = v.findViewById(R.id.edit_status_dialog_text);
                builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String text = textEdit.getText().toString();
                        if (!text.equals(""))
                        {
                            user.status = text;
                            status.setText(text);
                            UsersTableQueriesHelper.UpdateUser(user);
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
                dialog.show();
            }
        });

        Bundle args = new Bundle();
        args.putParcelable("user", new ParcebleUser(user));
        ProfileEvaluesFragment fragment = new ProfileEvaluesFragment();
        fragment.setArguments(args);
        getFragmentManager().beginTransaction().add(R.id.profile_evalues_section, fragment).commit();

        return view;
    }
}
