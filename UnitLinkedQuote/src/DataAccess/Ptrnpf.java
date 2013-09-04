package DataAccess;

import java.lang.reflect.Field;
import java.util.Date;

public class Ptrnpf {
	
	private String _BATCTRCDE;
	private Date _DATIME;
	private Date _TRANSACTION_DATE;
	
	public String get_BATCTRCDE() {
		return _BATCTRCDE;
	}
	public void set_BATCTRCDE(String _BATCTRCDE) {
		this._BATCTRCDE = _BATCTRCDE;
	}
	public Date get_DATIME() {
		return _DATIME;
	}
	public void set_DATIME(Date _DATIME) {
		this._DATIME = _DATIME;
	}
	public Date get_TRANSACTION_DATE() {
		return _TRANSACTION_DATE;
	}
	public void set_TRANSACTION_DATE(Date _TRANSACTION_DATE) {
		this._TRANSACTION_DATE = _TRANSACTION_DATE;
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
