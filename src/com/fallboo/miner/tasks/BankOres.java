package com.fallboo.miner.tasks;

import java.util.ArrayList;
import java.util.List;

import org.powerbot.script.rt6.Bank;
import org.powerbot.script.rt6.Bank.Amount;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

public class BankOres extends GraphScript.Action<ClientContext> {

    public BankOres(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean valid() {
        return ctx.backpack.select().count() == 28 && ctx.bank.opened();
    }

    @SuppressWarnings("serial")
    private ArrayList<Integer> picks = new ArrayList<Integer>() {
        {
            add(1269);
            add(1273);
            add(1271);
        }
    };

    @Override
    public void run() {
        Bank bank = ctx.bank;
        boolean pickInInve = false;
        for (Item i : ctx.backpack.items()) {
            if (picks.contains(i.id())) {
                pickInInve = true;
                break;
            }
        }
        if (pickInInve) {
            List<Integer> toDeposit = new ArrayList<Integer>();
            for (Item i : ctx.backpack.items()) {
                if (!picks.contains(i.id())) {
                    if (!toDeposit.contains(i.id())) {
                        toDeposit.add(i.id());
                    }
                }
            }
            for (int i : toDeposit) {
                bank.deposit(i, Amount.ALL);
            }
        } else {
            ctx.bank.depositInventory();
        }
    }
}
