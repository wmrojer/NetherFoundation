package tehseph.netherfoundation.client.renderer.effects;

import net.minecraft.client.particle.ParticleRedstone;
import net.minecraft.world.World;


public class ChargedOreFX  extends ParticleRedstone
{
	public ChargedOreFX( final World w, final double x, final double y, final double z, final float r, final float g, final float b )
	{
		super( w, x, y, z, 0.21f, 0.61f, 1.0f );
	}

	@Override
	public int getBrightnessForRender( final float par1 )
	{
		int j1 = super.getBrightnessForRender( par1 );
		j1 = Math.max( j1 >> 20, j1 >> 4 );
		j1 += 3;
		if( j1 > 15 )
		{
			j1 = 15;
		}
		return j1 << 20 | j1 << 4;
	}
	
}
