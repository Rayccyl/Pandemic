import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class View extends BorderPane {
    protected static final int PADDING=10;
    protected ComboBox<Integer> popularity =new ComboBox<Integer>();
    protected ComboBox<Integer> infectDistance =new ComboBox<Integer>();
    protected ComboBox<Integer> incubateDays =new ComboBox<Integer>();
    protected ComboBox<Integer> speed =new ComboBox<Integer>();
    protected ComboBox<Double> probDeath =new ComboBox<Double>();
    protected ComboBox<Integer> spreadDays =new ComboBox<Integer>();
    protected ComboBox<String> virusSelection = new ComboBox<>();
    protected ComboBox<Double> vaccineRate = new ComboBox<>();
    protected Button start=new Button("开始模拟");
    protected Button pause=new Button("暂停模拟");
    protected Button newSeed=new Button("新增传染源");
    protected Text day =new Text("Day0");
    protected Text t0=new Text("0");
    protected Text t1=new Text("0");
    protected Text t2=new Text("0");
    protected Text t3=new Text("0");
    protected Text t4=new Text("0");
    protected Pane map=new Pane();
    TitledPane virusPane = new TitledPane();
    Button about=new Button("关于本项目");
    public View(Model model) {
        newSeed.setDisable(true);
        FlowPane topBar = new FlowPane(PADDING,PADDING);
        Text rs=new Text("人数");
        Text fzsd=new Text("仿真速度");
        Text bdzl=new Text("病毒种类");
        Text ymjzl=new Text("疫苗接种率");
        rs.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        fzsd.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        bdzl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ymjzl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        topBar.getChildren().addAll(rs, popularity,
                incubateDays,fzsd, speed,bdzl, virusSelection,virusPane,ymjzl,vaccineRate);
        popularity.getItems().addAll(100,500,1000);
        popularity.setValue(100);
        infectDistance.getItems().addAll(1,5,10);
        infectDistance.setValue(1);
        infectDistance.valueProperty().addListener((observable, oldValue, newValue) -> {
            model.infectDistance = newValue;
        });
        incubateDays.getItems().addAll(3,5,7);
        incubateDays.setValue(7);
        incubateDays.valueProperty().addListener((observable, oldValue, newValue) -> {
            model.incubate = newValue;
        });
        speed.getItems().addAll(1,2,3);
        speed.setValue(1);
        speed.valueProperty().addListener((observable, oldValue, newValue) -> {
            model.speed = newValue;
        });
        probDeath.getItems().addAll(.1,.2,.5);
        probDeath.setValue(.1);
        probDeath.valueProperty().addListener((observable, oldValue, newValue) -> {
            model.probDeath = newValue;
        });
        spreadDays.getItems().addAll(10,14,18);
        spreadDays.setValue(14);
        spreadDays.valueProperty().addListener((observable, oldValue, newValue) -> {
            model.spreadDays = newValue;
        });
        vaccineRate.getItems().addAll(0.0,0.2,.6,.9);
        vaccineRate.setValue(0.0);
        vaccineRate.valueProperty().addListener((observable, oldValue, newValue) -> {
            model.vaccine = newValue;
        });

        ObservableList<String> virusOptions = FXCollections.observableArrayList(
                "新冠", "流感", "支原体病毒", "自定义病毒"
        );
        virusSelection.setItems(virusOptions);
        virusSelection.setValue("新冠");


        virusPane.setText("自定义病毒参数");
        virusPane.setExpanded(false);
        virusPane.setDisable(true);
        VBox virusContent = new VBox(10);
        virusContent.setPadding(new Insets(10));
        virusContent.getChildren().addAll(
                new Text("传染距离"),
                infectDistance,
                new Text("病程："),
                spreadDays,
                new Text("潜伏期"),
                incubateDays,
                new Text("病死率："),
                probDeath
        );

        virusPane.setContent(virusContent);
        VBox rBar=new VBox(PADDING);
        topBar.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10px;");
        rBar.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 10px;");



        topBar.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10px;");
        // Apply specific styles to each element as required

        virusContent.setStyle("-fx-background-color: #f8f8f8; -fx-padding: 10px;");
        virusContent.getChildren().forEach(node -> {
            if (node instanceof Text) {
                ((Text) node).setFill(Color.DARKGRAY); // Change text color
            }
        });

        // Style buttons
        start.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        pause.setStyle("-fx-background-color: #071cff; -fx-text-fill: white;");
        newSeed.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        about.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white;");

        day.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");

        // Apply specific styles to text elements

        // Style the right sidebar
        rBar.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 10px;");

        // Set styles to the scene components
        this.setStyle("-fx-background-color: #ffffff;");




        // Set preferred widths for the buttons
        start.setPrefWidth(100);
        pause.setPrefWidth(100);
        newSeed.setPrefWidth(120);
        about.setPrefWidth(120);

        model.num= popularity.getValue();
        model.infectDistance= infectDistance.getValue();
        model.incubate= incubateDays.getValue();
        model.speed = speed.getValue();
        pause.setDisable(true);
        Text tt0=new Text("正常");
        Text tt1=new Text("潜伏");
        Text tt2=new Text("传播");
        Text tt3=new Text("康复");
        Text tt4=new Text("死亡");
        for(Text t:new Text[]{tt0,tt1,tt2,tt3,tt4,day,t0,t1,t2,t3,t4}){
            t.setFont(Font.font(20));
        }
        t0.setFill(Model.COLOR0);
        t1.setFill(Model.COLOR1);
        t2.setFill(Model.COLOR2);
        t3.setFill(Model.COLOR3);
        t4.setFill(Model.COLOR4);

        rBar.getChildren().addAll(start,pause,newSeed,about,day,tt0,t0,tt1,t1,tt2,t2,tt3,t3,tt4,t4);

        this.setTop(topBar);
        this.setCenter(map);
        this.setRight(rBar);

    }
}
