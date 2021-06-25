import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class draconis is the main class of the game.
 *
 * @author Walter Pereira
 * @version 1.0
 */

public class draconis extends Canvas implements Runnable, KeyListener {

	/** Default reference ID. */
	private static final long serialVersionUID = 1L;

	/** Setting the dimensions of our game Window. */
	public static final int WIDTH = 1000, HEIGHT = 800;

	/** The running. */
	private boolean running = false;

	/** The thread of the game. */
	private Thread thread;

	/** The score P1. */
	public static double scoreP1 = 0;

	/** The score of P2. */
	public static double scoreP2 = 0;

	/** Used to know when to spawn the enemy. */
	public static boolean showingEnemy = false;

	/** The enemy killed is used to know when to remove the enemy. */
	public static boolean enemyKilled = false;

	/** The showing enemy 2 is used to know when to spawn the enemy2. */
	public static boolean showingEnemy2 = false;

	/** The enemy 2 killed is used to know when to remove the enemy 2. */
	public static boolean enemy2Killed = false;

	/** The showing player 1 is used to know when to spawn the player 1. */
	public static boolean showingPlayer1 = false;

	/** The showing player 2 is used to know when to spawn the player 2. */
	public static boolean showingPlayer2 = false;

	/**
	 * The player 1 killed is used to know when to remove player 1 in multiplayer.
	 */
	public static boolean player1Killed = false;

	/**
	 * The player 2 killed is used to know when to remove player 2 in multiplayer.
	 */
	public static boolean player2killed = false;

	/**
	 * Importing the objects of the different classes.
	 */

	public static Room room; 

	/** The dragon object of class Dragon. */
	public static Dragon dragon;

	/** The fireball object of the class Fireball. */
	public Fireball fireball;

	/** The fireball2 object of the class Fireball2. */
	public Fireball2 fireball2;

	/** The enemy object of the class Enemy. */
	public Enemy enemy;

	/** The enemy 2 is the object of the class Enemy2. */
	public Enemy2 enemy2;

	/** The menu is the object of the class Menu. */
	private Menu menu;

	/** The background is the object of the class background. */
	public Background background;

	/** The gameover is the object of the class GameOver. */
	public GameOver gameover;

	/** The laser is the object of the class Laser. */
	public Laser laser;

	/** The iceball is the object of the class Iceball. */
	public Iceball iceball;

	/** The player2 is the object of the class Player2. */
	public static Player2 player2;

	/**
	 * The Enum java class contains the different states of the game so that the
	 * STATE variable can take one of the states enum constants as value.
	 */
	public static enum STATE {

		MENU,

		SINGLEPLAYER,

		MULTIPLAYER,

		GAMEOVER,
	};

	/**
	 * The state variable will check in what state we are. Used the enum data type
	 * to represent the set of our different unchangeable state variables.
	 */
	public static STATE State = STATE.MENU;

	/**
	 * This constructor instantiates all of our objects.
	 */
	public draconis() {

		Dimension d = new Dimension(draconis.WIDTH, draconis.HEIGHT); // Creating a new dimension
		setPreferredSize(d);
		addKeyListener(this); 
		this.addMouseListener(new Menu()); // instantiating mouse listener from class Menu.
		room = new Room(90); // setting the time to spawn the tubes.
		dragon = new Dragon(20, draconis.HEIGHT / 2, room.tubes);
		player2 = new Player2(40, draconis.HEIGHT / 2, room.tubes);
		enemy = new Enemy(40, draconis.HEIGHT / 2, room.tubes);
		enemy2 = new Enemy2(40, draconis.HEIGHT / 2, room.tubes);
		menu = new Menu();
		gameover = new GameOver();
		generateFireballsPlayer1();
		generateFireballsPlayer2();
		generateLasers();
		generateIceballs();
		background = new Background(draconis.WIDTH, draconis.HEIGHT, null);

	}

	/**
	 * This method contains a timer that will help to generate a laser every second.
	 */
	public void generateLasers() {
		// Declare the timer
		Timer t = new Timer();
		// Setting the schedule function and rate
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// Called each time when 1000 milliseconds (1 second) (the period parameter)
				laser = new Laser(enemy.x - 60, enemy.y + 40, room.tubes);
				dragon.updateLaser(laser);
				player2.updateLaser(laser);
			}

		},
				// Set how long before to start calling the TimerTask (in milliseconds)
				0,
				// Set the amount of time between each execution (in milliseconds)
				1000);

	}

	/**
	 * this method is the timer for the iceballs generation.
	 */
	public void generateIceballs() {
		// Declare the timer
		Timer t = new Timer();
		// Setting the schedule function and rate
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// Called each time when 1000 milliseconds (1 second) (the period parameter)
				iceball = new Iceball(enemy2.x - 60, enemy2.y + 40, room.tubes);
				dragon.updateIceball(iceball);
				player2.updateIceball(iceball);
			}

		},
				// Set how long before to start calling the TimerTask (in milliseconds)
				0,
				// Set the amount of time between each execution (in milliseconds)
				1000);

	}

	/**
	 * This method is the timer for the Fireballs of the player 1.
	 */
	public void generateFireballsPlayer1() { // method to generate fireballs with timer
		// Declare the timer
		Timer t = new Timer();
		// Setting the schedule function and rate
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// Called each time when 1000 milliseconds (1 second) (the period parameter)
				fireball = new Fireball(dragon.x + 90, dragon.y, room.tubes);
				enemy.currentFireball(fireball);
			}

		},
				// Set how long before to start calling the TimerTask (in milliseconds)
				0,
				// Set the amount of time between each execution (in milliseconds)
				1000);
	}

	/**
	 * This method is the timer to generate fireballs of player 2.
	 */
	public void generateFireballsPlayer2() { // method to generate fireballs with timer
		// Declare the timer
		Timer t = new Timer();
		// Setting the schedule function and rate
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// Called each time when 1000 milliseconds (1 second) (the period parameter)
				fireball2 = new Fireball2(player2.x + 90, player2.y, room.tubes);
				enemy.currentFireball2(fireball2);
			}

		},
				// Set how long before to start calling the TimerTask (in milliseconds)
				0,
				// Set the amount of time between each execution (in milliseconds)
				1000);
	}

	/**
	 * This method is called to Start the thread.
	 */
	public synchronized void start() {

		if (running)
			return;
		running = true;
		thread = new Thread(this); // initializing our Thread on this class
		thread.start(); // Start the thread and look for the run method, and this will start our game
						// routine.
	}

	/**
	 * this method is called to stop the thread.
	 */
	public synchronized void stop() {
		// when we close the game, we are going to call this method.
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * The main method of the game contains mainly the generation of the window with
	 * resizing canvas and the different components and configuration of the window.
	 * Also, the main method starts the thread of the game.
	 * 
	 * @param args array of string arguments.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Draconis"); // Title of the window.
		draconis draconis = new draconis(); // creating a new instance of the draconis class.
		frame.add(draconis); // extends our canvas
		frame.setResizable(false); // To avoid the user to resize the window
		frame.pack(); // takes all of the components and sizes the frame to the sizes of its
						// components
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to make the exit button of the window to work.
		frame.setLocationRelativeTo(null); // To center the window in the screen
		frame.setVisible(true); // Window should be visible.
		frame.setLayout(new BorderLayout());

		draconis.start();

	}

	/**
	 * Run.
	 */
	@Override
	public void run() {
		int fps = 0;
		double timer = System.currentTimeMillis();
		long lastTime = System.nanoTime(); // using Long instead of int, to store a higher positive/negative
		double ns = 1000000000 / 60; // 1 second / 60fps
		double delta = 0; // calculating the time passed, so if fps is behind game can catch up.
		while (running) {
			long now = System.nanoTime(); // setting a certain amount of time where the game updates, limit it and
											// render graphics.
			delta += (now - lastTime) / ns; // tracking the difference of our last time and current time.
			lastTime = now; // this negates the time taken to get from above code to here.

			while (delta >= 1) {
				update();
				render();
				fps++; // increasing fps
				delta = 0; // returns delta to not being >=1
			}
			// calculating fps every second
			if (System.currentTimeMillis() - timer > +1000) {
				System.out.println("FPS: " + fps);// this code was used to display fps of the game
				fps = 0;
				timer += 1000;

			}

		}

		stop(); // after game loop finishes, we make it stop.
	}

	/**
	 * Render.
	 */
	private void render() {
		BufferStrategy bs = getBufferStrategy(); // initializing the bufferstrategy which helps to organize memory on
													// our canvas
		if (bs == null) {
			createBufferStrategy(3); // will implement 3 buffers to load 3 images back, to increase speed over time,
										// enough for cpu usage.
			return;

		}

		Graphics g = bs.getDrawGraphics(); // create graphics for buffers

		g.setColor(Color.black); // filling the window with color to clean what we have in the window
		g.fillRect(0, 0, draconis.WIDTH, draconis.HEIGHT); // filling the black background

		if (State == STATE.SINGLEPLAYER) {
			background.render(g);
			room.render(g);
			dragon.render(g);

			if (enemyKilled) {
				showingEnemy = false;
			}
			if (enemy2Killed) {
				showingEnemy2 = false;
			}

			if (this.scoreP1 >= 20 && !enemyKilled) {
				showingEnemy = true;
				enemy.render(g);
				laser.render(g);
			}
			if (this.scoreP1 >= 80 && !enemy2Killed) {
				showingEnemy2 = true;
				enemy2.render(g);
				iceball.render(g);
			}
			fireball.render(g);
			g.setColor(Color.white); // color of the "score" string.
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 19)); // font for the "score" string
			g.drawString("Score: " + (int) scoreP1, 10, 20); // drawing the score message in the screen
			// disposing our bufferstrategy if not next time it loops will be null.

		} else if (State == STATE.MENU) {
			menu.render(g);

		} else if (State == STATE.GAMEOVER) {
			room.clearTubes();
			dragon.updateTubeList(room.tubes);
			player2.updateTubeList(room.tubes);
			gameover.render(g);
			enemy.healthbar = 10;
			enemy2.healthbar = 10;

		}
		if (State == STATE.MULTIPLAYER) {
			background.render(g);
			room.render(g);
			dragon.render(g);
			player2.render(g);
			fireball2.render(g);
			fireball.render(g);

			showingPlayer2 = true;
			showingPlayer1 = true;

			g.setColor(Color.white); // color of the "score" string.
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 19)); // font for the "score" string
			g.drawString("ScoreP1: " + (int) scoreP1, 10, 20); // drawing the score message for player 1.
			g.drawString("ScoreP2: " + (int) scoreP2, 10, 40); // drawing the score message for player 2.
			// disposing our bufferstrategy if not next time it loops will be null.

			if (this.scoreP1 >= 5 && !enemyKilled) {
				showingEnemy = true;
				enemy.render(g);
				laser.render(g);
			} else if (this.scoreP2 >= 5 && !enemyKilled) {
				showingEnemy = true;
				enemy.render(g);
				laser.render(g);
			}
			if (this.scoreP1 >= 60 && !enemy2Killed) {
				showingEnemy2 = true;
				enemy2.render(g);
				iceball.render(g);
			} else if (this.scoreP2 >= 60 && !enemy2Killed) {
				showingEnemy2 = true;
				enemy.render(g);
				iceball.render(g);
			}
			if (enemyKilled) {
				showingEnemy = false;
			}
			if (enemy2Killed) {
				showingEnemy2 = false;
			}
			if (player1Killed) {
				showingPlayer1 = false;

			}
			if (player2killed) {
				showingPlayer2 = false;

			}
			if (player1Killed && player2killed) {
				State = STATE.GAMEOVER;
			}
		}
		g.dispose();
		bs.show(); // to show what we render in the screen.
	}

	/**
	 * Each object implements an update method that simulates one frame of the
	 * object's behavior. Each frame, the game updates every object in the
	 * collection.
	 */
	private void update() {
		// calling all the update methods from the other classes.
		background.update();
		room.update();
		dragon.update();
		player2.update();
		fireball.update();
		fireball2.update();
		try {
			enemy.update();
			enemy2.update();
			laser.update();
			iceball.update();
		} catch (Exception e) {

		}

	}

	/**
	 * //creating key event, if user press "Z" the dragon of player 1 goes up, if
	 * user press "N" the player 2 goes up. by calling the "isPressed" condition in
	 * Dragon & player2 class.
	 *
	 * @param e the e
	 */
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_Z) { // creating key event, if user press "Z" the dragon of player 1 goes up,
												// by calling the "isPressed" condition in Drache class.
			dragon.isPressed = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_N) {// creating key event, if user press "N" the dragon of player 2 goes up,
												// by calling the "isPressed" condition in Player2 class.
			player2.isPressed = true;

		}

	}

	/**
	 * Key released. if Z is not pressed, isPressed condition is false and moves to
	 * isFalling in Dragon class. if N is not pressed, isPressed condition is false
	 * and moves to isFalling in Player2 class.
	 *
	 * @param e the e
	 */
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_Z) { // if Z is not pressed, isPressed is false and moves to isFalling
												// condition.
			dragon.isPressed = false;

		}
		if (e.getKeyCode() == KeyEvent.VK_N) {// if N is not pressed, isPressed is false and moves to isFalling
												// condition.
			player2.isPressed = false;

		}

	}

	/**
	 * Key typed default method.
	 *
	 * @param e the e
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
