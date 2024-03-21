package com.example.puzzle_15;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

@SuppressLint("MissingInflatedId")
/**
 * Moves the tile with the given value to the empty space, if the tile is adjacent to the empty space.
 * @param value The value of the tile to move.
 * @return The new coordinates of the empty space.
 */
public class MainActivity extends AppCompatActivity {
    // The game logic for the "15 Puzzle" game.
    Puzzle_15 game = new Puzzle_15();

    /**
     * This method is called when the activity is starting.
     * It initializes the game and sets up the user interface.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The GridLayout for the game board.
        GridLayout grid = findViewById(R.id.gridLayout);

        // Initialize the game.
        game.init();

        // Set up the game board.
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {

                // Get the value of the tile at the current position.
                int value = game.getValue(j, i);
                if (value == 0) continue;

                // Create a new button for the tile.
                Button btn = new Button(this);
                btn.setText(Integer.toString(value));
                btn.setHeight(300);

                // Set the position of the button in the GridLayout.
                GridLayout.Spec row = GridLayout.spec(i);
                GridLayout.Spec col = GridLayout.spec(j);

                // Set the layout parameters for the button.
                GridLayout.LayoutParams lp = new GridLayout.LayoutParams(row, col);
                lp.setMargins(10, 10, 10, 10);
                grid.addView(btn, lp);

                // Set the click listener for the button.
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Get the clicked button.
                        Button btn = (Button) view;
                        // Get the value of the clicked button.
                        int value = Integer.parseInt((String) btn.getText());

                        // Move the clicked tile to the empty space.
                        Puzzle_15.Coord coord = game.go(value);

                        // If the move was valid, update the position of the button.
                        if (coord.isValid()) {

                            // Set the new position of the button in the GridLayout.
                            GridLayout.Spec row = GridLayout.spec(coord.x);
                            GridLayout.Spec col = GridLayout.spec(coord.y);

                            // Set the new layout parameters for the button.
                            GridLayout.LayoutParams lp = new GridLayout.LayoutParams(row, col);
                            lp.setMargins(10, 10, 10, 10);
                            btn.setLayoutParams(lp);

                            // If the game is won, show a toast message.
                            if (game.isWin()) {
                                Toast.makeText(MainActivity.this, "You win!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        }
    }
}