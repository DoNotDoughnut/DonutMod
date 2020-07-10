package net.rhysholloway.donutmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.rhysholloway.donutmod.DonutMod;
import net.rhysholloway.donutmod.lists.BlockList;
import net.rhysholloway.donutmod.screen.handler.SoulBenchScreenHandler;

@Environment(EnvType.CLIENT)
public class SoulBenchScreen extends HandledScreen<SoulBenchScreenHandler> {

	private static Identifier BG_TEX;

	public SoulBenchScreen(SoulBenchScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
		if(BG_TEX == null) {
			BG_TEX = new Identifier(DonutMod.modId, "textures/gui/container/"+Registry.BLOCK.getId(BlockList.soul_bench).getPath()+".png");
		}
	}

	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		this.renderBackground(matrices);
		super.render(matrices, mouseX, mouseY, delta);
		this.drawMouseoverTooltip(matrices, mouseX, mouseY);
	}

	protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
		this.textRenderer.draw(matrices, this.title, 28.0F, 6.0F, 4210752);
		this.textRenderer.draw(matrices, this.playerInventory.getDisplayName(), 8.0F + 20, (float) (this.backgroundHeight - 96 + 2), 4210752);
	}

	@Override
	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.client.getTextureManager().bindTexture(BG_TEX);
		int i = this.x;
		int j = (this.height - this.backgroundHeight) / 2;
		this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
	}
}
