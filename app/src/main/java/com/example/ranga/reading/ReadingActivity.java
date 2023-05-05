package com.example.ranga.reading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.example.ranga.R;
import com.example.ranga.main.comixInfo.ChoiseChapterFragment;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ReadingActivity extends AppCompatActivity
{
    private ViewPager2 pager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        Bundle args = getIntent().getExtras();
        String path = args.getString(ChoiseChapterFragment.PATH_ARG_ID);
        File[] chapter = new File(path).listFiles();
        Arrays.sort(chapter, new Comparator<File>() {
            @Override
            public int compare(File file, File t1) {
                return file.getName().compareTo(t1.getName());
            }
        });

        ArrayList<Bitmap> pages = new ArrayList<>();
        for (int i = 0; i < chapter.length; i++)
        {
            if (chapter[i].getAbsolutePath().endsWith(".nomedia")) continue;
            pages.add(BitmapFactory.decodeFile(chapter[i].getAbsolutePath()));
        }

        pager = findViewById(R.id.reading_view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getApplicationContext(), pages);
        pager.setAdapter(adapter);
    }
}