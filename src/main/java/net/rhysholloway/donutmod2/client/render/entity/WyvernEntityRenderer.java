package net.rhysholloway.donutmod2.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.rhysholloway.donutmod2.DonutMod;
import net.rhysholloway.donutmod2.client.render.entity.model.wyvern.WyvernEntityModel;
import net.rhysholloway.donutmod2.entity.mob.WyvernEntity;

public class WyvernEntityRenderer extends MobEntityRenderer<WyvernEntity, WyvernEntityModel> {

	public static final Identifier TEXTURE = new Identifier(DonutMod.modId, "textures/entity/wyvern/wyvern.png");
	
	public WyvernEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new WyvernEntityModel(), 0.5f);
	}

	@Override
	public Identifier getTexture(WyvernEntity entity) {
		return TEXTURE;
	}

}
