import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorAndForm {
	Planner Planner = new Planner();

	public static void main(String args[]) {
		(new ColorAndForm()).start();
	}

	public void start() {
		initOperators();
		Vector goalList = initGoalList();
		Vector initialState = initInitialState();
		Hashtable theBinding = new Hashtable();
		Planner.plan = new Vector();
		Planner.planning(goalList, initialState, theBinding, null);
		System.out.println("***** This is a plan! *****");
		for (int i = 0; i < Planner.plan.size(); i++) {
			Operator op = (Operator) Planner.plan.elementAt(i);
			System.out.println((op.instantiate(theBinding)).name);
		}
	}

	private void initOperators() {
		Planner.operators = new Vector();
		Vector operators = Planner.operators;

		// 色の判定
		String name5 = new String("Place red on ?y");
		// IF
		Vector ifList5 = new Vector();
		ifList5.addElement(new String("?x is color of red"));
		ifList5.addElement(new String("?x on ?y"));
		// ADD-LIST
		Vector addList5 = new Vector();
		addList5.addElement(new String("red on ?y"));
		// DELETE-LIST
		Vector deleteList5 = new Vector();
		Operator operator5 = new Operator(name5, ifList5, addList5,
				deleteList5, false);
		operators.addElement(operator5);

		// 色の判定
		String name6 = new String("Place white on ?y");
		// IF
		Vector ifList6 = new Vector();
		ifList6.addElement(new String("?x is color of white"));
		ifList6.addElement(new String("?x on ?y"));
		// ADD-LIST
		Vector addList6 = new Vector();
		addList6.addElement(new String("white on ?y"));
		// DELETE-LIST
		Vector deleteList6 = new Vector();
		Operator operator6 = new Operator(name6, ifList6, addList6,
				deleteList6, false);
		operators.addElement(operator6);

		// 色の判定
		String name7 = new String("Place green on ?y");
		// IF
		Vector ifList7 = new Vector();
		ifList7.addElement(new String("?x is color of green"));
		ifList7.addElement(new String("?x on ?y"));
		// ADD-LIST
		Vector addList7 = new Vector();
		addList7.addElement(new String("green on ?y"));
		// DELETE-LIST
		Vector deleteList7 = new Vector();
		Operator operator7 = new Operator(name7, ifList7, addList7,
				deleteList7, false);
		operators.addElement(operator7);

		// 色の判定
		String name8 = new String("Place ?x on red");
		// IF
		Vector ifList8 = new Vector();
		ifList8.addElement(new String("?y is color of red"));
		ifList8.addElement(new String("?x on ?y"));
		// ADD-LIST
		Vector addList8 = new Vector();
		addList8.addElement(new String("?x on red"));
		// DELETE-LIST
		Vector deleteList8 = new Vector();
		Operator operator8 = new Operator(name8, ifList8, addList8,
				deleteList8, false);
		operators.addElement(operator8);

		// 色の判定
		String name9 = new String("Place ?x on white");
		// IF
		Vector ifList9 = new Vector();
		ifList9.addElement(new String("?y is color of white"));
		ifList9.addElement(new String("?x on ?y"));
		// ADD-LIST
		Vector addList9 = new Vector();
		addList9.addElement(new String("?x on white"));
		// DELETE-LIST
		Vector deleteList9 = new Vector();
		Operator operator9 = new Operator(name9, ifList9, addList9,
				deleteList9, false);
		operators.addElement(operator9);

		// 色の判定
		String name10 = new String("Place ?x on green");
		// IF
		Vector ifList10 = new Vector();
		ifList10.addElement(new String("?y is color of green"));
		ifList10.addElement(new String("?x on ?y"));
		// ADD-LIST
		Vector addList10 = new Vector();
		addList10.addElement(new String("?x on green"));
		// DELETE-LIST
		Vector deleteList10 = new Vector();
		Operator operator10 = new Operator(name10, ifList10, addList10,
				deleteList10, false);
		operators.addElement(operator10);

		// 形の判定
		String name11 = new String("Place square on ?y");
		// IF
		Vector ifList11 = new Vector();
		ifList11.addElement(new String("?x is square"));
		ifList11.addElement(new String("?x on ?y"));
		// ADD-LIST
		Vector addList11 = new Vector();
		addList11.addElement(new String("square on ?y"));
		// DELETE-LIST
		Vector deleteList11 = new Vector();
		Operator operator11 = new Operator(name11, ifList11, addList11,
				deleteList11, false);
		operators.addElement(operator11);

		// 形の判定
		String name12 = new String("Place triangle on ?y");
		// IF
		Vector ifList12 = new Vector();
		ifList12.addElement(new String("?x is triangle"));
		ifList12.addElement(new String("?x on ?y"));
		// ADD-LIST
		Vector addList12 = new Vector();
		addList12.addElement(new String("triangle on ?y"));
		// DELETE-LIST
		Vector deleteList12 = new Vector();
		Operator operator12 = new Operator(name12, ifList12, addList12,
				deleteList12, false);
		operators.addElement(operator12);

		// 形の判定
		String name13 = new String("Place trapezoid on ?y");
		// IF
		Vector ifList13 = new Vector();
		ifList13.addElement(new String("?x is trapezoid"));
		ifList13.addElement(new String("?x on ?y"));
		// ADD-LIST
		Vector addList13 = new Vector();
		addList13.addElement(new String("trapezoid on ?y"));
		// DELETE-LIST
		Vector deleteList13 = new Vector();
		Operator operator13 = new Operator(name13, ifList13, addList13,
				deleteList13, false);
		operators.addElement(operator13);

		// 形の判定
		String name14 = new String("Place ?x on square");
		// IF
		Vector ifList14 = new Vector();
		ifList14.addElement(new String("?y is square"));
		ifList14.addElement(new String("?x on ?y"));
		// ADD-LIST
		Vector addList14 = new Vector();
		addList14.addElement(new String("?x on square"));
		// DELETE-LIST
		Vector deleteList14 = new Vector();
		Operator operator14 = new Operator(name14, ifList14, addList14,
				deleteList14, false);
		operators.addElement(operator14);

		// 形の判定
		String name15 = new String("Place ?x on trapezoid");
		// IF
		Vector ifList15 = new Vector();
		ifList15.addElement(new String("?y is trapezoid"));
		ifList15.addElement(new String("?x on ?y"));
		// ADD-LIST
		Vector addList15 = new Vector();
		addList15.addElement(new String("?x on trapezoid"));
		// DELETE-LIST
		Vector deleteList15 = new Vector();
		Operator operator15 = new Operator(name15, ifList15, addList15,
				deleteList15, false);
		operators.addElement(operator15);

		// OPERATOR 1
		// / NAME
		String name1 = new String("Place ?x on ?y");
		// / IF
		Vector ifList1 = new Vector();
		ifList1.addElement(new String("clear ?y"));
		ifList1.addElement(new String("holding ?x"));
		ifList1.addElement(new String("?y is not triangle"));
		// / ADD-LIST
		Vector addList1 = new Vector();
		addList1.addElement(new String("?x on ?y"));
		addList1.addElement(new String("clear ?x"));
		addList1.addElement(new String("handEmpty"));
		// / DELETE-LIST
		Vector deleteList1 = new Vector();
		deleteList1.addElement(new String("clear ?y"));
		deleteList1.addElement(new String("holding ?x"));
		Operator operator1 = new Operator(name1, ifList1, addList1,
				deleteList1, false);
		operators.addElement(operator1);

		// OPERATOR 2
		// / NAME
		String name2 = new String("remove ?x from on top ?y");
		// / IF
		Vector ifList2 = new Vector();
		ifList2.addElement(new String("?x on ?y"));
		ifList2.addElement(new String("clear ?x"));
		ifList2.addElement(new String("handEmpty"));
		// / ADD-LIST
		Vector addList2 = new Vector();
		addList2.addElement(new String("clear ?y"));
		addList2.addElement(new String("holding ?x"));
		// / DELETE-LIST
		Vector deleteList2 = new Vector();
		deleteList2.addElement(new String("?x on ?y"));
		deleteList2.addElement(new String("clear ?x"));
		deleteList2.addElement(new String("handEmpty"));
		Operator operator2 = new Operator(name2, ifList2, addList2,
				deleteList2, false);
		operators.addElement(operator2);

		// OPERATOR 3
		// / NAME
		String name3 = new String("pick up ?x from the table");
		// / IF
		Vector ifList3 = new Vector();
		ifList3.addElement(new String("ontable ?x"));
		ifList3.addElement(new String("clear ?x"));
		ifList3.addElement(new String("handEmpty"));
		// / ADD-LIST
		Vector addList3 = new Vector();
		addList3.addElement(new String("holding ?x"));
		// / DELETE-LIST
		Vector deleteList3 = new Vector();
		deleteList3.addElement(new String("ontable ?x"));
		deleteList3.addElement(new String("clear ?x"));
		deleteList3.addElement(new String("handEmpty"));
		Operator operator3 = new Operator(name3, ifList3, addList3,
				deleteList3, false);
		operators.addElement(operator3);

		// OPERATOR 4
		// / NAME
		String name4 = new String("put ?x down on the table");
		// / IF
		Vector ifList4 = new Vector();
		ifList4.addElement(new String("holding ?x"));
		// / ADD-LIST
		Vector addList4 = new Vector();
		addList4.addElement(new String("ontable ?x"));
		addList4.addElement(new String("clear ?x"));
		addList4.addElement(new String("handEmpty"));
		// / DELETE-LIST
		Vector deleteList4 = new Vector();
		deleteList4.addElement(new String("holding ?x"));
		Operator operator4 = new Operator(name4, ifList4, addList4,
				deleteList4, false);
		operators.addElement(operator4);

		// / NAME
		String name17 = new String("Not Success");
		// / IF
		Vector ifList17 = new Vector();
		ifList17.addElement(new String("?x is triangle"));
		// / ADD-LIST
		Vector addList17 = new Vector();
		addList17.addElement(new String("?x is not triangle"));
		// / DELETE-LIST
		Vector deleteList17 = new Vector();
		deleteList17.addElement(new String("?x is not triangle"));
		Operator operator17 = new Operator(name17, ifList17, addList17,
				deleteList17, false);
		operators.addElement(operator17);
	}

	private Vector initGoalList() {
		Vector goalList = new Vector();
		goalList.addElement("red on square");
		goalList.addElement("white on A");
		return goalList;
	}

	private Vector initInitialState() {
		Vector initialState = new Vector();
		initialState.addElement("clear A");
		initialState.addElement("clear B");
		initialState.addElement("clear C");

		initialState.addElement("A is triangle"); // triangle,trapezoid=台形,square
		initialState.addElement("B is trapezoid");
		initialState.addElement("C is square");

		initialState.addElement("A is color of red"); // red,white,green
		initialState.addElement("B is color of white");
		initialState.addElement("C is color of green");

		initialState.addElement("ontable A");
		initialState.addElement("ontable B");
		initialState.addElement("ontable C");
		initialState.addElement("handEmpty");

		return checkTriangle(initialState);
	}

	private Vector checkTriangle(Vector initialState) {
		Vector re = new Vector();

		for (int i = 0; i < initialState.size(); i++) {
			String str = (String) initialState.elementAt(i);
			Pattern pat1 = Pattern.compile("(?<object>.+?) is square");
			Matcher mat1 = pat1.matcher(str);
			if (mat1.find()) { // もしパターンにマッチすれば
				String object = mat1.group("object");
				re.addElement(object + " is not triangle");
			} else {
				Pattern pat2 = Pattern.compile("(?<object>.+?) is trapezoid");
				Matcher mat2 = pat2.matcher(str);
				if (mat2.find()) { // もしパターンにマッチすれば
					String object = mat2.group("object");
					re.addElement(object + " is not triangle");
				}
			}
			re.addElement(str);
		}
		return re;
	}
}
