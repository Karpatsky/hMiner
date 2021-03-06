package com.fallboo.miner.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.powerbot.script.Area;

import org.powerbot.script.Tile;

public enum Mines {

    VARROCK_WEST("Varrock West", new Tile(3179, 3369), new Tile(3187, 3439),
            new Tile[]{new Tile(3186, 3441, 0),
                new Tile(3182, 3431, 0),
                new Tile(3172, 3429, 0),
                new Tile(3170, 3419, 0),
                new Tile(3169, 3409, 0),
                new Tile(3168, 3399, 0),
                new Tile(3173, 3390, 0),
                new Tile(3176, 3380, 0),
                new Tile(3179, 3370, 0)},
            new Rocks[]{Rocks.COPPER, Rocks.TIN, Rocks.IRON, Rocks.SILVER}, new Area(
                    new Tile(3181, 3381, 0),
                    new Tile(3173, 3374, 0),
                    new Tile(3165, 3368, 0),
                    new Tile(3173, 3362, 0),
                    new Tile(3183, 3363, 0),
                    new Tile(3191, 3369, 0),
                    new Tile(3189, 3379, 0),
                    new Tile(3179, 3382, 0)
            )
    ),
    VARROCK_EAST("Varrock East", new Tile(3284, 3365), new Tile(3252, 3418),
            new Tile[]{new Tile(3253, 3421, 0),
                new Tile(3259, 3429, 0),
                new Tile(3269, 3430, 0),
                new Tile(3279, 3429, 0),
                new Tile(3288, 3424, 0),
                new Tile(3291, 3414, 0),
                new Tile(3292, 3404, 0),
                new Tile(3291, 3394, 0),
                new Tile(3294, 3384, 0),
                new Tile(3291, 3374, 0),
                new Tile(3285, 3366, 0)},
            new Rocks[]{Rocks.COPPER, Rocks.TIN, Rocks.IRON}, new Area(
                    new Tile(3281, 3374, 0),
                    new Tile(3275, 3366, 0),
                    new Tile(3278, 3356, 0),
                    new Tile(3288, 3354, 0),
                    new Tile(3298, 3357, 0),
                    new Tile(3294, 3367, 0),
                    new Tile(3292, 3373, 0)
            )
    ), LUMBRIDGE_EAST("Lumbridge East", new Tile(3229, 3148), new Tile(3270, 3168),
            new Tile[]{new Tile(3271, 3168, 0),
                new Tile(3262, 3173, 0),
                new Tile(3253, 3170, 0),
                new Tile(3238, 3164, 0),
                new Tile(3233, 3152, 0),
                new Tile(3228, 3147, 0)},
            new Rocks[]{Rocks.COPPER, Rocks.TIN,}, new Area(
                    new Tile(3219, 3153, 0),
                    new Tile(3219, 3142, 0),
                    new Tile(3236, 3142, 0),
                    new Tile(3236, 3153, 0)
            )
    ),
    LUMBRIDGE_WEST("Lumbridge West", new Tile(3146, 3146), new Tile(3092, 3245),
            new Tile[]{new Tile(3094, 3243, 0),
                new Tile(3099, 3242, 0),
                new Tile(3103, 3239, 0),
                new Tile(3106, 3235, 0),
                new Tile(3109, 3231, 0),
                new Tile(3112, 3227, 0),
                new Tile(3115, 3223, 0),
                new Tile(3118, 3219, 0),
                new Tile(3122, 3216, 0),
                new Tile(3126, 3213, 0),
                new Tile(3129, 3209, 0),
                new Tile(3133, 3206, 0),
                new Tile(3136, 3202, 0),
                new Tile(3138, 3197, 0),
                new Tile(3140, 3192, 0),
                new Tile(3143, 3188, 0),
                new Tile(3144, 3183, 0),
                new Tile(3145, 3178, 0),
                new Tile(3146, 3173, 0),
                new Tile(3148, 3168, 0),
                new Tile(3148, 3163, 0),
                new Tile(3148, 3158, 0),
                new Tile(3148, 3153, 0),
                new Tile(3146, 3148, 0)},
            new Rocks[]{Rocks.COAL, Rocks.MITHRIL}, new Area(
                    new Tile(3145, 3152, 0),
                    new Tile(3141, 3144, 0),
                    new Tile(3142, 3141, 0),
                    new Tile(3146, 3140, 0),
                    new Tile(3151, 3145, 0),
                    new Tile(3154, 3150, 0)
            )
    ), AL_KHARID("Al Kharid", new Tile(3300, 3310), new Tile(3270, 3168),
            new Tile[]{new Tile(3270, 3170, 0),
                new Tile(3273, 3174, 0),
                new Tile(3276, 3178, 0),
                new Tile(3279, 3182, 0),
                new Tile(3281, 3187, 0),
                new Tile(3281, 3192, 0),
                new Tile(3281, 3197, 0),
                new Tile(3282, 3202, 0),
                new Tile(3282, 3207, 0),
                new Tile(3282, 3212, 0),
                new Tile(3282, 3217, 0),
                new Tile(3279, 3222, 0),
                new Tile(3280, 3227, 0),
                new Tile(3282, 3232, 0),
                new Tile(3285, 3235, 0),
                new Tile(3290, 3237, 0),
                new Tile(3291, 3242, 0),
                new Tile(3293, 3247, 0),
                new Tile(3294, 3252, 0),
                new Tile(3295, 3257, 0),
                new Tile(3296, 3262, 0),
                new Tile(3294, 3267, 0),
                new Tile(3293, 3272, 0),
                new Tile(3297, 3275, 0),
                new Tile(3300, 3279, 0),
                new Tile(3301, 3284, 0),
                new Tile(3299, 3289, 0),
                new Tile(3298, 3294, 0),
                new Tile(3298, 3299, 0),
                new Tile(3299, 3304, 0)},
            new Rocks[]{Rocks.COAL, Rocks.SILVER, Rocks.IRON, Rocks.MITHRIL}, new Area(
                    new Tile(3300, 3321, 0),
                    new Tile(3294, 3313, 0),
                    new Tile(3294, 3313, 0),
                    new Tile(3291, 3305, 0),
                    new Tile(3293, 3299, 0),
                    new Tile(3295, 3297, 0),
                    new Tile(3293, 3285, 0),
                    new Tile(3297, 3282, 0),
                    new Tile(3308, 3287, 0),
                    new Tile(3307, 3299, 0),
                    new Tile(3308, 3307, 0),
                    new Tile(3308, 3319, 0),
                    new Tile(3307, 3321, 0)
            )
    );

    private String name;
    private Tile location, bank;
    private Rocks[] ores;
    private Tile[] bankToLocation;
    private Area mineArea;

    Mines(String name, Tile location, Tile bankLocation, Tile bankToLocation[],
            Rocks[] ores, Area mineArea) {
        this.name = name;
        this.location = location;
        this.bank = bankLocation;
        this.ores = ores;
        this.bankToLocation = bankToLocation;
        this.mineArea = mineArea;
    }

    public Area getMineArea() {
        return mineArea;
    }

    public Tile getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public Rocks[] getOres() {
        return ores;
    }

    public Tile getBank() {
        return bank;
    }

    public Tile[] getBankToLocation() {
        return bankToLocation;
    }

    public Tile[] getLocationToBank() {
        ArrayList<Tile> rev = new ArrayList<Tile>();
        rev.addAll(Arrays.asList(getBankToLocation()));
        Collections.reverse(rev);
        return rev.toArray(new Tile[rev.size()]);
    }
}
