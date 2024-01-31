package entities;

import components.Collider;
import components.Rigdbody;
import graphics.Sprite;

public class Player extends Entity {

	public boolean right = false, left=false, up = false, down = false;
	public float speed = 40f;
	Rigdbody rb;
	Collider collider;
	public Player(float x, float y) {
		super(x, y);
		rb = new Rigdbody(this,.1f);
		rb.addForce(0f, -5);
		rb.velocity.x = 10;
		collider = new Collider(this, (int)(16), (int)(16));
		//sprite = new Sprite("/Logo_1.png",.01f);
	}
	@Override
	public void Update() {
		if(right)x+=speed;
		if(left)x-=speed;
		if(down)y+=speed;
		if(up)y-=speed;
		super.Update();
	}
}
