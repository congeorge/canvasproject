package model;

public class UndoOperation implements CanvasOperation{

    public UndoOperation(String[] inputs) throws Exception {

    }
    @Override
    public boolean execute (Canvas canvas) {

        System.exit(0);
        return false;
    }

    @Override
    public void undo(Canvas canvas) {
        // do nothing.
        // remove the fill

    }
}
