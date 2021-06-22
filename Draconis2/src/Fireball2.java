import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Fireball2 extends Rectangle {

	private static final long serialVersionUID = 1L;

	public boolean isPressed = false;
	private BufferedImage sheet;
	private BufferedImage[] texture;
	private LinkedList<Rectangle> tubes;

	public Fireball2(int x, int y, LinkedList<Rectangle> tubes2) {
		setBounds(x, y, 40, 40);
		this.tubes = tubes2;
		texture = new BufferedImage[2];

		try {
			sheet = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Fireball.png"));

			texture[1] = sheet.getSubimage(60, 0, 60, 40);

		} catch (IOException e) {
		}
	}

	public void update() {
		this.setLocation(this.x + 10, this.y);
		;

		for (int i = 0; i < tubes.size(); i++) {
			if (this.intersects(tubes.get(i))) {
				// if the fireball collides with a tube...
				tubes.remove(i);
				draconis.scoreP2 += 1;
				break;
			}

		}
	}

	public void render(Graphics g) {
		g.drawImage(texture[1], x, y, width, height, null);

	}

	// creating method to get the location of the fireball rectangle, we will use it
	// for the collision system.
	public Rectangle bounds() {
		return (new Rectangle(x, y, 40, 40));

	}

}
