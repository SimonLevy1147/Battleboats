import java.util.Scanner;
import java.util.Random;
public class Board {
    public Cell[][] gameBoard;
    public int m;
    public int n;
    public Boat[] Dock;
    public int turnCount;
    public boolean debug = false;

    public Board(int m, int n) { //constructor creates m high by n wide 2d array for the game board. Also initializes all cells as blanks.
        this.m = m; //height (rows)
        this.n = n; //width (columns)

        gameBoard = new Cell[m][n];
        for (int i = 0; (i < m); i++) {
            for (int j = 0; (j < n); j++) {
                gameBoard[i][j] = new Cell(j, i, '-'); //populates arrays with cell objects
            }

        }
        fleetCreator(m,n); //places boats
    }

    public static boolean orientationRandomizer() { //boolean to determine if a boat is placed horizontally or vertically. false is horizontal, true is vertical.
        boolean vertical = false;
        if (Math.random()*2 >=1)
            vertical = true;
        return vertical;
    }

    public Boat[] fleetCreator(int m, int n) {//creates correct amount of boats of certain sizes depending on size of game board
        if (m == 3 || n == 3) {
            Dock = new Boat[1]; //1d array of boat objects for storage
            boolean vertical = orientationRandomizer();
            Dock[0] = placeBoats(2, vertical, findCells(2, vertical, boatLocator(2, vertical)));
            return Dock;
        }//3*3

        else if (3 < m && m <= 4 || 3 < n && n <= 4){
            Dock = new Boat[2];
            boolean vertical = orientationRandomizer();
            Dock[0] = placeBoats(2, vertical, findCells(2, vertical, boatLocator(2, vertical)));
            vertical = orientationRandomizer();
            Dock[1] = placeBoats(3, vertical, findCells(3, vertical, boatLocator(3, vertical)));
            return Dock;
        }//4*4

        else if (4 < m && m <= 6 || 4 < n && n <= 6){
            Dock = new Boat[3];
            boolean vertical = orientationRandomizer();
            Dock[0] = placeBoats(2, vertical, findCells(2, vertical, boatLocator(2, vertical)));
            vertical = orientationRandomizer();
            Dock[1] = placeBoats(3, vertical, findCells(3, vertical, boatLocator(3, vertical)));
            vertical = orientationRandomizer();
            Dock[2] = placeBoats(3, vertical, findCells(3, vertical, boatLocator(3, vertical)));
            for (int i = 0; i < Dock.length; i++)
                System.out.println(Dock[i]);
            return Dock;
        }//6*6

        else if (6 < m && m <= 8 || 6 < n && n <= 8){
            Dock = new Boat[4];
            boolean vertical = orientationRandomizer();
            Dock[0] = placeBoats(2, vertical, findCells(2, vertical, boatLocator(2, vertical)));
            vertical = orientationRandomizer();
            Dock[1] = placeBoats(3, vertical, findCells(3, vertical, boatLocator(3, vertical)));
            vertical = orientationRandomizer();
            Dock[2] = placeBoats(3, vertical, findCells(3, vertical, boatLocator(3, vertical)));
            vertical = orientationRandomizer();
            Dock[3] = placeBoats(4, vertical, findCells(4, vertical, boatLocator(4, vertical)));
            return Dock;
        }//8*8

        else {
            Dock = new Boat[5];
            boolean vertical = orientationRandomizer();
            Dock[0] = placeBoats(2, vertical, findCells(2, vertical, boatLocator(2, vertical)));
            vertical = orientationRandomizer();
            Dock[1] = placeBoats(3, vertical, findCells(3, vertical, boatLocator(3, vertical)));
            vertical = orientationRandomizer();
            Dock[2] = placeBoats(3, vertical, findCells(3, vertical, boatLocator(3, vertical)));
            vertical = orientationRandomizer();
            Dock[3] = placeBoats(4, vertical, findCells(4, vertical, boatLocator(4, vertical)));
            vertical = orientationRandomizer();
            Dock[4] = placeBoats(5, vertical, findCells(5, vertical, boatLocator(5, vertical)));
            return Dock;
        }//10*10
    }

    public int[] boatLocator(int Length, boolean vertical) {//returns integer array (x and y coordinates) for a boat to be placed.
        Random r = new Random();
        int[] pos = new int[2];
        if (vertical) {
            int y = r.nextInt(m);
            int x = r.nextInt(n - Length + 1);
            pos[0] = x;
            pos[1] = y;
        } else {
            int y = r.nextInt(m - Length + 1);
            int x = r.nextInt(n);
            pos[0] = x;
            pos[1] = y;
        }
        return pos;
    }

        public boolean isAvailable(int Length, boolean vertical, int[] pos) { //checks all cells in boat length away from origin coordinate (see boatLocator) to ensure no overlap
            int x = pos[0], y = pos[1];
            if (vertical) {
                for (int i = 0; i < Length; i++)
                    if (gameBoard[y][x + i].get_status() != '-') return false;
            } else {
                for (int i = 0; i < Length; i++)
                    if (gameBoard[y+i][x].get_status() != '-') return false;
            }
            return true;
        }

        public Cell[] findCells(int length, boolean vertical, int[] pos) { //creates array of cell objects used during creation of boat objects to store location and status
        Cell[] cells = new Cell[length];
        int x = pos[0], y = pos[1];

        if (vertical)
            for (int i = 0; i < length; i++) cells[i] = gameBoard[y][x + i];
            else
            for (int i = 0; i < length; i++) cells[i] = gameBoard[y + i][x];

            return cells;
        }

    public Boat placeBoats(int boatSize, boolean vertical, Cell[] origin) { //uses helper functions to create boat object
        while (true) {
            if (vertical) {
                int[] startingPos = boatLocator(boatSize, vertical);
                if (isAvailable(boatSize, vertical, startingPos)) {
                    Cell[] cells = findCells(boatSize, vertical, startingPos);
                    Boat boatyMcBoatFace = new Boat(boatSize, vertical, cells);
                    for (int i = 0; i < boatSize; i++) cells[i].set_status('B');
                    return boatyMcBoatFace; //has the word "boat" lost all meaning yet?
                }
            } else {
                int[] startingPos = boatLocator(boatSize, vertical);
                if (isAvailable(boatSize, vertical, startingPos)) {
                    Cell[] cells = findCells(boatSize, vertical, startingPos);
                    Boat boatyMcBoatFace = new Boat(boatSize, vertical, cells);
                    for (int i = 0; i < boatSize; i++) cells[i].set_status('B');
                    return boatyMcBoatFace;
                }
            }
        }
    }

    public int fire(int x, int y) { //checks if target is in bounds and status of target cell. If already guessed or not in bounds, penalty of losing a turn. Otherwise if a boat is present, checks if that boat is sunk
        if ((x > n-1 || y > m-1) || (x < 0 || y < 0)){
            System.out.println("Penalty, out of bounds");
            turnCount ++;
            System.out.println("Your turn is forfeit");
            turnCount ++;
            return turnCount;
        }
        else if (gameBoard[x][y].get_status() == '-') {
            System.out.println("miss");
            gameBoard[x][y].set_status('M');
            turnCount ++;

        } else if (gameBoard[x][y].get_status() == 'B') {
            System.out.println("hit");
            gameBoard[x][y].set_status('H');
            if (checkSunk(x, y))
                System.out.println("Sunk");
            turnCount ++;

        } else {
            System.out.println("Penalty, you've already fired there");
            turnCount ++;
            System.out.println("Your turn is forfeit");
            turnCount ++;

        }
        return turnCount;
    }

    public boolean checkSunk(int x, int y) { //checks if the boat that was hit in fire (See above) has any remaning cells that have not been hit.
        for (int i = 0; i < Dock.length; i++) {
            for(int j = 0; j < Dock[i].getSize(); j++) {
                if (Dock[i].getLocation()[j].equals(gameBoard[x][y])) {
                    for (int k = 0; k < Dock[i].getSize(); k++) {
                        if (Dock[i].getLocation()[k].get_status() == 'H')
                            return false;
                        else return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkAllSunk() { //checks to see if all boats in the array (see fleetCreator) are sunk
        for (int i = 0; i < Dock.length; i++) {
            for(int j = 0; j < Dock[i].getSize(); j++) {
                for (int k = 0; k < Dock[i].getSize(); k++) {
                    if (Dock[i].getLocation()[k].get_status() == 'B') {
                        return false;
                    }
                    }

            }
            }
        System.out.println("All boats sunk, congratulations!");
        return true;
    }

    public String toString() {//if in debug mode, prints entire board including boat locations. If not in debug mode, only displays cells guessed and blanks to avoid penalties.
        String debugBoard = "";
        if (debug ==true) {
            for (int i = 0; (i < m); i++) {
                for (int j = 0; (j < n); j++) {
                    debugBoard += gameBoard[i][j].get_status();
                }
                debugBoard += "\n";
            }
        } else {
            for (int i = 0; (i < m); i++) {
                for (int j = 0; (j < n); j++) {
                    if (gameBoard[i][j].get_status() == 'B')
                        debugBoard += '-';
                    else debugBoard += gameBoard[i][j].get_status();
                }
                debugBoard += "\n";
            }
        }
        return debugBoard;
    }

    public void gameLoop() { //uses while loop from checkSunk (See above) and prompts player for use of power-ups (see missile, drone, powerupHelper) or firing every turn until all boats are sunk. Also displays game board depending on if in debug mode (see toString)
        Scanner s =new Scanner(System.in);
        debug = false;
        System.out.println("Run game in debug mode? Enter 'yes' for debug.");
        String rc = s.nextLine();
        if (rc.equals("yes"))
            debug = true;
        while (checkAllSunk() == false) {
            if (debug == true)
                System.out.println(this);
            powerupHelper();
            System.out.println("Enter an x and y coordinate to fire on.");
            int x = s.nextInt();
            int y = s.nextInt();
            fire(x, y);
            if (debug == false)
                System.out.println(this);

        }// while loop
    }

    public void missile(int x, int y) { //takes in an x and y coordinate and hits a 3x3 cell area centered around the target. If the surrounding cells are off the board, they are simply ignored. No penalty for guessing a cell already guessed
        for (int i = x-1; i < x+2; i++) {
            for (int j = y-1; j < y+2; j++) {
                if (((i >= 0) && (i < n)) && ((j >=0) && (j < m))) {
                    if (gameBoard[i][j].get_status() == '-')
                        gameBoard[i][j].set_status('M');
                    else if (gameBoard[i][j].get_status() == 'B')
                        gameBoard[i][j].set_status('H');
                }
            }
        }

    }

    public int drone(int target, boolean vertical) { //returns the number of boat cells (including ones already hit) in the user's column or row of choice
        int boatTiles = 0;
        if (vertical ==false) {
            for (int i = 0; i < m; i++) {
                if (gameBoard[target][i].get_status() == 'B' || gameBoard[target][i].get_status() == 'H')
                boatTiles ++;
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (gameBoard[i][target].get_status() == 'B' || gameBoard[i][target].get_status() == 'H')
                    boatTiles ++;

            }
        }
        return boatTiles;
    }

    public void powerupHelper() { //Prompts user for power-up use. Alerts player until valid input is entered. Calls missile or drone functions after checking for valid targets.
        Scanner s = new Scanner(System.in);
        System.out.println("Use a power-up?");
        String rc = s.nextLine();
        if (rc.equals("yes")) {
            System.out.println("Would you like to use 'missile' or 'drone'?");
            rc = s.nextLine();
            if (rc.equals("missile")) {
                System.out.println("Please enter valid coordinates");
                int x = s.nextInt();
                int y = s.nextInt();
                while ((x > n - 1 || y > m - 1) || (x < 0 || y < 0)) {
                    System.out.println("Please enter valid coordinates");
                    x = s.nextInt();
                    y = s.nextInt();
                }
                missile(x, y);
                turnCount++;
            } else if (rc.equals("drone")) {
                System.out.println("Would you like to scan a 'row' or a 'column'?");
                while (!(rc.equals("row") || rc.equals("column"))) {
                    System.out.println("Please enter 'row' or 'column'");
                    rc = s.nextLine();
                }
                if (rc.equals("row")) {
                    System.out.println("Which row from 0 to " + (m - 1) + "?");
                    int row = s.nextInt();
                    System.out.println(drone(row, false));

                } else if (rc.equals("column")) {
                    System.out.println("Which column from 0 to " + (n - 1) + "?");
                    int col = s.nextInt();
                    System.out.println(drone(col, true));

                }
            }
        }
    }
}
