/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fallboo.miner.data;

/**
 *
 * @author Jake
 */
public class BarCost {

    private Items item1, item2;
    private int amnt2 = 0;

    public BarCost(Items item1, Items item2, int amnt2) {
        this.item1 = item1;
        this.item2 = item2;
        this.amnt2 = amnt2;
    }

    public BarCost(Items item1) {
        this.item1 = item1;
    }

    public boolean requiresSecondary() {
        return amnt2 > 0;
    }

    public int getAmnt2() {
        return amnt2;
    }

    public Items getItem1() {
        return item1;
    }

    public Items getItem2() {
        return item2;
    }

}
