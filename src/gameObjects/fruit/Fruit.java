package gameObjects.fruit;

import field.Position;

public class Fruit {

    private Position fruitPosition;

    public Fruit(Position fruitPosition) {

        this.fruitPosition = fruitPosition;
    }
    public Position getFruitPosition() {
        return fruitPosition;
    }

    public void setFruitPosition(Position fruitPosition) {
        this.fruitPosition = fruitPosition;

    }
}
