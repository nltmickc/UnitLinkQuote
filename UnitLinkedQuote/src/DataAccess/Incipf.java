package DataAccess;

import java.lang.reflect.Field;
import java.util.Date;

public class Incipf {
	private String _LIFE;
	private String _COVERAGE;
	private Date _RCDATE;
	private Date _PREM_CESS_DATE;
	private String _ORIG_PREM;
	private String _CURR_PREM;
	private String _PREM_START01;
	private String _PREM_START02;
	private String _PREM_START03;
	private String _PREM_START04;
	private String _PREM_CURR01;
	private String _PREM_CURR02;
	private String _PREM_CURR03;
	private String _PREM_CURR04;
	private String _PC_UNITS01;
	private String _PC_UNITS02;
	private String _PC_UNITS03;
	private String _PC_UNITS04;
	private String _UNIT_SPLIT01;
	private String _UNIT_SPLIT02;
	private String _UNIT_SPLIT03;
	private String _UNIT_SPLIT04;
	
	public String get_LIFE() {
		return _LIFE;
	}
	public void set_LIFE(String _LIFE) {
		this._LIFE = _LIFE;
	}
	public String get_COVERAGE() {
		return _COVERAGE;
	}
	public void set_COVERAGE(String _COVERAGE) {
		this._COVERAGE = _COVERAGE;
	}
	public Date get_RCDATE() {
		return _RCDATE;
	}
	public void set_RCDATE(Date _RCDATE) {
		this._RCDATE = _RCDATE;
	}
	public Date get_PREM_CESS_DATE() {
		return _PREM_CESS_DATE;
	}
	public void set_PREM_CESS_DATE(Date _PREM_CESS_DATE) {
		this._PREM_CESS_DATE = _PREM_CESS_DATE;
	}
	public String get_ORIG_PREM() {
		return _ORIG_PREM;
	}
	public void set_ORIG_PREM(String _ORIG_PREM) {
		this._ORIG_PREM = _ORIG_PREM;
	}
	public String get_CURR_PREM() {
		return _CURR_PREM;
	}
	public void set_CURR_PREM(String _CURR_PREM) {
		this._CURR_PREM = _CURR_PREM;
	}
	public String get_PREM_START01() {
		return _PREM_START01;
	}
	public void set_PREM_START01(String _PREM_START01) {
		this._PREM_START01 = _PREM_START01;
	}
	public String get_PREM_START02() {
		return _PREM_START02;
	}
	public void set_PREM_START02(String _PREM_START02) {
		this._PREM_START02 = _PREM_START02;
	}
	public String get_PREM_START03() {
		return _PREM_START03;
	}
	public void set_PREM_START03(String _PREM_START03) {
		this._PREM_START03 = _PREM_START03;
	}
	public String get_PREM_START04() {
		return _PREM_START04;
	}
	public void set_PREM_START04(String _PREM_START04) {
		this._PREM_START04 = _PREM_START04;
	}
	public String get_PREM_CURR01() {
		return _PREM_CURR01;
	}
	public void set_PREM_CURR01(String _PREM_CURR01) {
		this._PREM_CURR01 = _PREM_CURR01;
	}
	public String get_PREM_CURR02() {
		return _PREM_CURR02;
	}
	public void set_PREM_CURR02(String _PREM_CURR02) {
		this._PREM_CURR02 = _PREM_CURR02;
	}
	public String get_PREM_CURR03() {
		return _PREM_CURR03;
	}
	public void set_PREM_CURR03(String _PREM_CURR03) {
		this._PREM_CURR03 = _PREM_CURR03;
	}
	public String get_PREM_CURR04() {
		return _PREM_CURR04;
	}
	public void set_PREM_CURR04(String _PREM_CURR04) {
		this._PREM_CURR04 = _PREM_CURR04;
	}
	public String get_PC_UNITS01() {
		return _PC_UNITS01;
	}
	public void set_PC_UNITS01(String _PC_UNITS01) {
		this._PC_UNITS01 = _PC_UNITS01;
	}
	public String get_PC_UNITS02() {
		return _PC_UNITS02;
	}
	public void set_PC_UNITS02(String _PC_UNITS02) {
		this._PC_UNITS02 = _PC_UNITS02;
	}
	public String get_PC_UNITS03() {
		return _PC_UNITS03;
	}
	public void set_PC_UNITS03(String _PC_UNITS03) {
		this._PC_UNITS03 = _PC_UNITS03;
	}
	public String get_PC_UNITS04() {
		return _PC_UNITS04;
	}
	public void set_PC_UNITS04(String _PC_UNITS04) {
		this._PC_UNITS04 = _PC_UNITS04;
	}
	public String get_UNIT_SPLIT01() {
		return _UNIT_SPLIT01;
	}
	public void set_UNIT_SPLIT01(String _UNIT_SPLIT01) {
		this._UNIT_SPLIT01 = _UNIT_SPLIT01;
	}
	public String get_UNIT_SPLIT02() {
		return _UNIT_SPLIT02;
	}
	public void set_UNIT_SPLIT02(String _UNIT_SPLIT02) {
		this._UNIT_SPLIT02 = _UNIT_SPLIT02;
	}
	public String get_UNIT_SPLIT03() {
		return _UNIT_SPLIT03;
	}
	public void set_UNIT_SPLIT03(String _UNIT_SPLIT03) {
		this._UNIT_SPLIT03 = _UNIT_SPLIT03;
	}
	public String get_UNIT_SPLIT04() {
		return _UNIT_SPLIT04;
	}
	public void set_UNIT_SPLIT04(String _UNIT_SPLIT04) {
		this._UNIT_SPLIT04 = _UNIT_SPLIT04;
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
