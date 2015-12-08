package inputs;
import java.awt.event.ActionEvent;

public class AddFieldController implements ActionListener{

	private Input input;
	private JButton addVariable;
	
	public AddFieldController(Input input, JButton button)
	{
		this.input = input;
		this.addVariable = button;
	}
	
	public actionPerformed(ActionEvent e)
	{
		
	}
}
