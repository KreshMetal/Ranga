package com.example.ranga;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ranga.login.CreateAccFragmentViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class EditTextFragment extends Fragment
{
    protected void SetInputViewPatterListener(TextView view, Pattern pattern, int number, EditTextFragmentViewModel model)
    {
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                Matcher matcher = pattern.matcher(charSequence);
                if (!matcher.matches())
                {
                    view.setBackgroundResource(R.drawable.error_border);
                    model.SetReadyStatus(number, false);
                }
                else
                {
                    view.setBackgroundResource(R.drawable.defolt_border);
                    model.SetReadyStatus(number, true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
