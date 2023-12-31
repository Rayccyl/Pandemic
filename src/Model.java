import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Model {
    public double probDeath =.1;
    public double spreadDays =14;
    protected static final int DAY_STEPS=50;
    protected static final Color COLOR0 = Color.BLACK;
    protected static final Color COLOR1 = Color.GREEN;
    protected static final Color COLOR2 = Color.RED;
    protected static final Color COLOR3 = Color.DEEPSKYBLUE;
    protected static final Color COLOR4 = Color.GRAY;
    protected int num;
    protected int num0;
    protected int num1;
    protected int num2;
    protected int num3;
    protected int num4;
    protected double infectDistance;
    protected int incubate;
    protected int speed;
    protected double mapWidth;
    protected double mapHeight;
    protected int day;
    protected double vaccine;
    protected ArrayList<Person> pool = new ArrayList<Person>();
    XYChart.Series seriesHealthy=new XYChart.Series<>();
    XYChart.Series seriesIncubate=new XYChart.Series<>();
    XYChart.Series seriesInfecting=new XYChart.Series<>();
    XYChart.Series seriesCure=new XYChart.Series<>();
    XYChart.Series seriesDie=new XYChart.Series<>();
}
