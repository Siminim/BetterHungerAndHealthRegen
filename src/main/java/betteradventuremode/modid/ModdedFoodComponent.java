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

    public static void onInitialize()
    {
        new ModdedFoodComponent(FoodComponents.APPLE, 1.5f);
        new ModdedFoodComponent(FoodComponents.BAKED_POTATO, 1.5f);
        new ModdedFoodComponent(FoodComponents.BEEF, 1.5f);
        new ModdedFoodComponent(FoodComponents.BEETROOT, 1.5f);
        new ModdedFoodComponent(FoodComponents.BEETROOT_SOUP, 1.5f);
        new ModdedFoodComponent(FoodComponents.BREAD, 1.5f);
        new ModdedFoodComponent(FoodComponents.CARROT, 1.5f);
        new ModdedFoodComponent(FoodComponents.CHICKEN, 1.5f);
        new ModdedFoodComponent(FoodComponents.CHORUS_FRUIT, 1.5f);
        new ModdedFoodComponent(FoodComponents.COD, 1.5f);
        new ModdedFoodComponent(FoodComponents.COOKED_BEEF, 1.5f);
        new ModdedFoodComponent(FoodComponents.COOKED_CHICKEN, 1.5f);
        new ModdedFoodComponent(FoodComponents.COOKED_COD, 1.5f);
        new ModdedFoodComponent(FoodComponents.COOKED_MUTTON, 1.5f);
        new ModdedFoodComponent(FoodComponents.COOKED_PORKCHOP, 1.5f);
        new ModdedFoodComponent(FoodComponents.COOKED_RABBIT, 1.5f);
        new ModdedFoodComponent(FoodComponents.COOKED_SALMON, 1.5f);
        new ModdedFoodComponent(FoodComponents.COOKIE, 1.5f);
        new ModdedFoodComponent(FoodComponents.DRIED_KELP, 1.5f);
        new ModdedFoodComponent(FoodComponents.ENCHANTED_GOLDEN_APPLE, 1.5f);
        new ModdedFoodComponent(FoodComponents.GLOW_BERRIES, 1.5f);
        new ModdedFoodComponent(FoodComponents.GOLDEN_APPLE, 1.5f);
        new ModdedFoodComponent(FoodComponents.GOLDEN_CARROT, 1.5f);
        new ModdedFoodComponent(FoodComponents.HONEY_BOTTLE, 1.5f);
        new ModdedFoodComponent(FoodComponents.MELON_SLICE, 1.5f);
        new ModdedFoodComponent(FoodComponents.MUSHROOM_STEW, 1.5f);
        new ModdedFoodComponent(FoodComponents.MUTTON, 1.5f);
        new ModdedFoodComponent(FoodComponents.OMINOUS_BOTTLE, 1.5f);
        new ModdedFoodComponent(FoodComponents.POISONOUS_POTATO, 1.5f);
        new ModdedFoodComponent(FoodComponents.PORKCHOP, 1.5f);
        new ModdedFoodComponent(FoodComponents.POTATO, 1.5f);
        new ModdedFoodComponent(FoodComponents.PUFFERFISH, 1.5f);
        new ModdedFoodComponent(FoodComponents.PUMPKIN_PIE, 1.5f);
        new ModdedFoodComponent(FoodComponents.RABBIT, 1.5f);
        new ModdedFoodComponent(FoodComponents.RABBIT_STEW, 1.5f);
        new ModdedFoodComponent(FoodComponents.ROTTEN_FLESH, 1.5f);
        new ModdedFoodComponent(FoodComponents.SALMON, 1.5f);
        new ModdedFoodComponent(FoodComponents.SPIDER_EYE, 1.5f);
        new ModdedFoodComponent(FoodComponents.SUSPICIOUS_STEW, 1.5f);
        new ModdedFoodComponent(FoodComponents.SWEET_BERRIES, 1.5f);
        new ModdedFoodComponent(FoodComponents.TROPICAL_FISH, 1.5f);
    }

    private float regenAmount;
    private float regenTime;
    private int healthMod;
    private int timeTicks;

    public ModdedFoodComponent(FoodComponent foodComponent, float regenAmount)
    {
        this.regenAmount = regenAmount;
        FoodMap.put(foodComponent, this);
    }

    public float getRegeneration()
    {
        return regenAmount;
    }
}
