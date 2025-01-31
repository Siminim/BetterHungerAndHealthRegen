package betteradventuremode.modid;

import java.util.IdentityHashMap;

import net.minecraft.component.type.FoodComponent;

public class ModdedFoodComponent
{
    private static final IdentityHashMap<FoodComponent, ModdedFoodComponent> FoodMap = new IdentityHashMap<>();
    
    public static ModdedFoodComponent getModdedFoodComponent(FoodComponent foodComponent)
    {
        return FoodMap.get(foodComponent);
    }

    private final float regenAmount;
    //private float regenTime;
    private final float health;
    private final float duration;

    public ModdedFoodComponent(FoodComponent foodComponent, float health, float regenAmount, float duration)
    {
        this.health = health;
        this.regenAmount = regenAmount;
        this.duration = duration * 20;

        FoodMap.put(foodComponent, this);
    }

    public float getRegeneration()
    {
        return regenAmount;
    }

    public float getHealthBoost()
    {
        return health;
    }

    public float getDuration()
    {
        return duration;
    }
}
