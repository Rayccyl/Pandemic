import javafx.application.Application;
import javafx.application.HostServices;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class About extends Application {

    private static final String author = "吴松烨";
    private static final String studentsNumber="0224766";
    private VBox vBox=new VBox(20);
    private Label authorInfo;
    private Label projectInfo;
    private Hyperlink myGithubAddress=new Hyperlink("项目地址");
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        authorInfo = new Label(studentsNumber+author);
        projectInfo=new Label();
        projectInfo.setText("整理代码为若干个文件 部分标识符重命名 使得更加规范\n" +
                "使得person在墙壁旁时反弹\n" +
                "可以在任意时刻 在模型中新增感染者\n" +
                "完善了按键置灰逻辑 \"停止\"时重要属性归零\n" +
                "将病毒种类和参数封装 使得死亡率和染病时间可以由用户调整\n" +
                "增加了疫苗接种模拟\n" +
                "在模拟完成后 按\"停止模拟\" 可以看到分析图表\n" +
                "制作\"关于\"页面");
        vBox.getChildren().addAll(authorInfo,projectInfo,myGithubAddress);
        BorderPane borderPane=new BorderPane();
        borderPane.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        scene=new Scene(borderPane,500,420);
        authorInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        projectInfo.setStyle("-fx-font-size: 14px;");
        myGithubAddress.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px;");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Pandemic");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/main.png")));
        primaryStage.show();
        myGithubAddress.setOnAction(e -> {
            HostServices host=getHostServices();
            host.showDocument("https://github.com/Rayccyl/Pandemic");
        });
    }
}
