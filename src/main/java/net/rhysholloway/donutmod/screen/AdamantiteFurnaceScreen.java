package net.rhysholloway.donutmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.text.StringRenderable;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.rhysholloway.donutmod.DonutMod;
import net.rhysholloway.donutmod.screen.handler.AdamantiteFurnaceScreenHandler;

@Environment(EnvType.CLIENT)
public class AdamantiteFurnaceScreen extends HandledScreen<AdamantiteFurnaceScreenHandler> {

	private final Identifier background = new Identifier(DonutMod.modId, "textures/gui/container/adamantite_furnace.png");

	public AdamantiteFurnaceScreen(AdamantiteFurnaceScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}

	@Override
	public void init() {
		super.init();
		this.titleX = (this.backgroundWidth - this.textRenderer.getWidth((StringRenderable) this.title)) / 2;
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		this.renderBackground(matrices);
		super.render(matrices, mouseX, mouseY, delta);
		this.drawMouseoverTooltip(matrices, mouseX, mouseY);
	}
	
	@Override
	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	      this.client.getTextureManager().bindTexture(this.background);
	      int i = this.x;
	      int j = this.y;
	      this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
	      int l;
	      if (((AbstractFurnaceScreenHandler)this.handler).isBurning()) {
	         l = ((AbstractFurnaceScreenHandler)this.handler).getFuelProgress();
	         this.drawTexture(matrices, i + 56, j + 36 + 12 - l, 176, 12 - l, 14, l + 1);
	      }

	      l = ((AbstractFurnaceScreenHandler)this.handler).getCookProgress();
	      this.drawTexture(matrices, i + 79, j + 34, 176, 14, l + 1, 16);
	}

}
