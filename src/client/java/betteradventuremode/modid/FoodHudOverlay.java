package betteradventuremode.modid;


import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class FoodHudOverlay implements HudRenderCallback
{
    private static final Identifier CONTAINER_BOX = Identifier.of("minecraft", "hud/hotbar_offhand_left");

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter)
    {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.getCameraEntity() instanceof PlayerEntity playerEntity ? playerEntity : null;
        
        renderFoodContainers(drawContext, player);
        
    }

    private void renderFoodContainers(DrawContext drawContext, PlayerEntity player)
    {
        int x = drawContext.getScaledWindowWidth() / 2;
        int y = drawContext.getScaledWindowHeight();
        
        CustomHungerManager customHungerManager = (CustomHungerManager)player.getHungerManager();

        for (int i = 0; i < CustomHungerManager.maxFoodItems; i++)
        {
            int startX = x + 41 + (i * 16);
            int startY = y - 47;

            
            drawContext.drawGuiTexture(CONTAINER_BOX, startX, startY, 22, 18);
            
            if (customHungerManager.itemsEaten[i] == null)
                continue;

            float ticks = customHungerManager.itemsEatenTime[i];
            float seconds = ticks / 20.0f;
            float minutes = seconds / 60.0f;

            String time;

            if (minutes < 1.0f)
                time = String.valueOf((int)seconds) + "s";
            else
                time = String.valueOf((int)minutes) + "m";

            drawContext.getMatrices().push();
            drawContext.getMatrices().translate(startX + 8, startY+ 12, 0.0f);
            drawContext.getMatrices().scale(0.8f, 0.8f, 1.0f);
            drawContext.getMatrices().translate(-startX - 8, -startY - 12, 0.0f);
            
            drawContext.drawItem(customHungerManager.itemsEaten[i], startX, startY);
            drawContext.drawItemInSlot(MinecraftClient.getInstance().textRenderer, customHungerManager.itemsEaten[i], startX + 1, startY + 1, time);
            
            drawContext.getMatrices().pop();                
            

        }
    }
}
