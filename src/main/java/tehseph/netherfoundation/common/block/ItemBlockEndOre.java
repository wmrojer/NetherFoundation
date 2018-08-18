package tehseph.netherfoundation.common.block;

import cofh.core.block.ItemBlockCore;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemBlockEndOre extends ItemBlockCore {

    public ItemBlockEndOre(Block block) {

        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);

    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile.netherfoundation.end_ore." + BlockEndOre.Type.byMetadata(stack.getItemDamage()).getName().toLowerCase() + ".name";
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return BlockEndOre.Type.byMetadata(stack.getItemDamage()).getRarity();
    }

}
