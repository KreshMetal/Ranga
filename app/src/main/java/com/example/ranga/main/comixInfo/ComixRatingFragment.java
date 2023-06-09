package com.example.ranga.main.comixInfo;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.ranga.App;
import com.example.ranga.R;
import com.example.ranga.database.Comix;
import com.example.ranga.database.Evaluation;
import com.example.ranga.main.ParcebleComix;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ComixRatingFragment extends Fragment
{
    private Comix comix;
    private TextView rating;
    private TextView voiceCount;
    private Group pbsG;
    private Group starsG;
    private CircleImageView avatar;
    private ProgressBar[] pbs;
    private ImageView[] stars;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.rating_info, container, false);

        comix = ((ParcebleComix)getArguments().getParcelable(ParcebleComix.class.getSimpleName())).getComix();
        rating = (TextView) view.findViewById(R.id.rating_info_rating);
        voiceCount = (TextView) view.findViewById(R.id.rating_info_voice_count);
        pbsG = (Group) view.findViewById(R.id.rating_info_PBS);
        starsG = (Group) view.findViewById(R.id.rating_info_stars);
        avatar = (CircleImageView) view.findViewById(R.id.rating_info_avatar);

        pbs = new ProgressBar[5];
        int[] tmp = pbsG.getReferencedIds();
        for (int i = 0; i < 5; i++)
        {
            pbs[i] = view.findViewById(tmp[i]);
        }

        avatar.setImageResource((int) App.getInstance().getUser().avatar);

        ComixRatingViewModelFactory factory = new ComixRatingViewModelFactory(comix);
        ComixRatingViewModel model = new ViewModelProvider(this, factory).get(ComixRatingViewModel.class);

        stars = new ImageView[5];
        tmp = starsG.getReferencedIds();
        for (int i = 0; i < 5; i++)
        {
            stars[i] = (ImageView) view.findViewById(tmp[i]);
            stars[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    int starNum = Integer.parseInt(view.getTag().toString());
                    PaintStars(starNum);
                    model.OnStarClicked(starNum);
                }
            });
        }

        model.getEvalutations().observe(getViewLifecycleOwner(), new Observer<List<Evaluation>>() {
            @Override
            public void onChanged(List<Evaluation> evaluations)
            {
                ComixRatingViewModel.Rating rtg = model.GetViewsData();
                rating.setText(rtg.rating + "");
                voiceCount.setText(rtg.voiceCount + "");
                for (int i = 0; i < 5; i++)
                {
                    pbs[i].setProgress(rtg.pbs[i]);
                }
            }
        });

        long currentStars = model.GetUserEvaluation();
        PaintStars((int)currentStars);

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_comixInfoFragment_to_profileFragment);
            }
        });

        return view;
    }

    private void PaintStars(int fillStars)
    {
        for (int i = 0; i < fillStars; i++)
        {
            stars[i].setBackgroundResource(R.drawable.fill_star);
        }

        for (int i = fillStars; i < 5; i++)
        {
            stars[i].setBackgroundResource(R.drawable.blank_star);
        }
    }
}