/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fallboo.miner.tasks;

import com.fallboo.miner.AIOMiner;
import org.powerbot.script.rt4.Skills;
import org.powerbot.script.rt6.ClientContext;

/**
 *
 * @author Jake
 */
public class StatUpdaterTask extends GraphScript.Action<ClientContext> {

    private long lastUpdate = 0;
    private final AIOMiner miner;

    public StatUpdaterTask(ClientContext ctx, AIOMiner miner) {
        super(ctx);
        this.miner = miner;
    }

    @Override
    public boolean valid() {
        return lastUpdate < System.currentTimeMillis() && ctx.game.loggedIn();
    }

    @Override
    public void run() {
        if (miner.getStartLevel() == 0) {
            int startLevel = ctx.skills.level(Skills.MINING) + ctx.skills.level(Skills.SMITHING),
                    startXp = ctx.skills.experience(Skills.MINING) + ctx.skills.experience(Skills.SMITHING);
            miner.setStartLevel(startLevel);
            miner.setStartXp(startXp);
        } else {
            int currLevel = ctx.skills.level(Skills.MINING) + ctx.skills.level(Skills.SMITHING),
                    currXp = ctx.skills.experience(Skills.MINING) + ctx.skills.experience(Skills.SMITHING);
            miner.setCurrLevel(currLevel);
            miner.setCurrXp(currXp);
        }
        lastUpdate = System.currentTimeMillis() + 7500;
    }

}
