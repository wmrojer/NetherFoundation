package tehseph.netherfoundation.common.block;

import cofh.core.block.ItemBlockCore;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemBlockTFOre extends ItemBlockCore {

    public ItemBlockTFOre(Block block) {

        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);

    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile.netherfoundation.tf_ore." + BlockTFOre.Type.byMetadata(stack.getItemDamage()).getName().toLowerCase() + ".name";
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return BlockTFOre.Type.byMetadata(stack.getItemDamage()).getRarity();
    }

}
