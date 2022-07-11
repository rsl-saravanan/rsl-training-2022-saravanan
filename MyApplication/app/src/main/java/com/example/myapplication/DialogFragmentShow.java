package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class DialogFragmentShow extends DialogFragment {

    public static final String ARG_TITLE = "YesNoDialog.Title";
    public static final String ARG_MESSAGE = "YesNoDialog.Message";

    private YesNoDialogFragmentListener listener;

    public interface YesNoDialogFragmentListener {
        public void onYesNoResultDialog(int resultCode, @Nullable Intent data);
    }

    public void YesNoDialogFragment() {}

    public void setOnYesNoDialogFragmentListener(YesNoDialogFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        String title = args.getString(ARG_TITLE);
        String message = args.getString(ARG_MESSAGE);

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        buttonOkClick();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        buttonNoClick();
                    }
                })
                .create();
    }

    private void buttonOkClick() {
        int resultCode = Activity.RESULT_OK;

        Bundle bundle = new Bundle();
        bundle.putString("key1", "Value 1");
        bundle.putString("key2", "Value 2");
        Intent data  = new Intent().putExtras(bundle);

        if(this.listener != null)  {
            this.listener.onYesNoResultDialog(Activity.RESULT_OK, data);
        }
        else {
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, data);
        }
    }

    private void buttonNoClick() {
        int resultCode = Activity.RESULT_CANCELED;
        Intent data  = null;

        if(this.listener != null)  {
            this.listener.onYesNoResultDialog(resultCode, data);
        }
        else {
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, data);
        }
    }

}
