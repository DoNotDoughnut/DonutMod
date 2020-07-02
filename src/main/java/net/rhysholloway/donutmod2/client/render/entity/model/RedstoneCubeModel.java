package net.rhysholloway.donutmod2.client.render.entity.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.rhysholloway.donutmod2.entity.mob.RedstoneCubeEntity;

public class RedstoneCubeModel extends EntityModel<RedstoneCubeEntity> {

	private ModelPart body;
	
	public RedstoneCubeModel() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		body = new ModelPart(this, 0, 0);
	}

	@Override
	public void setAngles(RedstoneCubeEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertices, light, overlay);
	}

}
