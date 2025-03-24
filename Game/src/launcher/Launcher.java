package launcher;

import game.Game;

public class Launcher {
	
	Game game;
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

}
