package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// ---------------------------------------------------------------
/**
 * View and update the right triangle model with buttons that increment the base
 * and height by 1.
 *
 * @author David Brown from Byron Weber-Becker
 * @version 2017-06-19
 */
@SuppressWarnings("serial")
public class NewButton extends JPanel {
	
	// -------------------------------------------------------------------------------
	/**
	 * The formatter for displaying numeric output.
	 */
	private static final DecimalFormat f = new DecimalFormat("###.##");
	/**
	 * Displays the model's base value.
	 */
	private final JLabel base = new JLabel(" ");
	/**
	 * Decrements base by 1.
	 */
	private final JButton baseDown = new JButton("-");
	/**
	 * Increments base by 1.
	 */
	private final JButton baseUp = new JButton("+");
	/**
	 * Displays the model's height value.
	 */
	private final JLabel hypo = new JLabel(" ");
	/**
	 * The right triangle model.
	 */
	private final JButton setBandH = new JButton("Make Same");
	
	private final JLabel height = new JLabel(" ");
	private final RTModel model;
	private final JLabel same = new JLabel(" ");

	// ---------------------------------------------------------------

	// ---------------------------------------------------------------
	/**
	 * An inner class that uses an ActionListener to access the buttons. It sets the
	 * model values when the button is pressed.
	 */
	private class BaseButtonListener implements ActionListener {
		/**
		 * Determines whether values are incremented (+) or decremented (-).
		 */
		private int direction = 0;

		public BaseButtonListener(final int direction) {
			this.direction = direction;
		}

		@Override
		public void actionPerformed(final ActionEvent evt) {
			NewButton.this.model.setBase(NewButton.this.model.getBase() + this.direction);
			NewButton.this.model.setHeight(NewButton.this.model.getHeight() + this.direction);

		}

	}

	// -------------------------------------------------------------------------------
	/**
	 * An inner class the updates the base and hypotenuse labels whenever the
	 * model's base attribute is updated.
	 */
	private class BaseListener implements PropertyChangeListener {
		
		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			NewButton.this.base.setText(NewButton.f.format(NewButton.this.model.getBase()));
			NewButton.this.hypo.setText(NewButton.f.format(NewButton.this.model.getHypotenuse()));
			NewButton.this.height.setText(NewButton.f.format(NewButton.this.model.getHeight()));
			if (NewButton.this.model.getBase()==NewButton.this.model.getHeight()) {
				NewButton.this.same.setText("SAME: "+NewButton.f.format(NewButton.this.model.getBase()));
			}
			else {
				NewButton.this.same.setText(" ");
			}
		}
	}
	// -------------------------------------------------------------------------------
	
	private class setBandHListener implements ActionListener{
		public void actionPerformed(final ActionEvent evt) {
			int num=(int) ((NewButton.this.model.getHeight()+NewButton.this.model.getBase())/2);
			NewButton.this.model.setBase(num);
			NewButton.this.model.setHeight(num);
	}
	}
	// -------------------------------------------------------------------------------

	/**
	 * The view constructor.
	 *
	 * @param newModel The right triangle model.
	 */
	public NewButton(final RTModel newModel) {
		this.model = newModel;
		this.layoutView();
		this.registerListeners();
		// Initialize the view labels.
		this.base.setText(NewButton.f.format(this.model.getBase()));
		this.hypo.setText(NewButton.f.format(this.model.getHypotenuse()));
		this.height.setText(NewButton.f.format(this.model.getHeight()));
	}

	// ---------------------------------------------------------------
	/**
	 * Uses the GridLayout to place the labels and buttons.
	 */
	private void layoutView() {
		this.setLayout(new GridLayout(3, 5));
		this.add(new JLabel("Base: "));
		this.add(this.base);
		this.add(this.baseUp);
		this.add(this.baseDown);
		this.add(new JLabel());
		this.add(new JLabel());
		this.base.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(new JLabel("Height: "));
		this.add(this.height);
		this.add(new JLabel("Hypotenuse: "));
		this.add(this.hypo);
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(setBandH);
		this.add(new JLabel());
		
		this.add(new JLabel());
		this.add(same);
		this.add(new JLabel());
		this.hypo.setHorizontalAlignment(SwingConstants.RIGHT);
		
	}

	// ---------------------------------------------------------------
	/**
     * Assigns listeners to the view widgets and the model.
     */
    private void registerListeners() {
	// Add widget listeners.
	this.baseUp.addActionListener(new BaseButtonListener(1));
	this.baseDown.addActionListener(new BaseButtonListener(-1));
	this.setBandH.addActionListener(new setBandHListener());
	// Add model listeners.
	this.model.addPropertyChangeListener(RTModel.Type.BASE,
		new BaseListener());

    // ---------------------------------------------------------------
}}