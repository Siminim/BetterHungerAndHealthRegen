package betteradventuremode.modid.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.ItemStack;

@Mixin(FoodComponent.Builder.class)
public class FoodComponentBuilderMixin 
{
    @Shadow
    private int nutrition;

    @Shadow
	private float saturationModifier;
	
    @Shadow
    private boolean canAlwaysEat;
	
    @Shadow
    private float eatSeconds = 1.6F;
    
    @Shadow
    private Optional<ItemStack> usingConvertsTo = Optional.empty();
	
    @Shadow
    private final ImmutableList.Builder<FoodComponent.StatusEffectEntry> effects = ImmutableList.builder();

    @Unique
    private float regenAmount;

    @ModifyReturnValue(method = "build", at = @At("RETURN"))
    private FoodComponent onBuild(FoodComponent original) 
    {
		  return new FoodComponent(this.nutrition, this.saturationModifier, this.canAlwaysEat, this.eatSeconds, this.usingConvertsTo, this.effects.build());
    }

    public FoodComponent moddedBuild()
    {
         return new FoodComponent(this.nutrition, this.saturationModifier, this.canAlwaysEat, this.eatSeconds, this.usingConvertsTo, this.effects.build());
    }
}
