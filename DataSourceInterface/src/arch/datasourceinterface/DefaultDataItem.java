package arch.datasourceinterface;

public class DefaultDataItem implements DataItem {

	private String company = "";
	private int revenue = 0;
	private String area = "";
	
	public DefaultDataItem(String company, int revenue) {
		this.company = company;
		this.revenue = revenue;
	}
	
	@Override
	public String getCompany() {
		return company;
	}

	@Override
	public int getRevenue() {
		return revenue;
	}

	@Override
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
}
