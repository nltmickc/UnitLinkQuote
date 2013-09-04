package UnitLinkedQuote;

//possibly make an interface later when concrete implementation of 
//allocations is implemented.
public class Allocation {
	
	private double _AllocationRate;
	
	public Allocation() {
		_AllocationRate = 1.0;
	}

	public double get_AllocationRate(double premium) {
		return _AllocationRate;
	}

	public void set_AllocationRate(double _AllocationRate) {
	
		this._AllocationRate = _AllocationRate;
	
	}
			
}