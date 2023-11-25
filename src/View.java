import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class View extends BorderPane {
    protected static final int PADDING=10;
    protected ComboBox<Integer> set1=new ComboBox<Integer>();
    protected ComboBox<Integer> set2=new ComboBox<Integer>();
    protected ComboBox<Integer> set3=new ComboBox<Integer>();
    protected ComboBox<Integer> set4=new ComboBox<Integer>();
    protected Button start=new Button("开始模拟");
    protected Button pause=new Button("暂停模拟");
    protected Text day =new Text("Day0");
    protected Text t0=new Text("0");
    protected Text t1=new Text("0");
    protected Text t2=new Text("0");
    protected Text t3=new Text("0");
    protected Text t4=new Text("0");
    protected Pane map=new Pane();
    public View(Model model) {
        FlowPane topBar = new FlowPane(PADDING,PADDING);
        topBar.getChildren().addAll(new Text("人数"),set1,new Text("传染距离"),set2,new Text("潜伏期"),set3,new Text("仿真速度"),set4);
        set1.getItems().addAll(100,500,1000);
        set1.setValue(100);
        set2.getItems().addAll(1,5,10);
        set2.setValue(1);
        set3.getItems().addAll(3,5,7);
        set3.setValue(7);
        set4.getItems().addAll(1,2,3);
        set4.setValue(1);
        model.num=set1.getValue();
        model.infectDistance=set2.getValue();
        model.incubate=set3.getValue();
        model.speed = set4.getValue();

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
        VBox rBar=new VBox(PADDING);
        rBar.getChildren().addAll(start,pause,day,tt0,t0,tt1,t1,tt2,t2,tt3,t3,tt4,t4);

        this.setTop(topBar);
        this.setCenter(map);
        this.setRight(rBar);
    }
}
