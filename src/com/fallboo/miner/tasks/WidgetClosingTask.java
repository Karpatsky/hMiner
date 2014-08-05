/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fallboo.miner.tasks;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Widget;

/**
 *
 * @author Jake
 */
public class WidgetClosingTask extends MineTask<ClientContext> {

    private final Widget level, task;

    public WidgetClosingTask(ClientContext ctx) {
        super(ctx);
        level = ctx.widgets.select().id(149).poll();
        task = ctx.widgets.select().id(1223).poll();
    }

    @Override
    public boolean activate() {
        return level.component(236).visible() || task.component(17).visible();
    }

    @Override
    public void execute() {
        if (level.component(236).visible()) {
            level.component(236).click();
        } else if (task.component(17).visible()) {
            task.component(17).click();
        }
    }

}
