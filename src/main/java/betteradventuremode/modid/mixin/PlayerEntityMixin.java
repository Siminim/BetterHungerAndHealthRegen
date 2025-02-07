package betteradventuremode.modid.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import betteradventuremode.modid.CustomHungerManager;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin
{
    @Shadow
    protected HungerManager hungerManager;
    protected CustomHungerManager customHungerManager;

    
    @Inject(at = @At("RETURN"), method = "<init>")
    private void onInit(CallbackInfo info)
    {
        customHungerManager = new CustomHungerManager((PlayerEntity) (Object) this);
         hungerManager = customHungerManager;
    }

    // In case we need a different way to not use the original eat function.
    // @Redirect (method = "eatFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;eat(Lnet/minecraft/component/type/FoodComponent;)V"))
    // private void replaceHungerEat(HungerManager hm, FoodComponent food)
    // {
    // }

    @Inject(method = "eatFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;eat(Lnet/minecraft/component/type/FoodComponent;)V"))
    private void replaceHungerEat(World world, ItemStack stack, FoodComponent foodComponent, CallbackInfoReturnable<ItemStack> cir) 
    {
        customHungerManager.eat(foodComponent, stack);
    }

    @Inject(method = "damage", at = @At(value = "TAIL"))
    private void onDamage(CallbackInfoReturnable<ItemStack> cir)
    {
        if (!cir.getReturnValueZ())
            return;
        
        customHungerManager.applyDamageRegenerationPenalty();
    }

    @ModifyReturnValue(method = "canConsume", at = @At(value = "RETURN"))
    public boolean replaceCanConsume(boolean ignoreHunger) 
    {
        int count = customHungerManager.itemsEaten.length - 1;
        
        if (count < 0)
            return false;

        else if (customHungerManager.itemsEaten[count] == null)
        {
            return true;
        }
        else
        {
            ItemStack mainHand = ((PlayerEntity) (Object) this).getEquippedStack(EquipmentSlot.MAINHAND);
            ItemStack offHand = ((PlayerEntity) (Object) this).getEquippedStack(EquipmentSlot.OFFHAND);

            FoodComponent food = mainHand.get(DataComponentTypes.FOOD);
            if (food == null)
                food = offHand.get(DataComponentTypes.FOOD);

            if (food.canAlwaysEat())
                return true;

            for (int i = 0; i < CustomHungerManager.MaxFoodItems; i++)
            {
                if (food != null && food == customHungerManager.itemsEaten[i].get(DataComponentTypes.FOOD))
                    return true;
            }
        }

        return false;
    }


}
