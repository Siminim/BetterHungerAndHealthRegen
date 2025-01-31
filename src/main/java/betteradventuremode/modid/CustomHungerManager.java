package betteradventuremode.modid;

import betteradventuremode.modid.network.Payloads.FoodEatenPayload;
import betteradventuremode.modid.network.Payloads.FoodEmptyPayload;
import betteradventuremode.modid.network.Payloads.FoodTimeLeftPayload;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;

public class CustomHungerManager extends HungerManager
{
    private static final int MAX_FOOD_LEVEL = 20;
    private static final float MAX_SATURATION_LEVEL = MAX_FOOD_LEVEL;
    
    // should be in a config file
    public static final int maxFoodItems = 3;
    public static final float NEW_MAX_HEALTH = 6.0f; 
    public static final float regenerationSeconds = 30.0f;

    public ItemStack[] itemsEaten = new ItemStack[maxFoodItems];
    public int[] itemsEatenTime = new int[maxFoodItems];

    private float regenTime = 0.0f;

    private final PlayerEntity player;

    public CustomHungerManager(PlayerEntity player) 
    {
        this.player = player;
    }

    @Override
    public void update(PlayerEntity player)
    {
        foodCountDown();
        regenerationCounter();

        if (!player.getWorld().isClient())
            updateFoodTimesOnClient();
    }

    public void eat(FoodComponent food, ItemStack item)
    {
        ModdedFoodComponent mfood = ModdedFoodComponent.getModdedFoodComponent(food); 
        if (mfood == null)
            return;

        for (int i = 0; i < maxFoodItems; i++)
        {
            if (itemsEaten[i] == null)
            {
                itemsEaten[i] = new ItemStack(item.getRegistryEntry());
                itemsEatenTime[i] = (int)mfood.getDuration();

                updateMaxHealth();
                break;
            }
            else if (item.getItem() == itemsEaten[i].getItem())
            {
                itemsEatenTime[i] = (int)mfood.getDuration();
                break;
            }
        }
        
        updateFoodOnClient();
    }

    private void foodCountDown()
    {
        for (int i = 0; i < maxFoodItems; i++)
        {
            if (itemsEaten[i] == null)
                break;
            
            if (itemsEatenTime[i] > 0)
                itemsEatenTime[i] -= 1;
            else
            {
                removeFoodInSlot(i);
                i--;
            }
        }
    }

    private void removeFoodInSlot(int slot)
    {
        itemsEaten[slot] = null;

        for (int i = slot + 1; i < maxFoodItems; i++)
        {
            itemsEaten[i - 1] = itemsEaten[i];
            itemsEatenTime[i - 1] = itemsEatenTime[i]; 
            itemsEaten[i] = null;
        }

        if (!player.getWorld().isClient())
        {
            updateFoodOnClient();
            updateMaxHealth();
        }
    }

    public void clearFoods()
    {
        for (int i = 0; i < maxFoodItems; i++)
        {
            itemsEaten[i] = null;
            itemsEatenTime[i] = 0;
        }
        
        if (!player.getWorld().isClient())
        {
            updateFoodOnClient();
            updateFoodTimesOnClient();
            updateMaxHealth();
        }
    }

    // ------------------------------------------------------------------
    // -------------------- Health Related methods ----------------------
    // ------------------------------------------------------------------

    public void updateMaxHealth()
    {
        float health = NEW_MAX_HEALTH;

        for (int i = 0; i < maxFoodItems; i++)
        {
            if (itemsEaten[i] == null)
                break;

            FoodComponent food = itemsEaten[i].get(DataComponentTypes.FOOD);
            ModdedFoodComponent mfood = ModdedFoodComponent.getModdedFoodComponent(food);
            health += mfood.getHealthBoost();
        }
        setPlayerMaxHealth(player, health);
    }

    private void setPlayerMaxHealth(PlayerEntity player, float health) 
	{
		// Access the max health attribute and set its base value
        player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(health);

        // Ensure the player's health is not greater than the new max health
        if (player.getHealth() > health) 
		{
            player.setHealth(health);
        }
	}

    // Takes seconds to regenerate health starting from when you first lose health.
    // I want to implement a system that resets (or lowers) the counter when you take damage.
    // Sturdy variable in foods which would help with getting hit and losing regeneration time.
    private void regenerationCounter()
    {
        regenTime += 1.0f;

        if (regenTime < regenerationSeconds * 20.0f) // Ticks
            return;

        float regen = 0.0f;
        for (int i = 0; i < maxFoodItems; i++)
        {
            if (itemsEaten[i] == null)
                break;

            FoodComponent food = itemsEaten[i].get(DataComponentTypes.FOOD);
            regen += ModdedFoodComponent.getModdedFoodComponent(food).getRegeneration();
        }
        player.heal(regen);
        regenTime = 0.0f;
    }

    // ------------------------------------------------------------------
    // ----------------------- Static methods ---------------------------
    // ------------------------------------------------------------------

    public static void updatePlayerHungerManager(PlayerEntity player)
	{
		CustomHungerManager hungerManager = (CustomHungerManager)player.getHungerManager();
		hungerManager.updateFoodOnClient();
		hungerManager.updateFoodTimesOnClient();
		hungerManager.updateMaxHealth();
	}

	public static void setPlayerHungerManagerEvents()
	{
		// On Player Respawn
		ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> 
		{
			CustomHungerManager.updatePlayerHungerManager(newPlayer);
		});

		// On Player Join Server
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> 
		{
			CustomHungerManager.updatePlayerHungerManager(handler.getPlayer());
        });
	}

    // ------------------------------------------------------------------
    // -------------------- Send Packets to Client ----------------------
    // ------------------------------------------------------------------
    
    public void updateFoodOnClient()
    {
        if (player.getWorld().isClient())
            return;

        for (int i = 0; i < maxFoodItems; i++)
        {
            if (itemsEaten[i] == null)
                ServerPlayNetworking.send((ServerPlayerEntity)player, new FoodEmptyPayload(i));
            else
                ServerPlayNetworking.send((ServerPlayerEntity)player, new FoodEatenPayload(itemsEaten[i], i));
        }
    }

    public void updateFoodTimesOnClient()
    {
        if (player.getWorld().isClient())
            return;

        for (int i = 0; i < maxFoodItems; i++)
        {
            if (itemsEatenTime[i] <= 0 || itemsEatenTime[0] % 20 != 0)
                break;

            ServerPlayNetworking.send((ServerPlayerEntity)player, new FoodTimeLeftPayload(itemsEatenTime[i], i));
        }
    }

    // ------------------------------------------------------------------
    // --------------------- Read and Write Data ------------------------
    // ------------------------------------------------------------------

    @Override
    public void readNbt(NbtCompound nbt)
    {        
        NbtList nbtList = nbt.getList("FoodItems", NbtElement.COMPOUND_TYPE);
        
        int maxSize = maxFoodItems < nbtList.size() ? maxFoodItems : nbtList.size();
        
        for (int i = 0; i < maxSize; i++)
        {
            NbtCompound nbtCompound = nbtList.getCompound(i);

            int timeLeft = nbtCompound.getInt("TimeLeft");
            ItemStack itemStack = (ItemStack)ItemStack.fromNbt(this.player.getRegistryManager(), nbtCompound).orElse(ItemStack.EMPTY);

            itemsEaten[i] = itemStack;
            itemsEatenTime[i] = timeLeft;
        }
        updateMaxHealth();
    }

    @Override
    public void writeNbt(NbtCompound nbt)
    {
        if (itemsEaten[0] == null)
            return;
           
        NbtList nbtList = new NbtList();
        
        for (int i = 0; i < maxFoodItems; i++)
        {
            if (itemsEaten[i] == null)
                break;

            NbtCompound nbtCompound = new NbtCompound();
            nbtCompound.putInt("TimeLeft", itemsEatenTime[i]);
            nbtList.add(this.itemsEaten[i].encode(this.player.getRegistryManager(), nbtCompound));
        }

        nbt.put("FoodItems", nbtList);
    }

    // ------------------------------------------------------------------
    // --------------------- Client Only Methods ------------------------
    // ------------------------------------------------------------------

     public void setFoodEaten(ItemStack item, int slot)
    {
        if (player.getWorld().isClient())
            itemsEaten[slot] = item;
    }

    public void setTimeLeft(int timeLeft, int slot)
    {
        if (player.getWorld().isClient())
            itemsEatenTime[slot] = timeLeft;
    }


    // ------------------------------------------------------------------
    // --------------------- Disable Old Methods ------------------------
    // ------------------------------------------------------------------
    
    @Override
    public void add(int hunger, float saturation)
    {}

    @Override
    public void eat(FoodComponent food)
    {}

    @Override
    public int getFoodLevel() 
    {
        return MAX_FOOD_LEVEL;
    }

    @Override
    public int getPrevFoodLevel()
    {
        return MAX_FOOD_LEVEL;
    }

    @Override
    public float getSaturationLevel() 
    {
        return MAX_SATURATION_LEVEL;
    }

    @Override
    public boolean isNotFull()
    {
        return false;
    }

    @Override
    public void addExhaustion(float exhaustion)
    {}

    @Override
    public float getExhaustion()
    {
        return 0;
    }
    
    @Override
    public void setFoodLevel(int foodLevel)
    {}

    @Override
    public void setSaturationLevel(float saturationLevel)
    {}

    @Override
    public void setExhaustion(float exhaustion)
    {}
}
