package net.baasit.draatt;

import java.io.IOException;
import java.util.List;

import net.baasit.draatt.DraattXmlPullParser.AsyncResponse;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class DetailsListFragment extends ListFragment implements AsyncResponse {

	CustomDetailArrayAdapter mAdapter;
	DraattXmlPullParser asyncTask = new DraattXmlPullParser();

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		asyncTask.delegate = this;

		//I utgangspunktet er listen tom
		setEmptyText("Ingen data");

		//Tomt adapter blir opprettet for å vise data i listen.
		mAdapter = new CustomDetailArrayAdapter(getActivity());
		setListAdapter(mAdapter);

		//Progress loader
		setListShown(false);

		//Forbereder loader. Enten kobles til eksisterenede
		//eller start ny

		String url = "http://www.yr.no/sted/Norge/Oslo/Oslo/Oslo/varsel.xml";

		//Test av entries
		asyncTask.execute(url);

		//mAdapter.setData(entries);

		//getLoaderManager().initLoader(0, null, this);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		//Hva skjer når et item blir trykket på? 
		//Oppretter et nytt fragment med replace
	}

	public void processFinish(List<DraattDetails> output){
		//Receive result fired from async class of onPostExecute(result) method.
		mAdapter.setData(output);
	}

	//	@Override
	//	public Loader<List<DraattDetails>> onCreateLoader(int arg0, Bundle arg1) {
	//		return new DetailListLoader(getActivity());
	//	}
	//
	//	@Override
	//	public void onLoadFinished(Loader<List<DraattDetails>> arg0, List<DraattDetails> data) {
	//		mAdapter.setData(data);
	//		//På dette tidspunktet skal listen vises
	//		if(isResumed()){
	//			setListShown(true);
	//		}else{
	//			setListShownNoAnimation(true);
	//		}
	//	}
	//
	//	@Override
	//	public void onLoaderReset(Loader<List<DraattDetails>> arg0) {
	//		mAdapter.setData(null);
	//		
	//	}

}
