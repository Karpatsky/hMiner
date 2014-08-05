package com.fallboo.miner.tasks;

import com.fallboo.miner.data.MiningStyle;
import com.fallboo.miner.data.Ores;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

public class OreDrop extends GraphScript.Action<ClientContext> {

    private final MiningStyle miningStyle;
    private final Ores ore;

    public OreDrop(ClientContext ctx, MiningStyle miningStyle, Ores ore) {
        super(ctx);
        this.miningStyle = miningStyle;
        this.ore = ore;
    }

    @Override
    public boolean valid() {
        if (miningStyle == MiningStyle.DROP) {
            return ctx.backpack.select().count() == 28;
        }
        for (Item i : ctx.backpack.items()) {
            if (!picks.contains(i.id())) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("serial")
    //TODO Set-up a enum for pickaxes as this list is repeated in multiple classes
    private final ArrayList<Integer> picks = new ArrayList<Integer>() {
        {
            add(1269);
            add(1273);
            add(1271);
        }
    };

    @Override
    public void run() {
        if (!ctx.combatBar.select().id(ore.getId()).isEmpty()) {
            int ores = 0;
            for (Item i : ctx.backpack.items()) {
                System.out.println("Found: " + i.id());
                if (i.id() == ore.getId()) {
                    ores++;
                }
            }
            int key = ctx.combatBar.poll().slot() + 1;
            for (int i = 1; i <= ores + (miningStyle == MiningStyle.M1D1 ? 0 : Random.nextInt(0, 3)); i++) {
                ctx.input.send("{VK_" + key + "}");
                Condition.sleep(100);
            }
        }
        if (!Condition.wait(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                return ctx.backpack.select().isEmpty();
            }
        }, 250, 8)) {
            if (!ctx.backpack.select().isEmpty()) {
                for (Item i : ctx.backpack.items()) {
                    if (i.valid() && !picks.contains(i.id())) {
                        i.interact("Drop");
                    }
                }
            }
        }
    }

}
