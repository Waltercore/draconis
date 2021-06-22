import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Iceball extends Rectangle {

	private static final long serialVersionUID = 1L;

	public boolean isPressed = false;
	private BufferedImage sheet;
	private BufferedImage[] texture;
	private LinkedList<Rectangle> tubes;

	public Iceball(int x, int y, LinkedList<Rectangle> tubes) {
		setBounds(x, y, 40, 40);
		this.tubes = tubes;
		texture = new BufferedImage[2];

		try {
			sheet = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Iceball.png"));

			texture[1] = sheet.getSubimage(60, 0, 60, 40);

		} catch (IOException e) {
		}
	}

	public void update() {
		this.setLocation(this.x - 15, this.y);
		;

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
