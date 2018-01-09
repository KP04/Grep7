import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;


public class test
{
	static Vector goalList;

	public static void main(String argv[])
	{
		goalList = initGoalList();
	}

	private static Vector initGoalList() {
		Vector goalList = new Vector();

		goalList.addElement("A on B");
		goalList.addElement("C on D");
		Vector newGoalList = alignGoalList(goalList);
		System.out.println(newGoalList);
		return newGoalList;
	}

	private static Vector alignGoalList(Vector goalList)
	{

		Vector newGoalList = new Vector();
		ArrayList<String> allObjects = new ArrayList<String>();

		for(int index = 0; index < goalList.size(); ++index)
		{
			ArrayList<String> objects = new ArrayList<String>();
			boolean isOnState = false;
			StringTokenizer tokenizer = new StringTokenizer((String)goalList.get(index));
			String firstObject = "";
			while(tokenizer.hasMoreTokens())
			{
				String token = tokenizer.nextToken();
				if(!token.equals("on"))
				{
					objects.add(token);
					if(firstObject.equals(""))
						firstObject = token;
				}
				else
				{
					isOnState = true;
				}
			}

			int insertIndex = allObjects.size();

			if(allObjects.contains(firstObject))
			{
				insertIndex = allObjects.indexOf(firstObject);
				allObjects.remove(insertIndex);
			}

			if(isOnState)
			{
				allObjects.addAll(insertIndex, objects);
			}
			else
			{
				newGoalList.add((String)goalList.get(index));
			}
		}

		for(int index = allObjects.size() - 1; index > 0; --index)
		{
			String goal = allObjects.get(index - 1) + " on " + allObjects.get(index);
			newGoalList.add(goal);
		}

		return newGoalList;
	}
}
