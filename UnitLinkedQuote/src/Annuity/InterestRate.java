package Annuity;

public class InterestRate {
	
	private double _i;
	private double _j;
	private int _convertible;
	
	public void set_i(double _i) {
		this._i = _i;
	}
	
	public void set_j(double _j) {
		this._j = _j;
	}
	
	public void setConvertable(int convertible) {
		this._convertible = convertible;
	}
	
	public double i_Pthly() {
		
		double Temp;
		
		Temp = (1 + _i);
		
		Temp = Math.pow(Temp, 1/_convertible);
		
		Temp = Temp - 1;
		
		Temp = Temp * _convertible;
		
		return Temp;
	}
	
	public double Vt(double Term ) {
		
		double Temp;
		
		Temp = (1 + _i);
		
		Temp = Math.pow(Temp, -Term );
		
		return Temp;
		
	}
	
	public double delta() {
		
		double Temp;
		
		Temp = (1 + _i);
				
		Temp = Math.log(Temp );
		
		return Temp;
		
	}
	
}
