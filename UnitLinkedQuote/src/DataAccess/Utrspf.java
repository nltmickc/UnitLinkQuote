package DataAccess;

import java.lang.reflect.Field;

public class Utrspf {
	
	private String _LIFE;
	private String _COVERAGE;
	private String _UNIT_VIRTUAL_FUND;
	private String _CURRENT_UNIT_BAL;
	private String _UNIT_TYPE;

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
	public String get_UNIT_VIRTUAL_FUND() {
		return _UNIT_VIRTUAL_FUND;
	}
	public void set_UNIT_VIRTUAL_FUND(String _UNIT_VIRTUAL_FUND) {
		this._UNIT_VIRTUAL_FUND = _UNIT_VIRTUAL_FUND;
	}
	public String get_CURRENT_UNIT_BAL() {
		return _CURRENT_UNIT_BAL;
	}
	public void set_CURRENT_UNIT_BAL(String _CURRENT_UNIT_BAL) {
		this._CURRENT_UNIT_BAL = _CURRENT_UNIT_BAL;
	}
	public String get_UNIT_TYPE() {
		return _UNIT_TYPE;
	}
	public void set_UNIT_TYPE(String _UNIT_TYPE) {
		this._UNIT_TYPE = _UNIT_TYPE;
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
