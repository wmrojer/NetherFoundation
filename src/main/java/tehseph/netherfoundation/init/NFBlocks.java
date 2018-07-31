package tehseph.netherfoundation.init;

import tehseph.netherfoundation.Reference;
import tehseph.netherfoundation.common.block.*;

import cofh.core.fluid.BlockFluidInteractive;
import cofh.core.util.helpers.StringHelper;
import cofh.thermalfoundation.init.TFFluids;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@ObjectHolder(Reference.MOD_ID)
public class NFBlocks {

    public static final BlockHellfish HELLFISH   = new BlockHellfish();
    public static final BlockNetherOre NETHER_ORE = new BlockNetherOre();

    public static void preInitCommon() {

        ItemBlock itemBlock;

        if (NFConfig.HELLFISH) {

            HELLFISH.setRegistryName("hellfish");
            ForgeRegistries.BLOCKS.register(HELLFISH);

            itemBlock = new ItemBlock(HELLFISH);
            itemBlock.setRegistryName("hellfish");
            ForgeRegistries.ITEMS.register(itemBlock);

        }

        NETHER_ORE.setRegistryName("ore");
        ForgeRegistries.BLOCKS.register(NETHER_ORE);

        itemBlock = new ItemBlockNetherOre(NETHER_ORE);
        itemBlock.setRegistryName("ore");
        ForgeRegistries.ITEMS.register(itemBlock);

        for (int i = 0; i < BlockNetherOre.Type.values().length; i++) {

            String oreName = BlockNetherOre.Type.byMetadata(i).getName();
            ItemStack oreStack = new ItemStack(NETHER_ORE, 1, i);

            OreDictionary.registerOre("oreNether" + StringHelper.titleCase(oreName), oreStack);

        }

        IBlockState netherLead = NETHER_ORE.getDefaultState().withProperty(BlockNetherOre.VARIANT, BlockNetherOre.Type.LEAD);
        IBlockState netherGold = NETHER_ORE.getDefaultState().withProperty(BlockNetherOre.VARIANT, BlockNetherOre.Type.GOLD);
        ((BlockFluidInteractive) TFFluids.blockFluidMana).addInteraction(netherLead, netherGold);

        IBlockState netherSilver  = NETHER_ORE.getDefaultState().withProperty(BlockNetherOre.VARIANT, BlockNetherOre.Type.SILVER);
        IBlockState netherMithril = NETHER_ORE.getDefaultState().withProperty(BlockNetherOre.VARIANT, BlockNetherOre.Type.MITHRIL);
        ((BlockFluidInteractive) TFFluids.blockFluidMana).addInteraction(netherSilver, netherMithril);

    }

    @SideOnly(Side.CLIENT)
    public static void preInitClient() {

        Item item;
        ModelResourceLocation model;

        if (NFConfig.HELLFISH) {

            item = Item.getItemFromBlock(HELLFISH);
            model = new ModelResourceLocation(HELLFISH.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, 0, model);

        }

        for (int i = 0; i < BlockNetherOre.Type.values().length; i++) {

            item = Item.getItemFromBlock(NETHER_ORE);
            model = new ModelResourceLocation(NETHER_ORE.getRegistryName(), "type=" + BlockNetherOre.Type.byMetadata(i).getName());
            ModelLoader.setCustomModelResourceLocation(item, i, model);

        }

    }

}
