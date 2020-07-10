package net.rhysholloway.donutmod.client.render.entity.model.wyvern.parts;

import net.minecraft.client.model.Model;

public class WyvernBodyWithLeg extends WyvernBody {

	public WyvernBodyWithLeg(Model model, float x, float y, float z) {
		super(model, x, y, z);
		this.addCuboid("left_leg_shoulder", x - 8, y - 2, z - 15, 4, 6, 6, 0.0F, 38, 0);
		this.addCuboid("right_leg_shoulder", x + 4, y - 2, z - 15, 4, 6, 6, 0.0F, 0, 0);
		
		// left leg
		
		this.addCuboid("left_heel", x - 8, y + 4.5f, z - 18, 4, 3, 12, 0.0f, 0, 61);
		
		// right leg
		
		this.addCuboid("right_heel", x + 4, y + 4.5f, z - 18, 4, 3, 12, 0.0f, 0, 61);
	}

}
