package solver;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MazeSolverController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    //@RequestMapping("/solver")
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

        return null;
        //TODO: fix return null
    }
}
