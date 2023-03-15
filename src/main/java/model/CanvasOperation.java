package model;


@FunctionalInterface
public interface CanvasOperation {
    void execute(Canvas canvas);
}