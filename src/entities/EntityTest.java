package entities;

import components.Collider;

public class EntityTest extends Entity{

	Collider c;
	public EntityTest(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		c=new Collider(this, 16, 16);
	}

}
