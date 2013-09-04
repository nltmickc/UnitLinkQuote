package DataAccess;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chdrpf {

	private String _CNTTYPE;                  //ProductCode
	private String _CAMPAIGN;                 //ReasonForSaleCode
	private Date _CURRFROM;                   //EffectiveDate
	private String _STATCODE;                 //ContractStatus
	private Date _OCCDATE;
	private String _PSTATCODE;                //ContractPremStatus
	private Date _PTDATE;                   //PaidToDate
	private Date _BTDATE;                   //BillToDate
	private String _BILLFREQ;                 //PremFreq
	private String _SRCEBUS;                  //Source of Business, added 28/02/08 LEO
	private String _COWNNUM;                  //Client number
			
	public Chdrpf(String ContractNumber) {
		
		String SQL;
		ResultSet rs = null;
		SimpleDateFormat _SDF = new SimpleDateFormat("yyyyMMdd");
			
		SQL = "SELECT CNTTYPE, CAMPAIGN, CURRFROM, STATCODE, OCCDATE, PSTATCODE, PTDATE, BTDATE, BILLFREQ, ";
		SQL += "SRCEBUS, COWNNUM FROM CHDRPF WHERE (CHDRPFX = 'CH') AND (CHDRCOY = '1') AND (VALIDFLAG = '1') ";
		SQL += "AND (CHDRNUM = '" + ContractNumber + "')";
		
		DataConnection DC = DataConnection.Instance();
		
		try {
				// 	Get a statement from the connection
			rs = DC.ExecuteSQL(SQL);
			
			while (rs.next()) {
				
				_CNTTYPE = rs.getString("CNTTYPE");
				_CAMPAIGN = rs.getString("CAMPAIGN");
				_CURRFROM = _SDF.parse( rs.getString("CURRFROM") );
				_STATCODE = rs.getString("STATCODE");
				_OCCDATE = _SDF.parse( rs.getString("OCCDATE") );
				_PSTATCODE = rs.getString("PSTATCODE");
				_PTDATE = _SDF.parse( rs.getString("PTDATE") );
				_BTDATE = _SDF.parse( rs.getString("BTDATE") );
				_BILLFREQ = rs.getString("BILLFREQ");
				_SRCEBUS = rs.getString("SRCEBUS");
				_COWNNUM = rs.getString("COWNNUM");
			
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
	
	public String get_CNTTYPE() {
		return _CNTTYPE;
	}

	public String get_CAMPAIGN() {
		return _CAMPAIGN;
	}

	public Date get_CURRFROM() {
		return _CURRFROM;
	}

	public String get_STATCODE() {
		return _STATCODE;
	}

	public Date get_OCCDATE() {
		        	
		return _OCCDATE;
	}

	public String get_PSTATCODE() {
		return _PSTATCODE;
	}

	public Date get_PTDATE() {
		return _PTDATE;
	}

	public Date get_BTDATE() {
		return _BTDATE;
	}

	public String get_BILLFREQ() {
		return _BILLFREQ;
	}

	public String get_SRCEBUS() {
		return _SRCEBUS;
	}

	public String get_COWNNUM() {
		return _COWNNUM;
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
