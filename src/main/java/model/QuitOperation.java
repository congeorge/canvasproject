package model;

public class QuitOperation implements CanvasOperation{
    public QuitOperation(String[] inputArgs) {

    }

    @Override
    public void execute (Canvas canvas) {
        System.exit(0);
    }
}
