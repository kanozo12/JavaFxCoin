package layout;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import api.APILoader;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import vo.CoinData;
import vo.CoinPriceVO;

public class CoinChartController {
	int i = MainController.i;
	@FXML
	private CategoryAxis x;
	@FXML
	private NumberAxis y;
	@FXML
	private AnchorPane root;
	@FXML
	private Label coinName;
	@FXML
	private Label coinPrice;
	@FXML
	private Label coinAvg;
	@FXML
	private Label coinTrad;
	@FXML
	private Label coinFlu;
	@FXML
	private Label coinMax;
	@FXML
	private Label coinMin;
	
	private APILoader loader;

	private CoinData coinData;

	private String selectedCoinName;

	@FXML
	LineChart<String, Number> lineChart;

	private Map<String, String> coinNameMap = new HashMap<String, String>();

	public void init(String coinName) {
		this.selectedCoinName = coinName;
		coinNameMap.put("XEM", "NEM");
		coinNameMap.put("REX", "imbrex");
		coinNameMap.put("BZNT", "bezant");
		coinNameMap.put("LOOM", "loom-network");
		coinNameMap.put("GTO", "gifto");
		coinNameMap.put("CTXC", "cortex");
		coinNameMap.put("VET", "vechain");
		coinNameMap.put("WTC", "waltonchain");
		coinNameMap.put("PAY", "tenx");
		coinNameMap.put("TMTG", "the-midas-touch-gold");
		coinNameMap.put("DASH", "dash");
		coinNameMap.put("WAVES", "waves");
		coinNameMap.put("STEEM", "steem");
		coinNameMap.put("XRP", "ripple");
		coinNameMap.put("RNT", "oneroot-network");
		coinNameMap.put("TRX", "tron");
		coinNameMap.put("ZRX", "0x");
		coinNameMap.put("BCD", "bitcoin-diamond");
		coinNameMap.put("AE", "aeternity");
		coinNameMap.put("HSV", "bitcoin-sv"); // BSV
		coinNameMap.put("BCH", "bitcoin-cash");
		coinNameMap.put("HC", "hypercash");
		coinNameMap.put("BHPC", "bhp-coin");
		coinNameMap.put("OMG", "omisego");
		coinNameMap.put("TRUE", "truechain");
		coinNameMap.put("CMT", "comet");
		coinNameMap.put("BTC", "bitcoin");
		coinNameMap.put("PST", "primas");
		coinNameMap.put("BTG", "bitcoin-gold");
		coinNameMap.put("MITH", "mithril");
		coinNameMap.put("LTC", "litecoin");
		coinNameMap.put("STRAT", "stratis");
		coinNameMap.put("POWR", "power-ledger");
		coinNameMap.put("THETA", "theta");
		coinNameMap.put("ENJ", "enjin-coin");
		coinNameMap.put("KNC", "kingn-coin");
		coinNameMap.put("GNT", "golem-network-tokens");
		coinNameMap.put("WAX", "wax");
		coinNameMap.put("INS", "insolar");
		coinNameMap.put("ABT", "arcblock");
		coinNameMap.put("PPT", "populous");
		coinNameMap.put("SNT", "status");
		coinNameMap.put("XLM", "stellar");
		coinNameMap.put("LINK", "chainlink");
		coinNameMap.put("PLY", "playcoin-erc20");
		coinNameMap.put("QTUM", "qtum");
		coinNameMap.put("RDN", "raiden-network-token");
		coinNameMap.put("PIVX", "pivx");
		coinNameMap.put("EOS", "eos");
		coinNameMap.put("ETHOS", "ethos");
		coinNameMap.put("SALT", "salt");
		coinNameMap.put("ETC", "ethereum-classic");
		coinNameMap.put("BAT", "basic-attention-token");
		coinNameMap.put("ETH", "ethereum");
		coinNameMap.put("MCO", "crypto-com");
		coinNameMap.put("ZEC", "zcash");
		coinNameMap.put("ITC", "iot-chain");
		coinNameMap.put("OCN", "odyssey");
		coinNameMap.put("XMR", "monero");
		coinNameMap.put("ZIL", "zilliqa");
		coinNameMap.put("LRC", "loopring");
		coinNameMap.put("REP", "augur");
		coinNameMap.put("ELF", "aelf");
		coinNameMap.put("ADA", "cardano");
		coinNameMap.put("ICX", "icon");

	}

	public void btn(ActionEvent event) {
		loader = APILoader.getLoader();

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.MONTH, -2);
		
		DecimalFormat intformat = new DecimalFormat("###,###");
		DecimalFormat doubleformat = new DecimalFormat("###,###.00");
		
		coinData = loader.getCoinData(this.selectedCoinName);
		
		coinName.setText(coinData.getCoinname().toString());
		
		if (coinData.getFluctate_24H() >= 0) {
			coinFlu.setTextFill(javafx.scene.paint.Color.WHITE);
			coinFlu.setStyle("-fx-background-color: #f75467;");
			coinFlu.setText("+" + coinData.getFluctate_24H() + "%");
			
			coinPrice.setTextFill(javafx.scene.paint.Color.RED);
			coinPrice.setText(intformat.format(coinData.getClosing_price()));
		} else {
			coinFlu.setTextFill(javafx.scene.paint.Color.WHITE);
			coinFlu.setStyle("-fx-background-color: #4386f9;");
			coinFlu.setText(coinData.getFluctate_24H() + "%");
			
			coinPrice.setTextFill(javafx.scene.paint.Color.BLUE);
			coinPrice.setText(intformat.format(coinData.getClosing_price()));
		}

		coinMax.setText(coinData.getMax_price() + "");
		coinMin.setText(coinData.getMin_price() + "");
		
		coinTrad.setText(String.format("%,.4f", coinData.getUnits_traded()) + "");

		try {
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

			String coinName = coinNameMap.get(this.selectedCoinName);
			Document doc = Jsoup.connect("https://coinmarketcap.com/ko/currencies/" + coinName
					+ "/historical-data/?start=" + sdf.format(today) + "&end=" + cal).get();
			Elements trList = doc.select("#historical-data tbody tr");

			List<CoinPriceVO> coinPriceList = new ArrayList<CoinPriceVO>();

			lineChart.getData().clear();

			for (Element tr : trList) {
				Elements tdList = tr.select("td");
				String day = tdList.get(0).text();
				String price = tdList.get(1).text();
				day = day.substring(10, 12);
				CoinPriceVO temp = new CoinPriceVO();
				temp.setDay(day);
				temp.setPrice(Double.parseDouble(price));
				coinPriceList.add(temp);
			}

			for (int i = coinPriceList.size() - 1; i >= 0; i--) {
				CoinPriceVO item = coinPriceList.get(i);
				series.getData().add(new XYChart.Data<String, Number>(item.getDay(), item.getPrice()));
			}
			lineChart.getData().add(series);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ChartLoad Error");
		}

	}

	public void closeChart() {
		StackPane mainPage = (StackPane) root.getScene().getRoot();

		try {
			Timeline timeline = new Timeline();
			KeyValue keyValue = new KeyValue(root.opacityProperty(), 0);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), (ActionEvent e) -> {
				mainPage.getChildren().remove(root);
			}, keyValue);

			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SceneChange Error");
		}
	}
}
