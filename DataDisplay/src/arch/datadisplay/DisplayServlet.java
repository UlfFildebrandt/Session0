package arch.datadisplay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import arch.datasource1.DataSource1;
import arch.datasource2.DataSource2;
import arch.datasourceinterface.DataItem;

public class DisplayServlet extends HttpServlet {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 590808281763644925L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		
		writer.write("<html><head>");
		
		writer.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://localhost:1234/styles.css\"/>");
		
		writer.write("</head><body>");
		
		writer.write("<img src=annual-revenues.jpg/><br>");

		DataSource1 ds1 = new DataSource1();
		DataSource2 ds2 = new DataSource2();
		
		writer.write("<br><div class=title>Analyzed Data</div><br>");
		
		writer.write("<table cellspacing=\"0\"><tr><th width=\"300px\" scope=\"col\" class=\"nobg\">Company</th><th width=\"200px\" scope=\"col\">Area</th><th width=\"200px\" scope=\"col\">Revenue</th></tr>");
		
		DataAggregator da = new DataAggregator();
		da.addDataSource(ds1);
		da.addDataSource(ds2);

		ArrayList<DataItem> list1 = null;
		
		if ( req.getParameter("type").equals("identity") )
			list1 = da.getIdentity();
		if ( req.getParameter("type").equals("merge") )
			list1 = da.getMerged();
		
		for(int i = 0; i < list1.size(); i++) {
			writer.write("<tr><th scope=\"row\" class=\"spec\">" + list1.get(i).getCompany() + "</th><td>" + list1.get(i).getArea() + "</td><td>" + list1.get(i).getRevenue() + "</td></tr>");
		}

		writer.write("</table>");

		writer.write("<br><div class=title>Original Data</div><br>");

		writer.write("<table cellspacing=\"0\"><tr><th width=\"300px\" scope=\"col\" class=\"nobg\">Company</th><th width=\"200px\" scope=\"col\">Area</th><th width=\"200px\" scope=\"col\">Revenue</th></tr>");
		
		DataAggregator da2 = new DataAggregator();
		da2.addDataSource(ds1);
		da2.addDataSource(ds2);

		ArrayList<DataItem> list2 = null;

		list2 = da2.getIdentity();
	
		for(int i = 0; i < list2.size(); i++) {
			writer.write("<tr><th scope=\"row\" class=\"spec\">" + list2.get(i).getCompany() + "</th><td>" + list2.get(i).getArea() + "</td><td>" + list2.get(i).getRevenue() + "</td></tr>");
		}

		writer.write("</table>");

		writer.write("</body></html>");
	}

}
