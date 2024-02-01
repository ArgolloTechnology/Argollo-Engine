package components;

import entities.Entity;
import main.Vector2D;

public class Rigdbody extends Component {

	public float gravityScale = 1f;
	public float mass = 1f;
	public Vector2D velocity;
	public Vector2D aceleracion;
	public Vector2D maxSpeed;
	public Rigdbody(Entity e, float gravity) {
		super(e);
		velocity = new Vector2D(0, 0);
		aceleracion = new Vector2D(0, 0);
		this.gravityScale = gravity;
		addForce(0, gravityScale);
	}

	
	public void Update() {
		ownerUpdate(); 
	}


	private void ownerUpdate() {
		owner.x += velocity.x + aceleracion.x/2; 
		owner.y += velocity.y + aceleracion.y/2; 
		if (maxSpeed != null) {
			if (maxSpeed.x < velocity.x)
				velocity.x += aceleracion.x;
			else
				velocity.x = maxSpeed.x;
			if (maxSpeed.y < velocity.y)
				velocity.y += aceleracion.y;
			else
				velocity.y = maxSpeed.y;
		}else {
			velocity.x += aceleracion.x;
			velocity.y += aceleracion.y;
		}
		if (aceleracion.y != 0) {
			if ((aceleracion.y < gravityScale + 0.1 || aceleracion.y > gravityScale - 0.1))
				aceleracion.y = gravityScale;
			if (aceleracion.y < gravityScale)
				aceleracion.y += .1f;
		}
		
	}
	
	public void addForce(float x, float y) {
		aceleracion.x += (x/mass);
		aceleracion.y += (y/mass);
	}
}
