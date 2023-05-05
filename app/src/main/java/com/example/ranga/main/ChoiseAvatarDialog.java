package com.example.ranga.main;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.ranga.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChoiseAvatarDialog
{
    private final Context mContext;
    private final Integer[] mAvatarList =
            {
                    R.drawable.avatar1,
                    R.drawable.avatar2,
                    R.drawable.avatar3,
                    R.drawable.avatar4,
                    R.drawable.avatar5,
                    R.drawable.avatar6,
                    R.drawable.avatar7,
                    R.drawable.avatar8,
                    R.drawable.avatar9,
                    R.drawable.avatar10,
                    R.drawable.avatar11,
                    R.drawable.avatar12,
                    R.drawable.avatar13,
                    R.drawable.avatar14,
                    R.drawable.avatar15,
                    R.drawable.avatar16,
                    R.drawable.avatar17,
                    R.drawable.avatar18,
                    R.drawable.avatar19,
                    R.drawable.avatar20,
                    R.drawable.avatar21,
                    R.drawable.avatar22,
                    R.drawable.avatar23,
                    R.drawable.avatar24,
                    R.drawable.avatar25,
            }; // Массив ресурсов картинок
    private final OnAvatarSelectedListener mListener;

    public ChoiseAvatarDialog(Context context, @NonNull OnAvatarSelectedListener listener)
    {
        mContext = context;
        mListener = listener;
    }

    public void show()
    {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.choise_avatar_dialog);
        GridView grid = dialog.findViewById(R.id.avatarGridView);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        AvatarAdapter adapter = new AvatarAdapter();
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mListener.onAvatarSelected(mAvatarList[i]);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private class AvatarAdapter extends ArrayAdapter<Integer> {

        public AvatarAdapter()
        {
            super(mContext, R.layout.circle_avatar, mAvatarList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.circle_avatar, parent, false);
            }

            CircleImageView imageView = convertView.findViewById(R.id.circle_avatar_avatar);
            imageView.setImageResource(mAvatarList[position].intValue());

            return convertView;
        }
    }

    public interface OnAvatarSelectedListener {
        void onAvatarSelected(int avatarResId);
    }
}
