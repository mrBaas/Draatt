package net.baasit.draatt;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class ActivityDraattDetail extends FragmentActivity {
	
	//First commit comment.
	String varselUrl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draatt_detail);
		
		Bundle extras = getIntent().getExtras();
		String url = extras.getString("DRAATT");
		this.varselUrl = url;
		
		Log.d("VarselUrl: ", "" + varselUrl);
		
		//Henter ut getSupportFragmentManager() i stede for getFragmentManager()
		//Støtter tidligere android versjoner
		
		FragmentManager fm = getSupportFragmentManager();
		
		if(fm.findFragmentById(android.R.id.content) == null){
			DetailsListFragment list = new DetailsListFragment();
			fm.beginTransaction().add(android.R.id.content, list).commit();
		}
		
		
		
	}
}
