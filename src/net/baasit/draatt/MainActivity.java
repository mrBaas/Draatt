package net.baasit.draatt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.baasit.draatt.HeaderDraattFragment.myLocationListener;

import android.content.Context;
import android.database.SQLException;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Henter ut getSupportFragmentManager() i stede for getFragmentManager()
		//Støtter tidligere android versjoner
		FragmentManager fm = getSupportFragmentManager();
		
		if(fm.findFragmentById(android.R.id.content) == null){
			DataListFragment list = new DataListFragment();
			fm.beginTransaction().add(android.R.id.content, list).commit();
		}
		
		/*Original Code for Backup
		Fragment fragmentDraatt = fm.findFragmentById(R.id.fragmentContainer);
		Fragment fragmentHeader = fm.findFragmentById(R.id.fragmentContainer); 
		// Her skal det opprettes nye DrattFragment om det ligger søk i sharedpreferences
		// 
		if (fragmentDraatt == null && fragmentHeader == null) {
			fragmentHeader = new HeaderDraattFragment();
			fm.beginTransaction().add(R.id.fragmentContainer, fragmentHeader).commit();
		}
		*/
	}

}
