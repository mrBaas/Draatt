package net.baasit.draatt;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class DataListLoader extends AsyncTaskLoader<List<Draatt>> {
	List<Draatt> mDraatts;
	
	public DataListLoader(Context context){
		super(context);
	}

	@Override
	public List<Draatt> loadInBackground() {
		//Metode som laster inn data i bakgrunnen
		
		//Her m� det f�rst kj�res et kall etter data koblet til lokasjon
		//Deretter skal det lastes inn data i forbindelse med s�k/tidliger s�k
		
		
		//Test av entries
		List<Draatt> entries = new ArrayList<Draatt>(5);
		entries.add(new Draatt(1,"Mo i Rana","Nordland"));
		entries.add(new Draatt(2,"Mosj�en","Nordland"));
		entries.add(new Draatt(3,"Mordi","Nordland"));
		entries.add(new Draatt(4,"Jebubji","Nordland"));
		entries.add(new Draatt(5,"Nesna","Nordland"));
		return entries;
	}
	
	/**
	 * 
	 * LocationListener for henting av lokasjonsdata i DataListLoader
	 *
	 */
	
	
	/**
	*Metode som blir kalt n�r det er ny data som skal til klienten
	*
	*
	*/
	@Override
	public void deliverResult(List<Draatt> listOfData) {
		if(isReset()){
			if(listOfData != null){
				onReleaseResources(listOfData);
			}
		}
		List<Draatt> oldApps = listOfData;
		mDraatts = listOfData;
		
		if(isStarted()){
			//Hvis Loader allerede er startet, kan vi umiddelbart 
			//levere resultatet
			super.deliverResult(listOfData);
		}
		
		//P� dette tidspunktet kan vi se bort fra resources knyttet til "oldApps"
		//Siden det nye resultatet er levert vet vi at det ikke er i bruk
		if (oldApps != null){
			onReleaseResources(oldApps);
		}
		
	}
	
	/**
	*
	*H�ndterer foresp�rseln om � starte loader
	*
	*/
	@Override
	protected void onStartLoading(){
		if(mDraatts != null){
			//Hvis vi har et resultat tilgjengelig, lever det umiddelbart
			deliverResult(mDraatts);
		}
		
		if (takeContentChanged() || mDraatts == null){
			//Hvis dataen er endret siden sist det ble lastet inn,
			//ELLER at det ikke er tilgjengelig, begynn � laste inn.
			forceLoad();
		}
	}
	
	/**
	*H�ndterer foresp�rsel om � stoppe loader
	*/
	@Override 
	protected void onStopLoading(){
		//Pr�ver � stoppe loadingen om det er mulig
		cancelLoad();
	}
	
	/**
	*H�ndterer foresp�rsel om � avbryte loader
	*/
	@Override
	public void onCanceled(List<Draatt> apps){
		//P� dette tidspunktet kan vi gi slipp p� resources assosiert med "apps"
		//hvis n�dvendig
		onReleaseResources(apps);
	}
	
	/**
	*H�ndterer foresp�rsel om � resette loader fullstendig
	*/
	@Override
	protected void onReset(){
		super.onReset();
		
		//Forsikrer oss om at loader er stoppet
		onStopLoading();
		
		//P� dette tidspunktet kan vi gi slipp p� resources assosiert med "apps"
		//hvis n�dvendig
		if(mDraatts !=null){
			onReleaseResources(mDraatts);
			mDraatts = null;
		}
	}
	
	/**
     * Hjelper metode som tar h�nd om � gi slipp p� resources assosiert
     * med en actively loaded data set.
     */
    protected void onReleaseResources(List<Draatt> apps) {}
	
}