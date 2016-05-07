import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class MazeGenKruskal {
	private int[][] cellSet;
	private boolean[][] open;
	private ArrayList<Point> walls;
	private final int WIDTH;
	private final int HEIGHT;
	private static int setGroup = 1;

	public MazeGenKruskal(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		walls = new ArrayList<Point>();
		cellSet = new int[WIDTH][HEIGHT];
		open = new boolean[2 * WIDTH + 1][2 * HEIGHT + 1];
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				if (x < WIDTH - 1) {
					// Add East Wall
					walls.add(new Point(2 * x + 2, 2 * y + 1));
				}
				if (y < HEIGHT - 1) {
					// Add South Wall
					walls.add(new Point(2 * x + 1, 2 * y + 2));
				}
				cellSet[x][y] = setGroup++;
				open[2 * x + 1][2 * y + 1] = true;
			}
		}
		open[1][0] = true;
		open[2 * width - 1][2 * height] = true;
	}

	public void generateMaze() {
		Random r = new Random();
		while (!walls.isEmpty()) {
			int w = r.nextInt(walls.size());
			Point wall = walls.remove(w);
			int set1, set2;
			if (wall.x % 2 != 0) {
				// Horizontal Wall
				int x = (wall.x - 1) / 2;
				int yNorth = (wall.y - 2) / 2;
				int ySouth = wall.y / 2;
				set1 = cellSet[x][yNorth];
				set2 = cellSet[x][ySouth];
			} else {
				// Vertical Wall
				int y = (wall.y - 1) / 2;
				int xWest = (wall.x - 2) / 2;
				int xEast = wall.x / 2;
				set1 = cellSet[xWest][y];
				set2 = cellSet[xEast][y];

			}
			if (set1 != set2) {
				open[wall.x][wall.y] = true;
				for (int x = 0; x < WIDTH; x++) {
					for (int y = 0; y < HEIGHT; y++) {
						if (cellSet[x][y] == set2) {
							cellSet[x][y] = set1;
						}
					}
				}
			}
		}

	}

	public boolean[][] getMaze() {
		return open;
	}

}
