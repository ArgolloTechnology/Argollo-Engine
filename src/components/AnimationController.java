package components;

import java.awt.Graphics;

import entities.Entity;
import graphics.Animation;

public class AnimationController extends Component{

	Animation animation;
	public AnimationController(Entity e) {
		super(e);
		// TODO Auto-generated constructor stub
	}
	public void Play(Animation animation) {
		this.animation = animation;
	}
	@Override
	public void Render(Graphics g) {
		// TODO Auto-generated method stub
		super.Render(g);
		if (animation != null) {
			owner.sprite = animation.currentSprite;
			animation.Render(g);
		}else {
			System.out.println("Use Play(Animation animation) to select animation");
		}
	}
}
