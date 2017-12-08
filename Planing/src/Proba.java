import java.io.File;

public class Proba
{
	public static void main(String[] args)
	{
		Proba p = new Proba();
		p.start();
//		p.start2();
	}

	/**
	 * Construct a DOT graph in memory, convert it
	 * to image and store the image in the file system.
	 */
	private void start()
	{
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		//gv.addln("A -> B;");
		//gv.addln("A -> C;");
		String s1 = "pick_up_B_from_the_table_0";
		String s2 = "Place_B_on_A_1;";
		gv.addln(s1+" -> "+s2+";");
		//gv.addln("remove B from on top A -> D;");
		//gv.addln("B -> E;");
		//gv.addln("B -> F;");
		//gv.addln("C -> E;");
		//gv.addln("C -> G;");
		//gv.addln("D -> F;");
		//gv.addln("D -> G;");
		//gv.addln("E -> H;");
		//gv.addln("F -> H;");
		//gv.addln("G -> H;");
		//gv.addln(gv.end_graph());
		System.out.println(gv.getDotSource());

		//gv.increaseDpi();   // 106 dpi

		String type = "png";
		//String type = "gif";
		//      String type = "dot";
		//      String type = "fig";    // open with xfig
		//      String type = "pdf";
		//      String type = "ps";
		//      String type = "svg";    // open with inkscape
		//      String type = "png";
		//      String type = "plain";
		
		String repesentationType= "dot";
		//		String repesentationType= "neato";
		//		String repesentationType= "fdp";
		//		String repesentationType= "sfdp";
		// 		String repesentationType= "twopi";
		// 		String repesentationType= "circo";
		
		//File out = new File("tmp/out"+gv.getImageDpi()+"."+ type);   // Linux
		File out = new File("tmp/simple"+"."+ type);
		System.out.println("tmp/simple"+"."+ type);
		//System.out.println(FileLoading.fileLoading("tmp/test.txt").get(0));
		//      File out = new File("c:/eclipse.ws/graphviz-java-api/out." + type);    // Windows
		gv.writeGraphToFile( gv.getGraph(gv.getDotSource(), type, repesentationType), out );
	}

	/**
	 * Read the DOT source from a file,
	 * convert to image and store the image in the file system.
	 */
	private void start2()
	{
		String dir = "/home/jabba/Dropbox/git.projects/laszlo.own/graphviz-java-api";     // Linux
		String input = dir + "/sample/simple.dot";
		//	   String input = "c:/eclipse.ws/graphviz-java-api/sample/simple.dot";    // Windows

		GraphViz gv = new GraphViz();
		gv.readSource(input);
		System.out.println(gv.getDotSource());

		String type = "gif";
		//    String type = "dot";
		//    String type = "fig";    // open with xfig
		//    String type = "pdf";
		//    String type = "ps";
		//    String type = "svg";    // open with inkscape
		//    String type = "png";
		//      String type = "plain";
		
		
		String repesentationType= "dot";
		//		String repesentationType= "neato";
		//		String repesentationType= "fdp";
		//		String repesentationType= "sfdp";
		// 		String repesentationType= "twopi";
		//		String repesentationType= "circo";
		
		//File out = new File("tmp/simple." + type);   // Linux
			   File out = new File("c:/eclipse.ws/graphviz-java-api/tmp/simple." + type);   // Windows
		gv.writeGraphToFile( gv.getGraph(gv.getDotSource(), type, repesentationType), out );
	}
}