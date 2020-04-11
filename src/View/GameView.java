package View;

import javafx.scene.layout.VBox;

public class GameView extends VBox {
    private viewDelegate delegate;
    private int x , y ;

    public GameView(int x , int y){
        this.x = x;
        this.y = y;
    }

    public void init(){

    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setDelegate(viewDelegate delegate) {
        this.delegate = delegate;
    }

}
