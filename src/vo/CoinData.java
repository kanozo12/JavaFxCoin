package vo;

public class CoinData {
	private String coinname;
	private Double opening_price;
	private Double average_price;
	private Double units_traded;
	private Double fluctate_rate_24H;
	private Double closing_price;
	private Double min_price;
	private Double max_price;
	
	
	public Double getMin_price() {
		return min_price;
	}

	public void setMin_price(Double min_price) {
		this.min_price = min_price;
	}

	public Double getMax_price() {
		return max_price;
	}

	public void setMax_price(Double max_price) {
		this.max_price = max_price;
	}

	public Double getClosing_price() {
		return closing_price;
	}

	public void setClosing_price(Double closing_price) {
		this.closing_price = closing_price;
	}

	public Double getFluctate_rate_24H() {
		return fluctate_rate_24H;
	}

	public void setFluctate_rate_24H(Double fluctate_rate_24H) {
		this.fluctate_rate_24H = fluctate_rate_24H;
	}

	public Double getUnits_traded() {
		return units_traded;
	}

	public void setUnits_traded(Double units_traded) {
		this.units_traded = units_traded;
	}

	public String getCoinname() {
		return coinname;
	}

	public void setCoinname(String coinname) {
		this.coinname = coinname;
	}

	public Double getOpening_price() {
		return opening_price;
	}

	public void setOpening_price(Double opening_price) {
		this.opening_price = opening_price;
	}

	public Double getAverage_price() {
		return average_price;
	}

	public void setAverage_price(Double average_price) {
		this.average_price = average_price;
	}

	@Override
	public String toString() {
		return coinname;
	}

}
