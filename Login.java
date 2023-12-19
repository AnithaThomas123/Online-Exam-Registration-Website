package swpkg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField txteid;
	private JTextField txtename;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EmpId");
		lblNewLabel.setBounds(38, 57, 75, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txteid = new JTextField();
		txteid.setBounds(150, 54, 86, 20);
		frame.getContentPane().add(txteid);
		txteid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("EmpPass");
		lblNewLabel_1.setBounds(38, 108, 75, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtename = new JTextField();
		txtename.setBounds(150, 105, 86, 20);
		frame.getContentPane().add(txtename);
		txtename.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Jesus123");  
					PreparedStatement pst=con.prepareStatement("select * from emp where empid=? and emppass=?");
					pst.setString(1,txteid.getText()); 
					pst.setString(2,txtename.getText());
					ResultSet rs=pst.executeQuery();
					if (rs.next())
					{
						
						JOptionPane.showMessageDialog(null,"Correct");
						System.out.println("Correct");
						//show tabledemo form
						//TableDemo td = new TableDemo();
						//td.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Wrong");
						System.out.println("InCorrect");rs.close();pst.close();
					}
				}
				
				catch(Exception w)
				{ 
					System.out.println(w);}
				
			}
		});
		btnNewButton.setBounds(93, 164, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}

}