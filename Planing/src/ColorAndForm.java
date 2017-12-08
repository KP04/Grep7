import java.util.Hashtable;
import java.util.Vector;

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
		Planner.planning(goalList, initialState, theBinding);
		System.out.println("***** This is a plan! *****");
		for (int i = 0; i < Planner.plan.size(); i++) {
			Operator op = (Operator) Planner.plan.elementAt(i);
			System.out.println((op.instantiate(theBinding)).name);
		}
	}

	private void initOperators() {
		Planner.operators = new Vector();
		Vector operators = Planner.operators;
		
		// OPERATOR 1
		// / NAME
		String name1 = new String("Place ?x on ?y");
		// / IF
		Vector ifList1 = new Vector();
		ifList1.addElement(new String("clear ?y"));
		ifList1.addElement(new String("holding ?x"));
		ifList1.addElement(new String("?y is square"));
		// / ADD-LIST
		Vector addList1 = new Vector();
		addList1.addElement(new String("?x on ?y"));
		addList1.addElement(new String("clear ?x"));
		addList1.addElement(new String("handEmpty"));
		// / DELETE-LIST
		Vector deleteList1 = new Vector();
		deleteList1.addElement(new String("clear ?y"));
		deleteList1.addElement(new String("holding ?x"));
		Operator operator1 = new Operator(name1, ifList1, addList1, deleteList1);
		operators.addElement(operator1);
		
		
		// 台形の上に三角形が乗るときの判定
		String name5 = new String("Place ?x on ?y");
		// IF
		Vector ifList5 = new Vector();
		ifList5.addElement(new String("clear ?y"));
		ifList5.addElement(new String("holding ?x"));
		//ifList5.addElement(new String("?x is triangle"));
		ifList5.addElement(new String("?y is trapezoid"));
		// ADD-LIST
		Vector addList5 = new Vector();
		addList5.addElement(new String("?x on ?y"));
		addList5.addElement(new String("clear ?x"));
		addList5.addElement(new String("handEmpty"));
		// DELETE-LIST
		Vector deleteList5 = new Vector();
		deleteList5.addElement(new String("clear ?y"));
		deleteList5.addElement(new String("holding ?x"));
		Operator operator5 = new Operator(name5,ifList5,addList5,deleteList5);
		operators.addElement(operator5);
		
		
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
		Operator operator2 = new Operator(name2, ifList2, addList2, deleteList2);
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
		Operator operator3 = new Operator(name3, ifList3, addList3, deleteList3);
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
		Operator operator4 = new Operator(name4, ifList4, addList4, deleteList4);
		operators.addElement(operator4);
	}

	private Vector initGoalList() {
		Vector goalList = new Vector();
		goalList.addElement("B on C");
		goalList.addElement("A on B");
		return goalList;
	}

	private Vector initInitialState() {
		Vector initialState = new Vector();
		initialState.addElement("clear A");
		initialState.addElement("clear B");
		initialState.addElement("clear C");
		
		initialState.addElement("A is triangle"); //triangle,trapezoid=台形,square
		initialState.addElement("B is square");
		initialState.addElement("C is square");

		initialState.addElement("A is red"); //red,white,green
		initialState.addElement("A is white");
		initialState.addElement("A is green");

		initialState.addElement("ontable A");
		initialState.addElement("ontable B");
		initialState.addElement("ontable C");
		initialState.addElement("handEmpty");

		return initialState;
	}
	
	private Vector change(Vector addList){
		Vector CAFList = addList;
		return CAFList;
	}
}
