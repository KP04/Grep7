import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.HashMap;
import javax.swing.*;

public class PlannerGUI implements ActionListener {
	JFrame frame;
	Container contentPane;

	JButton load,save,run;
	JLabel fileNameLabel1,fileNameLabel2,imgLabel,opeLabel;
	JPanel btnPanel;
	JTextArea fileNameTextArea1,fileNameTextArea2,initialTextArea,goalTextArea,moveTextArea,opeTextArea;
	JScrollPane initialScrollPane,goalScrollPane,imgScrollPane;

	Runner runner;
	int counter = 0;

	PlannerGUI() {
		frame = new JFrame();

		frame.setTitle("Planner");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(936, 765);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);

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
		imgScrollPane.setPreferredSize(new Dimension(396,742));
		imgScrollPane.setBounds(540,0,396,742);

		opeLabel = new JLabel("Operation:");
		opeLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		opeLabel.setPreferredSize(new Dimension(150,32));
		opeLabel.setBounds(0,296,150,32);
		opeTextArea = new JTextArea("");
		opeTextArea.setFont(new Font("Arial", Font.PLAIN, 25));
		opeTextArea.setPreferredSize(new Dimension(390,32));
		opeTextArea.setBounds(150,296,390,32);

		moveTextArea = new JTextArea("");
		moveTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
		moveTextArea.setPreferredSize(new Dimension(540,412));
		moveTextArea.setBounds(0,338,540,412);

		contentPane.add(fileNameLabel1);
		contentPane.add(fileNameTextArea1);
		contentPane.add(initialScrollPane);
		contentPane.add(fileNameLabel2);
		contentPane.add(fileNameTextArea2);
		contentPane.add(goalScrollPane);
		contentPane.add(btnPanel);
		contentPane.add(imgScrollPane);
		contentPane.add(opeLabel);
		contentPane.add(opeTextArea);
		contentPane.add(moveTextArea);

		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event){
		JButton button = (JButton)event.getSource();

		if(button == load){
			/*
			 * FileLoading.fileLoading(fileNameTextArea.getText()でファイルのテキスト(リスト)を受け取り、
			 * 受け取ったテキスト(リスト)をwriteBuffer()でStringに変換し、
			 * 変換したStringをsetText()する。
			 */
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
			runner = new Runner(this); //GUIのポインタをrunnerに渡す
			runner.start(); //実行
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

	public static void main(String[] arg){
		new PlannerGUI();
	}
}

class Runner extends Thread{
	PlannerGUI pgui;
	Planner p;

	Runner(PlannerGUI pgui){
		this.pgui = pgui;
		this.p = new Planner(this.pgui);
	}


	public void run(){

		runPlanner();

		try{
			move(p.process);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	public void runPlanner(){
		p.startWithGUI();

		SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					ImageIcon icon = new ImageIcon("tmp/simple"+pgui.counter+".png");
					pgui.imgLabel.setIcon(icon);
					SwingUtilities.updateComponentTreeUI(pgui.frame);
				}
		});
	}


	public void move(ArrayList<String> process){
		HashMap<String,Integer> state = new HashMap<String,Integer>();
		state.put("A", 1);
		state.put("B", 2);
		state.put("C", 3);
		state.put("1", 1);
		state.put("2", 1);
		state.put("3", 1);

		String blank = "　　　　　";
		String stick = "　　／　　";
		String hand = "／／／／／";

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
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		move.add("　　　　　＿＿＿＿＿　　　　　＿＿＿＿＿　　　　　＿＿＿＿＿　　　　　");
		move.add("　　　　　＿　　　＿　　　　　＿　　　＿　　　　　＿　　　＿　　　　　");
		move.add("　　　　　＿　Ａ　＿　　　　　＿　Ｂ　＿　　　　　＿　Ｃ　＿　　　　　");
		move.add("　　　　　＿　　　＿　　　　　＿　　　＿　　　　　＿　　　＿　　　　　");
		move.add("　　　　　＿＿＿＿＿　　　　　＿＿＿＿＿　　　　　＿＿＿＿＿　　　　　");
		move.add("／／／／／／／／／／／／／／／／／／／／／／／／／／／／／／／／／／／／");

		pgui.moveTextArea.setText(pgui.writeBuffer(move));

		for(int i=0; i<process.size(); i++){
			pgui.opeTextArea.setText((i+1)+":"+process.get(i));
			String ope = process.get(i);
			_Unifier unifier = new _Unifier();

			if(ope.contains("Place")){
				unifier.unify(ope, "Place ?x on ?y");
				int xRen = 5+10*(state.get(unifier.vars.get("?x"))-1);
				int yRen = 5+10*(state.get(unifier.vars.get("?y"))-1);
				int Col = 5*(4-state.get(Integer.toString(state.get(unifier.vars.get("?y")))));
				int len = Math.abs(yRen-xRen);
				for(int j=0; j<len; j++){
					if(yRen>xRen){
						for(int k=0; k<8; k++){
							move.set(k, "　"+move.get(k).substring(0,move.get(k).length()-1));
						}
					}
					else{
						for(int k=0; k<8; k++){
							move.set(k, move.get(k).substring(1)+"　");
						}
					}
					sleep();
					pgui.moveTextArea.setText(pgui.writeBuffer(move));
				}

				for(int j=7; j<Col-1; j++){
					for(int k=j; k>=0; k--){
						move.set(k+1,move.get(k+1).substring(0,yRen)+move.get(k+1).substring(yRen+5));
						StringBuilder sb = new StringBuilder(move.get(k+1));
						move.set(k+1,sb.insert(yRen,move.get(k).substring(yRen,yRen+5)).toString());
						if(k==0){
							sb = new StringBuilder(move.get(k).substring(0,yRen)+move.get(k).substring(yRen+5));
							move.set(k,sb.insert(yRen,stick).toString());
						}
					}
					sleep();
					pgui.moveTextArea.setText(pgui.writeBuffer(move));
				}

				for(int j=Col-5; j>0; j--){
					for(int k=0; k<j; k++){
						move.set(k,move.get(k).substring(0,yRen)+move.get(k).substring(yRen+5));
						StringBuilder sb = new StringBuilder(move.get(k));
						move.set(k, sb.insert(yRen,move.get(k+1).substring(yRen,yRen+5)).toString());
						if(k==j-1){
							move.set(k,move.get(k).substring(0,yRen)+move.get(k).substring(yRen+5));
							sb = new StringBuilder(move.get(k));
							move.set(k,sb.insert(yRen,blank).toString());
						}
					}
					sleep();
					pgui.moveTextArea.setText(pgui.writeBuffer(move));
				}

				state.put(unifier.vars.get("?x"), state.get(unifier.vars.get("?y")));
				state.put(Integer.toString(state.get(unifier.vars.get("?x"))),-1*(Col/5-4)+1);
			}
			else if(ope.contains("remove")){
				unifier.unify(ope, "remove ?x from on top ?y");
				int Ren = 5+10*(state.get(unifier.vars.get("?x"))-1);
				int Col = 5*(4-state.get(Integer.toString(state.get(unifier.vars.get("?x")))));
				for(int j=0; j<Col; j++){
					for(int k=j; k>=0; k--){
						move.set(k,move.get(k).substring(0,Ren)+move.get(k).substring(Ren+5));
						StringBuilder sb = new StringBuilder(move.get(k));
						if(k!=j){
							move.set(k,sb.insert(Ren,stick).toString());
						}
						else{
							move.set(k,sb.insert(Ren,hand).toString());
						}
					}
					sleep();
					pgui.moveTextArea.setText(pgui.writeBuffer(move));
				}

				for(int j=Col-3; j>0; j--){
					for(int k=1; k<j+8; k++){
						move.set(k-1,move.get(k-1).substring(0,Ren)+move.get(k-1).substring(Ren+5));
						StringBuilder sb = new StringBuilder(move.get(k-1));
						move.set(k-1, sb.insert(Ren,move.get(k).substring(Ren,Ren+5)).toString());
						if(k==j+7){
							move.set(k,move.get(k).substring(0,Ren)+move.get(k).substring(Ren+5));
							sb = new StringBuilder(move.get(k));
							move.set(k,sb.insert(Ren,blank).toString());
						}
					}
					sleep();
					pgui.moveTextArea.setText(pgui.writeBuffer(move));
				}
				state.put(Integer.toString(state.get(unifier.vars.get("?y"))),-1*(Col/5-4)-1);
			}
			else if(ope.contains("pick")){
				unifier.unify(ope, "pick up ?x from the table");
				int Ren = 5+10*(state.get(unifier.vars.get("?x"))-1);
				int Col = 5*(4-state.get(Integer.toString(state.get(unifier.vars.get("?x")))));
				for(int j=0; j<Col; j++){
					for(int k=j; k>=0; k--){
						move.set(k,move.get(k).substring(0,Ren)+move.get(k).substring(Ren+5));
						StringBuilder sb = new StringBuilder(move.get(k));
						if(k!=j){
							move.set(k,sb.insert(Ren,stick).toString());
						}
						else{
							move.set(k,sb.insert(Ren,hand).toString());
						}
					}
					sleep();
					pgui.moveTextArea.setText(pgui.writeBuffer(move));
				}

				for(int j=Col-3; j>0; j--){
					for(int k=1; k<j+8; k++){
						move.set(k-1,move.get(k-1).substring(0,Ren)+move.get(k-1).substring(Ren+5));
						StringBuilder sb = new StringBuilder(move.get(k-1));
						move.set(k-1, sb.insert(Ren,move.get(k).substring(Ren,Ren+5)).toString());
						if(k==j+7){
							move.set(k,move.get(k).substring(0,Ren)+move.get(k).substring(Ren+5));
							sb = new StringBuilder(move.get(k));
							move.set(k,sb.insert(Ren,blank).toString());
						}
					}
					sleep();
					pgui.moveTextArea.setText(pgui.writeBuffer(move));
				}
				state.put(Integer.toString(state.get(unifier.vars.get("?x"))),-1*(Col/5-4)-1);
			}
			else if(ope.contains("put")){
				unifier.unify(ope, "put ?x down on the table");
				int xRen = 5+10*(state.get(unifier.vars.get("?x"))-1);
				int yRen;
				int Col = 20;
				switch(unifier.vars.get("?x")){
					case "A": yRen = 5; break;
					case "B": yRen = 15; break;
					default : yRen = 25;
				}

				int len = Math.abs(yRen-xRen);

				for(int j=0; j<len; j++){
					if(yRen>xRen){
						for(int k=0; k<8; k++){
							move.set(k, "　"+move.get(k).substring(0,move.get(k).length()-1));
						}
					}
					else{
						for(int k=0; k<8; k++){
							move.set(k, move.get(k).substring(1)+"　");
						}
					}
					sleep();
					pgui.moveTextArea.setText(pgui.writeBuffer(move));
				}

				for(int j=7; j<Col-1; j++){
					for(int k=j; k>=0; k--){
						move.set(k+1,move.get(k+1).substring(0,yRen)+move.get(k+1).substring(yRen+5));
						StringBuilder sb = new StringBuilder(move.get(k+1));
						move.set(k+1,sb.insert(yRen,move.get(k).substring(yRen,yRen+5)).toString());
						if(k==0){
							sb = new StringBuilder(move.get(k).substring(0,yRen)+move.get(k).substring(yRen+5));
							move.set(k,sb.insert(yRen,stick).toString());
						}
					}
					sleep();
					pgui.moveTextArea.setText(pgui.writeBuffer(move));
				}

				for(int j=Col-5; j>0; j--){
					for(int k=0; k<j; k++){
						move.set(k,move.get(k).substring(0,yRen)+move.get(k).substring(yRen+5));
						StringBuilder sb = new StringBuilder(move.get(k));
						move.set(k, sb.insert(yRen,move.get(k+1).substring(yRen,yRen+5)).toString());
						if(k==j-1){
							move.set(k,move.get(k).substring(0,yRen)+move.get(k).substring(yRen+5));
							sb = new StringBuilder(move.get(k));
							move.set(k,sb.insert(yRen,blank).toString());
						}
					}
					sleep();
					pgui.moveTextArea.setText(pgui.writeBuffer(move));
				}

				state.put(unifier.vars.get("?x"), (yRen-5)/10);
				state.put(Integer.toString(state.get(unifier.vars.get("?x"))),-1*(Col/5-4)+1);
			}
		}
	}

	public void sleep(){
		try{
			Thread.sleep(50);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}

class _Unifier {
	StringTokenizer st1;
	String buffer1[];
	StringTokenizer st2;
	String buffer2[];
	HashMap<String, String> vars;

	_Unifier() {
		vars = new HashMap<String, String>();
	}

	public boolean unify(String string1, String string2,
			HashMap<String, String> bindings) {
		this.vars = bindings;
		return unify(string1, string2);
	}

	public boolean unify(String string1, String string2) {
		// System.out.println(string1);
		// System.out.println(string2);

		// 同じなら成功
		if (string1.equals(string2))
			return true;

		// 各々トークンに分ける
		st1 = new StringTokenizer(string1);
		st2 = new StringTokenizer(string2);

		// 数が異なったら失敗
		if (st1.countTokens() != st2.countTokens())
			return false;

		// 定数同士
		int length = st1.countTokens();
		buffer1 = new String[length];
		buffer2 = new String[length];
		for (int i = 0; i < length; i++) {
			buffer1[i] = st1.nextToken();
			buffer2[i] = st2.nextToken();
		}
		for (int i = 0; i < length; i++) {
			if (!tokenMatching(buffer1[i], buffer2[i])) {
				return false;
			}
		}

		// 最後まで O.K. なら成功
		//System.out.println(vars.toString());
		return true;
	}

	boolean tokenMatching(String token1, String token2) {
		if (token1.equals(token2))
			return true;
		if (var(token1) && !var(token2))
			return varMatching(token1, token2);
		if (!var(token1) && var(token2))
			return varMatching(token2, token1);
		if (var(token1) && var(token2))
			return varMatching(token1, token2);
		return false;
	}

	boolean varMatching(String vartoken, String token) {
		if (vars.containsKey(vartoken)) {
			if (token.equals(vars.get(vartoken))) {
				return true;
			} else {
				return false;
			}
		} else {
			replaceBuffer(vartoken, token);
			if (vars.containsValue(vartoken)) {
				replaceBindings(vartoken, token);
			}
			vars.put(vartoken, token);
		}
		return true;
	}

	void replaceBuffer(String preString, String postString) {
		for (int i = 0; i < buffer1.length; i++) {
			if (preString.equals(buffer1[i])) {
				buffer1[i] = postString;
			}
			if (preString.equals(buffer2[i])) {
				buffer2[i] = postString;
			}
		}
	}

	void replaceBindings(String preString, String postString) {
		Iterator<String> keys;
		for (keys = vars.keySet().iterator(); keys.hasNext();) {
			String key = (String) keys.next();
			if (preString.equals(vars.get(key))) {
				vars.put(key, postString);
			}
		}
	}

	boolean var(String str1) {
		// 先頭が ? なら変数
		return str1.startsWith("?");
	}

}

