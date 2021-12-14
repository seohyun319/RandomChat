
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;



public class SelectName{

	private JFrame frame;

	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectName window = new SelectName();
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
	public SelectName() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("서울남산 장체EB", Font.PLAIN, 12));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JButton okButton = new JButton("친목");
		okButton.setFont(new Font("서울남산 장체EB", Font.PLAIN, 12));
		okButton.setBackground(Color.PINK);
		okButton.setForeground(Color.WHITE);
		okButton.setBounds(174, 51, 91, 23);
		frame.getContentPane().add(okButton);
		okButton.addActionListener(new ActionListener() {
			 
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	new ChatClient();
	            }
	        });

	
	
		
		JButton btnNewButton = new JButton("공부");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("서울남산 장체EB", Font.PLAIN, 12));
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setBounds(174, 103, 91, 23);
		frame.getContentPane().add(btnNewButton);
		  btnNewButton.addActionListener(new ActionListener() {
				 
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               new ChatClient2();
	            }
	        }); 
	}
	

	
	
}
