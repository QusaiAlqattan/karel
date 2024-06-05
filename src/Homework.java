import stanford.karel.SuperKarel;


public class Homework extends SuperKarel {

    private int w;
    private int h;
    private int total_count;

    /* You fill the code here */
    public void run() {
        w = 1;
        h = 1;
        total_count = 0;

        know_world();
        int state = select_state();
        System.out.println("w: " + w);
        System.out.println("h: " + h);
        System.out.println(state);


        // divide based on the state
        if (state == 2){
            // x
            while_n_no_beeper(2);
            move_n_beeper();
            turnLeft();
            while_n_beeper();
            turnLeft();
            move_n_beeper();
            turnLeft();
            while_n_beeper();
            turnAround();
            while_n_no_beeper(3);
            turnLeft();
            while_n_beeper();
            turnAround();
            // y
            while_n_beeper();
            turnLeft();
            while_n_no_beeper(1);
        } else if (state == 3) {
            // x
            while_n_no_beeper(2);
            putBeeper();
            turnLeft();
            while_n_beeper();
            turnAround();
            // y
            while_n_no_beeper(3);
            turnRight();
            while_n_beeper();
            turnLeft();
            move_n_beeper();
            turnLeft();
            while_n_beeper();
            turnLeft();
            move_n_beeper();
            turnLeft();
            while_n_beeper();
            turnRight();
            while_n_no_beeper(1);
            turnRight();
            while_n_no_beeper(1);
        } else if ( state == 10) {
            // x
            while_n_no_beeper(2);
            move_n_beeper();
            turnLeft();
            while_n_beeper();
            turnLeft();
            move_n_beeper();
            turnLeft();
            while_n_beeper();
            turnAround();
            // y
            while_n_no_beeper(3);
            turnRight();
            while_n_beeper();
            turnLeft();
            move_n_beeper();
            turnLeft();
            while_n_beeper();
            turnLeft();
            move_n_beeper();
            turnLeft();
            move_n_beeper();
            move_n_beeper();
            turnLeft();
            while_n_no_beeper(1);
            turnRight();
            while_n_no_beeper(1);
        }
    }

    // know the world's dimensions
    private void know_world(){
        while(!frontIsBlocked()){
            move();
            System.out.println(++total_count);
            w++;
        }
        turnLeft();
        while(!frontIsBlocked()){
            move();
            System.out.println(++total_count);
            h++;
        }
        turnLeft();
    }

    // find state
    private int select_state(){
        int state = 11;
        if (h <= 2 && w <= 2){
            state = 1;
        }else if (w <= 2 || h <= 2) {
            if (w > h){
                if(w % 4 == 0){
                    state = 4;
                }else if (w % 3 == 0) {
                    state = 5;
                }else if (w % 2 == 0) {
                    state = 6;
                }else{
                    state = 1;
                }
            }else{
                if(h % 4 == 0){
                    state = 7;
                }else if (h % 3 == 0) {
                    state = 8;
                }else if (h % 2 == 0) {
                    state = 9;
                }else{
                    state = 1;
                }
            }
        }else if (w % 2 == 0 && h % 2 == 0) {
            state = 10;
        }else if (h % 2 == 0) {
            state = 3;
        }else if (w % 2 == 0) {
            state = 2;
        }
        return state;
    }

    // move and put beeper
    private void move_n_beeper(){
        move();
        System.out.println(++total_count);
        if (noBeepersPresent())
            putBeeper();
    }

    // move and no beeper
    private void move_n_no_beeper(){
        move();
        System.out.println(++total_count);
    }

    // while with no beeper
    private void while_n_no_beeper(int z){
        if (z == 1){
            while(frontIsClear())
                move_n_no_beeper();
        } else if ( z == 2) {
            int x = w;
            while( x-- > w/2 + 1)
                move_n_no_beeper();
        } else if ( z == 3) {
            int y = h;
            while (y-- > h/2 + 1)
                move_n_no_beeper();
        }
    }

    // while with beeper
    private  void while_n_beeper(){
        while(frontIsClear())
            move_n_beeper();
    }
//    private void r_zig_zag(){
//        move();
//        System.out.println(++total_count);
//        turnLeft();
//        move();
//        System.out.println(++total_count);
//        if (noBeepersPresent())
//        putBeeper();
//        turnRight();
//    }
//
//    private void l_zig_zag(){
//        move();
//        System.out.println(++total_count);
//        turnRight();
//        move();
//        System.out.println(++total_count);
//        if (noBeepersPresent())
//        putBeeper();
//        turnLeft();
//    }
}