
package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class colLextpf  {
	
	private List<Lextpf> _Lextpfs = new ArrayList<Lextpf>();
		
	public colLextpf(String ContractNumber) {
		
		String SQL;
		ResultSet rs = null;
										
		SQL = "SELECT COVERAGE, RIDER, LIFE, AGERATE, OPPC, INSPRM FROM LEXTPF WHERE ";
		SQL += "(CHDRCOY = '1') AND (VALIDFLAG = '1') AND (CHDRNUM = '" + ContractNumber + "')";
		
		DataConnection DC = DataConnection.Instance();
				
		try {
				// 	Get a statement from the connection
			rs = DC.ExecuteSQL(SQL);
			
			while (rs.next()) {
				
				Lextpf _Lextpf = new Lextpf();
				
				_Lextpf.set_COVERAGE(rs.getString("COVERAGE"));
				_Lextpf.set_RIDER(rs.getString("RIDER"));
				_Lextpf.set_LIFE(rs.getString("LIFE"));
				_Lextpf.set_AGERATE(rs.getString("AGERATE"));
				_Lextpf.set_OPPC(rs.getString("OPPC"));
				_Lextpf.set_INSPRM(rs.getString("INSPRM"));
								
				_Lextpfs.add(_Lextpf);
				
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

	public List<Lextpf> get_Lextpfs() {
		return _Lextpfs;
	}
	
	@Override
	public String toString() {
		
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		
		result.append( this.getClass().getName() );
		result.append(newLine);
		result.append( "	Collection Object {" );
		result.append(newLine);
		
		for (Lextpf l : _Lextpfs) {
			
			result.append( "	Start Object " + (_Lextpfs.indexOf(l) + 1) + " of " + _Lextpfs.size());
			result.append(newLine);
			 
			result.append(l.toString());
			
			result.append(newLine);
			result.append( "	End Object " + (_Lextpfs.indexOf(l) + 1) + " of " + _Lextpfs.size());
		
		}
		
		result.append("		} End Collection");

		return result.toString();

	}
	
}

