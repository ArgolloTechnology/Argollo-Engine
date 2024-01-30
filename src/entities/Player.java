package entities;

import components.Rigdbody;
import graphics.Sprite;

public class Player extends Entity {

	public boolean right = false, left=false;
	public float speed = 10;
	Rigdbody rb;
	public Player(float x, float y) {
		super(x, y);
		//rb = new Rigdbody(this);
		sprite = new Sprite("/Logo_1.png",.05f);
	}
	@Override
	public void Update() {
		super.Update();
		if(right)x+=speed;
		if(left)x-=speed;
	}
}
