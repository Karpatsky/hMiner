/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fallboo.miner.tasks;

import com.fallboo.miner.data.ScheduledBreak;
import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;

/**
 *
 * @author Jake
 */
public class Sleeping extends GraphScript.Action<ClientContext> {

    private ScheduledBreak[] breaks;
    private int index = 0;
    private long nextAction;

    public Sleeping(ClientContext ctx, ScheduledBreak[] breaks) {
        super(ctx);
        this.breaks = breaks;
        nextAction = (breaks[index].getWorkTime() * 60000) + System.currentTimeMillis();
    }

    @Override
    public boolean valid() {
        return nextAction < System.currentTimeMillis();
    }

    @Override
    public void run() {
        System.out.println("Going to sleep");
        ctx.game.logout(true);
        Condition.sleep(breaks[index].getBreakTime() * 60000);
        index++;
        if (index == breaks.length) {
            index = 0;
        }
        nextAction = (breaks[index].getWorkTime() * 60000) + System.currentTimeMillis();
    }
}
