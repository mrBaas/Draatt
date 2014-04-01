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
		
		//Her må det først kjøres et kall etter data koblet til lokasjon
		//Deretter skal det lastes inn data i forbindelse med søk/tidliger søk
		
		
		//Test av entries
		List<Draatt> entries = new ArrayList<Draatt>(5);
		entries.add(new Draatt(1,"Mo i Rana","Nordland"));
		entries.add(new Draatt(2,"Mosjøen","Nordland"));
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
	*Metode som blir kalt når det er ny data som skal til klienten
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
		
		//På dette tidspunktet kan vi se bort fra resources knyttet til "oldApps"
		//Siden det nye resultatet er levert vet vi at det ikke er i bruk
		if (oldApps != null){
			onReleaseResources(oldApps);
		}
		
	}
	
	/**
	*
	*Håndterer forespørseln om å starte loader
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
			//ELLER at det ikke er tilgjengelig, begynn å laste inn.
			forceLoad();
		}
	}
	
	/**
	*Håndterer forespørsel om å stoppe loader
	*/
	@Override 
	protected void onStopLoading(){
		//Prøver å stoppe loadingen om det er mulig
		cancelLoad();
	}
	
	/**
	*Håndterer forespørsel om å avbryte loader
	*/
	@Override
	public void onCanceled(List<Draatt> apps){
		//På dette tidspunktet kan vi gi slipp på resources assosiert med "apps"
		//hvis nødvendig
		onReleaseResources(apps);
	}
	
	/**
	*Håndterer forespørsel om å resette loader fullstendig
	*/
	@Override
	protected void onReset(){
		super.onReset();
		
		//Forsikrer oss om at loader er stoppet
		onStopLoading();
		
		//På dette tidspunktet kan vi gi slipp på resources assosiert med "apps"
		//hvis nødvendig
		if(mDraatts !=null){
			onReleaseResources(mDraatts);
			mDraatts = null;
		}
	}
	
	/**
     * Hjelper metode som tar hånd om å gi slipp på resources assosiert
     * med en actively loaded data set.
     */
    protected void onReleaseResources(List<Draatt> apps) {}
	
}