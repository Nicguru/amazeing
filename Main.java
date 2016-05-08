
public class Main {

	public static void main(String[] args) {
		int width = 100;
		int height = 100;
		MazeGen mazeGen = new MazeGenKruskal();
		boolean[][] maze = mazeGen.generateMaze(width,height);
		for(int y = 0; y < 2*height+1; y++) {
			for(int x = 0; x < 2*width+1; x++) {
				if (maze[x][y])
					System.out.print("  ");
				else
					System.out.print("##");
			}
			System.out.println();
		}
		// TODO Auto-generated method stub

	}

}
