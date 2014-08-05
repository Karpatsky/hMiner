package com.fallboo.miner.tasks;

import com.fallboo.miner.data.Bars;
import com.fallboo.miner.data.Furnaces;
import org.powerbot.script.rt6.ClientContext;

import java.util.concurrent.Callable;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;

public class WalkToFurnace extends GraphScript.Action<ClientContext> {

    private final Furnaces furnaces;
    private final Bars bars;

    public WalkToFurnace(ClientContext ctx, Furnaces furnaces, Bars bars) {
        super(ctx);
        this.furnaces = furnaces;
        this.bars = bars;
    }

    @Override
    public boolean valid() {
        return !furnaces.getFurnaceArea().contains(ctx.players.local())
                && ctx.players.local().animation() == -1 && (ctx.backpack.select().id(bars.getCost().getItem1().getId()).count() != 0
                && (bars.getCost().requiresSecondary() && ctx.backpack.select().id(bars.getCost().getItem2().getId()).count() != 0));
    }

    @Override
    public void run() {
        if (ctx.bank.opened()) {
            ctx.bank.close();
        }
        if (!ctx.objects.select().id(45310).isEmpty()) {
            if (!ctx.objects.peek().inViewport() && furnaces == Furnaces.LUMBRIDGE) {
                ctx.camera.turnTo(ctx.objects.peek());
                Condition.sleep(Random.nextInt(350, 850));
                Condition.wait(new Callable<Boolean>() {

                    @Override
                    public Boolean call() throws Exception {
                        if (!ctx.objects.peek().inViewport()) {
                            ctx.camera.turnTo(ctx.objects.peek());
                        }
                        return ctx.objects.peek().inViewport();
                    }
                }, 100, 6);
            }
            if (ctx.objects.peek().inViewport()) {
                if (ctx.objects.poll().interact("Smelt")) {
                    Condition.sleep(Random.nextInt(350, 850));
                    Condition.wait(new Callable<Boolean>() {

                        @Override
                        public Boolean call() throws Exception {
                            return !ctx.players.local().inMotion() || ctx.widgets.select().id(1370).first().poll().component(20).visible();
                        }
                    }, 200, 50);
                    return;
                }
            }
        }
        if (!ctx.movement.newTilePath(furnaces.getBankToLocation()).traverse()) {
            ctx.movement.findPath(furnaces.getLocation()).traverse();
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
