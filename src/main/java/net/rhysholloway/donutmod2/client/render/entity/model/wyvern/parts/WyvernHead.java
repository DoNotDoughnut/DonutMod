package net.rhysholloway.donutmod2.client.render.entity.model.wyvern.parts;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;

public class WyvernHead extends ModelPart {

	public static int 
			HEAD_WIDTH = 36, HEAD_HEIGHT = HEAD_WIDTH, HEAD_LENGTH = 96,
			MUZZLE_WIDTH = HEAD_WIDTH, MUZZLE_HEIGHT = HEAD_HEIGHT / 3 * 2, MUZZLE_LENGTH = 48, 
			MANE_WIDTH = 4, MANE_HEIGHT = MANE_WIDTH, MANE_LENGTH = HEAD_LENGTH / 2;

	public WyvernHead(Model model, int textureOffsetU, int textureOffsetV) {
		super(model, textureOffsetU, textureOffsetV);

		this.addCuboid(-MUZZLE_LENGTH,  (HEAD_HEIGHT / 2) - MUZZLE_HEIGHT,	-MUZZLE_WIDTH / 2, MUZZLE_LENGTH, MUZZLE_HEIGHT, MUZZLE_WIDTH); // muzzle
		this.addCuboid(0, 				-HEAD_HEIGHT / 2, 					-HEAD_WIDTH / 2,   HEAD_LENGTH,   HEAD_HEIGHT,   HEAD_WIDTH); // head
		this.addCuboid(MANE_LENGTH,		-HEAD_HEIGHT / 2 - MANE_HEIGHT, 	0 - MANE_WIDTH / 2,   MANE_LENGTH,   MANE_HEIGHT,   MANE_WIDTH); // mane
		this.setPivot(0, 4F, -8F);
	}

}
