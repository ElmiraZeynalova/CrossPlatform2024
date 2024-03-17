public class Check {
    private double x = 3.0;
    private double y = 4.0;

    public double Dist() {
        return Math.sqrt(x * x + y * y);
    }

    public void setRandomData() {
        x = Math.random();
        y = Math.random();
    }

    @Override
    public String toString() {
        return "x = " + x + ", y = " + y;
    }

    public static void main(String[] args) {
        Check check = new Check();
        ObjectAnalyzer.inspectObject(check);
    }
}
