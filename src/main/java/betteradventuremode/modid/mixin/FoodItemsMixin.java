package betteradventuremode.modid.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ChorusFruitItem;
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
    public static final Item COOKIE = Items.register("cookie", new Item(new Item.Settings().maxCount(32).food(FoodComponents.COOKIE)));

    @Shadow
	public static final Item DRIED_KELP = Items.register("dried_kelp", new Item(new Item.Settings().maxCount(32).food(FoodComponents.DRIED_KELP)));
    
    @Shadow
    public static final Item CHORUS_FRUIT = Items.register("chorus_fruit", new ChorusFruitItem(new Item.Settings().maxCount(32).food(FoodComponents.CHORUS_FRUIT)));

    @Shadow
    public static final Item SWEET_BERRIES = Items.register(
		"sweet_berries", new AliasedBlockItem(Blocks.SWEET_BERRY_BUSH, new Item.Settings().maxCount(32).food(FoodComponents.SWEET_BERRIES))
	);
	
    @Shadow
    public static final Item GLOW_BERRIES = Items.register(
		"glow_berries", new AliasedBlockItem(Blocks.CAVE_VINES, new Item.Settings().maxCount(32).food(FoodComponents.GLOW_BERRIES))
	);

    // ---------------------------------------------------------------------
    // ---------------------------- Raw Food -------------------------------
    // ---------------------------------------------------------------------
    
    @Shadow
    public static final Item MELON_SLICE = Items.register("melon_slice", new Item(new Item.Settings().maxCount(16).food(FoodComponents.MELON_SLICE)));
    
    @Shadow
    public static final Item APPLE = Items.register("apple", new Item(new Item.Settings().maxCount(16).food(FoodComponents.APPLE)));

    @Shadow
    public static final Item BEETROOT = Items.register("beetroot", new Item(new Item.Settings().maxCount(16).food(FoodComponents.BEETROOT)));

    @Shadow
    public static final Item CARROT = Items.register("carrot", new AliasedBlockItem(Blocks.CARROTS, new Item.Settings().maxCount(16).food(FoodComponents.CARROT)));

    @Shadow
    public static final Item POTATO = Items.register("potato", new AliasedBlockItem(Blocks.POTATOES, new Item.Settings().maxCount(16).food(FoodComponents.POTATO)));

    @Shadow
    public static final Item POISONOUS_POTATO = Items.register("poisonous_potato", new Item(new Item.Settings().maxCount(16).food(FoodComponents.POISONOUS_POTATO)));

    @Shadow
    public static final Item PORKCHOP = Items.register("porkchop", new Item(new Item.Settings().maxCount(16).food(FoodComponents.PORKCHOP)));
	
    @Shadow
    public static final Item BEEF = Items.register("beef", new Item(new Item.Settings().maxCount(16).food(FoodComponents.BEEF)));

    @Shadow
    public static final Item CHICKEN = Items.register("chicken", new Item(new Item.Settings().maxCount(16).food(FoodComponents.CHICKEN)));

    @Shadow
    public static final Item MUTTON = Items.register("mutton", new Item(new Item.Settings().maxCount(16).food(FoodComponents.MUTTON)));

    @Shadow
    public static final Item RABBIT = Items.register("rabbit", new Item(new Item.Settings().maxCount(16).food(FoodComponents.RABBIT)));

    @Shadow
    public static final Item COD = Items.register("cod", new Item(new Item.Settings().maxCount(16).food(FoodComponents.COD)));
	
    @Shadow
    public static final Item SALMON = Items.register("salmon", new Item(new Item.Settings().maxCount(16).food(FoodComponents.SALMON)));
	
    @Shadow
    public static final Item TROPICAL_FISH = Items.register("tropical_fish", new Item(new Item.Settings().maxCount(16).food(FoodComponents.TROPICAL_FISH)));
	
    @Shadow
    public static final Item PUFFERFISH = Items.register("pufferfish", new Item(new Item.Settings().maxCount(16).food(FoodComponents.PUFFERFISH)));

    @Shadow
    public static final Item ROTTEN_FLESH = Items.register("rotten_flesh", new Item(new Item.Settings().maxCount(16).food(FoodComponents.ROTTEN_FLESH)));

    // ---------------------------------------------------------------------
    // --------------------------- Cooked Foods ----------------------------
    // ---------------------------------------------------------------------
    @Shadow
    public static final Item BREAD = Items.register("bread", new Item(new Item.Settings().maxCount(8).food(FoodComponents.BREAD)));

    @Shadow
    public static final Item BAKED_POTATO = Items.register("baked_potato", new Item(new Item.Settings().maxCount(8).food(FoodComponents.BAKED_POTATO)));

    @Shadow
    public static final Item COOKED_PORKCHOP = Items.register("cooked_porkchop", new Item(new Item.Settings().maxCount(8).food(FoodComponents.COOKED_PORKCHOP)));

    @Shadow
    public static final Item COOKED_BEEF = Items.register("cooked_beef", new Item(new Item.Settings().maxCount(8).food(FoodComponents.COOKED_BEEF)));

    @Shadow
    public static final Item COOKED_CHICKEN = Items.register("cooked_chicken", new Item(new Item.Settings().maxCount(8).food(FoodComponents.COOKED_CHICKEN)));

    @Shadow
    public static final Item COOKED_MUTTON = Items.register("cooked_mutton", new Item(new Item.Settings().maxCount(8).food(FoodComponents.COOKED_MUTTON)));

    @Shadow
    public static final Item COOKED_RABBIT = Items.register("cooked_rabbit", new Item(new Item.Settings().maxCount(8).food(FoodComponents.COOKED_RABBIT)));

    @Shadow
	public static final Item COOKED_COD = Items.register("cooked_cod", new Item(new Item.Settings().maxCount(8).food(FoodComponents.COOKED_COD)));
	
    @Shadow
    public static final Item COOKED_SALMON = Items.register("cooked_salmon", new Item(new Item.Settings().maxCount(8).food(FoodComponents.COOKED_SALMON)));


    // ---------------------------------------------------------------------
    // ------------------------------ Meals --------------------------------
    // ---------------------------------------------------------------------

    @Shadow
    public static final Item GOLDEN_CARROT = Items.register("golden_carrot", new Item(new Item.Settings().maxCount(4).food(FoodComponents.GOLDEN_CARROT)));

    @Shadow
    public static final Item PUMPKIN_PIE = Items.register("pumpkin_pie", new Item(new Item.Settings().maxCount(4).food(FoodComponents.PUMPKIN_PIE)));

    @Shadow
    public static final Item GOLDEN_APPLE = Items.register("golden_apple", new Item(new Item.Settings().maxCount(4).rarity(Rarity.RARE).food(FoodComponents.GOLDEN_APPLE)));
	


    // ---------------------------------------------------------------------
    // ----------------------------- Special -------------------------------
    // ---------------------------------------------------------------------

    @Shadow
    public static final Item ENCHANTED_GOLDEN_APPLE = Items.register(
		"enchanted_golden_apple",
		new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).food(FoodComponents.ENCHANTED_GOLDEN_APPLE).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true))
	);

}
