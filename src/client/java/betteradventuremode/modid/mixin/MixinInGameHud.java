package betteradventuremode.modid.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;


@Mixin(InGameHud.class)
public class MixinInGameHud 
{
    /**
     * Overwrite the renderFood method to remove it.
     * @reason We removed the functionality of the food bar so we don't need it anymore.
     * @author Siminim
     */
    @Overwrite
    private void renderFood(DrawContext context, PlayerEntity player, int top, int right) 
    {
        // do nothing
    }

    
}
