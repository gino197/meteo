package com.zebutech.meteo.utils;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;

public class DialogAlert
{

    private AlertDialog mAlertDialog;
    private AlertDialog.Builder builder;
    private Context context;
    public DialogAlert(Context ctx, String title, String message){
        this.context = ctx;
        builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
             mAlertDialog.cancel();
          }
        });
        mAlertDialog = builder.create();
        mAlertDialog.show();
    }
}
