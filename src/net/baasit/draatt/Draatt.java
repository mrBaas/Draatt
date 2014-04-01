package net.baasit.draatt;

import java.util.Date;
import java.util.UUID;

import android.widget.ImageView;

public class Draatt {
	
	private int mId;							//ID
	private String mStedsnavn;					//Stedsnavn ??
	private String mStedType;					//Type sted: By, grend, stred osv
	private String mFylke;						//Fylke navn
	private String mVarselUrl; 					//Adresse til XML.varsel på Yr.

	private Double mLongitude;					//??
	private Double mLatitude;					//??
	
	
	public Draatt(int index, String sted, String fylke) {
		this.mId = index;
		this.mStedsnavn = sted;
		this.mFylke = fylke;
	}


	public int getId() {
		return mId;
	}


	public void setId(int id) {
		mId = id;
	}


	public String getStedsnavn() {
		return mStedsnavn;
	}


	public void setStedsnavn(String stedsnavn) {
		mStedsnavn = stedsnavn;
	}


	public String getStedType() {
		return mStedType;
	}


	public void setStedType(String stedType) {
		mStedType = stedType;
	}


	public String getFylke() {
		return mFylke;
	}


	public void setFylke(String fylke) {
		mFylke = fylke;
	}


	public String getVarselUrl() {
		return mVarselUrl;
	}


	public void setVarselUrl(String varselUrl) {
		mVarselUrl = varselUrl;
	}


	public Double getLongitude() {
		return mLongitude;
	}


	public void setLongitude(Double longitude) {
		mLongitude = longitude;
	}


	public Double getLatitude() {
		return mLatitude;
	}


	public void setLatitude(Double latitude) {
		mLatitude = latitude;
	}
	
	
}
