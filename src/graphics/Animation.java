package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Animation{
    BufferedImage[] sprites;
    int fps;
    int currentFrame;
    long previousTime;
    public BufferedImage currentSprite;
    public Animation(BufferedImage[] spritesheet, int fps) {
        sprites = spritesheet;
        this.fps = fps;
        currentFrame = 0;
        previousTime = System.currentTimeMillis();
    }
    

    public void Render(Graphics g) {
			long currentTime = System.currentTimeMillis();
			long elapsedTime = currentTime - previousTime;
			if (elapsedTime > 1000 / fps) {
				currentFrame = (currentFrame + 1) % sprites.length;
				previousTime = currentTime;
			}
			currentSprite = sprites[currentFrame];
    }
}
