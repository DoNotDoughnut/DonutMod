package net.rhysholloway.donutmod.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.rhysholloway.donutmod.DonutMod;
import net.rhysholloway.donutmod.client.render.entity.model.wyvern.WyvernEntityModel;
import net.rhysholloway.donutmod.entity.mob.wyvern.WyvernEntity;

public class WyvernEntityRenderer extends MobEntityRenderer<WyvernEntity, WyvernEntityModel> {

	public static final Identifier TEXTURE = new Identifier(DonutMod.modId, "textures/entity/wyvern.png");
	
	public WyvernEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new WyvernEntityModel(), 0.5f);
	}

	@Override
	public Identifier getTexture(WyvernEntity entity) {
		return TEXTURE;
	}

}
