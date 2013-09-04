package DataAccess;

import java.lang.reflect.Field;

public class ContractData {
	 
	Chdrpf _ContractHeader;
	colCovrpf _ContractComponents;
	Lifepf _LifeData;
	Zchdpf _IndexationEtc;
	Zemppf _SalaryDetails;
	Pcddpf _AgentDetails;
	Zzsgpf _StrategyDetails;
	Aglfpf _AgentOveride; 
	Clntpf _PayerDetails;
	Zafspf _AutomaticFundSwitching;
	colIncipf _PremiumIncrements;
	colLextpf _SpecialTerms;  
	Ulnkpf _FundPercentages;
	colZzptpf _Components;
	colPtrnpf _TransactionDates;
	colUtrspf _UnitHolding;
	
	public ContractData( String ContractNumber ) {
		
		_ContractHeader = new Chdrpf( ContractNumber );
		
		_ContractComponents = new colCovrpf( ContractNumber );
		
		_LifeData = new Lifepf( ContractNumber );
				
		_IndexationEtc = new Zchdpf( ContractNumber );
				
		_SalaryDetails = new Zemppf( _ContractHeader.get_COWNNUM() );
		
		_AgentDetails = new Pcddpf( ContractNumber );
		
		_StrategyDetails = new Zzsgpf( ContractNumber );
		
		_AgentOveride = new Aglfpf( _AgentDetails.get_AGNTNUM() ); 
		
		_PayerDetails = new Clntpf( _ContractHeader.get_COWNNUM() );
		
		_AutomaticFundSwitching = new Zafspf(ContractNumber );
		
		_PremiumIncrements = new colIncipf( ContractNumber );
		
		_SpecialTerms = new colLextpf( ContractNumber );  
		
		_FundPercentages = new Ulnkpf( ContractNumber );
		
		_Components = new colZzptpf( ContractNumber );
		
		_TransactionDates = new colPtrnpf( ContractNumber );
		
		_UnitHolding = new colUtrspf( ContractNumber );
		
	}
	
	public Chdrpf get_ContractHeader() {
		return _ContractHeader;
	}

	public colCovrpf get_ContractComponents() {
		return _ContractComponents;
	}

	public Zchdpf get_IndexationEtc() {
		return _IndexationEtc;
	}

	public Zemppf get_SalaryDetails() {
		return _SalaryDetails;
	}

	public Pcddpf get_AgentDetails() {
		return _AgentDetails;
	}

	public Zzsgpf get_StrategyDetails() {
		return _StrategyDetails;
	}

	public Aglfpf get_AgentOveride() {
		return _AgentOveride;
	}

	public Clntpf get_PayerDetails() {
		return _PayerDetails;
	}

	public Zafspf get_AutomaticFundSwitching() {
		return _AutomaticFundSwitching;
	}

	public colIncipf get_PremiumIncrements() {
		return _PremiumIncrements;
	}

	public colLextpf get_SpecialTerms() {
		return _SpecialTerms;
	}

	public Ulnkpf get_FundPercentages() {
		return _FundPercentages;
	}

	public colZzptpf get_Components() {
		return _Components;
	}

	public colPtrnpf get_TransactionDates() {
		return _TransactionDates;
	}

	public colUtrspf get_UnitHolding() {
		return _UnitHolding;
	}
	
//	public int ContractTermInMonths() {
//		
//		if ( ( _ContractHeader != null ) && ( _LifeData != null ) ) {
//			
//			return (int)0; 
//									
//		}
//				
//	}
	
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