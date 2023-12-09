import com.googlecode.lanterna.input.Key;
import field.Field;
import field.Position;
import gameObjects.fruit.Fruit;
import gameObjects.snake.Direction;
import gameObjects.snake.Snake;

import java.util.Iterator;

public class Game {
    private Snake snake;
    private Fruit fruit;
    private int delay;


    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
    }

    public void start() throws InterruptedException {

        Field.drawFruit(generateFruit());  // uncomment when it's time to introduce fruits

        while (snake.isAlive()) {

            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawSnake(snake);
        }
    }

    private Fruit generateFruit() {

        int row = (int) (Math.random() * (Field.getHeight() - 2) + 1);
        int col = (int) (Math.random() * (Field.getWidth() - 2)  + 1);

        return this.fruit = new Fruit(new Position(col,row));
    }

    private void moveSnake() {

        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case ArrowUp:
                    if (snake.getDirection() == Direction.UP || snake.getDirection() == Direction.DOWN) {
                        return;
                    }
                    snake.move(Direction.UP);
                    return;

                case ArrowDown:
                    if (snake.getDirection() == Direction.UP || snake.getDirection() == Direction.DOWN) {
                        return;
                    }
                    snake.move(Direction.DOWN);
                    return;

                case ArrowLeft:
                    if (snake.getDirection() == Direction.RIGHT || snake.getDirection() == Direction.LEFT) {
                        return;
                    }
                    snake.move(Direction.LEFT);
                    return;

                case ArrowRight:
                    if (snake.getDirection() == Direction.RIGHT || snake.getDirection() == Direction.LEFT) {
                        return;
                    }
                    snake.move(Direction.RIGHT);
                    return;
            }
        }
        snake.move();
    }

    private void checkCollisions() {

        if (checkWallCollision()) snake.die();

        if (checkBodyCollision()) snake.die();

        if (checkFruitCollision()) {

            snake.increaseSize();
            Field.drawFruit(generateFruit());

        }
    }

    private boolean checkWallCollision() {

        return (snake.getHead().getCol() == 0
                || snake.getHead().getCol() == Field.getWidth() - 1
                || snake.getHead().getRow() == 0
                || snake.getHead().getRow() == Field.getHeight() - 1);
    }

    private boolean checkFruitCollision() {

        return (snake.getHead().getCol() == (fruit.getFruitPosition().getCol())
                && snake.getHead().getRow() == (fruit.getFruitPosition().getRow()));

    }

    private boolean checkBodyCollision() {
        Iterator<Position> iterator = snake.getFullSnake().iterator();
        iterator.next();
        while (iterator.hasNext()) {
            if(snake.getHead().equals(iterator.next())) {
                return true;
            }
        }
        return false;
    }

}
