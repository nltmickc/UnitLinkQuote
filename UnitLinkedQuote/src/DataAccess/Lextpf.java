package DataAccess;

import java.lang.reflect.Field;

public class Lextpf {
	
    private String _COVERAGE;
    private String _RIDER;
    private String _LIFE;
    private String _AGERATE;
    private String _OPPC;
    private String _INSPRM;
    
	public String get_COVERAGE() {
		return _COVERAGE;
	}
	public void set_COVERAGE(String _COVERAGE) {
		this._COVERAGE = _COVERAGE;
	}
	public String get_RIDER() {
		return _RIDER;
	}
	public void set_RIDER(String _RIDER) {
		this._RIDER = _RIDER;
	}
	public String get_LIFE() {
		return _LIFE;
	}
	public void set_LIFE(String _LIFE) {
		this._LIFE = _LIFE;
	}
	public String get_AGERATE() {
		return _AGERATE;
	}
	public void set_AGERATE(String _AGERATE) {
		this._AGERATE = _AGERATE;
	}
	public String get_OPPC() {
		return _OPPC;
	}
	public void set_OPPC(String _OPPC) {
		this._OPPC = _OPPC;
	}
	public String get_INSPRM() {
		return _INSPRM;
	}
	public void set_INSPRM(String _INSPRM) {
		this._INSPRM = _INSPRM;
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
