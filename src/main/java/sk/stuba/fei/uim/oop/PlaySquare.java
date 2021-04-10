package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class PlaySquare {
    private boolean visited;
    private boolean wall;
     ArrayList<PlaySquare> listOfNeighs;
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
        var lastNeighs = new ArrayList<>(listOfNeighs);
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
               //neigh.remove(this);
            }
            this.listOfNeighs=lastNeighs;
            this.listOfNeighs.remove(neigh); //vymaže suseda cez ktoreho isiel
            return neigh.randomNeigh();
        }
        this.listOfNeighs=lastNeighs;
       /* for(var n:listOfNeighs){
            n.unVisit();
            break;
        }*/
        return this;
    }
    public boolean wasVisited(){
        return visited;
    }
    public void remove(PlaySquare sq){
        listOfNeighs.remove(sq);
    }
    public boolean connectWay(){ //ked nejaky susedov sused je cesta spoji ich
        for(var n:listOfNeighs){
            int ways=0;
            //n má práve jedneho suseda ktorý je way
            for(var nn:n.listOfNeighs){
                if(!nn.isWall()){
                    ways++;
                }
            }
            if(ways==1){
                n.setWay();
                return true;
            }
        }
        return false;
    }
    public void connectWay1(){ //ked nejaky susedov sused je cesta spoji ich
        boolean done=false;
        for(var n:listOfNeighs){
            for(var nn:n.listOfNeighs){
                if (!nn.isWall()){
                    n.setWay();
                    done=true;
                    break;
                }
            }
            if (done){
                break;
            }
        }
    }
    public boolean isWall(){
        return wall;
    }
    public void visit(){
        this.visited=true;
    }
    public void unVisit(){
        this.visited=false;
    }
    public void setFin(){
        this.fin=true;
    }
    public boolean isFin(){
        return fin;
    }
    public void setWay(){
        wall=false;
        for(var neigh:listOfNeighs){
            neigh.visit();
        }
    }

}
