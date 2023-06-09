package com.example.ranga.main.comixList;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ranga.R;
import com.example.ranga.database.Comix;
import com.example.ranga.main.ParcebleComix;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ComixListFragment extends Fragment
{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.comix_list_screen, container, false);

        RecyclerView comixList = (RecyclerView) view.findViewById(R.id.comix_list_screen_list);
        comixList.addItemDecoration(new ItemSpacingDecoration(16));
        ComixListAdapter adapter = new ComixListAdapter(getContext(), new ArrayList<Comix>(), new ComixListAdapter.OnListItemWasCLicked() {
            @Override
            public void onListItemWasCLicked(Comix comix, int position)
            {
                Bundle args = new Bundle();
                args.putParcelable(ParcebleComix.class.getSimpleName(), new ParcebleComix(comix));
                Navigation.findNavController(view).navigate(R.id.action_COmixListFragment_to_comixInfoFragment, args);
            }
        });

        ComixListViewModel model = new ViewModelProvider(this).get(ComixListViewModel.class);
        model.getComixListState().observe(getViewLifecycleOwner(), new Observer<List<Comix>>()
        {
            @Override
            public void onChanged(List<Comix> comixes) {
                adapter.setCards(comixes);
                adapter.notifyDataSetChanged();
            }
        });
        comixList.setAdapter(adapter);

        SwipeToDeleteCallBack swipeToDeleteCallBack = new SwipeToDeleteCallBack(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallBack);
        itemTouchHelper.attachToRecyclerView(comixList);

        ((FloatingActionButton) view.findViewById(R.id.comix_list_screen_floating_Btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_comixListFragment_to_addComixFragment);
            }
        });
        return view;
    }



}
