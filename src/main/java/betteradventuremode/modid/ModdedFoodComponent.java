package betteradventuremode.modid;

import java.util.WeakHashMap;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;

public class ModdedFoodComponent
{
    private static final WeakHashMap<FoodComponent, ModdedFoodComponent> FoodMap = new WeakHashMap<>();
    
    public static ModdedFoodComponent getModdedFoodComponent(FoodComponent foodComponent)
    {
        return FoodMap.get(foodComponent);
    }

    // Nutrition is used for the health increase. Give health in 2's.
    // Satuartion is used on the time in seconds. Giving an extra 60s feels better.
    public static void onInitialize()
    {
        // new ModdedFoodComponent(FoodComponents.APPLE, 2, 1.5f, 360);
        // new ModdedFoodComponent(FoodComponents.BAKED_POTATO, 4, 1.5f, 960);
        // new ModdedFoodComponent(FoodComponents.BEEF, 2, 1.5f, 360);
        // new ModdedFoodComponent(FoodComponents.BEETROOT, 2, 1.5f, 360);
        // new ModdedFoodComponent(FoodComponents.BEETROOT_SOUP, 8, 1.5f, 1860);
        //new ModdedFoodComponent(FoodComponents.BREAD, 4, 1.5f, 660);
        // new ModdedFoodComponent(FoodComponents.CARROT, 2, 1.5f, 360);
        // new ModdedFoodComponent(FoodComponents.CHICKEN, 4, 1.5f, 360);
        // new ModdedFoodComponent(FoodComponents.CHORUS_FRUIT, 2, 1.5f, 180); // Chorus Fruit shouldn't be used for this food system
        // new ModdedFoodComponent(FoodComponents.COD, 2, 1.5f, 360);
        // new ModdedFoodComponent(FoodComponents.COOKED_BEEF, 6, 1.5f, 1260);
        // new ModdedFoodComponent(FoodComponents.COOKED_CHICKEN, 4, 1.5f, 960);
        // new ModdedFoodComponent(FoodComponents.COOKED_COD, 4, 1.5f, 960);
        // new ModdedFoodComponent(FoodComponents.COOKED_MUTTON, 4, 1.5f, 960);
        // new ModdedFoodComponent(FoodComponents.COOKED_PORKCHOP, 6, 1.5f, 1260);
        // new ModdedFoodComponent(FoodComponents.COOKED_RABBIT, 4, 1.5f, 960);
        // new ModdedFoodComponent(FoodComponents.COOKED_SALMON, 4, 1.5f, 960);
        // new ModdedFoodComponent(FoodComponents.COOKIE, 2, 1.5f, 180);
        // new ModdedFoodComponent(FoodComponents.DRIED_KELP, 2, 1.5f, 120);
        // new ModdedFoodComponent(FoodComponents.ENCHANTED_GOLDEN_APPLE, 6, 1.5f, 660);
        // new ModdedFoodComponent(FoodComponents.GLOW_BERRIES, 2, 1.5f, 360);
        // new ModdedFoodComponent(FoodComponents.GOLDEN_APPLE, 4, 1.5f, 660);
        // new ModdedFoodComponent(FoodComponents.GOLDEN_CARROT, 6, 1.5f, 660);
        // new ModdedFoodComponent(FoodComponents.HONEY_BOTTLE, 2, 1.5f, 360);
        // new ModdedFoodComponent(FoodComponents.MELON_SLICE, 2, 1.5f, 180);
        // new ModdedFoodComponent(FoodComponents.MUSHROOM_STEW, 6, 1.5f, 1860);
        // new ModdedFoodComponent(FoodComponents.MUTTON, 4, 1.5f, 360);
        // new ModdedFoodComponent(FoodComponents.OMINOUS_BOTTLE, 2, 1.5f, 10); // Ominous Bottle shouldn't be used for this food system
        new ModdedFoodComponent(FoodComponents.POISONOUS_POTATO, 2, 1.5f, 120);
        // new ModdedFoodComponent(FoodComponents.PORKCHOP, 2, 1.5f, 1260);
        // new ModdedFoodComponent(FoodComponents.POTATO, 2, 1.5f, 360);
        // new ModdedFoodComponent(FoodComponents.PUFFERFISH, 2, 1.5f, 120);
        // new ModdedFoodComponent(FoodComponents.PUMPKIN_PIE, 6, 1.5f, 1260);
        // new ModdedFoodComponent(FoodComponents.RABBIT, 2, 1.5f, 360);
        // new ModdedFoodComponent(FoodComponents.RABBIT_STEW, 6, 1.5f, 1860);
        new ModdedFoodComponent(FoodComponents.ROTTEN_FLESH, 2, 1.5f, 60);
        // new ModdedFoodComponent(FoodComponents.SALMON, 2, 1.5f, 360);
        new ModdedFoodComponent(FoodComponents.SPIDER_EYE, 2, 1.5f, 60);
        // new ModdedFoodComponent(FoodComponents.SUSPICIOUS_STEW, 2, 1.5f, 360); // Figure out what to do wit Suspicious Stew
        // new ModdedFoodComponent(FoodComponents.SWEET_BERRIES, 2, 1.5f, 180);
        // new ModdedFoodComponent(FoodComponents.TROPICAL_FISH, 2, 1.5f, 360);
    }

    private float regenAmount;
    //private float regenTime;
    private int health;
    private int duration;

    public ModdedFoodComponent(FoodComponent foodComponent, int health, float regenAmount, int duration)
    {
        this.regenAmount = regenAmount;
        this.health = health;
        this.duration = duration * 20;

        FoodMap.put(foodComponent, this);
    }

    public float getRegeneration()
    {
        return regenAmount;
    }

    public int getHealthBoost()
    {
        return health;
    }

    public int getDuration()
    {
        return duration;
    }
}
