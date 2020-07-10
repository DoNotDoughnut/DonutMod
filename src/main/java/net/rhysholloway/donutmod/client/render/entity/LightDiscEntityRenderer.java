package net.rhysholloway.donutmod.client.render.entity;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.rhysholloway.donutmod.DonutMod;
import net.rhysholloway.donutmod.client.render.entity.model.LightDiscModel;
import net.rhysholloway.donutmod.entity.projectile.LightDiscEntity;

public class LightDiscEntityRenderer extends EntityRenderer<LightDiscEntity> {

	public static final Identifier TEXTURE = new Identifier(DonutMod.modId, "textures/entity/light_disc.png");

	private LightDiscModel model;

	public LightDiscEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
		model = new LightDiscModel();
	}

	public void render(LightDiscEntity entity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		matrixStack.push();
		matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(tickDelta, entity.prevYaw, entity.yaw) - 90.0F));
		matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(tickDelta, entity.prevPitch, entity.pitch)));
		this.model.setAngles(entity, tickDelta, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
		this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStack.pop();
		super.render(entity, yaw, tickDelta, matrixStack, vertexConsumerProvider, i);
	}

	@Override
	public Identifier getTexture(LightDiscEntity entity) {
		return TEXTURE;
	}

}
