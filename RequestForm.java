package m1t1;

import javax.swing.*;



import java.awt.*;

public class RequestForm extends JFrame{
	private JLabel FirstNameLabel,LastNameLabel,EmailLabel, PhoneNumLabel, CarDetailLabel, DealerLocationLabel, MessageLabel, InterestLabel;
	private JTextField FirstNameField,LastNameField,EmailField, PhoneNumField,MessageField;
	private JButton SubmitButton;
	
	public RequestForm() {
		Container con = getContentPane();
		JPanel pnl = new JPanel(); 
		JPanel messagepnl = new JPanel();
		pnl.setLayout(new GridLayout(3,3,50,50));
		
		con.add(pnl,BorderLayout.NORTH);
		con.add(messagepnl);//,BorderLayout.SOUTH);
		FirstNameLabel = new JLabel("First Name");
		LastNameLabel = new JLabel("Last Name");
		EmailLabel = new JLabel("Email");
		PhoneNumLabel = new JLabel("Phone Number");
		CarDetailLabel = new JLabel("Car Details + vechile.detail");
		DealerLocationLabel = new JLabel("Dealer Location + vechile.dealerlocation");
		MessageLabel = new JLabel("Message");
		InterestLabel = new JLabel("There are + vechile.numberofInterested + people interested in this car");
		MessageLabel = new JLabel("Message");
		
		FirstNameField = new JTextField(10);
		LastNameField = new JTextField(10);
		EmailField=   new JTextField(10);
		PhoneNumField =  new JTextField(10);
		MessageField =  new JTextField(100);
		MessageField.setBounds(20, 20, 30, 50);

		
		SubmitButton = new JButton("Submit");
		//SubmitButton.addActionListener(new MyActionListener());
		
		
		
		pnl.add(FirstNameLabel, BorderLayout.CENTER);
		pnl.add(FirstNameField, BorderLayout.CENTER);
		pnl.add(LastNameLabel, BorderLayout.CENTER);
		pnl.add(LastNameField, BorderLayout.CENTER);
		pnl.add(EmailLabel, BorderLayout.CENTER);
		pnl.add(EmailField, BorderLayout.CENTER);
		pnl.add(PhoneNumLabel, BorderLayout.CENTER);
		pnl.add(PhoneNumField, BorderLayout.CENTER);
		pnl.add(CarDetailLabel,BorderLayout.CENTER);
		pnl.add(DealerLocationLabel,BorderLayout.CENTER);
		pnl.add(InterestLabel);
		messagepnl.add(MessageLabel,BorderLayout.CENTER);
		messagepnl.add(MessageField,BorderLayout.CENTER);
		messagepnl.add(SubmitButton,BorderLayout.CENTER);
		
	}
	public static void main(String[] args) {
        JFrame f = new RequestForm();
        f.setTitle("Customer Request Form");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 600);
        f.setVisible(true);
    }
}
