import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PlannerGUI implements ActionListener {
	JFrame frame;
	Container contentPane;
	
	JButton load,save,run;
	JLabel fileNameLabel1,fileNameLabel2,imgLabel;
	JPanel btnPanel;
	JTextArea fileNameTextArea1,fileNameTextArea2,initialTextArea,goalTextArea,moveTextArea;;
	JScrollPane initialScrollPane,goalScrollPane,imgScrollPane;
	
	PlannerGUI() {
		frame = new JFrame();

		frame.setTitle("Planner");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(936, 643);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		//frame.setResizable(false);

		contentPane = frame.getContentPane();
		
		fileNameLabel1 = new JLabel("Initial State Filename:");
		fileNameLabel1.setFont(new Font("Arial", Font.PLAIN, 25));
		fileNameLabel1.setPreferredSize(new Dimension(500,32));
		fileNameLabel1.setBounds(0,0,500,32);
		fileNameTextArea1 = new JTextArea("initialState.data");
		fileNameTextArea1.setFont(new Font("Arial", Font.PLAIN, 25));
		fileNameTextArea1.setPreferredSize(new Dimension(260,32));
		fileNameTextArea1.setBounds(0,32,260,32);
		initialTextArea = new JTextArea();
		initialScrollPane = new JScrollPane(initialTextArea);
		initialScrollPane.setPreferredSize(new Dimension(260,200));
		initialScrollPane.setBounds(0,64,260,200);
		
		fileNameLabel2 = new JLabel("Goal Filename:");
		fileNameLabel2.setFont(new Font("Arial", Font.PLAIN, 25));
		fileNameLabel2.setPreferredSize(new Dimension(500,32));
		fileNameLabel2.setBounds(280,0,500,32);
		fileNameTextArea2 = new JTextArea("goalState.data");
		fileNameTextArea2.setFont(new Font("Arial", Font.PLAIN, 25));
		fileNameTextArea2.setPreferredSize(new Dimension(260,32));
		fileNameTextArea2.setBounds(280,32,260,32);
		goalTextArea = new JTextArea();
		goalScrollPane = new JScrollPane(goalTextArea);
		goalScrollPane.setPreferredSize(new Dimension(260,200));
		goalScrollPane.setBounds(280,64,260,200);
		
		load = new JButton("Load");
		load.addActionListener(this);
		save = new JButton("Save");
		save.addActionListener(this);
		run = new JButton("Run");
		run.addActionListener(this);
		btnPanel = new JPanel();
		btnPanel = new JPanel(new GridLayout(1, 3));
		btnPanel.setPreferredSize(new Dimension(540,32));
		btnPanel.setBounds(0,264,540,32);
		btnPanel.add(load);
		btnPanel.add(save);
		btnPanel.add(run);
		
		imgLabel = new JLabel();
		imgScrollPane = new JScrollPane(imgLabel);
		imgScrollPane.setPreferredSize(new Dimension(396,620));
		imgScrollPane.setBounds(540,0,396,620);
		
		moveTextArea = new JTextArea("");
		moveTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
		moveTextArea.setPreferredSize(new Dimension(540,322));
		moveTextArea.setBounds(0,296,540,322);
		
		contentPane.add(fileNameLabel1);
		contentPane.add(fileNameTextArea1);
		contentPane.add(initialScrollPane);
		contentPane.add(fileNameLabel2);
		contentPane.add(fileNameTextArea2);
		contentPane.add(goalScrollPane);
		contentPane.add(btnPanel);
		contentPane.add(imgScrollPane);
		contentPane.add(moveTextArea);
		
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event){
		JButton button = (JButton)event.getSource();
		
		if(button == load){
			initialTextArea.setText(writeBuffer(FileLoading.fileLoading(fileNameTextArea1.getText())));
			goalTextArea.setText(writeBuffer(FileLoading.fileLoading(fileNameTextArea2.getText())));
		}
		else if(button == save){
			try {
				FileWriter initialFw = new FileWriter(fileNameTextArea1.getText());
				FileWriter goalFw = new FileWriter(fileNameTextArea2.getText());

				initialFw.write(initialTextArea.getText());
				initialFw.close();
				goalFw.write(goalTextArea.getText());
				goalFw.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		else if(button == run){
			runPlanner();
			move();
		}
	}
	
	/**
	 * ファイルから読み込んだテキストを1行ずつ格納したString型のリストをString型に変換するメソッド
	 * 
	 * @param data
	 * @return リストdataをString型に変換したもの
	 */
	public String writeBuffer(ArrayList<String> data) {
		String buffer = "";

		for (int i = 0; i < data.size(); i++) {
			buffer += data.get(i);
			buffer += "\n";
		}

		return buffer;
	}
	
	public void runPlanner(){
		Planner p = new Planner(this);
		p.startWithGUI();
		ImageIcon icon = new ImageIcon("tmp/simple.png");
		imgLabel.setIcon(null);
		//SwingUtilities.updateComponentTreeUI(frame);
		imgLabel.setIcon(icon);
		
	}
	
	public void move(){
		String blank = "　　　　　";
		String A = "＿　A 　＿";
		String B = "＿　B 　＿";
		String C = "＿　C 　＿";
		String stick = "　　＿　　";
		String hand = "／／／／／";
		String box1 = "＿＿＿＿＿";
		String box2 = "＿　　　＿";
		ArrayList<String> move = new ArrayList<String>();
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　＿＿＿＿＿　　　　　＿＿＿＿＿　　　　　＿＿＿＿＿　　　　　");
		move.add("　　　　　＿　　　＿　　　　　＿　　　＿　　　　　＿　　　＿　　　　　");
		move.add("　　　　　＿　A 　＿　　　　　＿　B 　＿　　　　　＿　C 　＿　　　　　");
		move.add("　　　　　＿　　　＿　　　　　＿　　　＿　　　　　＿　　　＿　　　　　");
		move.add("　　　　　＿＿＿＿＿　　　　　＿＿＿＿＿　　　　　＿＿＿＿＿　　　　　");
		move.add("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
		
		moveTextArea.setText(writeBuffer(move));
		
		for(int i=0; i<5; i++){
			
			try{
				TimeUnit.SECONDS.sleep(1);
				moveTextArea.setText("1"+i);
				System.out.println("1"+i);
			}
			catch(InterruptedException e){
			}
			
		}
	}
	
	public static void main(String[] arg){
		new PlannerGUI();
	}
}
