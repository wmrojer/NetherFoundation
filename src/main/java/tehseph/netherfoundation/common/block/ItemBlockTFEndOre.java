package tehseph.netherfoundation.common.block;

import cofh.core.block.ItemBlockCore;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemBlockTFEndOre extends ItemBlockCore {

    public ItemBlockTFEndOre(Block block) {

        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);

    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile.netherfoundation.tf_end_ore." + BlockTFEndOre.Type.byMetadata(stack.getItemDamage()).getName().toLowerCase() + ".name";
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return BlockTFEndOre.Type.byMetadata(stack.getItemDamage()).getRarity();
    }

}
