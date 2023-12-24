package screen.controller.page.analyzePage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import algorithm.GetTopCollectionFromJsonFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.dataPoint.DataPoint;
import model.dataPoint.DataPointv1;
import screen.controller.page.searchPage.SearchPageController;

public class CorrelationAnalysisControllerv1 {
	String TOP_COLLECTION_DATA_FILE = "data/json/collection/okx/topcollection_20231221_160726.json";
	ArrayList<DataPointv1> dataPointsList = new GetTopCollectionFromJsonFile(TOP_COLLECTION_DATA_FILE)
			.jsonReadAlgorithm();

	@FXML
	private LineChart<Number, Number> trendingHashtagChart;

	@FXML
	private LineChart<Number, Number> volumeHashtagChart;

	@FXML
	private LineChart<Number, Number> trendingKeywordChart;

	@FXML
	private LineChart<Number, Number> volumeKeywordChart;

	@FXML
	private ComboBox<String> nftAspectComboBox;

	@FXML
	private ComboBox<String> tweetBlogAspectComboBox;
	@FXML
	private Button searchButton;

	@FXML
	void openSearchPage(ActionEvent event) {
		try {
			final String SEARCH_PAGE_FXML_FILE_PATH = "/screen/view/page/SearchPageView.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(SEARCH_PAGE_FXML_FILE_PATH));
			SearchPageController searchPageController = new SearchPageController();
			fxmlLoader.setController(searchPageController);
			Parent root = fxmlLoader.load();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void initialize() {
		// Initialize ComboBox options
		List<String> nftAspectOptions = List.of("Trending", "Volume");
		List<String> tweetBlogAspectOptions = List.of("Hashtag", "Keyword");

		nftAspectComboBox.getItems().addAll(nftAspectOptions);
		tweetBlogAspectComboBox.getItems().addAll(tweetBlogAspectOptions);

		// Set default selection
		nftAspectComboBox.getSelectionModel().select("Trending");
		tweetBlogAspectComboBox.getSelectionModel().select("Hashtag");

		// Show default graph
		showGraph();

		nftAspectComboBox.setOnAction(event -> showGraph());
		tweetBlogAspectComboBox.setOnAction(event -> showGraph());
	}

	@FXML
	private void showGraph() {
		String nftAspect = nftAspectComboBox.getValue();
		String tweetBlogAspect = tweetBlogAspectComboBox.getValue();

		// Hiển thị các bảng dựa trên lựa chọn
		if ("Trending".equals(nftAspect) && "Hashtag".equals(tweetBlogAspect)) {
			activeGraph(trendingHashtagChart);
		} else if ("Volume".equals(nftAspect) && "Hashtag".equals(tweetBlogAspect)) {
			activeGraph(volumeHashtagChart);
		} else if ("Trending".equals(nftAspect) && "Keyword".equals(tweetBlogAspect)) {
			activeGraph(trendingKeywordChart);
		} else if ("Volume".equals(nftAspect) && "Keyword".equals(tweetBlogAspect)) {
			activeGraph(volumeKeywordChart);
		}

		fetchAndDisplayData(nftAspect, tweetBlogAspect);
	}

	// Chọn bảng để hiện thông tin
	private void activeGraph(LineChart<Number, Number> graph) {
		trendingHashtagChart.setVisible(false);
		volumeHashtagChart.setVisible(false);
		trendingKeywordChart.setVisible(false);
		volumeKeywordChart.setVisible(false);
		graph.setVisible(true);
	}

	// Lấy dữ liệu và hiển thị lên bảng
	private void fetchAndDisplayData(String nftAspect, String tweetBlogAspect) {
		// Calculate linear regression
		LineEquation regressionLine = calculateLinearRegression(dataPointsList);

		// Add data series
		XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
		XYChart.Series<Number, Number> trendlineSeries = new XYChart.Series<>();

		// Add data points to the series based on the selected aspects
		for (DataPointv1 dataPoint : dataPointsList) {
			int xValue = 0;
			int yValue = 0;

			switch (nftAspect) {
			case "Trending":
				xValue = dataPoint.getNftRanking();
				break;
			case "Volume":
				xValue = calculateRankingByVolume(dataPoint, dataPointsList);
				break;
			}

			switch (tweetBlogAspect) {
			case "Hashtag":
				yValue = dataPoint.getTweetRanking();
				break;
			case "Keyword":
				break;
			}

			dataSeries.getData().add(new XYChart.Data<>(xValue, yValue));
			System.out.println("X: " + xValue + ", Y: " + yValue + "\n");
		}

		// Add trendline
		int minX = dataPointsList.stream().mapToInt(dp -> {
			Object propertyValue = dp.getProperty(nftAspect);
			if (propertyValue instanceof Integer) {
				return (int) propertyValue;
			} else if (propertyValue instanceof Float) {
				return Math.round((float) propertyValue); // Round float to nearest integer
			} else {
				// Handle other types or provide a default value
				return 0;
			}
		}).min().orElse(0);

		int maxX = dataPointsList.stream().mapToInt(dp -> {
			Object propertyValue = dp.getProperty(nftAspect);
			if (propertyValue instanceof Integer) {
				return (int) propertyValue;
			} else if (propertyValue instanceof Float) {
				return Math.round((float) propertyValue); // Round float to nearest integer
			} else {
				// Handle other types or provide a default value
				return 0;
			}
		}).max().orElse(10);

		trendlineSeries.getData().add(new XYChart.Data<>(minX, regressionLine.calculateY(minX)));
		trendlineSeries.getData().add(new XYChart.Data<>(maxX, regressionLine.calculateY(maxX)));

		// Set chart properties
		LineChart<Number, Number> selectedChart = getSelectedChart(nftAspect, tweetBlogAspect);
		selectedChart.getData().setAll(dataSeries, trendlineSeries);
		selectedChart.setTitle("Correlation between " + nftAspect + " and " + tweetBlogAspect);
		selectedChart.getXAxis().setLabel(nftAspect + " Ranking");
		selectedChart.getYAxis().setLabel(tweetBlogAspect + " Ranking");

		// Remove legend
		selectedChart.setLegendVisible(false);
	}

	private LineEquation calculateLinearRegression(List<DataPointv1> dataPoints) {
		int n = dataPoints.size();
		double sumX = 0;
		double sumY = 0;
		double sumXY = 0;
		double sumXSquare = 0;

		for (DataPointv1 dataPoint : dataPoints) {
			double x = dataPoint.getNftRanking();
			double y = dataPoint.getVolume();

			sumX += x;
			sumY += y;
			sumXY += x * y;
			sumXSquare += x * x;
		}

		double slope = (n * sumXY - sumX * sumY) / (n * sumXSquare - sumX * sumX);
		double intercept = (sumY - slope * sumX) / n;

		return new LineEquation(slope, intercept);
	}

	private int calculateRankingByVolume(DataPointv1 targetPoint, List<DataPointv1> dataPoints) {
		List<DataPointv1> sortedList = new ArrayList<>(dataPoints);

		sortedList.sort(Comparator.comparingDouble(DataPointv1::getVolume).reversed());

		return IntStream.range(0, sortedList.size()).filter(i -> sortedList.get(i) == targetPoint).findFirst()
				.orElse(-1) + 1;
	}

	private LineChart<Number, Number> getSelectedChart(String nftAspect, String tweetBlogAspect) {
		// Map the selected aspects to the corresponding charts
		switch (nftAspect + tweetBlogAspect) {
		case "TrendingHashtag":
			return trendingHashtagChart;
		case "VolumeHashtag":
			return volumeHashtagChart;
		case "TrendingKeyword":
			return trendingKeywordChart;
		case "VolumeKeyword":
			return volumeKeywordChart;
		default:
			// Default to trending and hashtag
			return trendingHashtagChart;
		}
	}

	// Get data of top 10 trending colelction
	private ArrayList<DataPoint> getData() {
		ArrayList<DataPoint> tempArr = new ArrayList<DataPoint>();
		// Read file to get data
		// The required of a datapoint is:
		// 1. Top ranking; 2. Name of collection; 3. Volume per hour; 4. Number of post
		// (hashtag) in a day
		return tempArr;
	}

	public void printDataPoints(List<DataPointv1> dataPointsList) {
		for (DataPointv1 dataPoint : dataPointsList) {
			System.out.println("Name: " + dataPoint.getName());
			System.out.println("NFT Ranking: " + dataPoint.getNftRanking());
			System.out.println("Volume: " + dataPoint.getVolume());
			System.out.println("Tweet Blog Ranking: " + dataPoint.getTweetRanking());
			System.out.println();
		}
	}

	public static void main(String[] args) {

	}

}
