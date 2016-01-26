package org.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountTest
{
	@Test
	public void verifyBytes() throws Exception
	{
		byte[] test = new byte[]{0x00,0x00,0x00};
		assertEquals(0x00,test[0]);
		change(test); 
		assertEquals(0x01,test[0]);
	}

	private void change(byte[] test)
	{
		test[0] = 0x01;
	}

}
