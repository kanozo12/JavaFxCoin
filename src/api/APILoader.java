package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import vo.CoinData;

public class APILoader {
	
	private static APILoader loader = new APILoader();
	
	public static APILoader getLoader() {
		return loader;
	}
	
	private Map<String, CoinData> coinMap;
	
	public Map<String, CoinData> getCoinMap() {
		return coinMap;
	}
	
	public CoinData getCoinData(String coin) {
		return coinMap.get(coin);
	}
	
	public void init() throws IOException {
		String url = "https://api.bithumb.com/public/ticker/All";
		String data = loadData(url);
		
		coinMap = new HashMap<String, CoinData>();
		Gson gson = new Gson();
		
		JsonObject obj = gson.fromJson(data, JsonObject.class);
		JsonObject list = obj.get("data").getAsJsonObject();
		
		Set<Entry<String, JsonElement>> entrySet = list.entrySet();
		for (Entry<String, JsonElement> entry : entrySet) {
			String coin = entry.getKey();
			
			if (coin.equals("date")) {
				continue;
			}
			
			JsonObject value = (JsonObject) entry.getValue();
			Double opening_price = value.get("opening_price").getAsDouble();
			Double units_traded = value.get("units_traded").getAsDouble();
			Double average_price = value.get("average_price").getAsDouble();
			
			CoinData temp = new CoinData();
			
			temp.setOpening_price(opening_price);
			temp.setUnits_traded(units_traded);
			temp.setAverage_price(average_price);
			
			temp.setCoinname(coin);
			
			coinMap.put(coin, temp);

		}

		CoinData btc = getCoinData("BTC");
		System.out.println("비트코인 가격은 " + btc.getOpening_price());
		System.out.println("비트코인의 거래량은" + btc.getUnits_traded());

		CoinData eth = getCoinData("ETH");
		System.out.println("이더리움 가격은 " + eth.getOpening_price());
		System.out.println("이더리움 거래량은" + eth.getUnits_traded());

	}

	public String loadData(String urlString) throws IOException {
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		InputStreamReader isr = new InputStreamReader(con.getInputStream());
		BufferedReader br = new BufferedReader(isr);

		String data = "";
		while (true) {
			String recv = br.readLine();
			if (recv == null) {
				break;
			}
			data += recv;
		}

		br.close();
		return data;

	}
}
