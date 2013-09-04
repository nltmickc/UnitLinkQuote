package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class colCovrpf implements Iterable<Covrpf>  {
	
	private List<Covrpf> _Covrpfs = new ArrayList<Covrpf>();
	
	public colCovrpf (String ContractNumber) {
		
		String SQL;
		ResultSet rs = null;
		SimpleDateFormat _SDF = new SimpleDateFormat("yyyyMMdd"); 			
		
		SQL = "SELECT ZZFUTERM, CRTABLE, INSTPREM, PREM_CESS_DATE, PSTATCODE, SINGP, CRRCD, RISK_CESS_DATE, SUMINS, ";
		SQL += "STATCODE, INDEXATION_IND, PREM_CESS_TERM, COVERAGE_DEBT, RISK_CESS_TERM, PREM_CESS_AGE, RISK_CESS_AGE, ";
		SQL += "ANB_AT_CCD, SEX, MORTCLS, ZZAUTORED FROM COVRPF WHERE (CHDRCOY = '1') AND (VALIDFLAG = '1') ";
		SQL += "AND (CHDRNUM = '" + ContractNumber + "')";
		
		DataConnection DC = DataConnection.Instance();
				
		try {
				// 	Get a statement from the connection
			rs = DC.ExecuteSQL(SQL);
			
			while (rs.next()) {

				Covrpf _Covrpf = new Covrpf();
				
				_Covrpf.set_ZZFUTERM(rs.getInt("ZZFUTERM"));
				_Covrpf.set_CRTABLE(rs.getString("CRTABLE"));
				_Covrpf.set_INSTPREM(rs.getDouble("INSTPREM"));
				_Covrpf.set_PREM_CESS_DATE(_SDF.parse(rs.getString("PREM_CESS_DATE")));
				_Covrpf.set_PSTATCODE(rs.getString("PSTATCODE"));
				_Covrpf.set_SINGP(rs.getDouble("SINGP"));
				_Covrpf.set_CRRCD(_SDF.parse(rs.getString("CRRCD")));
				_Covrpf.set_RISK_CESS_DATE(_SDF.parse(rs.getString("RISK_CESS_DATE")));
				_Covrpf.set_SUMINS(rs.getDouble("SUMINS"));
				_Covrpf.set_STATCODE(rs.getString("STATCODE"));
				_Covrpf.set_INDEXATION_IND(rs.getString("INDEXATION_IND"));
				_Covrpf.set_PREM_CESS_TERM(rs.getInt("PREM_CESS_TERM"));
				_Covrpf.set_COVERAGE_DEBT(rs.getDouble("COVERAGE_DEBT"));
				_Covrpf.set_RISK_CESS_TERM(rs.getInt("RISK_CESS_TERM"));
				_Covrpf.set_PREM_CESS_AGE(rs.getInt("PREM_CESS_AGE"));
				_Covrpf.set_RISK_CESS_AGE(rs.getInt("RISK_CESS_AGE"));
				_Covrpf.set_ANB_AT_CCD(rs.getInt("ANB_AT_CCD"));
				_Covrpf.set_SEX(rs.getString("SEX"));
				_Covrpf.set_MORTCLS(rs.getString("MORTCLS"));
				_Covrpf.set_ZZAUTORED(rs.getString("ZZAUTORED"));
				
				_Covrpfs.add(_Covrpf);
				
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

	public List<Covrpf> get_Covrpfs() {
		return _Covrpfs;
	}
	
	@Override
	public String toString() {
		
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		
		result.append( this.getClass().getName() );
		result.append(newLine);
		result.append( "	Collection Object {" );
		result.append(newLine);
				
		for (Covrpf c : _Covrpfs) {
			
			result.append( "	Start Object " + (_Covrpfs.indexOf(c) + 1) + " of " + _Covrpfs.size());
			result.append(newLine);
			 
			result.append(c.toString());
			
			result.append(newLine);
			result.append( "	End Object " + (_Covrpfs.indexOf(c) + 1) + " of " + _Covrpfs.size());
					
		}
		
		result.append(newLine);
				
		result.append("		} End Collection");

		return result.toString();

	}

	@Override
	public Iterator<Covrpf> iterator() {
		Iterator<Covrpf> icovrpf= _Covrpfs.iterator();
        return icovrpf; 
	}
	
}