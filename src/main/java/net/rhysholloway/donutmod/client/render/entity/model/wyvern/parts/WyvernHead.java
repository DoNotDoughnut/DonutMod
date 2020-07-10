package net.rhysholloway.donutmod.client.render.entity.model.wyvern.parts;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;

public class WyvernHead extends ModelPart {

	public WyvernHead(Model model, float x, float y, float z) {
		super(model, 0, 0);
		this.addCuboid("head_body_fill", x - 4, y + 5, z - 1,  8,  1, 1,  0.0f, 0, 61);
		this.addCuboid("head_lower",     x - 5, y,     z - 6,  10, 5, 6,  0.0f, 0, 61);
		this.addCuboid("head_upper",     x - 4, y - 5, z - 10, 8,  6, 10, 0.0f, 0, 61);
		
	}

}
