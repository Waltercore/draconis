import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Background extends JLabel {

	private static final long serialVersionUID = 1L;

	public boolean isPressed = false;
	private BufferedImage sheet; // sheet
	private BufferedImage sheet2;
	private BufferedImage[] background; // texture

	public Background(int x, int y, ArrayList<JLabel> image) {
		background = new BufferedImage[2];

		try {
			sheet = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Background_game.png"));

			background[1] = sheet.getSubimage(0, 0, 1200, 800);

		} catch (IOException e) {
		}

	}

	public void update() {
		this.setLocation(SwingConstants.CENTER, SwingConstants.CENTER);
		;

	}

	public void render(Graphics g) {
		g.drawImage(background[1], CENTER, CENTER, draconis.WIDTH, draconis.HEIGHT, null);

	}

}
