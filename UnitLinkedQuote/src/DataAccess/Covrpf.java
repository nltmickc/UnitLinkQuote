package DataAccess;

import java.lang.reflect.Field;
import java.util.*;

public class Covrpf {
	
	private int _ZZFUTERM;
	private String _CRTABLE;
	private double _INSTPREM;
	private Date _PREM_CESS_DATE;
	private String _PSTATCODE;
	private double _SINGP;
	private Date _CRRCD;
	private Date  _RISK_CESS_DATE;
	private double _SUMINS;
	private String _STATCODE;
	private String _INDEXATION_IND;
	private int _PREM_CESS_TERM;
	private double _COVERAGE_DEBT;
	private int _RISK_CESS_TERM;
	private int _PREM_CESS_AGE;
	private int _RISK_CESS_AGE;
	private int _ANB_AT_CCD;
	private String _SEX;
	private String _MORTCLS;
	private String _ZZAUTORED;
	
	public int get_ZZFUTERM() {
		return _ZZFUTERM;
	}
	public void set_ZZFUTERM(int _ZZFUTERM) {
		this._ZZFUTERM = _ZZFUTERM;
	}
	public String get_CRTABLE() {
		return _CRTABLE;
	}
	public void set_CRTABLE(String _CRTABLE) {
		this._CRTABLE = _CRTABLE;
	}
	public double get_INSTPREM() {
		return _INSTPREM;
	}
	public void set_INSTPREM(double _INSTPREM) {
		this._INSTPREM = _INSTPREM;
	}
	public Date get_PREM_CESS_DATE() {
		return _PREM_CESS_DATE;
	}
	public void set_PREM_CESS_DATE(Date _PREM_CESS_DATE) {
		this._PREM_CESS_DATE = _PREM_CESS_DATE;
	}
	public String get_PSTATCODE() {
		return _PSTATCODE;
	}
	public void set_PSTATCODE(String _PSTATCODE) {
		this._PSTATCODE = _PSTATCODE;
	}
	public double get_SINGP() {
		return _SINGP;
	}
	public void set_SINGP(double _SINGP) {
		this._SINGP = _SINGP;
	}
	public Date get_CRRCD() {
		return _CRRCD;
	}
	public void set_CRRCD(Date _CRRCD) {
		this._CRRCD = _CRRCD;
	}
	public Date get_RISK_CESS_DATE() {
		return _RISK_CESS_DATE;
	}
	public void set_RISK_CESS_DATE(Date _RISK_CESS_DATE) {
		this._RISK_CESS_DATE = _RISK_CESS_DATE;
	}
	public double get_SUMINS() {
		return _SUMINS;
	}
	public void set_SUMINS(double _SUMINS) {
		this._SUMINS = _SUMINS;
	}
	public String get_STATCODE() {
		return _STATCODE;
	}
	public void set_STATCODE(String _STATCODE) {
		this._STATCODE = _STATCODE;
	}
	public String get_INDEXATION_IND() {
		return _INDEXATION_IND;
	}
	public void set_INDEXATION_IND(String _INDEXATION_IND) {
		this._INDEXATION_IND = _INDEXATION_IND;
	}
	public int get_PREM_CESS_TERM() {
		return _PREM_CESS_TERM;
	}
	public void set_PREM_CESS_TERM(int _PREM_CESS_TERM) {
		this._PREM_CESS_TERM = _PREM_CESS_TERM;
	}
	public double get_COVERAGE_DEBT() {
		return _COVERAGE_DEBT;
	}
	public void set_COVERAGE_DEBT(double _COVERAGE_DEBT) {
		this._COVERAGE_DEBT = _COVERAGE_DEBT;
	}
	public int get_RISK_CESS_TERM() {
		return _RISK_CESS_TERM;
	}
	public void set_RISK_CESS_TERM(int _RISK_CESS_TERM) {
		this._RISK_CESS_TERM = _RISK_CESS_TERM;
	}
	public int get_PREM_CESS_AGE() {
		return _PREM_CESS_AGE;
	}
	public void set_PREM_CESS_AGE(int _PREM_CESS_AGE) {
		this._PREM_CESS_AGE = _PREM_CESS_AGE;
	}
	public int get_RISK_CESS_AGE() {
		return _RISK_CESS_AGE;
	}
	public void set_RISK_CESS_AGE(int _RISK_CESS_AGE) {
		this._RISK_CESS_AGE = _RISK_CESS_AGE;
	}
	public int get_ANB_AT_CCD() {
		return _ANB_AT_CCD;
	}
	public void set_ANB_AT_CCD(int _ANB_AT_CCD) {
		this._ANB_AT_CCD = _ANB_AT_CCD;
	}
	public String get_SEX() {
		return _SEX;
	}
	public void set_SEX(String _SEX) {
		this._SEX = _SEX;
	}
	public String get_MORTCLS() {
		return _MORTCLS;
	}
	public void set_MORTCLS(String _MORTCLS) {
		this._MORTCLS = _MORTCLS;
	}
	public String get_ZZAUTORED() {
		return _ZZAUTORED;
	}
	public void set_ZZAUTORED(String _ZZAUTORED) {
		this._ZZAUTORED = _ZZAUTORED;
	}
		
	public int getTermInMonths() {
		
		int temp = MonthsBetween ( _RISK_CESS_DATE , _CRRCD ); 
		
		return  temp;
				
	}
					
	private int MonthsBetween( Date EndDate, Date StartDate ){  
		
		Calendar cal = Calendar.getInstance();  
		
		//default will be Gregorian in US Locales  
		cal.setTime(EndDate);  
		int EndMonth =  cal.get(Calendar.MONTH);  
		int EndYear = cal.get(Calendar.YEAR);  
		
		cal.setTime(StartDate);  
		int StartMonth =  cal.get(Calendar.MONTH);  
		int StartYear = cal.get(Calendar.YEAR);  
	  
		return ((EndYear - StartYear) * cal.getMaximum(Calendar.MONTH)) + (EndMonth - StartMonth);  
	}  
	
	@Override
	public String toString() {
		  StringBuilder result = new StringBuilder();
		  String newLine = System.getProperty("line.separator");

		  result.append( this.getClass().getName() );
		  result.append( " Object {" );
		  result.append(newLine);

		  //determine fields declared in this class only (no fields of superclass)
		  Field[] fields = this.getClass().getDeclaredFields();

		  //print field names paired with their values
		  for ( Field field : fields  ) {
		    result.append("  ");
		    try {
		      result.append( field.getName() );
		      result.append(": ");
		      //requires access to private field:
		      result.append( field.get(this) );
		    } catch ( IllegalAccessException ex ) {
		      System.out.println(ex);
		    }
		    result.append(newLine);
		  }
		  result.append("}");

		  return result.toString();
	}
}

