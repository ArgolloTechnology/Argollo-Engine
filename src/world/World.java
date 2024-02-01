package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Player;
import main.Main;

public class World {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static int TILE_SIZE = 48;

	public World(String path) {
		try {
			BufferedImage mapa = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[mapa.getWidth() * mapa.getHeight()];
			WIDTH = mapa.getWidth();
			HEIGHT = mapa.getHeight();
			tiles = new Tile[mapa.getWidth() * mapa.getHeight()];
			mapa.getRGB(0, 0, mapa.getWidth(), mapa.getHeight(), pixels, 0, mapa.getWidth());
			for (int xx = 0; xx < mapa.getWidth(); xx++) {
				for (int yy = 0; yy < mapa.getHeight(); yy++) {
					int pixelAtual = pixels[xx + (yy * mapa.getWidth())];
					//tiles[xx + (yy * WIDTH)] = new Floor_Tile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_FLOOR);
					if (pixelAtual == 0xff000000) {
						tiles[xx + (yy * WIDTH)] = new Solid_Tile(xx * TILE_SIZE, yy * TILE_SIZE, null);
					}else if (pixelAtual == 0xff0000ff) {
						Main.player.SetPosition(xx * TILE_SIZE, yy * TILE_SIZE);
					} 
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static boolean isFree(int xNext, int yNext) {
		int x1 = xNext / TILE_SIZE;
		int y1 = yNext / TILE_SIZE;

		int x2 = (xNext + TILE_SIZE - 1) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;

		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext + TILE_SIZE - 1) / TILE_SIZE;

		int x4 = (xNext + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (yNext + TILE_SIZE - 1) / TILE_SIZE;
		if((x1 + (y1 * World.WIDTH) > tiles.length)
				|| (x2 + (y2 * World.WIDTH) > tiles.length)
				|| (x3 + (y3 * World.WIDTH) > tiles.length)
				|| (x4 + (y4 * World.WIDTH) > tiles.length)
				|| (x1 + (y1 * World.WIDTH) < 0)
				|| (x2 + (y2 * World.WIDTH) < 0)
				|| (x3 + (y3 * World.WIDTH) < 0)
				|| (x4 + (y4 * World.WIDTH) < 0)) {
			return true;
		}
		return !((tiles[x1 + (y1 * World.WIDTH)] instanceof Solid_Tile)
				|| (tiles[x2 + (y2 * World.WIDTH)] instanceof Solid_Tile)
				|| (tiles[x3 + (y3 * World.WIDTH)] instanceof Solid_Tile)
				|| (tiles[x4 + (y4 * World.WIDTH)] instanceof Solid_Tile));
	}

	public void render(Graphics g) {
		int xStart = 0;
		int yStart = 0;

		int xFinal = xStart + (Main.WIDTH / TILE_SIZE);
		int yFinal = yStart + (Main.HEIGHT / TILE_SIZE);

		for (int xx = xStart; xx <= xFinal; xx++) {
			for (int yy = yStart; yy <= yFinal; yy++) {
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) {
					continue;
				}
				Tile tile = tiles[xx + (yy * WIDTH)];
				if(tile != null)tile.render(g);
			}
		}

	}
}

