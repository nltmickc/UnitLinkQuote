package DataAccess;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Clntpf {
	
	private String _SALUTL;
	private String _GIVNAME;
	private String _SURNAME;
		
	
	public Clntpf(String ClientNumber) {
		
		ResultSet rs = null;
		String SQL;
					
		SQL = "SELECT SALUTL, GIVNAME, SURNAME FROM CLNTPF WHERE (CLNTCOY = '1') AND (VALIDFLAG = '1') AND (CLNTNUM = '" + ClientNumber+ "')";
		
		DataConnection DC = DataConnection.Instance(); 
				
		try {
				// 	Get a statement from the connection
			rs = DC.ExecuteSQL(SQL);
			
			while (rs.next()) {
				_SALUTL = rs.getString("SALUTL");
				_GIVNAME = rs.getString("GIVNAME");
				_SURNAME = rs.getString("SURNAME");
			}  											
		} 
		catch (SQLException se) {
		   	
			System.out.println("SQL Exception:");

			// Loop through the SQL Exceptions
		    while( se != null ) {
		    	System.out.println( "State  : " + se.getSQLState());
		    	System.out.println( "Message: " + se.getMessage());
		    	System.out.println( "Error  : " + se.getErrorCode());

		    	se = se.getNextException();
		    }
		}
		       	    
	}

	public String get_SALUTL() {
		return _SALUTL;
	}

	public String get_GIVNAME() {
		return _GIVNAME;
	}

	public String get_SURNAME() {
		return _SURNAME;
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
