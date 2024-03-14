package com.example.puzzle_15;

public class Puzzle_15 {

    private int[][] gameField = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    public static class Coord {

        public int x;
        public int y;

        public Coord() {
            this(-1, -1);
        }

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isValid() {

            return x > -1 && x < 4 && y > -1 && y < 4;
        }
    }

    public Puzzle_15() {

        init();
    }

    public void init() {

        //TO DO: Home Work
    }

    public boolean isWin() {
        //TO DO: Home Work

        return false;
    }

    public int getValue(int x, int y) {

        return gameField[y][x];
    }

    public Coord findValue(int value) {

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (gameField[j][i] == value) {

                    return new Coord(j, i);
                }
            }
        }
        return new Coord();
    }

    public Coord go(int value) {

        Coord zeroCoord = findValue(0);
        Coord coord = findValue(value);

        if (Math.abs(zeroCoord.x - coord.x) + Math.abs(zeroCoord.y - coord.y) != 1) {

            return new Coord();
        }
        gameField[zeroCoord.x][zeroCoord.y] = value;
        gameField[coord.x][coord.y] = 0;

        return zeroCoord;
    }
}