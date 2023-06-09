package com.example.ranga.main.profile;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ranga.R;
import com.example.ranga.database.Comix;
import com.example.ranga.database.ComixTableQueriesHelper;
import com.example.ranga.database.Evaluation;
import com.example.ranga.main.comixList.ComixListAdapter;

import java.util.List;
import java.util.zip.Inflater;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ViewHolder>
{
    public void setEvaluations(List<Evaluation> evaluations)
    {
        this.evaluations = evaluations;
    }

    private List<Evaluation> evaluations;
    private LayoutInflater inflater;

    public ProfileListAdapter(Context context, List<Evaluation> comixes)
    {
        inflater = LayoutInflater.from(context);
        this.evaluations = comixes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.profile_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Evaluation evaluation = evaluations.get(position);
        Comix comix = ComixTableQueriesHelper.GetById(evaluation.comixId);
        holder.name.setText(comix.nameRus);
        holder.logo.setImageDrawable(Drawable.createFromPath(comix.nameFolder + "/logo.png"));
        RenderStars(holder.stars, (int)evaluation.rating);
    }

    @Override
    public int getItemCount()
    {
        return evaluations.size();
    }

    private void RenderStars(ImageView[] stars, int count)
    {
        for (int i = stars.length - 1; i >= count; i--)
        {
            stars[i].setImageResource(R.drawable.blank_star);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        final TextView name;
        final ImageView logo;
        final ImageView[] stars;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.profile_list_item_name);
            logo = itemView.findViewById(R.id.profile_list_item_logo);
            int[] ids = ((Group)itemView.findViewById(R.id.profile_list_item_stars)).getReferencedIds();
            stars = new ImageView[ids.length];
            for (int i = 0; i < ids.length; i++)
            {
                stars[i] = itemView.findViewById(ids[i]);
            }
        }
    }
}
