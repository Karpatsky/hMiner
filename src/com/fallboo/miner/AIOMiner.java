package com.fallboo.miner;

import com.fallboo.miner.data.Bars;
import com.fallboo.miner.data.Furnaces;
import com.fallboo.miner.data.Mines;
import com.fallboo.miner.data.MiningStyle;
import com.fallboo.miner.data.Rocks;
import com.fallboo.miner.data.ScheduledBreak;
import com.fallboo.miner.data.Style;
import com.fallboo.miner.gui.Gui;
import com.fallboo.miner.tasks.AntiPattern;
import com.fallboo.miner.tasks.BankBars;
import com.fallboo.miner.tasks.BankOres;
import com.fallboo.miner.tasks.GraphScript;
import com.fallboo.miner.tasks.KeyUsing;
import com.fallboo.miner.tasks.Mine;
import com.fallboo.miner.tasks.OreDrop;
import com.fallboo.miner.tasks.Sleeping;
import com.fallboo.miner.tasks.Smelt;
import com.fallboo.miner.tasks.StatUpdaterTask;
import com.fallboo.miner.tasks.WalkToBank;
import com.fallboo.miner.tasks.WalkToFurnace;
import com.fallboo.miner.tasks.WalkToMine;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;

@Script.Manifest(name = "hMiner", description = "Mines ores anywhere F2P")
public class AIOMiner extends GraphScript<ClientContext> implements PaintListener, MessageListener {

    private Gui gui = null;
    private int oresMined = 0;
    private Style style = Style.MINING;
    private int startXp = 0, currXp = 0, startLevel = 0, currLevel = 0;

    @SuppressWarnings("serial")
    @Override
    public void start() {
        gui = new Gui();
        gui.setAioMiner(this);
        gui.setVisible(true);
    }

    public void setStartLevel(int startLevel) {
        this.startLevel = startLevel;
        this.currLevel = startLevel;
    }

    public void setCurrXp(int currXp) {
        this.currXp = currXp;
    }

    public void setCurrLevel(int currLevel) {
        this.currLevel = currLevel;
    }

    public void setStartXp(int startXp) {
        this.startXp = startXp;
        this.currXp = startXp;
    }

    public int getStartXp() {
        return startXp;
    }

    public int getStartLevel() {
        return startLevel;
    }

    @Override
    public void stop() {
        System.out.println("STOP CALLED");
        System.out.println("Ores mined " + oresMined);
        System.out.println("Time run " + formatTime(getRuntime()));
        //ctx.game.logout(true);
        super.stop(); //To change body of generated methods, choose Tools | Templates.
    }
    private final Color color1 = new Color(0, 68, 255, 153);
    private final Color color2 = new Color(0, 126, 255, 144);
    private final Color color3 = new Color(0, 0, 0);
    private final Color color4 = new Color(255, 255, 255);
    private final Color color5 = Color.BLACK;

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("Forte", 0, 18);
    private final Font font2 = new Font("Arial", 1, 16);

    @Override
    public void repaint(Graphics g1) {
        double timePassed = 0;
        if (getRuntime() > 0) {
            timePassed = 3600000D / getRuntime();
        }
        Graphics2D g = (Graphics2D) g1;
        g.setColor(color1);
        g.fillRoundRect(72, 26, 199, 120, 16, 16);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRoundRect(72, 26, 199, 120, 16, 16);
        g.setFont(font1);
        g.setColor(color3);
        g.drawString("hMiner", 147, 45);
        g.setColor(color4);
        g.drawString("hMiner", 148, 46);
        g.setFont(font2);
        g.setColor(color5);
        g.drawString("Time Running: " + formatTime(getRuntime()), 75, 67);
        if (style == Style.MINING) {
            g.drawString("Ores Mined: " + oresMined + (oresMined == 0 || getRuntime() <= 0 ? "" : " (" + (int) (oresMined * timePassed) + ")"), 75, 87);
        } else if (style == Style.SMELTING) {
            g.drawString("Bars Smelted: " + oresMined + (oresMined == 0 || getRuntime() <= 0 ? "" : " (" + (int) (oresMined * timePassed) + ")"), 75, 87);
        }
        g.drawString("XP Gained: " + (currXp - startXp)
                + (currXp == startXp ? "" : " (" + (int) (((currXp - startXp)) * timePassed) + ")"), 75, 107);
        g.drawString("Levels Gained: " + (currLevel - startLevel), 75, 127);
    }

    private String formatTime(final long time) {
        final int sec = (int) (time / 1000), h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
        return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s);
    }

    public void setupSmelt(Furnaces mine, Bars bar) {
        this.style = Style.SMELTING;
        setupNormalActions();
        chain.add(new BankBars(ctx, bar));
        chain.add(new WalkToBank(ctx, null, mine, Style.SMELTING, bar));
        chain.add(new WalkToFurnace(ctx, mine, bar));
        chain.add(new Smelt(ctx, mine, bar));
    }

    private void setupNormalActions() {
        chain.add(new KeyUsing(ctx));
        chain.add(new StatUpdaterTask(ctx, this));
        chain.add(new AntiPattern(ctx));
    }

    public void setupMining(Mines mine, Rocks ores, MiningStyle ms) {
        setupNormalActions();
        chain.add(new WalkToMine(ctx, mine));
        chain.add(new Mine(ctx, mine, ores));
        if (ms == MiningStyle.BANK) {
            chain.add(new BankOres(ctx));
            chain.add(new WalkToBank(ctx, mine, null, Style.MINING, null));
        } else {
            chain.add(new OreDrop(ctx, ms));
        }
    }

    @Override
    public void messaged(MessageEvent me) {
        if (me.text().contains("You manage to mine some ") || me.text().contains("retrieve a bar of")) {
            oresMined++;
        }
    }

    public void setupSleeps(ScheduledBreak[] toArray) {
        chain.add(new Sleeping(ctx, toArray));
    }
}
