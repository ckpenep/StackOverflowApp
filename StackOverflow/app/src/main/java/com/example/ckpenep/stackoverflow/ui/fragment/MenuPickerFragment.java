package com.example.ckpenep.stackoverflow.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.common.InterfaceCommunicator;

public class MenuPickerFragment extends DialogFragment {

    public InterfaceCommunicator interfaceCommunicator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interfaceCommunicator = (InterfaceCommunicator) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement InterfaceCommunicator");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                //.setTitle(R.string.city)
                .setItems(R.array.menu_actions, (dialog, which) -> {
                    interfaceCommunicator.sendRequestCode(which);
                    dialog.dismiss();
                })
                .create();
    }
}
