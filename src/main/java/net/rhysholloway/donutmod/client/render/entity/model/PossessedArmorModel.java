package net.rhysholloway.donutmod.client.render.entity.model;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.CrossbowPosing;
import net.rhysholloway.donutmod.entity.mob.PossessedArmorEntity;

public class PossessedArmorModel extends BipedEntityModel<PossessedArmorEntity> {

	public PossessedArmorModel(float scale, boolean bl) {
		this(scale, 0.0F, 64, bl ? 32 : 64);
	}

	protected PossessedArmorModel(float f, float g, int i, int j) {
		super(f, g, i, j);
	}

	public void setAngles(PossessedArmorEntity entity, float f, float g, float h, float i, float j) {
		super.setAngles(entity, f, g, h, i, j);
		CrossbowPosing.method_29352(this.leftArm, this.rightArm, this.isAttacking(entity), this.handSwingProgress, h);
	}

	public boolean isAttacking(PossessedArmorEntity entity) {
		return entity.isAttacking();
	}

}
