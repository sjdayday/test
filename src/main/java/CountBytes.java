import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class CountBytes
{
	static byte[] header = new byte[]{(byte)0xff, (byte)0xfb, (byte)0xb2};
	private static int minimum; 
	public static void main(String[] arg)
	{
		File file = new File("/Users/stevedoubleday/Desktop/IREC0001.MP3"); 
		try
		{
			InputStream in = new BufferedInputStream(new FileInputStream(file));
			byte[] test = new byte[]{0x00,0x00,0x00};
			byte[] single = new byte[1];
			int where = 0; 
			int lastHeader = 0; 
			int nextHeader = 0; 
			int length = 0; 
			int num = 0; 
			minimum = 0;
			int index = in.read(single);
			while (index != -1)
			{
				if (foundHeader(test,single)) 
				{
					minimum = 0; 
					num++; 
					lastHeader = nextHeader; 
					nextHeader = where; 
					length = nextHeader - lastHeader; 
					if ((length != 627) && (length != 626))
					{
						System.out.println(num+": non-standard header of length "+length+" found at "+where);
					}
				}
				minimum++; 
				where++;
				index = in.read(single);
			}
			System.out.println(num);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static boolean foundHeader(byte[] test, byte[] single)
	{
		test[0] = test[1];
		test[1] = test[2];
		test[2] = single[0];
			if ((test[0] & 0xff) != 0xff) return false;
			if ((test[1] & 0xff) != 0xfb) return false;
			if (((test[2] & 0xff) == 0xb2) || ((test[2] & 0xff) == 0xb0)) 
				if (minimum > 625)
				{
					return true;
				}
//		for (int i = 0; i < test.length; i++)
//		{
//			if ((byte)test[i] != header[i]) return false; 
//		}
				else return false;
			return false; 
	}
}
