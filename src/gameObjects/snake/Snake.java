package gameObjects.snake;

import field.Position;

import java.util.LinkedList;

public class Snake {
    private final static int SNAKE_INITIAL_SIZE = 3;
    private Direction direction;
    private boolean alive;
    private LinkedList<Position> snakeBody;

    public Snake(){

        snakeBody = new LinkedList<>();
        for (int i = 0; i < SNAKE_INITIAL_SIZE; i++) {
            snakeBody.add(new Position(40 - i, 10));
        }
        alive = true;
        this.direction = Direction.RIGHT;
    }

    public void increaseSize() {

        snakeBody.add(new Position(snakeBody.getLast().getCol() + 1, snakeBody.getLast().getRow()));

    }

    public void move(Direction direction) {

        if(direction == Direction.RIGHT) {

            snakeBody.addFirst(new Position(getHead().getCol() + 1, getHead().getRow()));
            snakeBody.removeLast();
            this.direction = Direction.RIGHT;
        }

        if (direction == Direction.LEFT) {

            snakeBody.addFirst(new Position(getHead().getCol() - 1, getHead().getRow()));
            snakeBody.removeLast();
            this.direction = Direction.LEFT;

        }

        if (direction == Direction.UP) {

            snakeBody.addFirst(new Position(getHead().getCol(), getHead().getRow() - 1));
            snakeBody.removeLast();
            this.direction = Direction.UP;
        }

        if (direction == Direction.DOWN) {

            snakeBody.addFirst(new Position(getHead().getCol(), getHead().getRow() + 1));
            snakeBody.removeLast();
            this.direction = Direction.DOWN;
        }

    }

    public void move(){

        move(direction);

    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getHead() {
        return snakeBody.getFirst();
    }

    public Position getTail() {

        return snakeBody.getLast();
    }

    public LinkedList<Position> getFullSnake(){
        return snakeBody;
    }

    public int getSnakeSize() {
        return snakeBody.size();
    }

    public Direction getDirection() {
        return direction;
    }

}
