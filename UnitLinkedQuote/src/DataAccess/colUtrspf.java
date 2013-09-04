package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class colUtrspf {
	
private List<Utrspf> _Utrspfs = new ArrayList<Utrspf>();
	
	public colUtrspf( String ContractNumber ) {
		
		ResultSet rs = null;
		String SQL;

		SQL = "SELECT LIFE, COVERAGE, UNIT_VIRTUAL_FUND, CURRENT_UNIT_BAL, UNIT_TYPE FROM UTRSPF WHERE ";
		SQL += "(CHDRCOY = '1') AND (CHDRNUM = '" + ContractNumber + "')";
		
		DataConnection DC = DataConnection.Instance();
				
		try {
				// 	Get a statement from the connection
			rs = DC.ExecuteSQL(SQL);
			
			while (rs.next()) {
				
				Utrspf _Utrspf = new Utrspf();
								
				_Utrspf.set_LIFE(rs.getString("LIFE"));
				_Utrspf.set_COVERAGE(rs.getString("COVERAGE"));
				_Utrspf.set_UNIT_VIRTUAL_FUND(rs.getString("UNIT_VIRTUAL_FUND"));
				_Utrspf.set_CURRENT_UNIT_BAL(rs.getString("CURRENT_UNIT_BAL"));
				_Utrspf.set_UNIT_TYPE(rs.getString("UNIT_TYPE"));
								
				_Utrspfs.add(_Utrspf);
				
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

	public List<Utrspf> get_Utrspfs() {
		return _Utrspfs;
	}
	
	@Override
	public String toString() {
		
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		
		result.append( this.getClass().getName() );
		result.append(newLine);
		result.append( "	Collection Object {" );
		result.append(newLine);
		
		for (Utrspf u : _Utrspfs) {
			 
			result.append( "	Start Object " + (_Utrspfs.indexOf(u) + 1) + " of " + _Utrspfs.size());
			result.append(newLine);
			 
			result.append(u.toString());
			
			result.append(newLine);
			result.append( "	End Object " + (_Utrspfs.indexOf(u) + 1) + " of " + _Utrspfs.size());
		}
		
		result.append("		} End Collection");
		
		return result.toString();


	}

}