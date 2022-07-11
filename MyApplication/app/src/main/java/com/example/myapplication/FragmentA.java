package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentA extends Fragment implements DialogFragmentShow.YesNoDialogFragmentListener {

    private FragmentAListener listener;
    private TextView textView;
    private EditText editText;
    private Button buttonSend;
    private static final int REQUEST_CODE_YESNO_DIALOG_FRAGMENT = 1000;

    public interface FragmentAListener {
        void onInputASent(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fragment1, container, false);

        textView = v.findViewById(R.id.text_view);
        editText = v.findViewById(R.id.edit_text);
        buttonSend = v.findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonShowClicked();
            }
        });

        return v;
    }

    private void buttonShowClicked()  {

        // Create YesNoDialogFragment
        DialogFragment dialogFragment = new DialogFragmentShow();

        // Arguments:
        Bundle args = new Bundle();
        args.putString(DialogFragmentShow.ARG_TITLE, "Confirmation");
        args.putString(DialogFragmentShow.ARG_MESSAGE, "Do you send a message?");
        dialogFragment.setArguments(args);

        Fragment targetFragment = this;

        dialogFragment.setTargetFragment(targetFragment, REQUEST_CODE_YESNO_DIALOG_FRAGMENT);

        FragmentManager fragmentManager = this.getFragmentManager();

        dialogFragment.show(fragmentManager, "Dialog");
    }

    public void updateText(CharSequence newText) {
        textView.setText(newText);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAListener) {
            listener = (FragmentAListener) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof DialogFragmentShow) {
            DialogFragmentShow yesNoDialogFragment = (DialogFragmentShow) fragment;
            yesNoDialogFragment.setOnYesNoDialogFragmentListener(this);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_YESNO_DIALOG_FRAGMENT) {
            if(resultCode == Activity.RESULT_OK) {
                CharSequence input = editText.getText();
                listener.onInputASent(input);
                Log.d(String.valueOf(data),"###Edit text");
                this.editText.getText().clear();
                Toast.makeText(this.getContext(), "You select YES", Toast.LENGTH_LONG).show();
            } else if(resultCode == Activity.RESULT_CANCELED) {
                this.editText.getText().clear();
                Toast.makeText(this.getContext(), "You select NO", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onYesNoResultDialog(int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            this.textView.setText("You select YES");
        } else if(resultCode == Activity.RESULT_CANCELED) {
            this.editText.getText().clear();
            Log.d(String.valueOf(data),"###Edit text");
        } else {
            this.textView.setText("You don't select");
        }
    }
}
