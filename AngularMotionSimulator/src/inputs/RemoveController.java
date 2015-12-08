package inputs;
import java.awt.event.ActionEvent;

public class RemoveController implements ActionListener{

	private Input input;
	private JButton delete;
	
	public RemoveController(Input input, JButton delete)
	{
		this.input = input;
		this.delete = delete;
	}
	
	public actionPerformed(ActionEvent e)
	{
		
	}
}
