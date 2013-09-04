package DataAccess;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Zchdpf {
	 
	private String _INDEXATION_IND;
	private String _ZWAIV_FEE;
	private String _ZZSMTIND;
		
	public Zchdpf(String ContractNumber) {
		
		ResultSet rs = null;
		String SQL;
					
		SQL = "SELECT INDEXATION_IND, ZWAIV_FEE, ZZSMTIND FROM ZCHDPF WHERE (CHDRCOY = '1') AND (VALIDFLAG = '1') ";
		SQL += "AND (CHDRNUM = '" + ContractNumber + "')";
		
		DataConnection DC = DataConnection.Instance();
		
		try {
				// 	Get a statement from the connection
			rs = DC.ExecuteSQL(SQL);
			
			while (rs.next()) {
				_INDEXATION_IND = rs.getString("INDEXATION_IND");
				_ZWAIV_FEE = rs.getString("ZWAIV_FEE");
				_ZZSMTIND = rs.getString("ZZSMTIND");
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

	public String get_INDEXATION_IND() {
		return _INDEXATION_IND;
	}

	public String get_ZWAIV_FEE() {
		return _ZWAIV_FEE;
	}

	public String get_ZZSMTIND() {
		return _ZZSMTIND;
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
