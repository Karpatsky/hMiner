package com.fallboo.miner.tasks;

import org.powerbot.script.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;


public abstract class MineTask<C extends ClientContext> extends
        ClientAccessor<C> {

    public MineTask(C ctx) {
        super(ctx);
    }

    public abstract boolean activate();

    public abstract void execute();

}
