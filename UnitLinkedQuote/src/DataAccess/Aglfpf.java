package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Aglfpf {
			
	private String _ZZOVRGRP;  //Overide
				
	public Aglfpf(String AgentNumber) {
				
		ResultSet rs = null;
		String SQL;
							
		SQL = "SELECT ZZOVRGRP FROM AGLFPF WHERE (VALIDFLAG = '1') AND (AGNTCOY = '1') AND AGNTNUM = '" + AgentNumber+ "'";
		
		DataConnection DC = DataConnection.Instance();
								
		try {
			// 	Get a statement from the connection
			rs = DC.ExecuteSQL(SQL);
			
			while (rs.next()) {
				_ZZOVRGRP = rs.getString("ZZOVRGRP");
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
	
	public String get_ZZOVRGRP() {
		return _ZZOVRGRP;
	}

}
