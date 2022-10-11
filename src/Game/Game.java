package Game;

import Game.graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH * 3/4;
    public static final int SCALE = 4;
    public static final String TITLE = "Doom";
    public static final int FRAMES = 60;

    public boolean isRunning = false;

    public static final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    public static final int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    private Screen screen;

    public Game() {
        screen = new Screen(WIDTH, HEIGHT);
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
        final double nanosPerUpdate = 1000000000.0 / FRAMES;
        long lastTime = System.nanoTime();
        double unprocessedTime = 0;
        int frames = 0;
        int updates = 0;
        long frameCounter = System.currentTimeMillis();
        while (isRunning) {
            long currentTime = System.nanoTime();
            long elapsedTime = currentTime - lastTime;
            lastTime = currentTime;
            unprocessedTime += elapsedTime;
            if (unprocessedTime >= nanosPerUpdate) {
                unprocessedTime = 0;
                update();
                updates++;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - frameCounter >= 1000) {
                System.out.println("Frames: " + frames + ", Updates: " + updates);
                frames = 0;
                updates = 0;
                frameCounter += 1000;
            }
        }
        dispose();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
        screen.render();
        for (int i = 0; i <pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        g.dispose();
        bs.show();
    }

    public void update() {
        screen.update();
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
        frame.setTitle(TITLE);
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
