
package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class colZzptpf {
	
	private List<Zzptpf> _Zzptpfs = new ArrayList<Zzptpf>();
		
	public colZzptpf( String ContractNumber ) {
		
		String SQL;
		ResultSet rs = null;
		SimpleDateFormat _SDF = new SimpleDateFormat("yyyyMMdd");
								
		SQL = "SELECT CHDRCOY, COVERAGE, RIDER, LIFE, CPI_DATE, ZZACTIVE01, ZZACTIVE02 FROM ZZPTPF ";
		SQL +="WHERE (CHDRCOY = '1') AND (CHDRNUM = '" + ContractNumber + "')";
		
		DataConnection DC = DataConnection.Instance();
		
		try {
				// 	Get a statement from the connection
			rs = DC.ExecuteSQL(SQL);
			
			while (rs.next()) {
				
				Zzptpf _Zzptpf = new Zzptpf();
								
				_Zzptpf.set_CHDRCOY(rs.getString("CHDRCOY"));
				_Zzptpf.set_COVERAGE(rs.getString("COVERAGE"));
				_Zzptpf.set_RIDER(rs.getString("RIDER"));
				_Zzptpf.set_LIFE(rs.getString("LIFE"));
				_Zzptpf.set_CPI_DATE(_SDF.parse(rs.getString("CPI_DATE")));
				_Zzptpf.set_ZZACTIVE01(rs.getString("ZZACTIVE01"));
				_Zzptpf.set_ZZACTIVE02(rs.getString("ZZACTIVE02"));
												
				_Zzptpfs.add(_Zzptpf);
				
			}  											
		}
		catch (ParseException pe){
			 System.out.println(pe);
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

	public List<Zzptpf> get_Zzptpfs() {
		return _Zzptpfs;
	}
	
	@Override
	public String toString() {
		
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		
		result.append( this.getClass().getName() );
		result.append(newLine);
		result.append( "	Collection Object {" );
		result.append(newLine);
		
		for (Zzptpf z : _Zzptpfs) {
			 
			result.append( "	Start Object " + (_Zzptpfs.indexOf(z) + 1) + " of " + _Zzptpfs.size());
			result.append(newLine);
			 
			result.append(z.toString());
			
			result.append(newLine);
			result.append( "	End Object " + (_Zzptpfs.indexOf(z) + 1) + " of " + _Zzptpfs.size());
		
		}
		
		result.append("		} End Collection");

		return result.toString();

	}
	
}

