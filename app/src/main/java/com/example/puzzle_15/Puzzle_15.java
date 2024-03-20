package com.example.puzzle_15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gameField[i][j] = numbers.get(i * 4 + j);
            }
        }
    }

    public boolean isWin() {
        for (int i = 0; i < 16; i++) {
            if (gameField[i / 4][i % 4] != i + 1) {
                return false;
            }
        }
        return true;
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

    // TODO: В методе go() вы меняете местами выбранное число и ноль, но не проверяете, можно ли это сделать.
    // В игре "Пятнашки" можно менять местами только соседние числа.
    public Coord go(int value) { //
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