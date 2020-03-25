package code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import given.iDeque;
import given.iSimpleContainer;

public class Maze {

	// Characters that define the maze
	char O = 'O'; // allowable cells
	char I = 'I'; // walls
	char S = 'S'; // start point of the Maze
	char E = 'E'; // exit cell
	char visited = '*'; // visited cells

	char[][] currentMaze;
	int rows;
	int cols;


	public static class Coordinate {
		int x;
		int y;

		public Coordinate(int r, int c) {
			x = r;
			y = c;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (!Coordinate.class.isAssignableFrom(obj.getClass())) {
				return false;
			}
			final Coordinate other = (Coordinate) obj;
			if (this.x == other.x && this.y == other.y)
				return true;
			else
				return false;
		}

	}
	public void loadMaze(String fileName) throws IOException {

		// BARIS: ASK THEM TO MODIFY?

		BufferedReader br1 = new BufferedReader(new FileReader(fileName));
		String line1;

		line1 = br1.readLine();
		rows = 0;

		while (line1 != null) { // gets the size of the maze

			line1 = br1.readLine();
			rows++;
		}
		br1.close();

		BufferedReader br2 = new BufferedReader(new FileReader(fileName));
		currentMaze = new char[rows][]; // creates a char array with maze size

		String line2;
		int i = 0;

		while ((line2 = br2.readLine()) != null) // loads maze elements to 2-D char array
		{
			String newStr = line2.replaceAll(", ", "");
			currentMaze[i++] = newStr.toCharArray();
		}

		cols = currentMaze[0].length;

		br2.close();
	}

	// Prints the maze if you want to debug
	public String toString() {
		int i = 0, j = 0;
		StringBuilder sb = new StringBuilder(1000);
		sb.append("Maze: " + rows + " x " + cols);

		for (; i < rows; i++) {
			for (; j < cols - 1; j++) {
				sb.append(currentMaze[i][j] + ", ");
			}
			sb.append(currentMaze[i][j] + System.lineSeparator());
		}

		return sb.toString();
	}

	public boolean solveMaze(iSimpleContainer<Coordinate> sc, iDeque<Coordinate> visitedNodes, String mazeName)
			throws IOException {
		loadMaze(mazeName);
		sc.push(getStartState(currentMaze));
		while (!sc.isEmpty()) {
			Coordinate currentState = sc.pop();
			if (isExit(currentState.x, currentState.y))
				return true;
			else if (!isVisited(currentState)) {
				markVisited(currentState, visited);
				visitedNodes.addBehind(currentState);
				for (Coordinate c : emptyNeighbors(currentState)) {
					if (c == null)
						continue;
					sc.push(c);
				}
			}
		}
		return false;
	}

	private Coordinate[] emptyNeighbors(Coordinate current) {
		Coordinate[] neighbors = new Coordinate[4];
		Coordinate down = new Coordinate(current.x, current.y - 1), right = new Coordinate(current.x + 1, current.y),
				up = new Coordinate(current.x, current.y + 1), left = new Coordinate(current.x - 1, current.y);
		if (isInMaze(down) && isEmpty(down))
			neighbors[0] = down;
		if (isInMaze(right) && isEmpty(right))
			neighbors[1] = right;
		if (isInMaze(up) && isEmpty(up))
			neighbors[2] = up;
		if (isInMaze(left) && isEmpty(left))
			neighbors[3] = left;
		return neighbors;
	}

	private boolean isEmpty(Coordinate c) {
		return currentMaze[c.x][c.y] == 'O' || currentMaze[c.x][c.y] == 'E';
	}


	// Helper method which marks a coordinate as visited
	private void markVisited(Coordinate c, char val) {
		if (isInMaze(c))
			currentMaze[c.x][c.y] = val;
	}

	// Helper method which checks if the coordinate has been visited before
	private boolean isVisited(Coordinate c) {
		if (isInMaze(c))
			return currentMaze[c.x][c.y] == visited;
		return false;

	}

	// Helper method which checks if the coordinate is within the maze or not
	private boolean isInMaze(Coordinate c) {
		return c.x >= 0 && c.x < currentMaze.length && c.y >= 0 && c.y < currentMaze[0].length;
	}

	// Helper method which checks if the coordinate is an exit or not
	private boolean isExit(int row, int col) {
		return currentMaze[row][col] == 'E';
	}

	// Returns the start state from the maze
	private Coordinate getStartState(char[][] maze) {
		for (int i = 0; i < maze[0].length; i++) {
			for (int j = 0; j < maze.length; j++) {
				if (maze[i][j] == 'S')
					return new Coordinate(i, j);
			}
		}
		return null;
	}
};
