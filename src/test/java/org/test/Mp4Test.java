
package org.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.util.List;

import org.junit.Test;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.MovieBox;
import com.coremedia.iso.boxes.UserDataBox;

public class Mp4Test
{
	@Test
	public void verifyOpenMp4File() throws Exception
	{
		String file = "ta_talk_grading_zotero.mp4";
		FileChannel fc = new FileInputStream(file).getChannel();
//		IsoFile iso = new IsoFile(fc); 
		IsoFile iso = new IsoFile(file);
		List<Box> boxes = iso.getBoxes(); 
		int i = 0; 
		for (Box box : boxes)
		{
			System.out.println("box"+(i++)+"  "+box);
		}
		MovieBox moov = iso.getBoxes(MovieBox.class).get(0); 
		System.out.println("size: "+moov.getSize()); 
		System.out.println("count: "+moov.getTrackCount()); 
		System.out.println("numbers: "+moov.getTrackNumbers()); 
		System.out.println("header: "+moov.getMovieHeaderBox()); 
		UserDataBox udata = moov.getBoxes(UserDataBox.class).get(0); 
		System.out.println(udata.getBoxes());
	}

}
