package DataAccess;
import java.sql.*;

public class DataConnection {
	
	// DataConnection is a Singleton
	private static DataConnection _Instance = null; // once instance per type
	
	private Connection _Conn;
	
	public static DataConnection Instance() {
		
		if( _Instance == null ) {
			
			_Instance = new DataConnection();
		}
		return _Instance;
	}
	
	private DataConnection() {
		
		try
	    	{
			// Load the database driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			// Get a connection to the database
			_Conn = DriverManager.getConnection("jdbc:odbc:Life/400;Uid=MCD;Pwd=y89j49si;");

		      // Print all warnings
		      for( SQLWarning warn = _Conn.getWarnings(); warn != null; warn = warn.getNextWarning() )
		         {
		          System.out.println( "SQL Warning:" ) ;
		          System.out.println( "State  : " + warn.getSQLState()  ) ;
		          System.out.println( "Message: " + warn.getMessage()   ) ;
		          System.out.println( "Error  : " + warn.getErrorCode() ) ;
		         }
	
		     }
	    catch( SQLException se ) {
	    	
	    	System.out.println("SQL Exception:");

		    // Loop through the SQL Exceptions
	    	while( se != null ) {
	    		System.out.println( "State  : " + se.getSQLState()  ) ;
	    		System.out.println( "Message: " + se.getMessage()   ) ;
	    		System.out.println( "Error  : " + se.getErrorCode() ) ;

	    		se = se.getNextException() ;
	    	}
		}
		catch( Exception e ) {
		      System.out.println( e ) ;
		}
	}
		
	public ResultSet ExecuteSQL(String SQL) {
		
		// Execute the query
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			// 	Get a statement from the connection
			stmt = _Conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			rs = stmt.executeQuery(SQL);
						
		} catch (SQLException se) {
				
			System.out.println("DataConnection SQL Exception:");

			// Loop through the SQL Exceptions
		    while( se != null ) {
		    	System.out.println( "State  : " + se.getSQLState());
		    	System.out.println( "Message: " + se.getMessage());
		    	System.out.println( "Error  : " + se.getErrorCode());

		    	se = se.getNextException();
		    }
		}
		return rs;
	}
}