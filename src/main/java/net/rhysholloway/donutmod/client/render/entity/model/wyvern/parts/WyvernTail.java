package net.rhysholloway.donutmod.client.render.entity.model.wyvern.parts;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;

public class WyvernTail extends ModelPart {

	private static final int tail0l = 2, tail1l = 7, tail_2 = 8, tail_3 = 9, tail_4 = 8, tail_5 = 8, tail_6 = 8, tail_7 = 4, tail_8 = 7;
	private static final int tail0w = 8, tail1w = 7, tail2w = 6, tail3w = 5, tail4w = 4, tail5w = 3, tail6w = 2, tail7w = 2, tail8w = 1, tailFeatherW = 1;
	private static final int tail0h = 11, tail1h = 10, tail2h = 9, tail3h = 8, tail4h = 7, tail5h = 5, tail6h = 3, tail7h = 1, tail8h = 1, tailFeatherH = 9;
	
	public WyvernTail(Model model, float x, float y, float z) {
		super(model, 0, 0);
		
		float length = z;
		
		this.addCuboid("body_tail_connector", x - tail0w / 2f, y - tail0h / 2f, length, tail0w, tail0h, tail0l, 0.0f, 0, 61);
		this.addCuboid("body_tail_connector_mane", x - 0.5f, y - (tail0h / 2f + 3), length, 1, 3, tail0l, 0.0f, 0, 61);

		this.addCuboid("tail_1", x - tail1w / 2f, y - tail1h / 2f, length += tail0l, tail1w, tail1h, tail1l, 0.0f, 0, 61);
		this.addCuboid("tail_1_mane", x - 0.5f, y - (tail1h / 2f + 3), length, 1, 3, tail1l, 0.0f, 0, 61);
		
		this.addCuboid("tail_2", x - tail2w / 2f, y - tail2h / 2f, length += tail1l, tail2w, tail2h, tail_2, 0.0f, 0, 61);
		this.addCuboid("tail_2_mane", x - 0.5f, y - (tail2h / 2f + 3), length, 1, 3, tail_2, 0.0f, 0, 61);
		
		this.addCuboid("tail_3", x - tail3w / 2f, y - tail3h / 2f, length += tail_2, tail3w, tail3h, tail_3, 0.0f, 0, 61);
		this.addCuboid("tail_3_mane", x - 0.5f, y - (tail3h / 2f + 3), length, 1, 3, tail_3, 0.0f, 0, 61);

		this.addCuboid("tail_4", x - tail4w / 2f, y - tail4h / 2f, length += tail_3, tail4w, tail4h, tail_4, 0.0f, 0, 61);
		this.addCuboid("tail_4_mane", x - 0.5f, y - (tail4h / 2f + 3), length, 1, 3, tail_4, 0.0f, 0, 61);
		
		this.addCuboid("tail_5", x - tail5w / 2f, y - tail5h / 2f, length += tail_4, tail5w, tail5h, tail_5, 0.0f, 0, 61);
		this.addCuboid("tail_5_mane", x - 0.5f, y - (tail5h / 2f + 3), length, 1, 3, tail_5, 0.0f, 0, 61);

		this.addCuboid("tail_6", x - tail6w / 2f, y - tail6h / 2f, length += tail_5, tail6w, tail6h, tail_6, 0.0f, 0, 61);
		this.addCuboid("tail_6_mane", x - 0.5f, y - (tail6h / 2f + 3), length, 1, 3, tail_6, 0.0f, 0, 61);

		this.addCuboid("tail_7", x - tail7w / 2f, y - tail7h / 2f, length += tail_6, tail7w, tail7h, tail_7, 0.0f, 0, 61);
		this.addCuboid("tail_7_mane", x - 0.5f, y - (tail7h / 2f + 3), length, 1, 3, tail_7 - 1, 0.0f, 0, 61);
		
		this.addCuboid("tail_8", x - tail8w / 2f, y - tail8h / 2f, length += tail_7, tail8w, tail8h, tail_8, 0.0f, 0, 61);

		this.addCuboid("tail_feather_start", x - tailFeatherW / 2f, y - (tailFeatherH - 2) / 2f, length += tail_8, tailFeatherW, tailFeatherH - 2, 1, 0.0f, 0, 61);
		this.addCuboid("tail_feather", x - tailFeatherW / 2f, y - tailFeatherH / 2f, length += 1, tailFeatherW, tailFeatherH, 18, 0.0f, 0, 61);
		this.addCuboid("tail_feather_end", x - tailFeatherW / 2f, y - (tailFeatherH - 2) / 2f, length += 18, tailFeatherW, tailFeatherH - 2, 1, 0.0f, 0, 61);
	}

}
