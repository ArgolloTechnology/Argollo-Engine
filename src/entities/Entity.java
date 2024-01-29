package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import components.Component;
import components.Rigdbody;
import main.Main;

public class Entity {
	public float x, y;
	public List<Component> components = new ArrayList<>();
	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
		Main.entities.add(this);
	}
	public void Update() {
		if (components != null) {
			for (var c : components) {
				c.Update();
			} 
			System.out.println(components.size());
		}
	}
	public void Render(Graphics g){
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 4, 4);
	}
}
