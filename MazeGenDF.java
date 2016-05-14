package amazeing;

import java.awt.Point;
import java.util.Random;
import java.util.ArrayList;
/**
 * Class representing a maze generator using the recursive "depth first" algorithm
 * @author Nicholas
 *
 */
public class MazeGenDF implements MazeGen {
	private int width;
	private int height;
	private boolean[][] visited;
	private boolean[][]open;
	private Random r = new Random();
	
	/**
	 * Generates maze based on a given Point
	 * @param p - point
	 */
	private void generateMaze(Point p) {
		boolean deadEnd = false;
		while(!deadEnd) {
			ArrayList<Point> candidates = new ArrayList<Point>();
			
			if(p.x > 0 && !visited[p.x-1][p.y])
				candidates.add(new Point(p.x-1, p.y));
			
			if(p.x < width-1 && !visited[p.x+1][p.y])
				candidates.add(new Point(p.x+1, p.y));
			
			if(p.y > 0 && !visited[p.x][p.y-1])
				candidates.add(new Point(p.x, p.y-1));
			
			if(p.y < height-1 && !visited[p.x][p.y+1])
				candidates.add(new Point(p.x, p.y+1));
			
			if(candidates.isEmpty()) {
				deadEnd = true;
			}
			else {	
				int i = r.nextInt(candidates.size());
				Point q = candidates.get(i);
				visited[q.x][q.y] = true;
				open[2*q.x+1][2*q.y+1] = true;
				open[p.x+q.x+1][p.y+q.y+1] = true;
				//points.push(q);
				//points.push(q);
				generateMaze(q);
			}
		}
	}
		
	@Override
	/**
	 * Generates a maze based on a given width and height
	 */
	public boolean[][] generateMaze(int width, int height) {
		this.width = width;
		this.height = height;
		visited = new boolean[width][height];
		open = new boolean[2*width+1][2*height+1];
		visited[0][0] = true;
		open[1][1] = true;
		generateMaze(new Point(0,0));
		open[1][0] = true;
		open[2 * width - 1][2 * height] = true;
		return open;
	}
}
