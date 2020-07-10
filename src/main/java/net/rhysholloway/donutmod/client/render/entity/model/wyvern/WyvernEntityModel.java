package net.rhysholloway.donutmod.client.render.entity.model.wyvern;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.rhysholloway.donutmod.client.render.entity.model.wyvern.parts.WyvernBody;
import net.rhysholloway.donutmod.client.render.entity.model.wyvern.parts.WyvernBodyWithLeg;
import net.rhysholloway.donutmod.client.render.entity.model.wyvern.parts.WyvernHead;
import net.rhysholloway.donutmod.client.render.entity.model.wyvern.parts.WyvernTail;
import net.rhysholloway.donutmod.entity.mob.wyvern.WyvernEntity;

@Environment(EnvType.CLIENT)
public class WyvernEntityModel extends EntityModel<WyvernEntity> {

	private ModelPart head, front_legs, body1, body2, back_legs, tail;
	
	public WyvernEntityModel() {
		this.textureWidth = 256;
		this.textureHeight = 256;
		head = new WyvernHead(this, 0, 16, 0);
		front_legs = new WyvernBodyWithLeg(this, 0, 16, 22);
		body1 = new WyvernBody(this, 0, 16, 44);
		body2 = new WyvernBody(this, 0, 16, 66);
		back_legs = new WyvernBodyWithLeg(this, 0, 16, 88);
		tail = new WyvernTail(this, 0, 16, 88);
		
	}

	@Override
	public void setAngles(WyvernEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		matrices.scale(1.2f, 1.2f, 1.2f);
		head.render(matrices, vertices, light, overlay);
		front_legs.render(matrices, vertices, light, overlay);
		body1.render(matrices, vertices, light, overlay);
		body2.render(matrices, vertices, light, overlay);
		back_legs.render(matrices, vertices, light, overlay);
		tail.render(matrices, vertices, light, overlay);
	}

}
