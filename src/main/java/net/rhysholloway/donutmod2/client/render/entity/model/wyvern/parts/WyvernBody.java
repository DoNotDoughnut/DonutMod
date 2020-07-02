package net.rhysholloway.donutmod2.client.render.entity.model.wyvern.parts;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;

public class WyvernBody extends ModelPart {

	public static int 
			BODY_WIDTH = WyvernHead.HEAD_WIDTH, BODY_HEIGHT = BODY_WIDTH, BODY_LENGTH = BODY_WIDTH * 3,
			MANE_WIDTH = 4, MANE_HEIGHT = MANE_WIDTH * 2, MANE_LENGTH = BODY_LENGTH;
	
	public WyvernBody(Model model, int textureOffsetU, int textureOffsetV, int bodyNum) {
		super(model, textureOffsetU, textureOffsetV);
		this.addCuboid(-BODY_LENGTH * (bodyNum - 2), 				-BODY_WIDTH / 2, 				-BODY_WIDTH / 2, 		BODY_LENGTH, BODY_WIDTH, BODY_WIDTH);
		this.addCuboid(-BODY_LENGTH * (bodyNum - 1) * MANE_LENGTH, -BODY_HEIGHT / 2 - MANE_HEIGHT, 0 - MANE_WIDTH / 2, 	MANE_LENGTH, MANE_HEIGHT, MANE_WIDTH);
	}

}
