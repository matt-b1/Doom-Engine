package Game;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH * 3/4;
    public static final int SCALE = 4;
    public static final String TITLE = "3D";

    public boolean isRunning = false;

    public Game() {

    }

    public void start(){
        if (isRunning) {
            return;
        }
        isRunning = true;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (isRunning) {
            render();
            update();
        }
        dispose();
    }

    public void render() {

    }

    public void update() {

    }

    public void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
    }

    public void dispose() {
        System.exit(0);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Game game = new Game();
        Dimension d = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        game.setMinimumSize(d);
        game.setMaximumSize(d);
        game.setPreferredSize(d);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.start();
    }
}
