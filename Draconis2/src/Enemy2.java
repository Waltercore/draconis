import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Enemy2 extends Rectangle {

	private BufferedImage sheet; // variable to load the spritesheet containing the enemy image.
	private BufferedImage[] texture; // creating array to crop the different images in spritesheet.
	private LinkedList<Rectangle> tubes;// rectangle for the tubes
	private Fireball currentFireball;
	private Fireball2 currentFireball2;
	public int healthbar = 10;
	int speed = 4;

	public Enemy2(int x, int y, LinkedList<Rectangle> tubes) {
		setBounds(700, 200, 200, 106);
		this.tubes = tubes;
		texture = new BufferedImage[1]; // initializing our array with 1 image

		try {// this can fail, so we use try and catch to load the spritesheet.
			BufferedImage sheet = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemydragon.png")); // loading
																													// the
																													// spritesheet
																													// with
																													// dragon
																													// image.

			texture[0] = sheet.getSubimage(0, 0, 200, 106); // cropping the spritesheet to only show the first square.
		} catch (IOException e) {
		}
	}

	public void currentFireball(Fireball fireball) {
		this.currentFireball = fireball;
	}

	public void currentFireball2(Fireball2 fireball2) {
		this.currentFireball2 = fireball2;
	}

	public void update() {
		y += speed;

		if (y > draconis.HEIGHT - 200) {
			speed = -4;
		}
		if (y < 0) {
			speed = 4;
		}

		// collision system player 1
		if (this.currentFireball != null) {
			if (this.intersects(this.currentFireball) && draconis.showingEnemy2) {
				this.healthbar--;
				this.currentFireball = null;

			}
			if (this.healthbar == 0 && draconis.showingEnemy2) {
				draconis.scoreP1 = draconis.scoreP1 + 20;
				draconis.enemyKilled = true;
			}
		}

		// collision system player 2
		if (this.currentFireball2 != null) {
			if (this.intersects(this.currentFireball2) && draconis.showingEnemy2) {
				this.healthbar--;
				this.currentFireball2 = null;

			}
			if (this.healthbar == 0 && draconis.showingEnemy2) {
				draconis.scoreP2 = draconis.scoreP2 + 20;
				draconis.enemyKilled = true;
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(texture[0], x, y, width, height, null); // rendering the subimage containing the enemy image.

		g.setColor(Color.red);
		g.fillRect(this.x, this.y, 60, 16);
		g.setColor(Color.green);
		g.fillRect(this.x, this.y, healthbar * 6, 16);

	}

}
