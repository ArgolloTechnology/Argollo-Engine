package components;

import entities.Entity;

public class Rigdbody extends Component {

	public float gravityScale = 1;
	
	public Rigdbody(Entity e) {
		super(e);
		
		// TODO Auto-generated constructor stub
	}

	
	public void Update() {
		owner.y+=gravityScale;
	}
}
