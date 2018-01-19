import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Show implements ActionListener{
	
	JFrame frame;
	Container contentPane;
	
	Runner runner;
	
	JLabel opeLabel;
	JButton start;
	JTextArea moveText,opeText;

	Show(Runner runner){
		this.runner = runner;
		int x=15*5*(2*runner.size+1),y=20*5*(runner.size+1)+20;

		frame = new JFrame();
		
		frame.setTitle("Show");
		frame.setSize(x, y+30);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		//frame.setBounds(890,0,x,y+30);
		frame.setResizable(false);
		
		moveText = new JTextArea();
		moveText.setFont(new Font("Arial", Font.PLAIN, 15));
		moveText.setPreferredSize(new Dimension(x,y));
		moveText.setBounds(0,32,x,y);
		
		start = new JButton("Start");
		start.setPreferredSize(new Dimension(100,32));
		start.setBounds(0,0,100,32);
		start.addActionListener(this);
		
		contentPane = frame.getContentPane();
		contentPane.add(start);
		contentPane.add(moveText);
		
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event){
		JButton button = (JButton)event.getSource();
		
		if(button == start){
			System.out.println("test");
			runner.start();
		}
	}

}