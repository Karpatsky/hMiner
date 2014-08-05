package com.fallboo.miner.data;

public enum Bars {

    BRONZE(2349, new BarCost(Items.COPPER, Items.TIN, 1), 14), IRON(2351, new BarCost(Items.IRON), 28), STEEL(2353, new BarCost(Items.IRON, Items.COAL, 2), 9);
    private int id, primaryAmount;
    private BarCost cost;

    Bars(int id, BarCost cost, int primaryAmount) {
        this.id = id;
        this.cost = cost;
        this.primaryAmount = primaryAmount;
    }

    public BarCost getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }

    public int getPrimaryAmount() {
        return primaryAmount;
    }

}
