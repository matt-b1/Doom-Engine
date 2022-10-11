package Game.graphics;

import java.util.Random;

public class Screen extends Bitmap{
    public Random r = new Random();

    public Screen (int width, int height) {
        super(width, height);
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = r.nextInt(0xfffffff);
        }
    }

    public void render() {

    }

    public void update() {

    }
}
