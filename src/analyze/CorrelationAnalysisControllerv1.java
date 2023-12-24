package analyze;

import controller.algorithm.getTopCollectionFromJsonFile;
import controller.page.SearchPageController;
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
import main.test.SearchPageTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CorrelationAnalysisControllerv1 {
    String TOP_COLLECTION_DATA_FILE = "data/json/collection/okx/topcollection_20231221_160726.json";
    ArrayList<DataPointv1> dataPointsList = new getTopCollectionFromJsonFile(TOP_COLLECTION_DATA_FILE).jsonReadAlgorithm();

    @FXML
    private LineChart<Number, Number> trendingHashtagChart;

    @FXML
    private LineChart<Number, Number> salesHashtagChart;

    @FXML
    private LineChart<Number, Number> volumeHashtagChart;

    @FXML
    private LineChart<Number, Number> trendingKeywordChart;

    @FXML
    private LineChart<Number, Number> salesKeywordChart;

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
        try{
            final String SEARCH_PAGE_FXML_FILE_PATH = "/view/page/SearchPageView.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(SEARCH_PAGE_FXML_FILE_PATH));
            SearchPageController searchPageController = new SearchPageController();
            fxmlLoader.setController(searchPageController);
            Parent root = fxmlLoader.load();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        // Initialize ComboBox options
        List<String> nftAspectOptions = List.of("Trending", "Sales", "Volume");
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
        } else if ("Sales".equals(nftAspect) && "Hashtag".equals(tweetBlogAspect)) {
            activeGraph(salesHashtagChart);
        } else if ("Volume".equals(nftAspect) && "Hashtag".equals(tweetBlogAspect)) {
            activeGraph(volumeHashtagChart);
        } else if ("Trending".equals(nftAspect) && "Keyword".equals(tweetBlogAspect)) {
            activeGraph(trendingKeywordChart);
        } else if ("Sales".equals(nftAspect) && "Keyword".equals(tweetBlogAspect)) {
            activeGraph(salesKeywordChart);
            volumeKeywordChart.setVisible(false);
        } else if ("Volume".equals(nftAspect) && "Keyword".equals(tweetBlogAspect)) {
            activeGraph(volumeKeywordChart);
        }
        
        // Your logic to fetch data and update the corresponding chart
//        fetchAndDisplayData("Trending", "Hashtag");
        fetchAndDisplayData(nftAspect, tweetBlogAspect);
    }
    
    

    // Chọn bảng để hiện thông tin
    private void activeGraph(LineChart<Number, Number> graph) {
        trendingHashtagChart.setVisible(false);
        salesHashtagChart.setVisible(false);
        volumeHashtagChart.setVisible(false);
        trendingKeywordChart.setVisible(false);
        salesKeywordChart.setVisible(false);
        volumeKeywordChart.setVisible(false);
        graph.setVisible(true);
    }



    // Lấy dữ liệu và hiển thị lên bảng
    private void fetchAndDisplayData(String nftAspect, String tweetBlogAspect){
        // Calculate linear regression
        /* XXX: cần thêm dữ liệu các bài tweet, từ đó thực hiện sắp xếp để tìm ra thứ collection nà có nhiều bài nhất
            * để set cho giá trị rank của các datapoint */
        LineEquation regressionLine = calculateLinearRegression(dataPointsList);

        // Add data series
        // Todo đây là nơi mình tạo các điểm dữ liệu
        XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
        for (DataPointv1 dataPoint : dataPointsList) {
            dataSeries.getData().add(new XYChart.Data<>(dataPoint.getProperty(nftAspect), dataPoint.getProperty(tweetBlogAspect)));
        }

        // Add trendline
        XYChart.Series<Number, Number> trendlineSeries = new XYChart.Series<>();
        int minX = dataPointsList.stream().mapToInt(DataPointv1::getNftRanking).min().orElse(0);
        int maxX = dataPointsList.stream().mapToInt(DataPointv1::getNftRanking).max().orElse(10);
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

//    private List<DataPoint> fetchDataFromDatabase(String nftAspect, String tweetBlogAspect) {
//        // Placeholder logic to fetch data from the database
//        // Replace with your actual database query
//        // Example: JDBC query
//        String query = "SELECT nft_ranking, tweet_blog_ranking FROM your_table WHERE nft_aspect = ? AND tweet_blog_aspect = ?";
//        // Execute the query and retrieve results
//        return executeQuery(query, nftAspect, tweetBlogAspect);
//    }
//
//    private List<DataPoint> executeQuery(String query, String nftAspect, String tweetBlogAspect) {
//        // Execute the query and return the result
//        // This is a placeholder, replace it with actual database access logic
//        return List.of(
//                new DataPoint("Point1", 5, 3),
//                new DataPoint("Point2", 8, 6),
//                new DataPoint("Point3", 3, 4),
//                new DataPoint("Point4", 10, 8)
//        );
//    }

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

    private LineChart<Number, Number> getSelectedChart(String nftAspect, String tweetBlogAspect) {
        // Map the selected aspects to the corresponding charts
        switch (nftAspect + tweetBlogAspect) {
            case "TrendingHashtag":
                return trendingHashtagChart;
            case "SalesHashtag":
                return salesHashtagChart;
            case "VolumeHashtag":
                return volumeHashtagChart;
            case "TrendingKeyword":
                return trendingKeywordChart;
            case "SalesKeyword":
                return salesKeywordChart;
            case "VolumeKeyword":
                return volumeKeywordChart;
            default:
                // Default to trending and hashtag
                return trendingHashtagChart;
        }
    }

    // Get data of top 10 trending colelction
    private ArrayList<DataPoint> getData(){
        ArrayList<DataPoint> tempArr = new ArrayList<DataPoint>();
        // Read file to get data
        // The required of a datapoint is:
        //1. Top ranking; 2. Name of collection; 3. Volume per hour; 4. Number of post (hashtag) in a day
        return tempArr;
    }

    public static void main(String[] args){

    }

}
