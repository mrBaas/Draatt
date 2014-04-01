package net.baasit.draatt;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{
	 
    //The Android's default system path of your application database.
    private static final String DB_PATH = "/data/data/net.baasit.draatt/databases/";
 
    private static final String DB_NAME = "noregDB";
 
    private SQLiteDatabase myDataBase; 
 
    private final Context myContext;
 
    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DataBaseHelper(Context context) {
 
    	super(context, DB_NAME, null, 1);
        this.myContext = context;
    }	
 
  /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();	
 
    	if(dbExist){
    		Message.message(myContext, "Databasen eksisterer i Applikasjonen");
    		//do nothing - database already exist
    	}else{
 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
 
        	try {
    			copyDataBase();
    			Message.message(myContext, "Database ble opprettet");
    		} catch (IOException e) {
 
        		Message.message(myContext, ""+e);
 
        	}
    	}
 
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
    	
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    		Message.message(myContext, "Databasen er kontrollert!");
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public void openDataBase() throws SQLException{
 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }
 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close();
 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}
 

	
	public String hentNaermestBy(Location location){
		String TABLE_NAME = "noreg";
		String longitude = String.valueOf(location.getLongitude());
		String latitude = String.valueOf(location.getLatitude());
		
		Cursor cursor = myDataBase.query(TABLE_NAME, new String[] {"_id","Stadnamn","Lat","Lon"}, null, null, null, null, null);
		Location temp = new Location("NaermestLokasjon");
		
		int counter = 0;
		double rekkevidde = 0;
		int naermestBy = 0;
		String byNavn = "";
		try {
			if(cursor != null){
				while(cursor.moveToNext()){
					
					temp.setLatitude(cursor.getDouble(cursor.getColumnIndex("Lat")));
					temp.setLongitude(cursor.getDouble(cursor.getColumnIndex("Lon")));
					
					//F�rste resultat registreres uansett for � sammenlikne distanse.
					if(counter == 0){
						rekkevidde = location.distanceTo(temp);
						
					}if(counter > 0 && location.distanceTo(temp) < rekkevidde){
						naermestBy = cursor.getInt(cursor.getColumnIndex("_id"));
						rekkevidde = location.distanceTo(temp);
						
					}
					counter++;
				}
				Log.d("Hvor er cursor? ", "" + naermestBy);
				cursor.moveToPosition(naermestBy - 1);
				byNavn = cursor.getString(cursor.getColumnIndex("Stadnamn"));
			}
		
		}finally{
			cursor.close();
		}
		

		return byNavn;
	
	}
}
