import javafx.animation.FadeTransition;
import javafx.scene.chart.XYChart;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class Person extends Circle {
    private static final int RADIUS=5;
    private int status=0;
    //正常0 潜伏1 传播2 痊愈3 死亡4
    private double dx,dy;
    private int step;
    private int day_infected;
    private static Model model;
    private static View view;
    public Person(int status,Model model,View view) {
        super(RADIUS*2+Math.random()*(model.mapWidth-4*RADIUS),RADIUS*2+Math.random()*(model.mapHeight-4*RADIUS),RADIUS);
        this.status=status;
        this.model=model;
        this.view = view;
        this.setId(model.num0+model.num1+model.num2+model.num3+model.num4+"");
        switch (status){
            case 1:model.num1++;break;
            case 2:model.num2++;break;
            case 3:model.num3++;break;
            case 4:model.num4++;break;
            default:model.num0++;
        }
        update();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private void update() {
        switch (this.status){
            case 1:this.setStroke(model.COLOR0);this.setFill(model.COLOR1);break;
            case 2:this.setStroke(model.COLOR2);this.setFill(null);break;
            case 3:this.setStroke(model.COLOR0);this.setFill(model.COLOR3);break;
            case 4:this.setStroke(model.COLOR0);this.setFill(model.COLOR4);break;
            default:this.setStroke(model.COLOR0);this.setFill(null);break;
        }
    }
    void randomWalk(){
        if(status==4)return;
        if(model.num1+model.num2==0) {
            view.pause.setDisable(true);
            view.infectDistance.setDisable(true);
            view.incubateDays.setDisable(true);
            view.speed.setDisable(true);
            view.probDeath.setDisable(true);
            view.spreadDays.setDisable(true);
            return;
        }
        if(view.start.getText()=="开始模拟"){
            //点击停止
            this.setVisible(false);
            return;
        } else if (view.pause.getText()=="继续模拟") {
            //点击暂停
            return;
        }
        //status:2特殊处理
        if(this.status==2){
            model.pool.forEach(person -> {
                if(person.status==0&&this.dist(person)<=model.infectDistance){
                    person.setStatus(1);
                    model.num1++;
                    model.num0--;
                }
                //突出
                if(this.getRadius()>1.5*RADIUS){
                    this.setRadius(.7*RADIUS);
                }else {
                    this.setRadius(this.getRadius()+1);
                }
            });
        }


        if(step% Model.DAY_STEPS==this.hashCode()%Model.DAY_STEPS){
            double dayX,dayY;
            dayX = this.getCenterX() +0.2*model.mapWidth - .4*model.mapWidth*Math.random();
            dayY = this.getCenterY() +0.2*model.mapHeight - .4*model.mapHeight*Math.random();

            dx=(dayX-this.getCenterX())/ Model.DAY_STEPS;
            dy=(dayY-this.getCenterY())/ Model.DAY_STEPS;
            if(status==1){
                this.day_infected++;
                if(this.day_infected>=model.incubate){
                    this.setStatus(2);
                    model.num1--;model.num2++;
                }
            } else if (this.status==2) {
                this.day_infected++;
                if(this.day_infected>=model.incubate+model.spreadDays){
                    if(Math.random()< model.probDeath){
                        this.setStatus(4);
                        model.num2--;model.num4++;
                    }else{
                        this.setStatus(3);
                        model.num2--;
                        model.num3++;
                    }
                }
            }
            update();
            if(step/Model.DAY_STEPS== model.day+1){
                model.day++;
                view.day.setText("day"+model.day);

                model.seriesHealthy.getData().add(new XYChart.Data(model.day,model.num0));
                model.seriesIncubate.getData().add(new XYChart.Data(model.day,model.num1));
                model.seriesInfecting.getData().add(new XYChart.Data(model.day,model.num2));
                model.seriesCure.getData().add(new XYChart.Data(model.day,model.num3));
                model.seriesDie.getData().add(new XYChart.Data(model.day,model.num4));
            }
            view.t0.setText(model.num0+"");
            view.t1.setText(model.num1+"");
            view.t2.setText(model.num2+"");
            view.t3.setText(model.num3+"");
            view.t4.setText(model.num4+"");
        }
        if(this.getCenterX()+dx<2*RADIUS||this.getCenterX()+dx+2*RADIUS>model.mapWidth){
            dx*=-1;
        }
        if(this.getCenterY()+dy<2*RADIUS||this.getCenterY()+dy+2*RADIUS>model.mapHeight){
            dy*=-1;
        }



        //走
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(1);
        fadeTransition.setDuration(Duration.millis(1000/ Model.DAY_STEPS/model.speed));
        fadeTransition.setDelay(Duration.millis(Math.random()/15));
        fadeTransition.setNode(this);

        fadeTransition.setOnFinished(event -> {
            this.setCenterX(this.getCenterX()+dx);
            this.setCenterY(this.getCenterY()+dy);
            this.step++;
            randomWalk();
        });
        fadeTransition.play();
    }

    private double dist(Person person) {
        return Math.sqrt(Math.pow(this.getCenterX()-person.getCenterX(),2)+Math.pow(this.getCenterY()-person.getCenterY(),2));
    }

    public Person(){
        this(0,model,view);
    }

}
