package sk.stuba.fei.uim.oop;

public class PlaySquare {
    private boolean wall;
    private boolean fin;

    public PlaySquare() {
        wall=true;
        fin=false;
    }

    public boolean isWall(){
        return wall;
    }
    public void setFin(){
        this.fin=true;
    }
    public void setWay(){
        this.wall=false;
    }
    public boolean isFin(){
        return fin;
    }

}
