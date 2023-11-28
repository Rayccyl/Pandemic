import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model =new Model();
        View view = new View(model);
        Controller controller = new Controller(model,view,primaryStage);
        Scene scene =new Scene(view,600,500);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(700);
        System.out.println("Started");
        primaryStage.setTitle("疫情模拟");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/main.png")));
        primaryStage.show();
    }
}
