public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[36m";

    public static void main(String[] args) {
        Maze maze = new Maze();
        // Read the maze file and store each line as an element in the 'lines' array.
        String[] lines = maze.readFile();
        // Initialize variables to store the number of rows and columns in the maze
        int num_of_cols = 0;
        int num_of_rows = 0;
        // Initialize a 2D char array to represent the maze and a 2D boolean array to track visited positions
        char[][] mazeArray = new char[0][];
        boolean[][] visited = new boolean[0][];

        // Check if the file was read successfully
        if (lines != null) {
            num_of_rows = lines.length;
            num_of_cols = lines[0].length();
            mazeArray = new char[num_of_rows][num_of_cols];
            visited = new boolean[num_of_rows][num_of_cols];

            // Convert each line of the input into a char array and store it in mazeArray.
            for (int i = 0; i < lines.length; i++) {
                mazeArray[i] = lines[i].toCharArray();
            }
        }
        // Initialize the 'visited' array to mark all positions as unvisited.
        for (int i = 0; i < num_of_rows; i++) {
            for (int j = 0; j < num_of_cols; j++) {
                visited[i][j] = false;
            }
        }
        // Measure the execution time of the Depth-First Search (DFS) algorithm
        long startTime = System.nanoTime();
        Maze.DFS(mazeArray, visited, num_of_rows, num_of_cols);
        long endTime = System.nanoTime();

        // Calculate the execution time in milliseconds.
        long executionTime = (endTime - startTime) / 1000000;
        System.out.println("Execution time: " + executionTime + " milliseconds");
    }
}




