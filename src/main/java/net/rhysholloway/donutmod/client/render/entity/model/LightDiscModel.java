package net.rhysholloway.donutmod.client.render.entity.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.rhysholloway.donutmod.entity.projectile.LightDiscEntity;

public class LightDiscModel extends CompositeEntityModel<LightDiscEntity> {

	private static int modelWidth = 12, modelHeight = 1, modelDepth = 12;
	
	private ModelPart body;
	
	public LightDiscModel() {
		this.textureWidth = 32;
		this.textureHeight = 32;
		body = new ModelPart(this, 0, 0);
		body.addCuboid(-(modelWidth / 2f), -(modelHeight / 2f), -(modelDepth / 2f), modelWidth, modelHeight, modelDepth);
	}
	
	@Override
	public void setAngles(LightDiscEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}

	@Override
	public Iterable<ModelPart> getParts() {
		return ImmutableList.of(body);
	}



}
