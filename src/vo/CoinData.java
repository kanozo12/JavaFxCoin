package vo;

public class CoinData {
	private String coinname;
	private Double opening_price;
	private Double average_price;
	private Double units_traded;
	private Double fluctate_24H;
	
	public Double getFluctate_24H() {
		return fluctate_24H;
	}

	public void setFluctate_24H(Double fluctate_24h) {
		fluctate_24H = fluctate_24h;
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
