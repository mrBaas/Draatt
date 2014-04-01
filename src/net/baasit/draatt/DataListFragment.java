package net.baasit.draatt;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class DataListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<Draatt>>{
	CustomMainArrayAdapter mAdapter;
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		//I utgangspunktet er listen tom
		setEmptyText("Listen er tom?");
		
		//Tomt adapter blir opprettet for å vise data i listen.
		mAdapter = new CustomMainArrayAdapter(getActivity());
		setListAdapter(mAdapter);
		
		//Progress loader
		setListShown(false);
		
		//Forbereder loader. Enten kobles til eksisterenede
		//eller start ny.
		getLoaderManager().initLoader(0, null, this);
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		//Hva skjer når et item blir trykket på? 
		//Oppretter et nytt fragment med replace
		
		Log.d("Which activity? ", "" + getActivity());
		
		/*
		if(getActivity() == ){
			
		}else if(getActivity() == ){
			
		}
		*/
		Intent i = new Intent(v.getContext(), ActivityDraattDetail.class);
		Draatt theDraatt = (Draatt)l.getItemAtPosition(position);
		
		//i.putExtra("DRAATT", theDraatt.getVarselUrl());
		i.putExtra("DRAATT", "http://www.yr.no/sted/Norge/Nordland/Rana/Mo_i_Rana_lufthavn,_R%C3%B8ssvoll/varsel.xml");
		startActivity(i);
		
	}

	@Override
	public Loader<List<Draatt>> onCreateLoader(int arg0, Bundle arg1) {
		return new DataListLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<List<Draatt>> arg0, List<Draatt> data) {
		mAdapter.setData(data);
		//På dette tidspunktet skal listen vises
		if(isResumed()){
			setListShown(true);
		}else{
			setListShownNoAnimation(true);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<Draatt>> arg0) {
		mAdapter.setData(null);
		
	}
	
}
