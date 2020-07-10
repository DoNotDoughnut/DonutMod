package net.rhysholloway.donutmod.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.util.Identifier;
import net.rhysholloway.donutmod.DonutMod;
import net.rhysholloway.donutmod.client.render.entity.model.PossessedArmorModel;
import net.rhysholloway.donutmod.entity.mob.PossessedArmorEntity;

@Environment(EnvType.CLIENT)
public class PossessedArmorEntityRenderer extends BipedEntityRenderer<PossessedArmorEntity, PossessedArmorModel> {

	private static final Identifier TEXTURE = new Identifier(DonutMod.modId, "textures/entity/possessed_armor.png");
	
	public PossessedArmorEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher, new PossessedArmorModel(0.0f, false), 0.5f);
		this.addFeature(new ArmorFeatureRenderer<PossessedArmorEntity, PossessedArmorModel, PossessedArmorModel>(this, new PossessedArmorModel(0.5f, true), new PossessedArmorModel(1.0f, true)));
	}

	public Identifier getTexture(PossessedArmorEntity entity) {
		return TEXTURE;
	}

}
