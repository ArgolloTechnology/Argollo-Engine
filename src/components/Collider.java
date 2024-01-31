package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Entity;
import main.Main;

public class Collider extends Component {

    public int width;
    public int height;
    
    boolean Debug = true;

    public Collider(Entity e, int width, int height) {
        super(e);
        this.width = width;
        this.height = height;
    }

    @Override
    public void Update() {
        // You can implement collision logic or any other updates related to the collider here
    	
    	Rectangle o = new Rectangle((int)owner.x-1, (int)owner.y-1, width+1, height+1);
    	for (Entity e : Main.entities) {
			if(e != owner) {
				for (Component c : e.components) {
					if(c instanceof Collider) {
						Collider co = (Collider) c;
						Rectangle other = new Rectangle((int)e.x-1, (int)e.y-1, co.width+1, co.height+1);
						System.out.println(e.toString() + o.intersects(other));
					}
				}
			}
		}
    }
    @Override
    public void Render(Graphics g) {
    	// TODO Auto-generated method stub
    	super.Render(g);
    	if (Debug) {
			g.setColor(Color.red);
			g.drawRect((int) owner.x, (int) owner.y, width, height);
		}
    }
}
