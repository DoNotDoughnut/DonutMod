package net.rhysholloway.donutmod2.client.render.entity;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.rhysholloway.donutmod2.DonutMod;
import net.rhysholloway.donutmod2.client.render.entity.model.LightDiscModel;
import net.rhysholloway.donutmod2.entity.projectile.LightDiscEntity;

public class LightDiscEntityRenderer extends EntityRenderer<LightDiscEntity> {

	public static final Identifier TEXTURE = new Identifier(DonutMod.modId, "textures/entity/light_disc.png");

	private final LightDiscModel model = new LightDiscModel();

	public LightDiscEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(LightDiscEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		matrices.push();
		matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(tickDelta, entity.prevYaw, entity.yaw) - 90.0F));
		matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(tickDelta, entity.prevPitch, entity.pitch) + 90.0F));
		VertexConsumer vertexConsumer = ItemRenderer.method_29711(vertexConsumers, this.model.getLayer(this.getTexture(entity)), false, entity.isEnchanted());
		this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
		matrices.pop();
		super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
	}

	@Override
	public Identifier getTexture(LightDiscEntity entity) {
		return TEXTURE;
	}

}
