package arch.datadisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import arch.datasourceinterface.DataInterface;
import arch.datasourceinterface.DataItem;
import arch.datasourceinterface.DefaultDataItem;

public class DataAggregator {

	private ArrayList<DataInterface> list = new ArrayList<DataInterface>();
	
	public void addDataSource(DataInterface inf) {
		list.add(inf);
	}
	
	public ArrayList<DataItem> getIdentity() {
		ArrayList<DataItem> items = new ArrayList<DataItem>();
		
		for(int i = 0; i < list.size(); i++) {
			DataInterface inf = list.get(i);
			
			ArrayList<DataItem> di = inf.getData();
			for(int j = 0; j < di.size(); j++)
				items.add(di.get(j));
		}
		
		return items;
	}
	
	public ArrayList<DataItem> getMerged() {
		ArrayList<DataItem> items = new ArrayList<DataItem>();
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for(int i = 0; i < list.size(); i++) {
			DataInterface inf = list.get(i);
			
			ArrayList<DataItem> di = inf.getData();
			for(int j = 0; j < di.size(); j++) {
				DataItem item = di.get(j);
				if (map.containsKey(item.getCompany())) {
					Integer revenue = map.get(item.getCompany());
					revenue = revenue + item.getRevenue();
					map.put(item.getCompany(), revenue);
				} else {
					map.put(item.getCompany(), item.getRevenue());
				}
			}
		}
		
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		while( it.hasNext() ) {
			String s = it.next();
			DefaultDataItem k = new DefaultDataItem(s, map.get(s));
			k.setArea("Total");
			items.add(k);
		}
		
		return items;
	}
}
