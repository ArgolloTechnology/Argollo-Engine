package main;

public class Vector2D {
	public float x, y;
	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public float Magnitude() {
		return (float) Math.sqrt(Math.pow(x, x) + Math.pow(y, y));
	}
}
