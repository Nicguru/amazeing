package amazeing;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class MazeGenKruskal implements MazeGen {
	private ArrayList<Point>[][] cellSet;
	private boolean[][] open;
	private ArrayList<Point> walls;

	private void init(int width, int height) {
		walls = new ArrayList<Point>();
		cellSet = new ArrayList[width][height];
		open = new boolean[2 * width + 1][2 * height + 1];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (x < width - 1) {
					// Add East Wall
					walls.add(new Point(2 * x + 2, 2 * y + 1));
				}
				if (y < height - 1) {
					// Add South Wall
					walls.add(new Point(2 * x + 1, 2 * y + 2));
				}
				cellSet[x][y] = new ArrayList<Point>();
				cellSet[x][y].add(new Point(x, y));

				open[2 * x + 1][2 * y + 1] = true;
			}
		}
		open[1][0] = true;
		open[2 * width - 1][2 * height] = true;
	}

	public boolean[][] generateMaze(int width, int height) {
		init(width, height);
		Random r = new Random();
		while (!walls.isEmpty()) {
			int w = r.nextInt(walls.size());
			Point wall = walls.remove(w);
			ArrayList<Point> set1, set2;
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
				for (Point p : set2) {
					set1.add(p);
					cellSet[p.x][p.y] = set1;
				}
			}
		}
		return open;

	}

}
