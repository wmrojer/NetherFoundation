package tehseph.netherfoundation.common.block;

import cofh.core.block.ItemBlockCore;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemBlockExtraOre extends ItemBlockCore {

    public ItemBlockExtraOre(Block block) {

        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);

    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile.netherfoundation.extra_ore." + BlockExtraOre.Type.byMetadata(stack.getItemDamage()).getName().toLowerCase() + ".name";
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return BlockExtraOre.Type.byMetadata(stack.getItemDamage()).getRarity();
    }

}
