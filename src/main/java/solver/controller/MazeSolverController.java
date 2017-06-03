package solver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import solver.model.CorrectPath;
import solver.model.AsciiMazeTree;

@RestController
public class MazeSolverController {

    @RequestMapping(value = "/solver", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CorrectPath solveMaze(@RequestBody String[] asciiMaze)   {
        //first, convert asciiMaze input into a tree
        //second, traverse tree to find end, keeping track of correct path to end
        //third, return correct path

        //first, convert asciiMaze input into a tree
        final AsciiMazeTree mazeTree = new AsciiMazeTree(asciiMaze);

        //second, traverse tree to find end, keeping track of correct path to end
        MazeSolver ms = new MazeSolver(mazeTree.getHead());
        CorrectPath solution = null;
        try {
            solution = ms.findpath();
        } catch(Exception e)    {
            solution = new CorrectPath();
            solution.add(e.getMessage());
        }

        return solution;
    }
}
