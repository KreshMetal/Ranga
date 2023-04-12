package com.example.ranga.main.comixList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ranga.R;
import com.example.ranga.database.Comix;

import java.util.List;

public class ComixListAdapter extends RecyclerView.Adapter<ComixListAdapter.ViewHolder>
{
    private final LayoutInflater inflater;

    private final OnListItemWasCLicked clickListener;

    private List<Comix> cards;

    public interface OnListItemWasCLicked
    {
        void onListItemWasCLicked(Comix comix, int position);
    }

    public ComixListAdapter (Context context, List<Comix> cards, @NonNull OnListItemWasCLicked clickListener)
    {
        this.inflater = LayoutInflater.from(context);
        this.cards = cards;
        this.clickListener = clickListener;
    }

    public void setCards(List<Comix> cards)
    {
        this.cards = cards;
    }

    @NonNull
    @Override
    public ComixListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = inflater.inflate(R.layout.comix_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComixListAdapter.ViewHolder holder, int position)
    {
        Comix comix = cards.get(position);
        holder.name.setText(comix.nameRus);
        holder.desc.setText(comix.desc);
        holder.logo.setImageDrawable(Drawable.createFromPath(comix.nameFolder + "/logo.png"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onListItemWasCLicked(comix, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return cards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        final ImageView logo;
        final TextView name;
        final TextView desc;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            logo = (ImageView) itemView.findViewById(R.id.comix_card_logo);
            name = (TextView) itemView.findViewById(R.id.comix_card_name);
            desc = (TextView) itemView.findViewById(R.id.comix_card_desc);
        }
    }
}
