package com.example.ranga.main.comixInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ranga.R;
import com.example.ranga.reading.ReadingActivity;

import java.io.File;
import java.io.FilenameFilter;

public class ChoiseChapterFragment extends Fragment
{

    public static String PATH_ARG_ID = "PATh";
    ListView chapters;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.choise_chapter_screen, container, false);

        chapters = v.findViewById(R.id.choise_chapter_screen_list);
        String path = getArguments().getString("folder");
        File folder = new File(path);
        String[] items = InitArr(folder.listFiles().length - 1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        chapters.setAdapter(adapter);

        chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                String path = null;

                for (File chap : folder.listFiles())
                {
                    if (chap.getName().contains("Ch. " + (i+1)))
                    {
                        path = chap.getPath();
                        break;
                    }
                }

                if (path != null)
                {
                    Intent intent = new Intent(getContext(), ReadingActivity.class);
                    intent.putExtra(PATH_ARG_ID,path);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getContext(), "Глава повреждена", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    private String[] InitArr(int count)
    {
        String[] result = new String[count];
        for (int i = 0; i < count; i++)
        {
            result[i] = "Глава " + (i + 1);
        }
        return result;
    }
}