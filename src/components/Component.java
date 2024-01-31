package components;

import java.awt.Graphics;

import entities.Entity;

public class Component {
	Entity owner;
	public Component(Entity e) {
		this.owner = e;
		owner.components.add(this);
	}
	public void Update() {
		
	}
	public void Render(Graphics g) {
		
	}
}
