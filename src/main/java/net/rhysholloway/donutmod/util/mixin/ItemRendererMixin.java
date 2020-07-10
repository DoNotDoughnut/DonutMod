package net.rhysholloway.donutmod.util.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.Block;
import net.minecraft.block.StainedGlassPaneBlock;
import net.minecraft.block.TransparentBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.resource.SynchronousResourceReloadListener;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin implements SynchronousResourceReloadListener {

	@Shadow
	@Final
	private ItemModels models;
	
	@Shadow
	abstract void renderBakedItemModel(BakedModel model, ItemStack stack, int light, int overlay, MatrixStack matrices, VertexConsumer vertices);
	
	@Overwrite
	public void renderItem(ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model) {
		if (!stack.isEmpty()) {
			matrices.push();
			boolean bl = renderMode == ModelTransformation.Mode.GUI || renderMode == ModelTransformation.Mode.GROUND || renderMode == ModelTransformation.Mode.FIXED;
			if (stack.getItem() == Items.TRIDENT && bl) {
				model = this.models.getModelManager().getModel(new ModelIdentifier("minecraft:trident#inventory"));
			}

			model.getTransformation().getTransformation(renderMode).apply(leftHanded, matrices);
			matrices.translate(-0.5D, -0.5D, -0.5D);
			if (model.isBuiltin() || stack.getItem() == Items.TRIDENT && !bl) {
				BuiltinModelItemRenderer.INSTANCE.render(stack, renderMode, matrices, vertexConsumers, light, overlay);
			} else {
				boolean bl3;
				if (renderMode != ModelTransformation.Mode.GUI && !renderMode.method_29998() && stack.getItem() instanceof BlockItem) {
					Block block = ((BlockItem) stack.getItem()).getBlock();
					bl3 = !(block instanceof TransparentBlock) && !(block instanceof StainedGlassPaneBlock);
				} else {
					bl3 = true;
				}

				RenderLayer renderLayer = RenderLayers.getItemLayer(stack, bl3);
				VertexConsumer vertexConsumer4;
				if (stack.getItem() == Items.COMPASS && stack.hasGlint()) {
					matrices.push();
					MatrixStack.Entry entry = matrices.peek();
					if (renderMode == ModelTransformation.Mode.GUI) {
						entry.getModel().multiply(0.5F);
					} else if (renderMode.method_29998()) {
						entry.getModel().multiply(0.75F);
					}

					if (bl3) {
						vertexConsumer4 = ItemRenderer.method_30115(vertexConsumers, renderLayer, entry);
					} else {
						vertexConsumer4 = ItemRenderer.method_30114(vertexConsumers, renderLayer, entry);
					}

					matrices.pop();
				} else if (bl3) {
					vertexConsumer4 = ItemRenderer.method_29711(vertexConsumers, renderLayer, true, stack.hasGlint());
				} else {
					vertexConsumer4 = ItemRenderer.getArmorVertexConsumer(vertexConsumers, renderLayer, true, stack.hasGlint());
				}

				this.renderBakedItemModel(model, stack, light, overlay, matrices, vertexConsumer4);
			}

			matrices.pop();
		}
	}

}
