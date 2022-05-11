package com.zebutech.meteo.activities;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import java.io.InputStream;
/************
Appelé automatique quand il y a une erreur.
*/

public class DebugActivity extends Activity {

	String[] exceptionType = {
			"",
			"",
			"",
			"",
			""

	};

	String[] errMessage= new String[]{"","","","",""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



		Intent intent = getIntent();
		String errMsg = "";
		String madeErrMsg = "";
		if(intent != null){
			errMsg = intent.getStringExtra("error");

			String[] spilt = errMsg.split("\n");
			//errMsg = spilt[0];
			try {
				for (int j = 0; j < exceptionType.length; j++) {
					if (spilt[0].contains(exceptionType[j])) {
						madeErrMsg = errMessage[j];

						int addIndex = spilt[0].indexOf(exceptionType[j]) + exceptionType[j].length();

						madeErrMsg += spilt[0].substring(addIndex, spilt[0].length());
						break;

					}
				}

				if(madeErrMsg.isEmpty()) madeErrMsg = errMsg;
			}catch(Exception e){}

		}

        AlertDialog.Builder bld = new AlertDialog.Builder(this);

		bld.setTitle("Erreur");
		bld.setMessage( madeErrMsg );
		bld.setNeutralButton("Fermer", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
		bld.create().show();

    }
}
