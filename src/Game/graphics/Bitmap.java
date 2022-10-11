package Game.graphics;

public class Bitmap {
    public int width;
    public int height;
    public int[] pixels;

    public Bitmap(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void render() {

    }
}
