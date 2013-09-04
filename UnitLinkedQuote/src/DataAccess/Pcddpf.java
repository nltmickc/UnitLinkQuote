package DataAccess;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pcddpf {
	
	private String _AGNTNUM;
	private String _SPLIT_BCOMM;
	private String _AGTYPE;
		
	public Pcddpf(String ContractNumber) {
		
		ResultSet rs = null;
		String SQL;
					
		SQL = "SELECT AGNTNUM, SPLIT_BCOMM, AGTYPE FROM PCDDPF WHERE (CHDRCOY = '1') AND (VALIDFLAG = '1') ";
		SQL += "AND (CHDRNUM = '" + ContractNumber + "')";
		
		DataConnection DC = DataConnection.Instance();
		
		try {
				// 	Get a statement from the connection
			rs = DC.ExecuteSQL(SQL);
			
			while (rs.next()) {
				_AGNTNUM = rs.getString("AGNTNUM");
				_SPLIT_BCOMM = rs.getString("SPLIT_BCOMM");
				_AGTYPE = rs.getString("AGTYPE");
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

	public String get_AGNTNUM() {
		return _AGNTNUM;
	}

	public String get_SPLIT_BCOMM() {
		return _SPLIT_BCOMM;
	}

	public String get_AGTYPE() {
		return _AGTYPE;
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
