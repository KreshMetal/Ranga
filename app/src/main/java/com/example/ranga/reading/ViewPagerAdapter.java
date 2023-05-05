package com.example.ranga.reading;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ranga.R;

import java.util.List;
import java.util.zip.Inflater;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>
{
    private LayoutInflater infalter;
    private List<Bitmap> pages;

    public ViewPagerAdapter(Context context, List<Bitmap> pages)
    {
        infalter = LayoutInflater.from(context);
        this.pages = pages;
    }

    @NonNull
    @Override
    public ViewPagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = infalter.inflate(R.layout.reading_page, parent, false);
        return new ViewPagerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerHolder holder, int position)
    {
        holder.page.setImageBitmap(pages.get(position));
    }

    @Override
    public int getItemCount()
    {
        return pages.size();
    }

    public class ViewPagerHolder extends RecyclerView.ViewHolder
    {
        ImageView page;
        public ViewPagerHolder(@NonNull View itemView)
        {
            super(itemView);
            page = itemView.findViewById(R.id.page);
        }
    }
}
