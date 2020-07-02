package net.rhysholloway.donutmod2.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.rhysholloway.donutmod2.DonutMod;
import net.rhysholloway.donutmod2.client.render.entity.model.RedstoneCubeModel;
import net.rhysholloway.donutmod2.entity.mob.RedstoneCubeEntity;

public class RedstoneCubeEntityRenderer extends MobEntityRenderer<RedstoneCubeEntity, RedstoneCubeModel> {

	public static final Identifier TEXTURE = new Identifier(DonutMod.modId, "textures/entity/redstone_cube/redstone_cube.png");
	
	public RedstoneCubeEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new RedstoneCubeModel(), 0.5f);
	}
	
	@Override
	public Identifier getTexture(RedstoneCubeEntity entity) {
		return TEXTURE;
	}

}
