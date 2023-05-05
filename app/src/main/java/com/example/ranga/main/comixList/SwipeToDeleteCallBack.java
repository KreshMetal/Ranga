package com.example.ranga.main.comixList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ranga.R;

public class SwipeToDeleteCallBack extends ItemTouchHelper.Callback
{
    private ComixListAdapter adapter;
    private ColorDrawable swipeBack = new ColorDrawable(Color.parseColor("#FF0000"));
    private Drawable deleteIcon;
    public SwipeToDeleteCallBack (ComixListAdapter adapter)
    {
        this.adapter = adapter;
        this.deleteIcon = ActivityCompat.getDrawable(adapter.getInflater().getContext(), R.drawable.delete_icon);
    }
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder)
    {
        return makeMovementFlags(0, ItemTouchHelper.LEFT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
    {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
    {
        adapter.DeleteItem(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive)
    {
        View itemView = viewHolder.itemView;
        int iconMargin = (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;

        if (dX < 0)
        {
            swipeBack.setBounds(itemView.getRight() + (int)dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
            deleteIcon.setBounds(itemView.getRight() - iconMargin - deleteIcon.getIntrinsicWidth(), itemView.getTop() + iconMargin,
                                        itemView.getRight() - iconMargin, itemView.getBottom() - iconMargin);
        }

        swipeBack.draw(c);
        c.save();

        c.clipRect(itemView.getRight() + (int)dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        deleteIcon.draw(c);

        c.restore();

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
