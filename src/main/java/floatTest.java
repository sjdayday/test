import static org.junit.Assert.*;

import org.junit.Test;
	

public class floatTest
{

	@Test
	public void testFloatingPoint()
	{
		assertEquals(2.3, mult(2,3,0), .01); 
		assertEquals(23, mult(2,3,1), .1); 
		assertEquals(230, mult(2,3,2), 1); 
		assertEquals(2300000000d, mult(2,3,9), 10000); 
		assertEquals(.23, mult(2,3,-1), .01); 
		assertEquals(.023, mult(2,3,-2), .001); 
		assertEquals(.000023, mult(2,3,-5), .000001); 
	}
	public double mult(double first, double second, int exponent)
	{
		double total = 0; 
		second = second/10;
		double mantissa = first + second; 
		double power = Math.pow(10, exponent); 
		System.out.println("exponent "+exponent+" power: "+power);
		total = mantissa * (power); 
		return total ;
	}
}
