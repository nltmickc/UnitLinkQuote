package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Zzsgpf {

	private String _ZZINVCODE;  //Investment Strategy
	private String _ZZTRACKNO;  // PRSA Reference Number
			
	public Zzsgpf(String ContractNumber) {
		
		ResultSet rs = null;
		String SQL;
					
		SQL = "SELECT ZZINVCODE, ZZTRACKNO FROM ZZSGPF WHERE (CHDRCOY = '1') AND (CHDRNUM = '" + ContractNumber + "')";
	
		DataConnection DC = DataConnection.Instance();
		
		try {
				// 	Get a statement from the connection
			rs = DC.ExecuteSQL(SQL);
			
			while (rs.next()) {	
				_ZZINVCODE = rs.getString("ZZINVCODE");
				_ZZTRACKNO = rs.getString("ZZTRACKNO");
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

	//Investment Strategy
	public String get_ZZINVCODE() {
		return _ZZINVCODE;
	}
	// PRSA Reference Number
	public String get_ZZTRACKNO() {
		return _ZZTRACKNO;
	}
	
}
