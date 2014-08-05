package com.fallboo.miner.tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;

import com.fallboo.miner.data.Mines;
import com.fallboo.miner.data.Ores;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.Interactive;

public class Mine extends GraphScript.Action<ClientContext> {

    Mines mine = Mines.VARROCK_WEST;
    Ores ore = Ores.IRON;
    //GameObject interacting = null;
    OreClicker oreClicker;

    public Mine(ClientContext ctx, Mines mines, Ores ores) {
        super(ctx);
        oreClicker = new OreClicker(ctx, ores);
        this.mine = mines;
        this.ore = ores;
    }

    @Override
    public boolean valid() {
        return ctx.backpack.select().count() < 28
                && mine.getMineArea().contains(ctx.players.local())
                && hasMine()
                && ctx.players.local().animation() == -1
                && !ctx.players.local().inMotion();
    }

    @Override
    public void run() {
        oreClicker.clickNextOre();
        Condition.sleep(Random.nextInt(850, 1350));
        if (!Condition.wait(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                return !ctx.players.local().inMotion()
                        || ctx.players.local().animation() != -1 || !oreClicker.isCurrentOreValid();
            }
        }, 200, 30)) {
            return;
        }
        if (!Condition.wait(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() != -1 || !oreClicker.isCurrentOreValid();
            }
        }, 100, 5)) {
            return;
        }
        Condition.sleep(Random.nextInt(450, 950));
        Condition.wait(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                return !oreClicker.isCurrentOreValid();
            }
        }, 100, 120);
        oreClicker.clearInteracting();
    }

    private boolean hasMine() {
        if (!ctx.objects.select().id(ore.getIds()).select(Interactive.areInViewport()).isEmpty()) {
            return true;
        }
        if (!ctx.objects.select().id(ore.getIds()).isEmpty()) {
            return true;
        }
        return false;
    }
}
