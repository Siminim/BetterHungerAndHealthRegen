package betteradventuremode.modid;

import betteradventuremode.modid.network.FoodEatenPayload;
import betteradventuremode.modid.network.FoodEmptyPayload;
import betteradventuremode.modid.network.FoodTimeLeftPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
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

    public static final int maxFoodItems = 3;

    public ItemStack[] itemsEaten = new ItemStack[maxFoodItems];
    public int[] itemsEatenTime = new int[maxFoodItems];

    private final PlayerEntity player;

    public CustomHungerManager(PlayerEntity player) 
    {
        this.player = player;
    }

    @Override
    public void add(int hunger, float saturation)
    {
        player.heal(hunger);
    }

    @Override
    public void eat(FoodComponent food)
    {
        // add(food.nutrition(), food.saturation());
    }

    public void eat(FoodComponent food, ItemStack item)
    {
        for (int i = 0; i < maxFoodItems; i++)
        {
            if (itemsEaten[i] == null)
            {
                itemsEaten[i] = new ItemStack(item.getRegistryEntry());
                
                float ticks = food.saturation() * 20.0f;
                itemsEatenTime[i] = (int)ticks;

                updateMaxHealth();
                break;
            }
            else if (item.getItem() == itemsEaten[i].getItem())
            {
                float ticks = food.saturation() * 20.0f;
                itemsEatenTime[i] = (int)ticks;
                break;
            }
        }

        updateFoodOnClient();
    }

    public void updateMaxHealth()
    {
        float health = BetterAdventureMode.NEW_MAX_HEALTH;
        for (int i = 0; i < maxFoodItems; i++)
        {
            if (itemsEaten[i] == null)
                break;

            FoodComponent food = itemsEaten[i].get(DataComponentTypes.FOOD);
            health += (float)food.nutrition();

        }

        BetterAdventureMode.setPlayerMaxHealth(player, health);
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

    public void removeFoodInSlot(int slot)
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

    @Override
    public void update(PlayerEntity player)
    {
        foodCountDown();

        if (!player.getWorld().isClient())
            updateFoodTimesOnClient();
    }

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

    // Client Only
     public void setFoodEaten(ItemStack item, int slot)
    {
        if (player.getWorld().isClient())
            itemsEaten[slot] = item;
    }

    // Client Only
    public void setTimeLeft(int timeLeft, int slot)
    {
        if (player.getWorld().isClient())
            itemsEatenTime[slot] = timeLeft;
    }

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

    public float getExhaustion()
    {
        return 0;
    }

    public void setFoodLevel(int foodLevel)
    {}

    public void setSaturationLevel(float saturationLevel)
    {}

    public void setExhaustion(float exhaustion)
    {}

}
