package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class colIncipf implements Iterable<Incipf> {
	
	private List<Incipf> _Incipfs = new ArrayList<Incipf>();
		
	public colIncipf(String ContractNumber) {
		
		String SQL;
		ResultSet rs = null;
		SimpleDateFormat _SDF = new SimpleDateFormat("yyyyMMdd");
							
		SQL = "SELECT LIFE, COVERAGE, RCDATE, PREM_CESS_DATE, ORIG_PREM, CURR_PREM, PREM_START01, ";
		SQL += "PREM_START02, PREM_START03, PREM_START04, PREM_CURR01, PREM_CURR02, PREM_CURR03, ";
		SQL += "PREM_CURR04, PC_UNITS01, PC_UNITS02, PC_UNITS03, PC_UNITS04, UNIT_SPLIT01, ";
		SQL += "UNIT_SPLIT02, UNIT_SPLIT03, UNIT_SPLIT04 FROM INCIPF WHERE (CHDRCOY = '1') ";
		SQL += "AND (VALIDFLAG = '1') AND (CHDRNUM = '" + ContractNumber + "')";
		
		DataConnection DC = DataConnection.Instance();
				
		try {
				// 	Get a statement from the connection
			rs = DC.ExecuteSQL(SQL);
			
			while (rs.next()) {
				Incipf _Incipf = new Incipf();
				
				_Incipf.set_LIFE(rs.getString("LIFE"));
				_Incipf.set_COVERAGE(rs.getString("COVERAGE"));
				_Incipf.set_RCDATE(_SDF.parse(rs.getString("RCDATE")));
				_Incipf.set_PREM_CESS_DATE(_SDF.parse(rs.getString("PREM_CESS_DATE")));
				_Incipf.set_ORIG_PREM(rs.getString("ORIG_PREM"));
				_Incipf.set_CURR_PREM(rs.getString("CURR_PREM"));
				_Incipf.set_PREM_START01(rs.getString("PREM_START01"));
				_Incipf.set_PREM_START02(rs.getString("PREM_START02"));
				_Incipf.set_PREM_START03(rs.getString("PREM_START03"));
				_Incipf.set_PREM_START04(rs.getString("PREM_START04"));
				_Incipf.set_PREM_CURR01(rs.getString("PREM_CURR01"));
				_Incipf.set_PREM_CURR02(rs.getString("PREM_CURR02"));
				_Incipf.set_PREM_CURR03(rs.getString("PREM_CURR03"));
				_Incipf.set_PREM_CURR04(rs.getString("PREM_CURR04"));
				_Incipf.set_PC_UNITS01(rs.getString("PC_UNITS01"));
				_Incipf.set_PC_UNITS02(rs.getString("PC_UNITS02"));
				_Incipf.set_PC_UNITS03(rs.getString("PC_UNITS03"));
				_Incipf.set_PC_UNITS04(rs.getString("PC_UNITS04"));
				_Incipf.set_UNIT_SPLIT01(rs.getString("UNIT_SPLIT01"));
				_Incipf.set_UNIT_SPLIT02(rs.getString("UNIT_SPLIT02"));
				_Incipf.set_UNIT_SPLIT03(rs.getString("UNIT_SPLIT03"));
				_Incipf.set_UNIT_SPLIT04(rs.getString("UNIT_SPLIT04"));

				_Incipfs.add(_Incipf);
				
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

	public List<Incipf> get_Incipfs() {
		return _Incipfs;
	}
	
	@Override
	public String toString() {
		
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		
		result.append( this.getClass().getName() );
		result.append(newLine);
		result.append( "	Collection Object {" );
		result.append(newLine);
		
		for (Incipf i : _Incipfs) {
			
			result.append( "	Start Object " + (_Incipfs.indexOf(i) + 1) + " of " + _Incipfs.size());
			result.append(newLine);
			 
			result.append(i.toString());
			
			result.append(newLine);
			result.append( "	End Object " + (_Incipfs.indexOf(i) + 1) + " of " + _Incipfs.size());
			
		
		}
		
		result.append("		} End Collection");

		return result.toString();

	}

	@Override
	public Iterator<Incipf> iterator() {
		Iterator<Incipf> incipf = _Incipfs.iterator();
	    return incipf; 
	}
	
}
