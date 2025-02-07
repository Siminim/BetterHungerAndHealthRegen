package betteradventuremode.modid.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import betteradventuremode.modid.ModdedFoodComponent;
import betteradventuremode.modid.itemoverrides.FoodAliasedBlockTooltipItem;
import betteradventuremode.modid.itemoverrides.FoodTooltipItem;
import betteradventuremode.modid.itemoverrides.HoneyBottleTooltipItem;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;

@Mixin(Items.class)
public class FoodItemsMixin 
{
    // ---------------------------------------------------------------------
    // ----------------------------- Snacks --------------------------------
    // ---------------------------------------------------------------------

    @Shadow
    public static final Item COOKIE = Items.register("cookie", new FoodTooltipItem(new Item.Settings().maxCount(32).food(FoodComponents.COOKIE),
                                                                                      new ModdedFoodComponent(FoodComponents.COOKIE, 1, 2.0f, 180)));

    @Shadow
    public static final Item DRIED_KELP = Items.register("dried_kelp", new FoodTooltipItem(new Item.Settings().maxCount(32).food(FoodComponents.DRIED_KELP),
                                                                                            new ModdedFoodComponent(FoodComponents.DRIED_KELP, 1, 1.0f, 120)));
    
    // @Shadow
    // public static final Item CHORUS_FRUIT = Items.register("chorus_fruit", new ChorusFruitItem(new Item.Settings().maxCount(64).food(FoodComponents.CHORUS_FRUIT)));

    @Shadow
    public static final Item SWEET_BERRIES = Items.register(
		"sweet_berries", new FoodAliasedBlockTooltipItem(Blocks.SWEET_BERRY_BUSH, new Item.Settings().maxCount(32).food(FoodComponents.SWEET_BERRIES),
                                                      new ModdedFoodComponent(FoodComponents.SWEET_BERRIES, 1, 1.0f, 180)));
	
    @Shadow
    public static final Item GLOW_BERRIES = Items.register(
		"glow_berries", new FoodAliasedBlockTooltipItem(Blocks.CAVE_VINES, new Item.Settings().maxCount(32).food(FoodComponents.GLOW_BERRIES),
                                                      new ModdedFoodComponent(FoodComponents.GLOW_BERRIES, 1, 1.0f, 180)));

    @Shadow
    public static final Item HONEY_BOTTLE = Items.register(
		"honey_bottle", new HoneyBottleTooltipItem(new Item.Settings().recipeRemainder(Items.GLASS_BOTTLE).food(FoodComponents.HONEY_BOTTLE).maxCount(32),
                                                  new ModdedFoodComponent(FoodComponents.HONEY_BOTTLE, 1, 3.0f, 180)));

    // ---------------------------------------------------------------------
    // ---------------------------- Raw Food -------------------------------
    // ---------------------------------------------------------------------
    
    @Shadow
    public static final Item MELON_SLICE = Items.register("melon_slice", new FoodTooltipItem(new Item.Settings().maxCount(16).food(FoodComponents.MELON_SLICE),
                                                                                                new ModdedFoodComponent(FoodComponents.MELON_SLICE, 2, 1.0f, 360)));
    
    @Shadow
    public static final Item APPLE = Items.register("apple", new FoodTooltipItem(new Item.Settings().maxCount(16).food(FoodComponents.APPLE),
                                                                                    new ModdedFoodComponent(FoodComponents.APPLE, 2, 1.0f, 360)));

    @Shadow
    public static final Item BEETROOT = Items.register("beetroot", new FoodTooltipItem(new Item.Settings().maxCount(16).food(FoodComponents.BEETROOT),
                                                                                            new ModdedFoodComponent(FoodComponents.BEETROOT, 2, 1.0f, 360)));

    @Shadow
    public static final Item CARROT = Items.register("carrot", new FoodAliasedBlockTooltipItem(Blocks.CARROTS, new Item.Settings().maxCount(16).food(FoodComponents.CARROT),
                                                                                                    new ModdedFoodComponent(FoodComponents.CARROT, 2, 1.0f, 360)));

    @Shadow
    public static final Item POTATO = Items.register("potato", new FoodAliasedBlockTooltipItem(Blocks.POTATOES, new Item.Settings().maxCount(16).food(FoodComponents.POTATO),
                                                                                                  new ModdedFoodComponent(FoodComponents.POTATO, 2, 1.0f, 360)));

    // @Shadow
    // public static final Item POISONOUS_POTATO = Items.register("poisonous_potato", new Item(new Item.Settings().maxCount(16).food(FoodComponents.POISONOUS_POTATO)));

    @Shadow
    public static final Item PORKCHOP = Items.register("porkchop", new FoodTooltipItem(new Item.Settings().maxCount(16).food(FoodComponents.PORKCHOP),
                                                                                            new ModdedFoodComponent(FoodComponents.PORKCHOP, 2, 0.5f, 360)));
	
    @Shadow
    public static final Item BEEF = Items.register("beef", new FoodTooltipItem(new Item.Settings().maxCount(16).food(FoodComponents.BEEF),
                                                                                    new ModdedFoodComponent(FoodComponents.BEEF, 2, 0.5f, 360)));

    @Shadow
    public static final Item CHICKEN = Items.register("chicken", new FoodTooltipItem(new Item.Settings().maxCount(16).food(FoodComponents.CHICKEN),
                                                                                         new ModdedFoodComponent(FoodComponents.CHICKEN, 2, 0.5f, 360)));

    @Shadow
    public static final Item MUTTON = Items.register("mutton", new FoodTooltipItem(new Item.Settings().maxCount(16).food(FoodComponents.MUTTON),
                                                                                         new ModdedFoodComponent(FoodComponents.MUTTON, 2, 0.5f, 360)));

    @Shadow
    public static final Item RABBIT = Items.register("rabbit", new FoodTooltipItem(new Item.Settings().maxCount(16).food(FoodComponents.RABBIT),
                                                                                         new ModdedFoodComponent(FoodComponents.RABBIT, 2, 0.5f, 360)));

    @Shadow
    public static final Item COD = Items.register("cod", new FoodTooltipItem(new Item.Settings().maxCount(16).food(FoodComponents.COD),
                                                                                 new ModdedFoodComponent(FoodComponents.COD, 2, 0.5f, 360)));
	
    @Shadow
    public static final Item SALMON = Items.register("salmon", new FoodTooltipItem(new Item.Settings().maxCount(16).food(FoodComponents.SALMON),
                                                                                        new ModdedFoodComponent(FoodComponents.SALMON, 2, 0.5f, 360)));
	
    @Shadow
    public static final Item TROPICAL_FISH = Items.register("tropical_fish", new FoodTooltipItem(new Item.Settings().maxCount(16).food(FoodComponents.TROPICAL_FISH),
                                                                                                    new ModdedFoodComponent(FoodComponents.TROPICAL_FISH, 2, 0.5f, 360)));
	
    @Shadow
    public static final Item PUFFERFISH = Items.register("pufferfish", new FoodTooltipItem(new Item.Settings().maxCount(16).food(FoodComponents.PUFFERFISH), 
                                                                                                new ModdedFoodComponent(FoodComponents.PUFFERFISH, 2.0f, 0.5f, 120)));

    // @Shadow
    // public static final Item ROTTEN_FLESH = Items.register("rotten_flesh", new Item(new Item.Settings().maxCount(16).food(FoodComponents.ROTTEN_FLESH)));

    // ---------------------------------------------------------------------
    // --------------------------- Cooked Foods ----------------------------
    // ---------------------------------------------------------------------
    @Shadow
    public static final Item BREAD = Items.register("bread", new FoodTooltipItem(new Item.Settings().maxCount(8).food(FoodComponents.BREAD), 
                                                                                    new ModdedFoodComponent(FoodComponents.BREAD, 2.0f, 2.0f, 660)));

    @Shadow
    public static final Item BAKED_POTATO = Items.register("baked_potato", new FoodTooltipItem(new Item.Settings().maxCount(8).food(FoodComponents.BAKED_POTATO),
                                                                                                    new ModdedFoodComponent(FoodComponents.BAKED_POTATO, 3.0f, 2.0f, 960)));

    @Shadow
    public static final Item COOKED_PORKCHOP = Items.register("cooked_porkchop", new FoodTooltipItem(new Item.Settings().maxCount(8).food(FoodComponents.COOKED_PORKCHOP),
                                                                                                        new ModdedFoodComponent(FoodComponents.COOKED_PORKCHOP, 4.0f, 1.0f, 1260)));

    @Shadow
    public static final Item COOKED_BEEF = Items.register("cooked_beef", new FoodTooltipItem(new Item.Settings().maxCount(8).food(FoodComponents.COOKED_BEEF),
                                                                                                new ModdedFoodComponent(FoodComponents.COOKED_BEEF, 4, 1.0f, 1260)));

    @Shadow
    public static final Item COOKED_CHICKEN = Items.register("cooked_chicken", new FoodTooltipItem(new Item.Settings().maxCount(8).food(FoodComponents.COOKED_CHICKEN),
                                                                                                     new ModdedFoodComponent(FoodComponents.COOKED_CHICKEN, 4, 1.0f, 960)));

    @Shadow
    public static final Item COOKED_MUTTON = Items.register("cooked_mutton", new FoodTooltipItem(new Item.Settings().maxCount(8).food(FoodComponents.COOKED_MUTTON),
                                                                                                    new ModdedFoodComponent(FoodComponents.COOKED_MUTTON, 4, 1.0f, 960)));

    @Shadow
    public static final Item COOKED_RABBIT = Items.register("cooked_rabbit", new FoodTooltipItem(new Item.Settings().maxCount(8).food(FoodComponents.COOKED_RABBIT),
                                                                                                    new ModdedFoodComponent(FoodComponents.COOKED_RABBIT, 4.0f, 1.0f, 960)));

    @Shadow
	  public static final Item COOKED_COD = Items.register("cooked_cod", new FoodTooltipItem(new Item.Settings().maxCount(8).food(FoodComponents.COOKED_COD),
                                                                                            new ModdedFoodComponent(FoodComponents.COOKED_COD, 4.0f, 1.0f, 960)));
	
    @Shadow
    public static final Item COOKED_SALMON = Items.register("cooked_salmon", new FoodTooltipItem(new Item.Settings().maxCount(8).food(FoodComponents.COOKED_SALMON),
                                                                                                     new ModdedFoodComponent(FoodComponents.COOKED_SALMON, 4, 1.0f, 960)));


    // ---------------------------------------------------------------------
    // ------------------------------ Meals --------------------------------
    // ---------------------------------------------------------------------

    @Shadow
    public static final Item GOLDEN_CARROT = Items.register("golden_carrot", new FoodTooltipItem(new Item.Settings().maxCount(4).food(FoodComponents.GOLDEN_CARROT),
                                                                                                    new ModdedFoodComponent(FoodComponents.GOLDEN_CARROT, 4, 2.0f, 660)));

    @Shadow
    public static final Item PUMPKIN_PIE = Items.register("pumpkin_pie", new FoodTooltipItem(new Item.Settings().maxCount(4).food(FoodComponents.PUMPKIN_PIE),
                                                                                                new ModdedFoodComponent(FoodComponents.PUMPKIN_PIE, 5, 2.0f, 1260)));

    @Shadow
    public static final Item GOLDEN_APPLE = Items.register("golden_apple", new FoodTooltipItem(new Item.Settings().maxCount(4).rarity(Rarity.RARE).food(FoodComponents.GOLDEN_APPLE),
                                                                                                    new ModdedFoodComponent(FoodComponents.GOLDEN_APPLE, 4, 1.0f, 660)));
    @Shadow
    public static final Item BEETROOT_SOUP = Items.register("beetroot_soup", new FoodTooltipItem(new Item.Settings().maxCount(4).food(FoodComponents.BEETROOT_SOUP),
                                                                                                  new ModdedFoodComponent(FoodComponents.BEETROOT_SOUP, 4, 2.0f, 1860)));

    @Shadow
    public static final Item MUSHROOM_STEW = Items.register("mushroom_stew", new FoodTooltipItem(new Item.Settings().maxCount(4).food(FoodComponents.MUSHROOM_STEW),
                                                                                                  new ModdedFoodComponent(FoodComponents.MUSHROOM_STEW, 4, 2.0f, 1860)));

    @Shadow
    public static final Item RABBIT_STEW = Items.register("rabbit_stew", new FoodTooltipItem(new Item.Settings().maxCount(4).food(FoodComponents.RABBIT_STEW),
                                                                                                new ModdedFoodComponent(FoodComponents.RABBIT_STEW, 4, 2.0f, 1860)));



    // ---------------------------------------------------------------------
    // ----------------------------- Special -------------------------------
    // ---------------------------------------------------------------------

    @Shadow
    public static final Item ENCHANTED_GOLDEN_APPLE = Items.register(
		"enchanted_golden_apple",
		new FoodTooltipItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).food(FoodComponents.ENCHANTED_GOLDEN_APPLE)
                            .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true),
                            new ModdedFoodComponent(FoodComponents.ENCHANTED_GOLDEN_APPLE, 6, 2.0f, 660)));

}
