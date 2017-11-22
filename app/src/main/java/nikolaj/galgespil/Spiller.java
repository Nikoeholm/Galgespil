package nikolaj.galgespil;

public class Spiller {
    String initialer;
    int score;

    public Spiller(String initialer, int score){
        this.initialer = initialer;
        this.score = score;
    }
    public String getInitialer(){
        return initialer.toString();
    }
    public  int getScore(){
        return  score;
    }
    @Override
    public String toString(){
        return initialer;
    }
}

