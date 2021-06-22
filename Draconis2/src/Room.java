import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

//this class handles tubes(columns) generations

public class Room {

	public LinkedList<Rectangle> tubes; // using linked list to generate rectangles for the columns
	private int time;
	private int currentTime = 0;
	private int spd = 4; // speed of tubes
	private BufferedImage sheet; // variable to load the spritesheet containing the wood tubes.
	private BufferedImage[] texture; // creating array to crop the different images in spritesheet.

	private final int WIDTH_TUBES = 45; // creating the width of the tubes.
	private final int HEIGHT_TUBES = 100;
	public Fireball fireball;

	public Room(int time) {
		tubes = new LinkedList<>(); // initializing linked list.
		this.time = time; // set the target time when we want the tubes to appear.

		int x;
		int y;

		this.tubes = tubes;
		x = WIDTH_TUBES;
		y = HEIGHT_TUBES;
		texture = new BufferedImage[1];

		try {// this can fail, so we use try and catch to load the spritesheet.
			sheet = ImageIO.read(getClass().getClassLoader().getResourceAsStream("woodtubes.png")); // loading the
																									// spritesheet with
																									// wood image.

			texture[0] = sheet.getSubimage(0, 0, WIDTH_TUBES, HEIGHT_TUBES); // cropping the spritesheet to only show
																				// the first square.
		} catch (IOException e) {
		}
	}

	public void clearTubes() {
		tubes = new LinkedList<>(); // initializing linked list.
	}

	public void update() {

		currentTime++;
		if (currentTime == time) { // if the current time is equal to the target time, we create a new tube.

			currentTime = 0; // resetting the time

			// creation of multiple rectangles to complete a column, each rectangle with 50
			// px height, to use a collision system and destroy each rectangle when
			// colliding with a fireball.
			if (!draconis.showingEnemy && !draconis.showingEnemy2) {
				tubes.add(new Rectangle(draconis.WIDTH, 0, WIDTH_TUBES, HEIGHT_TUBES));
				tubes.add(new Rectangle(draconis.WIDTH, 100, WIDTH_TUBES, HEIGHT_TUBES));
				tubes.add(new Rectangle(draconis.WIDTH, 200, WIDTH_TUBES, HEIGHT_TUBES));
				tubes.add(new Rectangle(draconis.WIDTH, 300, WIDTH_TUBES, HEIGHT_TUBES));
				tubes.add(new Rectangle(draconis.WIDTH, 400, WIDTH_TUBES, HEIGHT_TUBES));
				tubes.add(new Rectangle(draconis.WIDTH, 500, WIDTH_TUBES, HEIGHT_TUBES));
				tubes.add(new Rectangle(draconis.WIDTH, 600, WIDTH_TUBES, HEIGHT_TUBES));
				tubes.add(new Rectangle(draconis.WIDTH, 700, WIDTH_TUBES, HEIGHT_TUBES));
			}

		}

		for (int i = 0; i < tubes.size(); i++) {
			Rectangle rect = tubes.get(i);
			rect.x -= spd;

			if (rect.x + rect.width <= 0) { // removing our current tubes after it is out of the screen.
				tubes.remove(i--);
				continue;

			}

		}
	}

	public void render(Graphics g) {

		for (int i = 0; i < tubes.size(); i++) {

			Rectangle rect = tubes.get(i); // current rectangles that we see in the screen
			g.fillRect(rect.x, rect.y, rect.width, rect.height); // position of rectangle
			g.drawImage(texture[0], rect.x, rect.y, WIDTH_TUBES, HEIGHT_TUBES, null); // rendering the subimage
																						// containing the dragon.

		}
	}

}
