import stanford.karel.SuperKarel;

public class Homework extends SuperKarel {

    private int w;
    private int h;
    private int total_count;
    private int jump;
    private boolean is_extra;

    /* You fill the code here */
    public void run() {
        setBeepersInBag(1000);

        w = 1;
        h = 1;
        total_count = 0;
        is_extra = false;

        while (!facingEast()){
            turnRight();
        }

        know_world();
        return_to_origin();
        int state = select_state();
        System.out.println("w: " + w);
        System.out.println("h: " + h);
        System.out.print("jump: ");
        System.out.println(jump);
        System.out.print("state: ");
        System.out.println(state);

        // divide based on the state
        if (state == 2){
            // w is even and h is odd
            double_lines('w');
            single_lines('h');
        } else if (state == 3) {
            // w is odd h is even
            single_lines('w');
            double_lines('h');
        } else if ( state == 10) {
            // w and h are even
            double_lines('w');
            double_lines('h');
        } else if (state == 11) {
            // w and h are odd
            single_lines('w');
            single_lines('h');
        } else if (state == 4 || state == 5 || state == 6) {
            // long horizontal
            with_jump('w');
            if (h == 2){
                turnLeft();
                move_n_no_beeper();
                turnRight();
                with_jump('w');
            }
        } else if (state == 7 || state == 8 || state == 9) {
            // long vertical
            turnLeft();
            with_jump('h');
            if (w == 2){
                move_n_no_beeper();
                turnLeft();
                with_jump('h');
            }
        } else if (w == 2 & h == 2) {
            zig_zag();
        } else {
            System.out.println(("Karel: Sorry, can't be divided"));
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
                if((w - 3 != 0) && ((w - 3) % 4 == 0) || ((w - 4 != 0) && (w - 4) % 4 == 0)){
                    state = 4;
                    jump = (w - 3) / 4;
                    if ((w - 4 != 0) && (w - 4) % 4 == 0){
                        is_extra = true;
                    }
                }else if (((w - 2) % 3 == 0) || ((w - 3 != 0) &&  (w - 3) % 3 == 0)) {
                    state = 5;
                    jump = (w - 2) / 3;
                    if ((w - 3 != 0) &&  (w - 3) % 3 == 0){
                        is_extra = true;
                    }
                }else if (((w - 1) % 2 == 0) || ((w - 2) % 2 == 0)) {
                    state = 6;
                    jump = (w - 1) / 2;
                    if ((w - 2) % 2 == 0){
                        is_extra = true;
                    }
                }else{
                    state = 1;
                }
            }else{
                if((h - 3 != 0) && ((h - 3) % 4 == 0) || ((h - 4 != 0) && (h - 4) % 4 == 0)){
                    state = 7;
                    jump = (h - 3) / 4;
                    if ((h - 4 != 0) && (h - 4) % 4 == 0){
                        is_extra = true;
                    }
                }else if (((h - 2) % 3 == 0) || ((h - 3 != 0) &&  (h - 3) % 3 == 0)) {
                    state = 8;
                    jump = (h - 2) / 3;
                    if ((h - 3 != 0) &&  (h - 3) % 3 == 0){
                        is_extra = true;
                    }
                }else if (((h - 1) % 2 == 0) || ((h - 2) % 2 == 0)) {
                    state = 9;
                    jump = (h - 1) / 2;
                    if ((h - 2) % 2 == 0){
                        is_extra = true;
                    }
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
    private void while_n_no_beeper(char z){
        if (z == 'e'){
            while(frontIsClear())
                move_n_no_beeper();
        } else if ( z == 'w') {
            int x = 1;
            while( x++ < w/2 + 1)
                move_n_no_beeper();
        } else if ( z == 'h') {
            int y = 1;
            while (y++ < h/2 + 1)
                move_n_no_beeper();
        }
    }

    // while with beeper
    private  void while_n_beeper(char z){
        if (z == 'e'){
            while(frontIsClear())
                move_n_beeper();
        } else if ( z == 'w') {
            int x = 1;
            while( x++ > w/2 + 1)
                move_n_beeper();
        } else if ( z == 'h') {
            int y = 1;
            while (y++ > h/2 + 1)
                move_n_beeper();
        }
    }

    // return to origin
    private void return_to_origin(){
        while(!facingSouth()){
            turnLeft();
        }
        while_n_no_beeper('e');
        turnRight();
        while_n_no_beeper('e');
        turnAround();
    }

    // double line
    private void double_lines(char z){
        if (z == 'w'){
            while_n_no_beeper('w');
            turnLeft();
            putBeeper();
            while_n_beeper('e');
            turnLeft();
            move_n_beeper();
            turnLeft();
            while_n_beeper('e');
        } else if (z == 'h') {
            turnLeft();
            while_n_no_beeper('h');
            putBeeper();
            turnRight();
            while_n_beeper('e');
            turnRight();
            move_n_beeper();
            turnRight();
            while_n_beeper('e');
        }
        return_to_origin();
    }

    // single line
    private void single_lines(char z){
        if (z == 'w'){
            while_n_no_beeper('w');
            turnLeft();
            putBeeper();
            while_n_beeper('e');
            return_to_origin();
        } else if (z == 'h') {
            turnLeft();
            while_n_no_beeper('h');
            turnRight();
            putBeeper();
            while_n_beeper('e');
            return_to_origin();
        }
        return_to_origin();
    }

    // fill with jump
    private void with_jump(char z){
        int x = 1;
        int a;
        if ( z == 'w') {
            a = w;
        }else{
            a = h;
        }
        while (x <= (a - (jump + 1 ))) {
            int count = 0;
            while (count++ < jump) {
                move_n_no_beeper();
                x++;
            }
            putBeeper();
            move_n_no_beeper();
            x++;
        }
        if(is_extra){
            int cout = 0;
            while (cout++ < jump)
                move_n_no_beeper();
            putBeeper();
        }
        return_to_origin();
    }

    // zig zag for 2x2
    private void zig_zag(){
        putBeeper();
        move_n_no_beeper();
        turnLeft();
        move_n_beeper();
        return_to_origin();
    }
}