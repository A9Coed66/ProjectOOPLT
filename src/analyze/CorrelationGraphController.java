package analyze;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.List;

public class CorrelationGraphController {

    @FXML
    private LineChart<Number, Number> correlationChart;

    @FXML
    private void initialize() {
        // Sample data points
        List<DataPoint> dataPoints = List.of(
                new DataPoint("Point1", 5, 3),
                new DataPoint("Point2", 8, 6),
                new DataPoint("Point3", 3, 4),
                new DataPoint("Point4", 10, 8)
        );

        // Calculate linear regression
        LineEquation regressionLine = calculateLinearRegression(dataPoints);

        // Add data series
        XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
        
        for (DataPoint dataPoint : dataPoints) {
            dataSeries.getData().add(new XYChart.Data<>(dataPoint.getNftRanking(), dataPoint.getHashtagRanking()));
        }

        // Add trendline
        XYChart.Series<Number, Number> trendlineSeries = new XYChart.Series<>();
        
        int minX = dataPoints.stream().mapToInt(DataPoint::getNftRanking).min().orElse(0);
        int maxX = dataPoints.stream().mapToInt(DataPoint::getNftRanking).max().orElse(10);

        // Add points to represent the line
        trendlineSeries.getData().add(new XYChart.Data<>(minX, regressionLine.calculateY(minX)));
        trendlineSeries.getData().add(new XYChart.Data<>(maxX, regressionLine.calculateY(maxX)));

        // Set chart properties
        correlationChart.getData().addAll(dataSeries, trendlineSeries);
        correlationChart.setTitle("Correlation between NFT Collections and Hashtags");
        correlationChart.getXAxis().setLabel("NFT Collection Ranking");
        correlationChart.getYAxis().setLabel("Hashtag Ranking");
        
        // Remove legend
        correlationChart.setLegendVisible(false);

        // Apply CSS styling
        correlationChart.getStyleClass().add("custom-line-chart");
    }

    private LineEquation calculateLinearRegression(List<DataPoint> dataPoints) {
        int n = dataPoints.size();
        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumXSquare = 0;

        for (DataPoint dataPoint : dataPoints) {
            double x = dataPoint.getNftRanking();
            double y = dataPoint.getHashtagRanking();

            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumXSquare += x * x;
        }

        double slope = (n * sumXY - sumX * sumY) / (n * sumXSquare - sumX * sumX);
        double intercept = (sumY - slope * sumX) / n;

        return new LineEquation(slope, intercept);
    }

}
