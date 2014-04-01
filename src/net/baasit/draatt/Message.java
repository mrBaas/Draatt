package net.baasit.draatt;

import android.content.Context;
import android.widget.Toast;

public class Message {
	//Melding klasse brukt for debugging
	public static void message(Context context, String message){
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
}
