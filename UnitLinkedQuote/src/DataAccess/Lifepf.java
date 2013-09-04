package DataAccess;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lifepf {
		
	private String _CHDRCOY; 
	private String _CHDRNUM;
	private String _VALIDFLAG; 
	private String _LIFE; 
	private String _JLIFE; 
	private String _LIFCNUM; 
	private Date _CLTDOB; 
	private String _CLTSEX; 
	private int _ANB_AT_CCD;
			
	public Lifepf(String ContractNumber) {
			
		ResultSet rs = null;
		String SQL;
		SimpleDateFormat _SDF = new SimpleDateFormat("yyyyMMdd");
									
		SQL = "SELECT CHDRCOY, CHDRNUM, VALIDFLAG, LIFE, JLIFE, LIFCNUM, CLTDOB, CLTSEX, ANB_AT_CCD FROM LIFEPF WHERE ";
		SQL += "(CHDRCOY = '1') AND (VALIDFLAG = '1') AND (CHDRNUM = '" + ContractNumber + "')";
			
		DataConnection DC = DataConnection.Instance();

		try {
			// 	Get a statement from the connection
			rs = DC.ExecuteSQL(SQL);

			while (rs.next()) {
				_CHDRCOY = rs.getString("CHDRCOY"); 
				_CHDRNUM = rs.getString("CHDRNUM");
				_VALIDFLAG = rs.getString("VALIDFLAG"); 
				_LIFE = rs.getString("LIFE"); 
				_JLIFE = rs.getString("JLIFE"); 
				_LIFCNUM = rs.getString("LIFCNUM"); 
				_CLTDOB = _SDF.parse( rs.getString("CLTDOB") ); 
				_CLTSEX = rs.getString("CLTSEX"); 
				_ANB_AT_CCD = rs.getInt("ANB_AT_CCD");

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

	public String get_CHDRCOY() {
		return _CHDRCOY;
	}


	public String get_CHDRNUM() {
		return _CHDRNUM;
	}


	public String get_VALIDFLAG() {
		return _VALIDFLAG;
	}


	public String get_LIFE() {
		return _LIFE;
	}


	public String get_JLIFE() {
		return _JLIFE;
	}


	public String get_LIFCNUM() {
		return _LIFCNUM;
	}


	public Date get_CLTDOB() {
		return _CLTDOB;
	}


	public String get_CLTSEX() {
		return _CLTSEX;
	}


	public int get_ANB_AT_CCD() {
		return _ANB_AT_CCD;
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


