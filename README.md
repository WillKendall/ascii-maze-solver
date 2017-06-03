# ascii-maze-solver
A restful webservice that solves json ascii mazes

http://www.delorie.com/game-room/mazes/genmaze.cgi

Number of cells across (1..N): whatever size you want
Number of cells up/down (1..N): whatever size you want
Type of maze: Text (cell size in characters)
Width of each cell (2..N): 2
Height of each cell (2..N): 2
Random Number Seed (optional): whatever you want

Once the website above generates the maze, feel free to replace the two open
points on the maze with & (start point) and * (end point), or fill in the
gaps that are the original start and end points with wall characters and
place the start and end points wherever you want.

Please see resources/exampleInput.json and resources/commandToExecute.txt for 
information on how to call the web service and format the json version of 
the maze.  These files also show the maze for each test case in 
test/MazeSolverControllerTests.java.

Other requirements:
The "hall way" character must be a space (0x20).
& must be the start point character.
* must be the end point character.
All other characters will be considered wall.
Please stick with ascii characters, unicode is untested and undefined
in the context of this app.
The maze must be 2 dimensional.
Each node in the maze can have up to four adjoining directions.
    (No diagonal movement, square grid based only.)

To compile the code use the following command in the project's root directory:
./gradlew build

To run the web service use the following command in the project's root directory:
java -jar build/libs/asciimazesolver-0.1.0.jar
