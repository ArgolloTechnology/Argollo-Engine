package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Vector2D;

public class Sprite {
	
	private BufferedImage sprite;
	boolean spritesheet = false;
	public BufferedImage[] sprites;
	public float scale = 1f;
	public Vector2D size;
	
	public Sprite(String path, boolean spritesheet, float scale) {
		try {
			sprite = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.spritesheet = spritesheet;
		size = new Vector2D(sprite.getWidth(), sprite.getHeight());
		this.scale = scale;
	}
	public Sprite(String path, float scale) {
		try {
			sprite = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.spritesheet = false;
		size = new Vector2D(sprite.getWidth(), sprite.getHeight());
		this.scale = scale;
	}
	public BufferedImage[] getSprites(int size, int width, int height) {
	    sprites = new BufferedImage[size];
	    for (int i = 0; i < size; i++) {
	        int x = i * width;
	        int y = i * height;

	        // Certifique-se de que x + width e y + height estejam dentro dos limites da imagem original.
	        sprites[i] = getSprite(x, y, width, height);
	    }
	    return sprites;
	}

	public BufferedImage getSprite() {
	    return sprite;
	}

	public BufferedImage getSprite(int x, int y, int width, int height) {
	    // Certifique-se de que x + width e y + height estejam dentro dos limites da imagem original.
	    x = Math.min(x, sprite.getWidth() - width);
	    y = Math.min(y, sprite.getHeight() - height);

	    return sprite.getSubimage(x, y, width, height);
	}

}