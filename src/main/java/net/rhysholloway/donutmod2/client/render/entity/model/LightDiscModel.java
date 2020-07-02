package net.rhysholloway.donutmod2.client.render.entity.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class LightDiscModel extends Model {

	private final ModelPart model = new ModelPart(32, 32, 9, 9);
	
	public LightDiscModel() {
		super(RenderLayer::getEntitySolid);
		this.textureWidth = 32;
		this.textureHeight = 32;
		model.addCuboid(-0.5f, -8.0f, -0.5f, 1f, 16f, 1.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		this.model.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}

}
