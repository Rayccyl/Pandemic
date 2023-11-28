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
                view.popularity.setDisable(true);
                view.speed.setDisable(false);
                model.seriesHealthy.getData().clear();
                model.seriesIncubate.getData().clear();
                model.seriesInfecting.getData().clear();
                model.seriesCure.getData().clear();
                model.seriesDie.getData().clear();
                Person seed =new Person(2,model,view);
                model.pool.add(seed);
                model.num=view.popularity.getValue();
                model.speed = view.speed.getValue();
                String selectedVirus = view.virusSelection.getValue();
                if(selectedVirus.equals("自定义病毒")){
                    model.infectDistance=view.infectDistance.getValue();
                    model.incubate=view.incubateDays.getValue();
                    model.spreadDays=view.spreadDays.getValue();
                    model.probDeath=view.probDeath.getValue();
                }else{
                    switch (selectedVirus) {
                        case "新冠":
                            //model.setVirusParameters(/* 设置病毒A的参数 */);
                            model.probDeath=0.0001;
                            model.infectDistance=11;
                            model.incubate=5;
                            model.spreadDays=9;
                            break;
                        case "流感":
                            //model.setVirusParameters(/* 设置病毒B的参数 */);
                            model.probDeath = 0.0001;
                            model.infectDistance=6;
                            model.incubate=2;
                            model.spreadDays=6;
                            break;
                        case "支原体病毒":
                            model.probDeath = 0.007;
                            model.infectDistance=6;
                            model.incubate= 17;
                            model.spreadDays=30;
                            break;
                        default:
                            break;
                    }
                }

                for(int i=0;i<model.num;i++){
                    if(Math.random()<model.vaccine){
                        model.pool.add(new Person(3,model,view));
                    }else{
                        model.pool.add(new Person());
                    }

                }
                view.map.getChildren().addAll(model.pool);
                stage.setResizable(false);
                view.virusPane.setExpanded(false);
                view.virusPane.setDisable(true);
                view.virusSelection.setDisable(true);
                view.vaccineRate.setDisable(true);
                view.newSeed.setDisable(false);
                model.pool.forEach(person -> person.randomWalk());

            }else {
                new ShowChart(model);
                view.start.setText("开始模拟");
                view.pause.setDisable(true);
                view.popularity.setDisable(false);
                view.infectDistance.setDisable(false);
                view.incubateDays.setDisable(false);
                view.speed.setDisable(false);
                view.spreadDays.setDisable(false);
                view.probDeath.setDisable(false);
                view.virusPane.setDisable(false);
                view.virusSelection.setDisable(false);
                view.vaccineRate.setDisable(false);
                view.newSeed.setDisable(true);
                model.day=0;

                view.day.setText("Day0");
                for (Text t : new Text[]{view.t0, view.t1, view.t2, view.t3, view.t4}) {
                    t.setText("0");
                }
                model.pool.clear();
                stage.setResizable(true);
                view.map.getChildren().clear();
            }
        });
        view.newSeed.setOnAction(event -> {
            Person seed =new Person(2,model,view);
            model.pool.add(seed);
            view.map.getChildren().add(seed);
            seed.randomWalk();
            if(model.num1+ model.num2==0)model.pool.forEach(person -> person.randomWalk());
            view.pause.setDisable(false);
            view.speed.setDisable(false);
        });
        view.pause.setOnAction(event -> {
            if(view.pause.getText()=="暂停模拟"){
                view.pause.setText("继续模拟");
            }else {
                view.pause.setText("暂停模拟");
                model.pool.forEach(person -> person.randomWalk());
            }
        });
        view.virusSelection.setOnAction(event -> {
            String selectedVirus = view.virusSelection.getValue();
            if (selectedVirus.equals("自定义病毒")) {
                view.virusPane.setExpanded(true);
                view.virusPane.setDisable(false);
            } else {
                view.virusPane.setExpanded(false);
                view.virusPane.setDisable(true);
            }
        });
        view.about.setOnAction(event -> {
            try {
                new About().start(new Stage());
            } catch (Exception e) {
                return;
            }
        });
    }
}



