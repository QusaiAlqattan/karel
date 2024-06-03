import stanford.karel.SuperKarel;


public class Homework extends SuperKarel {

    private int w;
    private int h;
    private int total_count;
    private int state;

    /* You fill the code here */
    public void run() {
        w = 1;
        h = 1;
        total_count = 0;

        know_world();
        state = select_state();
        System.out.println("w: " + w);
        System.out.println("h: " + h);
        System.out.println(state);

        // divide based on the state
        if (state == 2){
            putBeeper();
            while(frontIsClear()){
                r_zig_zag();
            }
            turnRight();
            while(frontIsClear()){
                move();
            }
            putBeeper();
            turnRight();
            while(frontIsClear()){
                l_zig_zag();
            }
            turnAround();
            while(frontIsClear()){
                move();
            }
        }
    }

    // know the world's dimensions
    private void know_world(){
        while(!frontIsBlocked()){
            move();
            w++;
            total_count++;
            System.out.println(total_count);
        }
        turnLeft();
        while(!frontIsBlocked()){
            move();
            h++;
            total_count++;
            System.out.println(total_count);
        }
        turnLeft();
    }
    
    // find state
    private int select_state(){
        // state = 3 (w != h) unless one of the conditions is satisfied
        int state = 3;
        if (h <= 2 && w <= 2){
            state = 1;
        } else if (w == h) {
            state = 2;
        } else if (w <= 2 || h <= 2) {
            if (w > h){
                if(w % 4 == 0){
                    state = 4;
                } else if (w % 3 == 0) {
                    state = 5;
                } else if (w % 2 == 0) {
                    state = 6;
                }else{
                    state = 1;
                }
            }else{
                if(h % 4 == 0){
                    state = 7;
                } else if (h % 3 == 0) {
                    state = 8;
                } else if (h % 2 == 0) {
                    state = 9;
                }else{
                    state = 1;
                }
            }
        }

        return state;
    }

    private void r_zig_zag(){
        move();
        turnLeft();
        move();
        putBeeper();
        turnRight();
    }

    private void l_zig_zag(){
        move();
        turnRight();
        move();
        putBeeper();
        turnLeft();
    }
}
