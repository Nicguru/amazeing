package amazeing;

import java.awt.Point;
import java.util.Random;
import java.util.ArrayList;

public class MazeGenDF {
	private final int WIDTH;
	private final int HEIGHT;
	private boolean[][] visited;
	private boolean[][]open;
	private Random r = new Random();
	
	
	public MazeGenDF(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		visited = new boolean[WIDTH][HEIGHT];
		open = new boolean[2*WIDTH+1][2*HEIGHT+1];
	}
	
	private void generateMaze(Point p) {
		boolean deadEnd = false;
		while(!deadEnd) {
			ArrayList<Point> candidates = new ArrayList<Point>();
			
			if(p.x > 0 && !visited[p.x-1][p.y])
				candidates.add(new Point(p.x-1, p.y));
			
			if(p.x < WIDTH-1 && !visited[p.x+1][p.y])
				candidates.add(new Point(p.x+1, p.y));
			
			if(p.y > 0 && !visited[p.x][p.y-1])
				candidates.add(new Point(p.x, p.y-1));
			
			if(p.y < HEIGHT-1 && !visited[p.x][p.y+1])
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
	
	public void generateMaze() {
		visited[0][0] = true;
		open[1][1] = true;
		generateMaze(new Point(0,0));
	}
	
	public boolean[][] getMaze() {
		return open;
	}
	
	public String toString() {
		String str = "";
		for (int x = 0; x < open.length; x++) {
			for (int y = 0; y < open[x].length; y++) {
				if (open[x][y]) {
					str += "  ";
				}
				else {
					str += "##";
				}
			}
			str += "\n";
		}
		return str;
	}
	
	
	public static void main(String[] args) {
		MazeGenDF maze = new MazeGenDF(21,34);
		maze.generateMaze();
		System.out.println(maze);
	}
}
