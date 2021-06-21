import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;


//This class handles the dragon of player #2.

public class Dragon extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	private float spd = 4; // flying speed of the dragon.
	public boolean isPressed = false; //variable used for gravity(up/down) when user does not press a key.
	private BufferedImage sheet; //variable to load the spritesheet containing the dragon.
	private BufferedImage[] texture; //creating array to crop the different images in spritesheet.
	private LinkedList<Rectangle> tubes;// rectangle for the tubes
	private boolean isFalling = false; // used for gravity condition.	private boolean isAlive = true; // used to know if we still need to show the player or remove it from the game when it dies.
	private int frames = 0;
	private Laser laser;
	private Iceball iceball;
	private float gravity = 0.3f;
	
	public Dragon(int x, int y,LinkedList<Rectangle> tubes) {
		setBounds(x,y,105, 85); 
		this.tubes = tubes;
		texture = new BufferedImage[1]; //initializing our array with 1 image
		
		try {// this can fail, so we use try and catch to load the spritesheet.
		sheet = ImageIO.read(getClass().getClassLoader().getResourceAsStream("spritedrache_gruen1.png")); //loading the spritesheet with dragon image.
		
		
		texture[0] = sheet.getSubimage(0, 0, 105, 85); //cropping the spritesheet to only show the first square.
		}catch(IOException e) {}
	}
	
	public void updateTubeList(LinkedList<Rectangle> tubes) {
		this.tubes = tubes;
	}
	
	public void updateLaser(Laser laser) {
		this.laser = laser;
	}
	
	public void updateIceball(Iceball iceball) {
		this.iceball = iceball;
	}
	
	public void update() {
		isFalling = false;
		if(isPressed) {//if the key Z is pressed, it goes up.
			spd = 4; // speed of the dragon when going up.
			y-= spd; // goes up.
			frames = 0;
		
			//collision with top border
			
			if(y < 0) {
				
				y = 0;
			}
			
			
		}else { // if it is not pressed, it goes down.
			isFalling = true;
			y+= spd; // goes down.
			frames ++; // each time the dragon falls, we increase the frames.
			if (frames > 20) frames = 20; // setting a limit for the frames.
		}
		
		if(isFalling) {
			spd+= gravity; // adding gravity when the dragon falls, speed of 4 + gravity.
			if(spd > 8) spd=8; // setting a limit of speed no greater than 8.
		}
		//collision system
		
		if(this.intersects(this.laser) && draconis.showingEnemy) {
			// if the dragon collides with a tube display to game over screen and restart values.
			draconis.player1Killed = true;
			

			draconis.State = draconis.STATE.GAMEOVER;
			tubes = draconis.room.tubes;
			y = draconis.HEIGHT/2;
			draconis.scoreP2 = 0; // restarting the score
			spd = 4; // restarting the speed.
			return;
			}
		
		
		if(this.intersects(this.iceball) && draconis.showingEnemy) {
			// if the dragon collides with a tube display to game over screen and restart values.
			draconis.player1Killed = true;
			
			draconis.State = draconis.STATE.GAMEOVER;
			tubes = draconis.room.tubes;
			y = draconis.HEIGHT/2;
			draconis.scoreP2 = 0; // restarting the score
			spd = 4; // restarting the speed.
			return;
			}
		
		
		for(int i = 0; i < tubes.size(); i++) {
			if (this.intersects(tubes.get(i))) {
				// if the dragon collides with a tube display game over screen and restart values.
				draconis.player1Killed = true;
				

				draconis.State = draconis.STATE.GAMEOVER;
				tubes = draconis.room.tubes;
				y = draconis.HEIGHT/2;
				draconis.scoreP2 = 0; // restarting the score
				spd = 4; // restarting the speed.
				break;
				}
			}
			
				
		if( y >= draconis.HEIGHT) {
			//if the dragon falls off the screen display game over screen and restart values.
			draconis.player1Killed = true;
			

			draconis.State = draconis.STATE.GAMEOVER;
			tubes = draconis.room.tubes;
			y = draconis.HEIGHT/2;
			draconis.scoreP2 = 0; //restarting the score.
			spd = 4; // restarting the speed
			}
		}

	public void render(Graphics g) {
		g.drawImage(texture[0],x,y,width,height,null); //rendering the subimage containing the dragon.
		
	}
	
}
