package tehseph.netherfoundation.common.block;

import cofh.core.block.ItemBlockCore;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemBlockAE2Ore extends ItemBlockCore {

    public ItemBlockAE2Ore(Block block) {

        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);

    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile.netherfoundation.ae2_ore." + BlockAE2Ore.Type.byMetadata(stack.getItemDamage()).getName().toLowerCase() + ".name";
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return BlockAE2Ore.Type.byMetadata(stack.getItemDamage()).getRarity();
    }

}
