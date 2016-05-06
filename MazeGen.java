
import java.awt.Point;
import java.util.Random;
import java.util.ArrayList;

public class MazeGen {
	public static void main(String[] args) {
		final int width = 30;
		final int height = 10;
		Random r = new Random();
		boolean visited[][] = new boolean[width][height];
		boolean open[][] = new boolean[2*width+1][2*height+1];
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(0, 0));
		visited[0][0] = true;
		open[1][1] = true;
		while(!points.isEmpty()) {
			//int x = r.nextInt(points.size());
			int x = points.size()-1;
			Point p = points.remove(x);
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
					points.add(q);
					//points.push(q);
					//points.push(q);
					p = q;
				}
			}
		}
		
		open[1][0] = true;
		open[2*width-1][2*height] = true;
		
		for(int y = 0; y < 2*height+1; y++) {
			for(int x = 0; x < 2*width+1; x++) {
				if (open[x][y])
					System.out.print("  ");
				else
					System.out.print("##");
			}
			System.out.println();
		}
	}
}
