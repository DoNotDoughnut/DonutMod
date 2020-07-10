package net.rhysholloway.donutmod.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.rhysholloway.donutmod.DonutMod;
import net.rhysholloway.donutmod.client.render.entity.model.LivingRedstoneModel;
import net.rhysholloway.donutmod.entity.mob.LivingRedstoneEntity;

public class LivingRedstoneEntityRenderer extends MobEntityRenderer<LivingRedstoneEntity, LivingRedstoneModel> {

	public static final Identifier TEXTURE = new Identifier(DonutMod.modId, "textures/entity/living_redstone.png");
	
	public LivingRedstoneEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new LivingRedstoneModel(), 0.3f);
	}

	@Override
	public Identifier getTexture(LivingRedstoneEntity entity) {
		return TEXTURE;
	}

}
