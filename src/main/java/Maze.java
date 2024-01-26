import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {
    int count = 0;
    int index = 0;

    //Read the text file.
    File txtFile = new File("sample.txt");

    public String[] readFile() {
        String[] lines = null;
        try {
            Scanner scan = new Scanner(txtFile);
            List<String> linesList = new ArrayList<>();

            while (scan.hasNextLine()) {
                String line = scan.nextLine().trim();
                if (!line.isEmpty()) {
                    linesList.add(line);
                    count++;
                }
            }

            if (count != 0) {
                lines = linesList.toArray(new String[0]);

                // Remove the spaces
                for (int i = 0; i < lines.length; i++) {
                    lines[i] = lines[i].replaceAll("\\s", "");
                }
            } else {
                // If the text file is empty show it as an error.
                System.out.println("Your text file is empty. :( \n Please enter a maze in your text file.");
                System.exit(0);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }


    public static void DFS(char[][] mazeArray,boolean[][] visited, int num_of_rows, int num_of_cols) {
        int startRow = -1;
        int startCol = -1;
        int row = -1;
        int col = -1;
        try{
            for (int i = 0; i < num_of_rows; i++) {
                for (int j = 0; j < num_of_cols; j++) {
                    if (mazeArray[i][j] == 'S') {
                        startRow = i;
                        startCol = j;
                        break;
                    }
                }
            }}
        catch (IndexOutOfBoundsException e){
            System.out.println("Number of characters in each line should be same.");
            System.exit(0);
        }
        if(startRow == -1){
            System.out.println("Please mention start of the maze.");
            System.exit(0);

        }

        //Get the size of the maze
        int mazeSize = num_of_rows * num_of_cols;
        //Solving the maze using a stack
        MyStack<Index> stack_1 = new MyStack<>(mazeSize);
        Index startIndex = new Index(startRow, startCol);
        stack_1.push(startIndex);
        //Mark the starting point as visited.
        mazeArray[startRow][startCol] = '*';
        visited[startRow][startCol] = true;

        while (!stack_1.isEmpty()) {
            //Get current Index
            Index currentIndex = stack_1.peek();
            row = currentIndex.getCurrent_row();
            col = currentIndex.getCurrent_col();

            // Check if the current cell is the destination
            if ((row - 1 >= 0 && mazeArray[row - 1][col] == 'E' && visited[row - 1][col]==false) || (col + 1 < num_of_cols && mazeArray[row][col + 1] == 'E' && visited[row][col + 1]==false)||(row + 1 < num_of_rows && mazeArray[row + 1][col] == 'E' && visited[row + 1][col]==false)|| (col - 1 >= 0 && mazeArray[row][col - 1] == 'E'&& visited[row][col - 1]==false)) {
                while (!stack_1.isEmpty()) {
                    Index element = stack_1.pop();
                    row = element.getCurrent_row();
                    col = element.getCurrent_col();
                    mazeArray[row][col] = '*'; // Update the mazeArray to mark the path
                }
                printMaze(mazeArray);
                break;
            }

            // check whether the upper character is a not visited path
            else if (row - 1 >= 0 && mazeArray[row - 1][col] == '.' && visited[row - 1][col]==false) {
                Index up = new Index(row - 1, col);
                stack_1.push(up);
                visited[row - 1][col] = true;
            }
            // check whether the right side character is a not visited path
            else if (col + 1 < num_of_cols && mazeArray[row][col + 1] == '.' && visited[row][col + 1]==false) {
                Index right = new Index(row, col + 1);
                stack_1.push(right);
                visited[row][col + 1] = true;

            }
            // check whether the bottom character is a not visited path
            else if (row + 1 < num_of_rows && mazeArray[row + 1][col] == '.' && visited[row + 1][col]==false) {
                Index down = new Index(row + 1, col);
                stack_1.push(down);
                visited[row + 1][col] = true;

            }
            // check whether the left side character is a not visited path
            else if (col - 1 >= 0 && mazeArray[row][col - 1] == '.' && visited[row][col - 1]==false) {
                Index left = new Index(row, col - 1);
                stack_1.push(left);
                visited[row][col - 1] = true;
            }
            else{
                //Backtracking
                stack_1.pop();}
        }


        //System.out.println(Arrays.deepToString(visited));
    }
    public static void printMaze(char[][] mazeArray) {

        for (char[] chars : mazeArray) {
            for (char currentChar : chars) {
                // Check if the current character is '*'
                if (currentChar == '*') {
                    // Print the '*' in green (ANSI escape code for green color: "\u001B[32m")
                    System.out.print(Main.ANSI_GREEN + currentChar + Main.ANSI_RESET + " ");
                } else {
                    // Print other characters normally
                    System.out.print(currentChar + " ");
                }
            }
            // Move to the next line after printing each row
            System.out.println();
        }

    }



    public static class Index{
        private  int current_row;
        private int current_col;

        public Index(int current_row, int current_col){
            this.current_row = current_row;
            this.current_col = current_col;
        }
        public int getCurrent_row(){
            return current_row;
        }
        public int getCurrent_col(){
            return current_col;
        }

    }
}
