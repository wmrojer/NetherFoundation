package tehseph.netherfoundation.client.renderer.entity;

import tehseph.netherfoundation.Reference;
import tehseph.netherfoundation.entity.monster.EntityHellfish;

import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderHellfish extends RenderLiving<EntityHellfish> {

    private static final ResourceLocation HELLFISH_TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/entity/hellfish.png");
    public static final RenderFactory RENDER_FACTORY = new RenderFactory();

    public RenderHellfish(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelSilverfish(), 0.3F);
    }

    @Override
    protected float getDeathMaxRotation(EntityHellfish entity) {
        return 180.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityHellfish entity) {
        return HELLFISH_TEXTURES;
    }

    public static class RenderFactory implements IRenderFactory<EntityHellfish> {

        @Override
        public Render<? super EntityHellfish> createRenderFor(RenderManager renderManager) {
            return new RenderHellfish(renderManager);
        }

    }

}
