package entities;

import components.Rigdbody;

public class Player extends Entity {

	public boolean right = false, left=false;
	Rigdbody rb;
	public Player(float x, float y) {
		super(x, y);
		rb = new Rigdbody(this);
	}
	@Override
	public void Update() {
		super.Update();
		if(right)x++;
		if(left)x--;
	}
}
