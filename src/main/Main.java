package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import components.Rigdbody;
import entities.Entity;
import entities.Player;


public class Main extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	
	public final static int WIDTH = 4 * 16;
	public final static int HEIGHT = 4 * 8;
	public final static int SCALE = 3;

	private BufferedImage image;

	public static Random rand;
	public static String mode = "MENU";
	public static boolean FULL_SCREEN = true;
	
	public static List<Entity> entities = new ArrayList<Entity>();
	Player player = new Player(10,10);
	// *******//

	public Main() {
		this.addKeyListener(this);
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		if(FULL_SCREEN)setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		else setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		intframe();
	}

	public void intframe() {
		// Abre a janela
		frame = new JFrame("");
		frame.add(this);
		frame.setResizable(true);
		frame.setUndecorated(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Main game = new Main();
		game.start();
	}

	public void tick() {
		for (var entity : entities) {
			entity.Update();
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0xff5e5e5e));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for (var entity : entities) {
			entity.Render(g);
		}
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height, null);
		bs.show();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println(frames);
				frames = 0;
				timer += 1000;
			}
		}
		stop();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = true;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = true;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = false;
	}
}
