package layout;

import java.util.Map;

import api.APILoader;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import vo.CoinData;

public class MainController {
	public static int i = 100;

	@FXML
	private Label lblTitle;
	@FXML
	private ListView<String> lstCoin;
	@FXML
	private Label coinValue;
	@FXML
	private AnchorPane mainAnchor;
	@FXML
	private ImageView btcImg;
	@FXML
	private ImageView ethImg;
	@FXML
	private ImageView btccashImg;
	@FXML
	private ImageView dashImg;
	@FXML
	private ImageView home;

	private APILoader loader;

	public void init() {
		loader = APILoader.getLoader();
		try {
			
			loader.init();

			Map<String, CoinData> coinMap = loader.getCoinMap();
			for (String key : coinMap.keySet()) {

				lstCoin.getItems().add(coinMap.get(key).toString());
			}
//			MultipleSelectionModel<String> coinName = lstCoin.getSelectionModel();
//			System.out.println(coinName.toString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러발생");
		}

	}

	public void openData(MouseEvent arg0) {
		StackPane mainPage = (StackPane) lstCoin.getScene().getRoot();

		String item = lstCoin.getSelectionModel().getSelectedItem();

		if (item == null) {
			return;
		}

		FXMLLoader CoinChartLoader = new FXMLLoader();
		CoinChartLoader.setLocation(getClass().getResource("/layout/CoinChartLayout.fxml"));

		try {
			AnchorPane CoinChartPage = CoinChartLoader.load();

			CoinChartController cc = CoinChartLoader.getController();
			cc.init(item);

			CoinChartPage.setOpacity(0); // 투명하게 바꿔서 안보이게 함.
			mainPage.getChildren().add(CoinChartPage);

			Timeline timeline = new Timeline();
			KeyValue keyValue = new KeyValue(CoinChartPage.opacityProperty(), 1);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);

			timeline.getKeyFrames().add(keyFrame);
			timeline.play();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("화면 전환중 오류 발생");
		}

	}

	public void handleBtnLogout() {

		try {
			StackPane mainPage = (StackPane) home.getScene().getRoot();

			Timeline timeline = new Timeline();
			KeyValue keyValue = new KeyValue(mainAnchor.opacityProperty(), 0);

			KeyFrame keyFrame = new KeyFrame(Duration.millis(800), (ActionEvent event) -> {
				mainPage.getChildren().remove(mainAnchor);
			}, keyValue);

			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("화면전환중 오류 발생");
		}
	}

}
