public class RegularPolygon {

	//	define class variables
	private int	n;	//number	of	sides	of	polygon
	private double	side;	//	length of sides
	private double	x;	//	x coordinate for center	of	polygon
	private double	y;	//	y coordinate for center	of	polygon

	//	no-arg constructor
	public RegularPolygon()	{
		n = 3;
		side = 1.0;
		x = 0;
		y = 0;
	}

	//	constructor	1
	public RegularPolygon(int nInt, double	nSide) {
		n = nInt;
		side = nSide;
	}

	//	contructor 2
	public RegularPolygon(int nInt, double	nSide, double nX,	double nY) {
		n = nInt;
		side = nSide;
		x = nX;
		y = nY;
	}

	//	start	of	setters
	public void	setN(int	nInt)	{
		n = nInt;
	}

	public void	setSide(double	nSide) {
		side = nSide;
	}

	public void	setX(double	nX) {
		x = nX;
	}

	public void	setY(double	nY) {
		y = nY;
	}
	//	end setters

	//	start	of	getters
	public int getN()	{
		return n;
	}

	public double getSide()	{
		return side;
	}

	public double getX()	{
		return x;
	}

	public double getY()	{
		return y;
	}
	//	end getters

	//start methods
	public double getPerimeter() {
		return (side *	n);
	}

	public double getApothem()	{ // I wasn't a fan of the	formula used in the book as it wasn't what I	was used	to	using	to	calculate area	of	regular polygons
		return (side /	(2	* (Math.tan(Math.toRadians(180/n)))));
	}

	public double getArea()	{	//	the formula	I used is A=pa/2 where p =	perimeter and a =	apothem//
		return ((getPerimeter()	* getApothem()) /	2);
	}
}