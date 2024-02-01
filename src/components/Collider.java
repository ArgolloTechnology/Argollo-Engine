package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Entity;
import main.Main;
import main.Vector2D;
import world.Tile;
import world.World;

public class Collider extends Component {

    public int width;
    public int height;
    public int xOffSet;
    public int yOffSet;

    boolean Debug = true;
    Rigdbody rb;
    
    Vector2D lastAceleration = new Vector2D(0, 0);
    public Collider(Entity e, int width, int height, int xOff, int yOff) {
        super(e);
        this.width = width;
        this.height = height;
        this.xOffSet = xOff;
        this.yOffSet = yOff;
        for (Component component : e.components) {
			if(component instanceof Rigdbody) rb = (Rigdbody) component;
		}
    }

    @Override
    public void Update() {
        // You can implement collision logic or any other updates related to the collider here

        Rectangle o = new Rectangle((int) owner.x - 1 + xOffSet, (int) owner.y - 1 + yOffSet, width + 1, height + 1);
        for (Entity e : Main.entities) {
            if (e != owner) {
                for (Component c : e.components) {
                    if (c instanceof Collider) {
                        Collider co = (Collider) c;
                        Rectangle other = new Rectangle((int) e.x - 1 + co.xOffSet, (int) e.y - 1 + co.yOffSet,
                                co.width + 1, co.height + 1);

                        if (o.intersects(other)) {
                            // Calculate intersection depth in x and y directions
                            int xDepth = Math.min((int) (o.getMaxX() - other.getMinX()),
                                    (int) (other.getMaxX() - o.getMinX()));
                            int yDepth = Math.min((int) (o.getMaxY() - other.getMinY()),
                                    (int) (other.getMaxY() - o.getMinY()));

                            // Determine collision side based on the intersection depth
                            if (xDepth < yDepth) {
                                if (o.getMinX() < other.getMinX()) {
                                    System.out.println(e.toString() + " Collision on LEFT side");
                                } else {
                                    System.out.println(e.toString() + " Collision on RIGHT side");
                                }
                            } else {
                                if (o.getMinY() < other.getMinY()) {
                                    System.out.println(e.toString() + " Collision on TOP side");
                                } else {
                                	rb.velocity.y = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!World.isFree((int) (owner.x + width +1), (int) owner.y)) {
			
		}else if (!World.isFree((int) (owner.x - 1), (int) owner.y)) {
			
		} 
        if (!World.isFree((int) (owner.x), (int) ((int) owner.y + rb.velocity.y)) && rb.velocity.y > 0) {
        	lastAceleration.y = rb.aceleracion.y;
        	rb.aceleracion.y = 0;
        	rb.velocity.y = 0;
		}else if (World.isFree((int) (owner.x), (int) ((int) owner.y + 1)) && rb.velocity.y == 0) {
        	rb.aceleracion.y = rb.gravityScale;
        	rb.velocity.y = 0;
		}else if (!World.isFree((int) (owner.x), (int) owner.y - height- 1)) {
			
		} 
    }

    @Override
    public void Render(Graphics g) {
        super.Render(g);
        if (Debug) {
            g.setColor(Color.red);
            g.drawRect((int) owner.x + xOffSet, (int) owner.y + yOffSet, width, height);
        }
    }
}
