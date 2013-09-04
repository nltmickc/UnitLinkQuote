package UnitLinkedQuote;

import java.util.Calendar;

public class Tick {
	
	private int _Year;
	private int _Month;
	private int _TermInMonths;
	private Events _Event;
	private Calendar _Cal;
	
	public Tick (int TermInMonths) {
		
		this._TermInMonths = TermInMonths;
	}
	
	public void Set(int year, int month, Events e, Calendar cal ) {
		
		this._Year = year;
		
		this._Month = month;
		
		this._Event= e;
		
		this._Cal = cal;
	}
	
	public int getYear() {
		
		return this._Year;
	}
		
	public int getMonth() {
		
		return this._Month;
	}
	
	public Calendar getCalendar() {
		
		return this._Cal;
	}
	
	public Events getEvent() {
		
		return this._Event;
	}
	
	public int getMonthNumber() {
		
		return this._Year * 12 + this._Month;
	}
	
	public int getMonthsRemaining() {
		
		return this._TermInMonths  - getMonthNumber();
	}
}