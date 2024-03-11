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
        // TODO: Homework. Проверка на победу. Перепроверить этот код(не тестировал).
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

    public boolean move(Coordinate from, Coordinate to) {
        if (!from.isValid() || !to.isValid()) {
            return false;
        }
        if (from.x == to.x && from.y == to.y) {
            return false;
        }
        if (from.x == to.x) {
            if (from.y > to.y) {
                for (int y = from.y; y > to.y; y--) {
                    board[y][from.x] = board[y - 1][from.x];
                }
            } else {
                for (int y = from.y; y < to.y; y++) {
                    board[y][from.x] = board[y + 1][from.x];
                }
            }
            board[to.y][to.x] = 0;
            return true;
        }
        if (from.y == to.y) {
            if (from.x > to.x) {
                for (int x = from.x; x > to.x; x--) {
                    board[from.y][x] = board[from.y][x - 1];
                }
            } else {
                for (int x = from.x; x < to.x; x++) {
                    board[from.y][x] = board[from.y][x + 1];
                }
            }
            board[to.y][to.x] = 0;
            return true;
        }
        return false;
    }

}
