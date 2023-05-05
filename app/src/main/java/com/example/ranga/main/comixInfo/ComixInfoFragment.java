package com.example.ranga.main.comixInfo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.Navigation;

import com.example.ranga.R;
import com.example.ranga.database.Comix;
import com.example.ranga.main.ParcebleComix;

public class ComixInfoFragment extends Fragment
{
    private Comix comix;
    private ImageView logo;
    private TextView rusName;
    private TextView engName;
    private TextView desc;
    private Button readBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.comix_info_screen, container, false);

        logo = (ImageView) view.findViewById(R.id.comix_info_screen_logo);
        rusName = (TextView) view.findViewById(R.id.comix_info_screen_name);
        engName = (TextView) view.findViewById(R.id.comix_info_screen_name_eng);
        desc = (TextView) view.findViewById(R.id.comix_info_screen_desc);
        readBtn = (Button) view.findViewById(R.id.comix_info_screen_read_btn);

        comix = ((ParcebleComix)getArguments().getParcelable(ParcebleComix.class.getSimpleName())).getComix();

        logo.setImageDrawable(Drawable.createFromPath(comix.nameFolder + "/logo.png"));
        rusName.setText(comix.nameRus);
        engName.setText(comix.nameEng);
        desc.setText(comix.desc);

        Bundle args = new Bundle();
        args.putParcelable(ParcebleComix.class.getSimpleName(), new ParcebleComix(comix));
        ComixRatingFragment fragment = new ComixRatingFragment();
        fragment.setArguments(args);
        getFragmentManager().beginTransaction().add(R.id.comix_info_screen_rating, fragment).commit();

        ComixCommentsFragment comFragment = new ComixCommentsFragment();
        comFragment.setArguments(args);
        getFragmentManager().beginTransaction().add(R.id.comix_info_screen_comments, comFragment).commit();

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("folder", comix.nameFolder);
                Navigation.findNavController(view).navigate(R.id.action_comixInfoFragment_to_choiseChapterFragment, args);
            }
        });
        return view;
    }
}
