package com.fallboo.miner.tasks;

import com.fallboo.miner.data.MiningStyle;
import java.util.ArrayList;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

public class OreDrop extends GraphScript.Action<ClientContext> {

    private final MiningStyle miningStyle;

    public OreDrop(ClientContext ctx, MiningStyle miningStyle) {
        super(ctx);
        this.miningStyle = miningStyle;
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
        for (Item i : ctx.backpack.items()) {
            if (!picks.contains(i.id())) {
                i.interact("Drop");
            }
        }
    }

}
