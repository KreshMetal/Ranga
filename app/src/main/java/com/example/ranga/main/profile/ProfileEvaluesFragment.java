package com.example.ranga.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ranga.R;
import com.example.ranga.database.Evaluation;
import com.example.ranga.database.User;
import com.example.ranga.main.ParcebleUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileEvaluesFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.profile_evlues_selection_layout, container, false);

        User user = ((ParcebleUser)getArguments().getParcelable("user")).getUser();
        ProfileEvaluesViewModelFactory factory = new ProfileEvaluesViewModelFactory(user);
        ProfileEvaluesViewModel model = new ViewModelProvider(this, factory).get(ProfileEvaluesViewModel.class);

        RecyclerView list = view.findViewById(R.id.profile_evalues_section_list);
        ProfileListAdapter adapter = new ProfileListAdapter(getContext(), new ArrayList<>());
        model.getEvalutations().observe(getViewLifecycleOwner(), new Observer<List<Evaluation>>() {
            @Override
            public void onChanged(List<Evaluation> evaluations) {
                adapter.setEvaluations(evaluations);
                adapter.notifyDataSetChanged();
            }
        });
        list.setAdapter(adapter);
        return  view;
    }
}
