package betteradventuremode.modid.itemoverrides;

import java.util.List;

import betteradventuremode.modid.ModdedFoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class FoodTooltipItem extends Item
{
    private String healthString;
    private String regenerationString;
    private String durationString; 


    public FoodTooltipItem(Settings settings, ModdedFoodComponent foodComponent) 
    {
        super(settings);
        healthString =          "Health : " + String.valueOf(foodComponent.getHealthBoost());
        regenerationString =    "Regen : " + String.valueOf(foodComponent.getRegeneration());

        float time = (foodComponent.getDuration() / 20) / 60; // Ticks -> Seconds -> Minutes
        durationString =        "Time    : " + String.valueOf((int)time) + "m";
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type)
    {
        tooltip.add(Text.translatable(healthString).formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable(regenerationString).formatted(Formatting.LIGHT_PURPLE));
        tooltip.add(Text.translatable(durationString).formatted(Formatting.GOLD));
    }
}
