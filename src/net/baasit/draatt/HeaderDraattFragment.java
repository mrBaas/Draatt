package net.baasit.draatt;

import java.io.IOException;

import android.content.Context;
import android.database.SQLException;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HeaderDraattFragment extends Fragment {
	
	private TextView mHeader;
	private TextView mLongitude;
	private TextView mLatitude;
	private LocationManager lm;
	private LocationListener ll;
	private DataBaseHelper myDbHelper;
	private Context thisContext;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.header_draatt, parent, false);
		
		thisContext = getActivity();
		this.myDbHelper = new DataBaseHelper(thisContext);
		
		try {
        	myDbHelper.createDataBase();
        	
		} catch (IOException ioe) {
	 		throw new Error("Unable to create database");
		}
	 	try {
	 		myDbHelper.openDataBase();

	 	}catch(SQLException sqle){
	 		throw sqle;
	 	}
		
		mHeader = (TextView)v.findViewById(R.id.location_name);
		mLongitude = (TextView)v.findViewById(R.id.longitude);
		mLatitude = (TextView)v.findViewById(R.id.latitude);
		
		
		lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		ll = new myLocationListener();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 600000, 0, ll);
		
		return v;
	}
	
		
	class myLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			if(location != null){
				
				double pLong = location.getLongitude();
				double pLat = location.getLatitude();
				String byen = myDbHelper.hentNaermestBy(location);
				
				mLongitude.setText(Double.toString(pLong));
				mLatitude.setText(Double.toString(pLat));
				mHeader.setText(byen);
			}
			
		}

		@Override
		public void onProviderDisabled(String location) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String location) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
