package net.baasit.draatt;

import java.util.Date;

public class DraattDetails {

	private Date mFrom;
	private Date mTo;
	private int mSymbolNummer;
	private int mTemperatur;
	private double mNedbor;
	private double mVindRetning;
	private double mVindStyrke;
	

	public Date getFrom() {
		return mFrom;
	}

	public void setFrom(Date from) {
		mFrom = from;
	}

	public Date getTo() {
		return mTo;
	}

	public void setTo(Date to) {
		mTo = to;
	}

	public int getSymbolNummer() {
		return mSymbolNummer;
	}

	public void setSymbolNummer(int symbolNummer) {
		mSymbolNummer = symbolNummer;
	}

	public int getTemperatur() {
		return mTemperatur;
	}

	public void setTemperatur(int temperatur) {
		mTemperatur = temperatur;
	}

	public double getNedbor() {
		return mNedbor;
	}

	public void setNedbor(double nedbor) {
		mNedbor = nedbor;
	}

	public double getVindRetning() {
		return mVindRetning;
	}

	public void setVindRetning(double vindRetning) {
		mVindRetning = vindRetning;
	}

	public double getVindStyrke() {
		return mVindStyrke;
	}

	public void setVindStyrke(double vindStyrke) {
		mVindStyrke = vindStyrke;
	}
	
	
	
}
