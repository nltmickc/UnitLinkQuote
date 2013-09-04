package DataAccess;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Ulnkpf {
	
	private String _LIFE;
	private String _COVERAGE;
	private String _UNIT_ALLOC_FUND01;
	private String _UNIT_ALLOC_PERC_AMT01;
	private String _UNIT_ALLOC_FUND02;
	private String _UNIT_ALLOC_PERC_AMT02;
	private String _UNIT_ALLOC_FUND03;
	private String _UNIT_ALLOC_PERC_AMT03;
	private String _UNIT_ALLOC_FUND04;
	private String _UNIT_ALLOC_PERC_AMT04;
	private String _UNIT_ALLOC_FUND05;
	private String _UNIT_ALLOC_PERC_AMT05;
	private String _UNIT_ALLOC_FUND06;
	private String _UNIT_ALLOC_PERC_AMT06;
	private String _UNIT_ALLOC_FUND07;
	private String _UNIT_ALLOC_PERC_AMT07;
	private String _UNIT_ALLOC_FUND08;
	private String _UNIT_ALLOC_PERC_AMT08;
	private String _UNIT_ALLOC_FUND09;
	private String _UNIT_ALLOC_PERC_AMT09;
	private String _UNIT_ALLOC_FUND10;
	private String _UNIT_ALLOC_PERC_AMT10;
	
	public Ulnkpf(String ContractNumber) {
				
		ResultSet rs = null;
		String SQL;
							
		SQL = "SELECT * FROM ULNKPF WHERE (VALIDFLAG = '1') AND (CHDRCOY = '1') AND (CHDRNUM = '" + ContractNumber + "')";
		
		DataConnection DC = DataConnection.Instance();
								
		try {
			// 	Get a statement from the connection
			rs = DC.ExecuteSQL(SQL);
			
			while (rs.next()) {
							
				_LIFE = rs.getString("LIFE");
				_COVERAGE = rs.getString("COVERAGE");
				_UNIT_ALLOC_FUND01 = rs.getString("UNIT_ALLOC_FUND01");
				_UNIT_ALLOC_PERC_AMT01 = rs.getString("UNIT_ALLOC_PERC_AMT01");
				_UNIT_ALLOC_FUND02 = rs.getString("UNIT_ALLOC_FUND02");
				_UNIT_ALLOC_PERC_AMT02 = rs.getString("UNIT_ALLOC_PERC_AMT02");
				_UNIT_ALLOC_FUND03 = rs.getString("UNIT_ALLOC_FUND03");
				_UNIT_ALLOC_PERC_AMT03 = rs.getString("UNIT_ALLOC_PERC_AMT03");
				_UNIT_ALLOC_FUND04 = rs.getString("UNIT_ALLOC_FUND04");
				_UNIT_ALLOC_PERC_AMT04 = rs.getString("UNIT_ALLOC_PERC_AMT04");
				_UNIT_ALLOC_FUND05 = rs.getString("UNIT_ALLOC_FUND05");
				_UNIT_ALLOC_PERC_AMT05 = rs.getString("UNIT_ALLOC_PERC_AMT05");
				_UNIT_ALLOC_FUND06 = rs.getString("UNIT_ALLOC_FUND06");
				_UNIT_ALLOC_PERC_AMT06 = rs.getString("UNIT_ALLOC_PERC_AMT06");
				_UNIT_ALLOC_FUND07 = rs.getString("UNIT_ALLOC_FUND07");
				_UNIT_ALLOC_PERC_AMT07 = rs.getString("UNIT_ALLOC_PERC_AMT07");
				_UNIT_ALLOC_FUND08 = rs.getString("UNIT_ALLOC_FUND08");
				_UNIT_ALLOC_PERC_AMT08 = rs.getString("UNIT_ALLOC_PERC_AMT08");
				_UNIT_ALLOC_FUND09 = rs.getString("UNIT_ALLOC_FUND09");
				_UNIT_ALLOC_PERC_AMT09 = rs.getString("UNIT_ALLOC_PERC_AMT09");
				_UNIT_ALLOC_FUND10 = rs.getString("UNIT_ALLOC_FUND10");
				_UNIT_ALLOC_PERC_AMT10 = rs.getString("UNIT_ALLOC_PERC_AMT10");

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

	public String get_LIFE() {
		return _LIFE;
	}

	public String get_COVERAGE() {
		return _COVERAGE;
	}

	public String get_UNIT_ALLOC_FUND01() {
		return _UNIT_ALLOC_FUND01;
	}

	public String get_UNIT_ALLOC_PERC_AMT01() {
		return _UNIT_ALLOC_PERC_AMT01;
	}

	public String get_UNIT_ALLOC_FUND02() {
		return _UNIT_ALLOC_FUND02;
	}

	public String get_UNIT_ALLOC_PERC_AMT02() {
		return _UNIT_ALLOC_PERC_AMT02;
	}

	public String get_UNIT_ALLOC_FUND03() {
		return _UNIT_ALLOC_FUND03;
	}

	public String get_UNIT_ALLOC_PERC_AMT03() {
		return _UNIT_ALLOC_PERC_AMT03;
	}

	public String get_UNIT_ALLOC_FUND04() {
		return _UNIT_ALLOC_FUND04;
	}

	public String get_UNIT_ALLOC_PERC_AMT04() {
		return _UNIT_ALLOC_PERC_AMT04;
	}

	public String get_UNIT_ALLOC_FUND05() {
		return _UNIT_ALLOC_FUND05;
	}

	public String get_UNIT_ALLOC_PERC_AMT05() {
		return _UNIT_ALLOC_PERC_AMT05;
	}

	public String get_UNIT_ALLOC_FUND06() {
		return _UNIT_ALLOC_FUND06;
	}

	public String get_UNIT_ALLOC_PERC_AMT06() {
		return _UNIT_ALLOC_PERC_AMT06;
	}

	public String get_UNIT_ALLOC_FUND07() {
		return _UNIT_ALLOC_FUND07;
	}

	public String get_UNIT_ALLOC_PERC_AMT07() {
		return _UNIT_ALLOC_PERC_AMT07;
	}

	public String get_UNIT_ALLOC_FUND08() {
		return _UNIT_ALLOC_FUND08;
	}

	public String get_UNIT_ALLOC_PERC_AMT08() {
		return _UNIT_ALLOC_PERC_AMT08;
	}

	public String get_UNIT_ALLOC_FUND09() {
		return _UNIT_ALLOC_FUND09;
	}

	public String get_UNIT_ALLOC_PERC_AMT09() {
		return _UNIT_ALLOC_PERC_AMT09;
	}

	public String get_UNIT_ALLOC_FUND10() {
		return _UNIT_ALLOC_FUND10;
	}

	public String get_UNIT_ALLOC_PERC_AMT10() {
		return _UNIT_ALLOC_PERC_AMT10;
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
