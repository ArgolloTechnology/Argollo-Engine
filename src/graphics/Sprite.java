package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Vector2D;

public class Sprite {
	
	private BufferedImage sprite;
	boolean spritesheet = false;
	
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
	public BufferedImage getSprite(){
		return sprite;
	}
	public BufferedImage getSprite(int x,int y,int width,int height){
		return sprite.getSubimage(x, y, width, height);
	}
}