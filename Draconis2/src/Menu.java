import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Menu extends JLabel implements MouseListener {

	private static final long serialVersionUID = 1L;
	// creating rectangles for our buttons.
	public Rectangle SinglePlayerButton = new Rectangle(draconis.WIDTH / 3 + 60, 250, 215, 50);
	public Rectangle MultiPlayerButton = new Rectangle(draconis.WIDTH / 3 + 70, 350, 195, 50);
	public Rectangle quitButton = new Rectangle(draconis.WIDTH / 3 + 120, 450, 100, 50);

	private BufferedImage sheet;
	private BufferedImage[] background;

	public void render(Graphics g) {

		background = new BufferedImage[2];

		try {
			sheet = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Menu_background.png"));

			background[1] = sheet.getSubimage(0, 0, 1200, 800);

		} catch (IOException e) {
		}

		g.drawImage(background[1], CENTER, CENTER, draconis.WIDTH, draconis.HEIGHT, null);

		Graphics2D g2d = (Graphics2D) g; // using Graphics2D in order to draw the rectangles of our buttons.

		g.setColor(Color.white); // color of the font

		Font fnt1 = new Font("arial", Font.BOLD, 30); // creating font for the button text
		g.setFont(fnt1);
		g.drawString("SinglePlayer", SinglePlayerButton.x + 19, SinglePlayerButton.y + 35); // drawing the string "play"
																							// to the play button and
		// centered it.
		g2d.draw(SinglePlayerButton); // drawing the play button rectangle in the screen

		g.drawString("Multiplayer", MultiPlayerButton.x + 19, MultiPlayerButton.y + 35); // drawing the string
																							// "Multiplayer"

		g2d.draw(MultiPlayerButton); // drawing the multiplayer button rectangle in the screen.

		g.drawString("Quit", quitButton.x + 19, quitButton.y + 35); // drawing the string "quit" to the quit button and
																	// centered it.
		g2d.draw(quitButton); // drawing the quit button rectangle in the screen

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

		int mx = e.getX(); // mx will get the value of the mouse event and the method returns the x value
							// of the mouse.
		int my = e.getY(); // my the same but with the y value.

		// play button
		if (mx >= draconis.WIDTH / 3 + 120 && mx <= draconis.WIDTH / 3 + 220) {

			if (my >= 250 && my <= 300) {

				// Pressed Play Button
				draconis.State = draconis.STATE.SINGLEPLAYER;
				draconis.showingEnemy = false;
				draconis.showingPlayer1 = true;
			}
		}

		if (mx >= draconis.WIDTH / 3 + 120 && mx <= draconis.WIDTH / 3 + 220) {

			if (my >= 350 && my <= 400) {

				// pressed multiplayer button
				draconis.State = draconis.STATE.MULTIPLAYER;
				draconis.showingEnemy = false;
				draconis.showingPlayer2 = false;
				draconis.showingPlayer1 = false;
			}
		}
		// Quit button
		if (mx >= draconis.WIDTH / 3 + 120 && mx <= draconis.WIDTH / 3 + 220) {

			if (my >= 450 && my <= 500) {

				// Pressed Quit Button
				System.exit(1);
			}
		}

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}
}
