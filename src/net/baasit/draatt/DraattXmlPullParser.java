package net.baasit.draatt;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;

public class DraattXmlPullParser extends AsyncTask<String, Integer, List<DraattDetails>>{
	
	private static final String TAG = "DRAATT";
	private static final String KEY_HEAD = "time";
	private static final String KEY_SYMBOL = "symbol";
	private static final String KEY_TEMPERATUR = "temperatur";
	private static final String KEY_PRECIPITATION = "precipitation";
	private static final String KEY_WINDDIRECTION = "windDirection";
	private static final String KEY_WINDSPEED = "windSpeed";
	public AsyncResponse delegate = null;

	/*
	public static List<DraattDetails> getDraattDetailsFromUrl() {

		// Liste med DraattDetaljer som skal returneres
		Log.d(TAG, "Steg 1");
		List<DraattDetails> draattDetails;
		draattDetails = new ArrayList<DraattDetails>();
		Log.d(TAG, "Steg 2");
		// temp holder for nåværende draattDetails mens parsinga foregår
		DraattDetails curDraattDetail = null;
		// temp holder for nåværende tekst verdie mens parsinga foregår
		String curText = "";
		Log.d(TAG, "Steg 3");
		try {
			// Get our factory and PullParser
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			Log.d(TAG, "Steg 4");
			URLConnection conn = new URL(URL).openConnection();
			conn.setConnectTimeout(50000);
			conn.setReadTimeout(50000);
			//URL URL = new URL(url);
			Log.d(TAG, "STEG 4 NÅ?");
			InputStream stream = conn.getInputStream();
			Log.d(TAG, "Steg 5");
			xpp.setInput(stream, null);

			// get initial eventType
			int eventType = xpp.getEventType();
			Log.d(TAG, "Steg 6");
			// Loop through pull events until we reach END_DOCUMENT
			while (eventType != XmlPullParser.END_DOCUMENT) {
				// Get the current tag
				String tagname = xpp.getName();

				// React to different event types appropriately
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if (tagname.equalsIgnoreCase(KEY_HEAD)) {
						// If we are starting a new <site> block we need
						//a new StackSite object to represent it
						curDraattDetail = new DraattDetails();
						
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
						String from = xpp.getAttributeValue(null, "from");
						String to = xpp.getAttributeValue(null, "to");
						
						Date fromDate = null;
						Date toDate = null;
						
						try {
							fromDate = dateFormat.parse(from);
							toDate = dateFormat.parse(to);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						curDraattDetail.setFrom(fromDate);
						curDraattDetail.setTo(toDate);
					}
					break;

				case XmlPullParser.TEXT:
					//grab the current text so we can use it in END_TAG event
					curText = xpp.getText();
					break;

				case XmlPullParser.END_TAG:
					if (tagname.equalsIgnoreCase(KEY_HEAD)) {
						// if </time> then we are done with current Site
						// add it to the list.
						draattDetails.add(curDraattDetail);
					} else if (tagname.equalsIgnoreCase(KEY_SYMBOL)) {
						// if </symbol> use setName() on curSite
						int symbolNumber = Integer.valueOf(xpp.getAttributeValue(null, "value"));
						curDraattDetail.setSymbolNummer(symbolNumber);
					} else if (tagname.equalsIgnoreCase(KEY_TEMPERATUR)) {
						// if </temperatur> use setLink() on curSite
						int temperaturNummer = Integer.valueOf(xpp.getAttributeValue(null, "value"));
						curDraattDetail.setTemperatur(temperaturNummer);
					} else if (tagname.equalsIgnoreCase(KEY_PRECIPITATION)) {
						// if </pricipitation> use setAbout() on curSite
						double nedborNummer = Double.valueOf(xpp.getAttributeValue(null, "value"));
						curDraattDetail.setNedbor(nedborNummer);
					} else if (tagname.equalsIgnoreCase(KEY_WINDDIRECTION)) {
						// if </windDirection> use setImgUrl() on curSite
						double vindRetning = Double.valueOf(xpp.getAttributeValue(null, "deg"));
						curDraattDetail.setVindRetning(vindRetning);
					}else if (tagname.equalsIgnoreCase(KEY_WINDSPEED)) {
						// if </windDirection> use setImgUrl() on curSite
						double vindStyrke = Double.valueOf(xpp.getAttributeValue(null, "mps"));
						curDraattDetail.setVindStyrke(vindStyrke);
					}
					break;

				default:
					break;
				}
				//move on to next iteration
				eventType = xpp.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return the populated list.
		return draattDetails;
	}
	*/
	
	public interface AsyncResponse {
	    void processFinish(List<DraattDetails> output);
	}
	
    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();

        Log.d("Loading", " Now");
        
    }
    
	@Override
	protected List<DraattDetails> doInBackground(String... params) {
		
		// Liste med DraattDetaljer som skal returneres
				Log.d(TAG, "Steg 1");
				List<DraattDetails> draattDetails;
				draattDetails = new ArrayList<DraattDetails>();
				Log.d(TAG, "Steg 2");
				// temp holder for nåværende draattDetails mens parsinga foregår
				DraattDetails curDraattDetail = null;
				// temp holder for nåværende tekst verdie mens parsinga foregår
				String curText = "";
				Log.d(TAG, "Steg 3");
				try {
					// Get our factory and PullParser
					XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser xpp = factory.newPullParser();
					Log.d(TAG, "Steg 4");
					URLConnection conn = new URL(params[0]).openConnection();
					conn.setConnectTimeout(50000);
					conn.setReadTimeout(50000);
					//URL URL = new URL(url);
					Log.d(TAG, "STEG 4 NÅ?");
					InputStream stream = conn.getInputStream();
					Log.d(TAG, "Steg 5");
					xpp.setInput(stream, null);

					// get initial eventType
					int eventType = xpp.getEventType();
					Log.d(TAG, "Steg 6");
					// Loop through pull events until we reach END_DOCUMENT
					while (eventType != XmlPullParser.END_DOCUMENT) {
						// Get the current tag
						String tagname = xpp.getName();

						// React to different event types appropriately
						switch (eventType) {
						case XmlPullParser.START_TAG:
							if (tagname.equalsIgnoreCase(KEY_HEAD)) {
								// If we are starting a new <site> block we need
								//a new StackSite object to represent it
								curDraattDetail = new DraattDetails();
								
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
								String from = xpp.getAttributeValue(null, "from");
								String to = xpp.getAttributeValue(null, "to");
								
								Date fromDate = null;
								Date toDate = null;
								
								try {
									fromDate = dateFormat.parse(from);
									toDate = dateFormat.parse(to);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								curDraattDetail.setFrom(fromDate);
								curDraattDetail.setTo(toDate);
							}
							break;

						case XmlPullParser.TEXT:
							//grab the current text so we can use it in END_TAG event
							curText = xpp.getText();
							break;

						case XmlPullParser.END_TAG:
							if (tagname.equalsIgnoreCase(KEY_HEAD)) {
								// if </time> then we are done with current Site
								// add it to the list.
								draattDetails.add(curDraattDetail);
							} else if (tagname.equalsIgnoreCase(KEY_SYMBOL)) {
								// if </symbol> use setName() on curSite
								int symbolNumber = Integer.valueOf(xpp.getAttributeValue(null, "value"));
								curDraattDetail.setSymbolNummer(symbolNumber);
							} else if (tagname.equalsIgnoreCase(KEY_TEMPERATUR)) {
								// if </temperatur> use setLink() on curSite
								int temperaturNummer = Integer.valueOf(xpp.getAttributeValue(null, "value"));
								curDraattDetail.setTemperatur(temperaturNummer);
							} else if (tagname.equalsIgnoreCase(KEY_PRECIPITATION)) {
								// if </pricipitation> use setAbout() on curSite
								double nedborNummer = Double.valueOf(xpp.getAttributeValue(null, "value"));
								curDraattDetail.setNedbor(nedborNummer);
							} else if (tagname.equalsIgnoreCase(KEY_WINDDIRECTION)) {
								// if </windDirection> use setImgUrl() on curSite
								double vindRetning = Double.valueOf(xpp.getAttributeValue(null, "deg"));
								curDraattDetail.setVindRetning(vindRetning);
							}else if (tagname.equalsIgnoreCase(KEY_WINDSPEED)) {
								// if </windDirection> use setImgUrl() on curSite
								double vindStyrke = Double.valueOf(xpp.getAttributeValue(null, "mps"));
								curDraattDetail.setVindStyrke(vindStyrke);
							}
							break;

						default:
							break;
						}
						//move on to next iteration
						eventType = xpp.next();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			// return the populated list.
			return draattDetails;
	}
	
	protected void onProgressUpdate(Integer... progress) {
        //setProgressPercent(progress[0]);
    }

	@Override
    protected void onPostExecute(List<DraattDetails> result) {
		delegate.processFinish(result);
    }


	
}
