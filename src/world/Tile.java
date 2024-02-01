package world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Tile {

	
	protected BufferedImage sprite;
	protected int x,y;
	
	public Tile(int x,int y,BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, World.TILE_SIZE, World.TILE_SIZE);
		//g.drawImage(sprite, x - Camera.x, y - Camera.y,World.TILE_SIZE,World.TILE_SIZE, null);
	}
	
	
}