package com.example.puzzle_15;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;


@SuppressLint("MissingInflatedId")
public class MainActivity extends AppCompatActivity {

    Puzzle_15 game = new Puzzle_15();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout grid = findViewById(R.id.gridLayout);
        //if (grid == null) return;

        //grid.removeAllViews();
        game.init();

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {

                int value = game.getValue(j, i);
                if (value == 0) continue;

                Button btn = new Button(this);
                btn.setText(Integer.toString(value));
                btn.setHeight(300);

                GridLayout.Spec row = GridLayout.spec(i);
                GridLayout.Spec col = GridLayout.spec(j);

                GridLayout.LayoutParams lp = new GridLayout.LayoutParams(row, col);
                lp.setMargins(10, 10, 10, 10);
                grid.addView(btn, lp);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Button btn = (Button) view;
                        int value = Integer.parseInt((String) btn.getText());

                        Puzzle_15.Coord coord = game.go(value);

                        if (coord.isValid()) {

                            GridLayout.Spec row = GridLayout.spec(coord.x);
                            GridLayout.Spec col = GridLayout.spec(coord.y);

                            GridLayout.LayoutParams lp = new GridLayout.LayoutParams(row, col);
                            btn.setLayoutParams(lp);

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