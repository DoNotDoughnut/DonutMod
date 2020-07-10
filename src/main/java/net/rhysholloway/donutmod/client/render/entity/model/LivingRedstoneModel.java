package net.rhysholloway.donutmod.client.render.entity.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.rhysholloway.donutmod.client.render.entity.model.living_redstone.LivingRedstoneModelPart;
import net.rhysholloway.donutmod.entity.mob.LivingRedstoneEntity;

public class LivingRedstoneModel extends CompositeEntityModel<LivingRedstoneEntity> {

	private ModelPart body;

	public LivingRedstoneModel() {
		this.textureWidth = 32;
		this.textureHeight = 32;
		body = new LivingRedstoneModelPart(this);

	}
	
	@Override
	public void setAngles(LivingRedstoneEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}

	@Override
	public Iterable<ModelPart> getParts() {
		return ImmutableList.of(body);
	}

}
