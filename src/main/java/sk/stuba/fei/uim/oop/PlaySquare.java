package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class PlaySquare {
    private boolean visited;
    private boolean wall;
    private ArrayList<PlaySquare> listOfNeighs;
    private boolean fin;
    //int x;
    //int y;

    public PlaySquare() {
        listOfNeighs=new ArrayList<>();
        visited=false;
        wall=true;
        fin=false;
    }

    public void setNeigh(ArrayList<PlaySquare> listOfNeighs){
        this.listOfNeighs=listOfNeighs;
    }
    public PlaySquare randomNeigh(){
        this.visit();
        wall=false;
        int ind=0;
        //Vyberie random not visited suseda
        while(listOfNeighs.size()!=0){
            ind =(int)(Math.random()*listOfNeighs.size());
            if(listOfNeighs.get(ind).visited){
                listOfNeighs.remove(ind);
            }
            else{
                break;
            }
        }
        if(listOfNeighs.size()!=0){
            PlaySquare neigh=listOfNeighs.get(ind);
            for(var n:listOfNeighs){
                n.visit();
               neigh.remove(this);
            }
            return neigh.randomNeigh();
        }
        return this;
    }
    public boolean wasVisited(){
        return visited;
    }
    public void remove(PlaySquare sq){
        listOfNeighs.remove(sq);
    }
    public int getS(){
        return listOfNeighs.size();
    }
    public boolean isWall(){
        return wall;
    }
    public void visit(){
        this.visited=true;
    }
    public void setFin(){
        this.fin=true;
    }
    public boolean isFin(){
        return fin;
    }

}
