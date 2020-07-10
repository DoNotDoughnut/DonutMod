package net.rhysholloway.donutmod.client.render.entity.model.living_redstone;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;

public class LivingRedstoneModelPart extends ModelPart {
	
	private static final int textureFaceSize = 8;
	
	public LivingRedstoneModelPart(Model model) {
		super(model);
		this.addCuboid(-(textureFaceSize / 2f), -(textureFaceSize / 2f) + 20, -(textureFaceSize / 2f), textureFaceSize, textureFaceSize, textureFaceSize);
	}

}
