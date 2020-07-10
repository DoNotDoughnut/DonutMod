package net.rhysholloway.donutmod.client.render.entity.model.wyvern.parts;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;

public class WyvernBody extends ModelPart {
	
	public WyvernBody(Model model, float x, float y, float z) {
		super(model, 0, 0);
		this.addCuboid("body", x - 4, y - 5.5f, z - 22, 8, 11, 22, 0.0F, 0, 0);
		this.addCuboid("mane", x - 0.5f, y - 8.5f, z - 22, 1, 3, 22, 0.0F, 38, 11);
		this.addCuboid("belly", x - 3, y + 5.5f, z - 22, 6, 1, 22, 0.0F, 62, 14);
	}

}
