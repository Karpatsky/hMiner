package com.fallboo.miner.tasks;

import com.fallboo.miner.data.Mines;
import com.fallboo.miner.tasks.GraphScript.Action;
import org.powerbot.script.rt6.ClientContext;

import java.util.concurrent.Callable;
import org.powerbot.script.Condition;

public class WalkToMine extends Action<ClientContext> {

    private final Mines mine;

    public WalkToMine(ClientContext ctx, Mines mines) {
        super(ctx);
        this.mine = mines;
    }

    @Override
    public boolean valid() {
        return ctx.backpack.select().count() < 28
                && !mine.getMineArea().contains(ctx.players.local())
                && ctx.players.local().animation() == -1;
    }

    @Override
    public void run() {
        if (!ctx.movement.newTilePath(mine.getBankToLocation()).traverse()) {
            ctx.movement.findPath(mine.getLocation()).traverse();
        }
        Condition.wait(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().inMotion()
                        || ctx.players.local().animation() != -1;
            }
        }, 100, 5);
    }
}
