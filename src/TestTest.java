import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JLabel;

//public class PIPETest
//{
//
//	@Test
//	public void test()
//	{
//		fail("Not yet implemented");
//	}
//
//}
public class TestTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.equinox.app.IApplicationstart(org.eclipse.equinox.app.
	 * IApplicationContext)
	 */

	public static void main(String[] args) {
		System.out.println("Hello RCP World!");
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			createAndShowGUI();
		}
	});
		
//		try
//		{
////			javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
//				public void run() {
//					createAndShowGUI();
//				}
//			});
//		}
//		catch (InterruptedException e1)
//		{
//			e1.printStackTrace();
//		}
//		catch (InvocationTargetException e1)
//		{
//			e1.printStackTrace();
//		}
		try
		{
			Thread.sleep(10000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("Closing down...");
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		System.out.println("creating window");
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Add the ubiquitous "Hello World" label.
		JLabel label = new JLabel("Hello World");
		frame.getContentPane().add(label);
		// Display the window.
		frame.setSize(400, 400);
		frame.pack();
		frame.setVisible(true);
		System.out.println("should be visible now");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplicationstop()
	 */
	public void stop() {
		// nothing to do
	}
}

