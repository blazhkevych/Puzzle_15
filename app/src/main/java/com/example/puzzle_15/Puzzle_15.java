package com.example.puzzle_15;

public class Puzzle_15 {

    private int[][] board = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    public static class Coordinate {
        public int x;
        public int y;

        public Coordinate() {
            this(-1, -1);
        }

        public Coordinate(int x, int y) {
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

    private void init() {
        // TODO: Homework. Для перемешивания и всего прочьего.
    }

    public boolean isWin() {
        // TODO: Homework. Проверка на победу.
        int number = 1;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (board[y][x] != number) {
                    return false;
                }
                number++;
                if (number == 16) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getNumber(Coordinate coordinate) {
        if (coordinate.isValid()) {
            return board[coordinate.y][coordinate.x];
        }
        return -1;
    }

    public Coordinate findCoordinate(int number) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (board[y][x] == number) {
                    return new Coordinate(x, y); // x - это столбец, y - это строка.
                }
            }
        }
        return new Coordinate();
    }
}
