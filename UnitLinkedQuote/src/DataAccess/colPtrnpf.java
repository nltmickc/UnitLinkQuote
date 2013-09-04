package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class colPtrnpf {

	private List<Ptrnpf> _Ptrnpfs = new ArrayList<Ptrnpf>();
	
	public colPtrnpf( String ContractNumber ) {
		
		String SQL;
		ResultSet rs = null;
		SimpleDateFormat _SDF; 
								
		SQL = "SELECT BATCTRCDE, DATIME, TRANSACTION_DATE FROM PTRNPF WHERE (CHDRPFX = '  ') AND ";
		SQL += "(CHDRCOY = '1') AND (BATCTRCDE IN ('T580','T642')) AND (CHDRNUM = '" + ContractNumber + "')";
		
		DataConnection DC = DataConnection.Instance();
				
		try {
				// 	Get a statement from the connection
			rs = DC.ExecuteSQL(SQL);
			
			while (rs.next()) {
				
				Ptrnpf _Ptrnpf = new Ptrnpf();
				
				_Ptrnpf.set_BATCTRCDE(rs.getString("BATCTRCDE"));
				_SDF = new SimpleDateFormat("yyyyMMdd");
				_Ptrnpf.set_DATIME(_SDF.parse(rs.getString("DATIME")));
				_SDF = new SimpleDateFormat("yyMMdd");
				_Ptrnpf.set_TRANSACTION_DATE(_SDF.parse(rs.getString("TRANSACTION_DATE")));
								
				_Ptrnpfs.add(_Ptrnpf);
				
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

	public List<Ptrnpf> get_Ptrnpfs() {
		return _Ptrnpfs;
	}
	
	@Override
	public String toString() {
		
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		
		result.append( this.getClass().getName() );
		result.append(newLine);
		result.append( "	Collection Object {" );
		result.append(newLine);
		
		for (Ptrnpf p : _Ptrnpfs) {
			 
			result.append( "	Start Object " + (_Ptrnpfs.indexOf(p) + 1) + " of " + _Ptrnpfs.size());
			result.append(newLine);
			 
			result.append(p.toString());
			
			result.append(newLine);
			result.append( "	End Object " + (_Ptrnpfs.indexOf(p) + 1) + " of " + _Ptrnpfs.size());
		
		}
		
		result.append("		} End Collection");

		return result.toString();

	}
	
}
