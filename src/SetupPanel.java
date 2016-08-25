import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SetupPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JTextField playerNameTextField;
	private final JButton addDrafterButton;
	private final JButton addMeButton;
	private final JButton finalizeSetupButton;
	
	public SetupPanel() {

		this.playerNameTextField = new JTextField("Drafter Name");
		
		this.addDrafterButton = new JButton("Add Drafter");
		this.addMeButton = new JButton("Add Me");
		this.finalizeSetupButton = new JButton("Finalize Setup");

		add(this.playerNameTextField);
		add(this.addDrafterButton);
		add(this.addMeButton);
		add(this.finalizeSetupButton);

		// register the current panel as listener for the buttons
		this.addDrafterButton.addActionListener(this);
		this.addMeButton.addActionListener(this);
		this.finalizeSetupButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
		
		if(source == this.addDrafterButton) {
			
		} else if(source == this.addMeButton) {
			
		} else if(source == this.finalizeSetupButton) {
			
		}
	}

}
