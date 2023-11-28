import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Controller {


    public Controller(Model model, View view,Stage stage) {

        view.start.setOnAction(event -> {
            model.num0=model.num1=model.num2=model.num3=model.num4=0;
            view.day.setText("day0");
            for(Text t:new Text[]{view.t0,view.t1,view.t2,view.t3,view.t4}){
                t.setText("0");
            }
            model.mapWidth=view.map.getWidth();
            model.mapHeight=view.map.getHeight();
            model.pool=new ArrayList<Person>();
            view.map.getChildren().clear();
            if(view.start.getText()=="开始模拟"){
                //点击开始
                view.start.setText("停止模拟");
                view.pause.setDisable(false);
                view.pause.setText("暂停模拟");
                view.set1.setDisable(true);
                Person seed =new Person(2,model,view);
                model.pool.add(seed);
                model.num=view.set1.getValue();
                model.infectDistance=view.set2.getValue();
                model.incubate=view.set3.getValue();
                model.speed = view.set4.getValue();
                for(int i=0;i<model.num;i++){
                    model.pool.add(new Person());
                }
                view.map.getChildren().addAll(model.pool);
                stage.setResizable(false);
                model.pool.forEach(person -> person.randomWalk());

            }else {
                view.start.setText("开始模拟");
                view.pause.setDisable(true);
                view.set1.setDisable(false);
                view.set2.setDisable(false);
                view.set3.setDisable(false);
                view.set4.setDisable(false);
                view.day.setText("Day0");
                for (Text t : new Text[]{view.t0, view.t1, view.t2, view.t3, view.t4}) {
                    t.setText("0");
                }
                model.pool.clear();
                stage.setResizable(true);
                view.map.getChildren().clear();
            }
        });

        view.pause.setOnAction(event -> {
            if(view.pause.getText()=="暂停模拟"){
                view.pause.setText("继续模拟");
            }else {
                view.pause.setText("暂停模拟");
                model.pool.forEach(person -> person.randomWalk());
            }
        });
    }
}
