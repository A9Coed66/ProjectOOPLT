package screen.controller.page.analyzePage;

public class LineEquation {
    private final double slope;
    private final double intercept;

    public LineEquation(double slope, double intercept) {
        this.slope = slope;
        this.intercept = intercept;
    }

    public double getSlope() {
        return slope;
    }

    public double getIntercept() {
        return intercept;
    }
    
    public double calculateY(double x) {
        return slope * x + intercept;
    }
}

