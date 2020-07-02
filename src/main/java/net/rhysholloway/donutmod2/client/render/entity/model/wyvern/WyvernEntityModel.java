package net.rhysholloway.donutmod2.client.render.entity.model.wyvern;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.rhysholloway.donutmod2.client.render.entity.model.wyvern.parts.WyvernBody;
import net.rhysholloway.donutmod2.client.render.entity.model.wyvern.parts.WyvernHead;
import net.rhysholloway.donutmod2.entity.mob.WyvernEntity;

@Environment(EnvType.CLIENT)
public class WyvernEntityModel extends EntityModel<WyvernEntity> {

	public final WyvernHead head;
	public WyvernBody body0;
	public WyvernBody body1;
	
	public WyvernEntityModel() {
		this.textureWidth = 256;
		this.textureHeight = 128;
		head = new WyvernHead(this, 0, 0);
		head.addChild(body0 = new WyvernBody(this, 0, 0, 0));
		head.addChild(body1 = new WyvernBody(this, 0, 0, 1));
		//body_leg = new WyvernBodyLeg(this, 0, 0);
		//tail = new WyvernTail(this, 0, 0);
	}
	
	@Override
	public void setAngles(WyvernEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		matrices.translate(0, 1.125, 0);
		
		head.render(matrices, vertices, light, overlay);	
	}

}
