package arch.datasource2;

import java.util.ArrayList;
import java.util.Random;

import arch.datasourceinterface.DataInterface;
import arch.datasourceinterface.DataItem;
import arch.datasourceinterface.DefaultDataItem;

public class DataSource2 implements DataInterface {
	
	private static String[] companies = {"BMW", "Volkswagen"};
	
	private ArrayList<DataItem> list = null;
	
	public ArrayList<DataItem> getData() {
		if ( list != null )
			return list;
		list = new ArrayList<DataItem>();
		
		Random r = new Random();
		
		for(int i = 0; i < companies.length; i++) {	
			DataItem di = new DefaultDataItem(companies[i], (int)(r.nextFloat()*100000));
			di.setArea(getArea());
			list.add(di);		
		}
		
		return list;
	}
	
	public String getArea() {
		return "France";
	}
}
