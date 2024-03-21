package com.example.puzzle_15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the game logic for the game "15 Puzzle".
 * It contains methods for initializing the game, checking the win condition, and moving the tiles.
 */
public class Puzzle_15 {

    // The game field represented as a 2D array.
    private int[][] gameField = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    /**
     * This class represents a coordinate on the game field.
     */
    public static class Coord {

        public int x;
        public int y;

        /**
         * Default constructor. Initializes the coordinate to (-1, -1).
         */
        public Coord() {
            this(-1, -1);
        }

        /**
         * Constructor with parameters. Initializes the coordinate to the given values.
         *
         * @param x The x-coordinate.
         * @param y The y-coordinate.
         */
        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Checks if the coordinate is valid (i.e., within the game field).
         *
         * @return true if the coordinate is valid, false otherwise.
         */
        public boolean isValid() {
            return x > -1 && x < 4 && y > -1 && y < 4;
        }
    }

    /**
     * Constructor for the Puzzle_15 class. Initializes the game field.
     */
    public Puzzle_15() {
        init();
    }

    /**
     * Initializes the game field with a random arrangement of tiles.
     */
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

    /**
     * Checks if the current arrangement of tiles is a winning arrangement.
     *
     * @return true if the arrangement is winning, false otherwise.
     */
    public boolean isWin() {
        for (int i = 0; i < 16; i++) {
            if (gameField[i / 4][i % 4] != i + 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the value at the given coordinates on the game field.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The value at the given coordinates.
     */
    public int getValue(int x, int y) {
        return gameField[y][x];
    }

    /**
     * Finds the coordinates of the given value on the game field.
     *
     * @param value The value to find.
     * @return The coordinates of the value.
     */
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

    /**
     * Moves the tile with the given value to the empty space, if the tile is adjacent to the empty space.
     *
     * @param value The value of the tile to move.
     * @return The new coordinates of the empty space.
     */
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