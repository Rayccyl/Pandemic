import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ShowChart  {

    ShowChart(Model model) {
        NumberAxis xAxis = new NumberAxis(0, model.day, model.day/5);
        xAxis.setLabel("日期");
        NumberAxis yAxis = new NumberAxis(0, model.num, model.num/4);
        yAxis.setLabel("人数");
        LineChart lineChart = new LineChart(xAxis,yAxis);
        lineChart.getData().addAll(model.seriesHealthy,model.seriesIncubate,model.seriesInfecting,model.seriesCure,model.seriesDie);
        model.seriesHealthy.setName("健康");
        model.seriesIncubate.setName("潜伏期");
        model.seriesInfecting.setName("感染中");
        model.seriesCure.setName("已获得抗体");
        model.seriesDie.setName("死亡");
        BorderPane root =new BorderPane(lineChart);

        lineChart.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 10px;");
        lineChart.setTitle("疫情趋势分析");
        lineChart.setCreateSymbols(true);

        Scene scene = new Scene(root,600,500);
        Stage stage = new Stage();
        stage.setTitle("疫情分析");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/analise.png")));
        stage.show();
    }

}
