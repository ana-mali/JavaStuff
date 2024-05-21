package cp213;

import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

//---------------------------------------------------------------
/**
 * 
 *
 * @author Anastasia Malinovski
 * @version 2020-07-24
 */
public class RTSameView {
	public static void main(String args[]) {
		final RTModel model = new RTModel();
		final OneFram4RT view = new OneFram4RT(model);

		final JFrame f = new JFrame("Right Triangle");
		f.setContentPane(view);
		f.setSize(300, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	    }

	    // ---------------------------------------------------------------
	}
