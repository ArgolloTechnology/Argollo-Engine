package entities;

import java.awt.Graphics;

import components.AnimationController;
import components.Collider;
import components.Rigdbody;
import graphics.Animation;
import graphics.Sprite;

public class Player extends Entity {

	public boolean right = false, left=false, up = false, down = false;
	public float speed = 40f;
	Rigdbody rb;
	Collider collider;
	Animation anim;
	AnimationController animator;
	Sprite idlesheet;
	public Player(float x, float y) {
		super(x, y);
		idlesheet = new Sprite("/Character Idle 48x48.png", true, 1);
		rb = new Rigdbody(this,.0f);
		collider = new Collider(this, (int)(16), (int)(16));
		idlesheet.getSprites(10, 48, 48);
		anim = new Animation(idlesheet.sprites, 12);
		animator = new AnimationController(this);
		animator.Play(anim);
	}
	@Override
	public void Update() {
		if(right)x+=speed;
		if(left)x-=speed;
		if(down)y+=speed;
		if(up)y-=speed;
		super.Update();
	}
	@Override
	public void Render(Graphics g) {
		// TODO Auto-generated method stub
		super.Render(g);
	}
}
