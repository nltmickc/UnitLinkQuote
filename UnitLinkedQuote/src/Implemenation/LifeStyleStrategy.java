package Implemenation;

public class LifeStyleStrategy {
	
	private String _Code;
	private String _SourceFund;
	private String _DestinationFund;
	private int _FundingPeriod;
	
	public LifeStyleStrategy (String Code, String Source, String Destination, int Period) {
		
		this._Code = Code;
		
		this._SourceFund = Source;
		
		this._DestinationFund = Destination;
		
		this._FundingPeriod = Period;
	} 
	
	public String getCode() {
		
		return _Code;
	}
	
	public String getSourceFund() {
		
		return _SourceFund;
	}
	
	public String getDestinationFund() {
		
		return _DestinationFund;
	}
	
	public int getFundingPeriod() {
		
		return _FundingPeriod * 12;
	}
}