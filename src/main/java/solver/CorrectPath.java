package solver;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by will on 5/20/17.
 */
public class CorrectPath {

    private LinkedList<String> steps;

    public CorrectPath() {
        this.steps = new LinkedList<String>();
    }

    public LinkedList<String> getSteps() {
        return steps;
    }

    public void add(String step)    {
        this.steps.add(step);
    }

    public boolean removeLast() {
        try {
            this.steps.removeLast();
        } catch (NoSuchElementException e)  {
            return false;
        }
        return true;
    }
}
