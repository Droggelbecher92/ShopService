package shop;

import java.util.UUID;

public class NonFoodProduct implements Product{

    private final String name;
    private final String id;

    private boolean dishwasherSafe;

    public NonFoodProduct(String name) {
        this.name = name;
        id = UUID.randomUUID().toString();
        dishwasherSafe=true;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

}
