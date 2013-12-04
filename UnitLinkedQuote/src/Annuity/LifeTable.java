package Annuity;

public interface LifeTable {
	public double Lx( int Age, String Sex );
	public double tPx( int Age, int Term, String Sex);
	public double qx( int Age, String Sex);
	public double tPy( int Age1, String Sex1, int Age2, String Sex2, int Term );
}