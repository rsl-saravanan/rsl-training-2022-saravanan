package com.example.myapplication;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/** This is simple fragment which shows text returned from fragment's argument. */
public class SecondFragment extends Fragment {

    private static String ARG_ANY_STRING = "arg_any_string";

    /**
     * Returns new instance of {@link SecondFragment}
     * @param anyString this string will be shown in this fragment.
     */
    public static SecondFragment newInstance(String anyString) {
        SecondFragment fragment = new SecondFragment();

        Bundle arguments = new Bundle(1);
        arguments.putString(ARG_ANY_STRING, anyString);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        TextView tvAnyString = view.findViewById(R.id.tvAnyString);

        Bundle arguments = getArguments();
        if (arguments != null) {
            String anyString = arguments.getString(ARG_ANY_STRING);
            tvAnyString.setText(anyString);
        }
    }
}
