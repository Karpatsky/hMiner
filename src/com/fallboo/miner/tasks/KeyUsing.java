package com.fallboo.miner.tasks;

import com.fallboo.miner.tasks.GraphScript.Action;
import java.util.concurrent.Callable;
import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.Widget;

public class KeyUsing extends Action<ClientContext> {

    public KeyUsing(ClientContext ctx) {
        super(ctx);
        keyWidget = ctx.widgets.select().id(1048).poll();
    }
    private final Widget keyWidget;

    @Override
    public boolean valid() {
        return !ctx.backpack.select().id(24154).isEmpty();
    }

    @Override
    public void run() {
        for (Item i : ctx.backpack.first()) {
            i.interact("Claim key");
            if (Condition.wait(new Callable<Boolean>() {

                @Override
                public Boolean call() throws Exception {
                    return keyWidget.component(30).visible();
                }
            }, 100, 6)) {
                keyWidget.component(30).click();
            }
        }
    }

}
