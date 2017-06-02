package solver.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import solver.model.CorrectPath;
import solver.Greeting;
import solver.model.AsciiMazeTree;

@RestController
public class MazeSolverController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    //@RequestMapping("/greeting")
    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Greeting greeting(@RequestBody String[] asciiMaze) {
        return new Greeting(counter.incrementAndGet(), String.format(template, asciiMaze[0]));
    }

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
