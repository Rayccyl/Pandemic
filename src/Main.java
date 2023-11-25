import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model =new Model();
        View view = new View(model);
        Controller controller = new Controller(model,view);
        Scene scene =new Scene(view,600,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
