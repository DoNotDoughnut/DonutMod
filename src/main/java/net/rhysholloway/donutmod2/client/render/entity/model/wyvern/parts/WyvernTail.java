package net.rhysholloway.donutmod2.client.render.entity.model.wyvern.parts;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;

public class WyvernTail extends ModelPart {

	public WyvernTail(Model model, int textureOffsetU, int textureOffsetV) {
		super(model, textureOffsetU, textureOffsetV);
		this.addCuboid(-6, -6, -6, 6, 6, 6);
		this.addCuboid(0, 0, 0, 3, 3, 3);
		this.addCuboid(1.5f, 1.5f, 1.5f, 6, 1, 1);
	}

}
