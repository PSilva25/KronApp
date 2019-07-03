package com.xetelas.nova;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class FireMissilesDialogFacebook extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Nesta área será usada a permissão do user_link, onde a utilizaremos como uma maneira do usuário que ira pegar a carona ter uma segurança a respeito da pessoa que está oferecendo a carona, ao clicar na logo do Facebook o usuário será direcionado para a página do perfil do Facebook do usuário do aplicativo que está oferecendo a carona.")
                .setPositiveButton("Proseguir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}