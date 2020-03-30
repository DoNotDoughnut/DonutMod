package net.rhysholloway.donutmod1.blocks.mythril_anvil;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.rhysholloway.donutmod1.items.DonutItems;
import net.rhysholloway.donutmod1.util.DonutRegistries.BLOCK;

public class MythrilAnvil extends BLOCK implements BlockEntityProvider {

	public static MythrilAnvil mythril_anvil = new MythrilAnvil();
	public static BlockEntityType<MythrilAnvilEntity> MYTHRIL_ANVIL_ENTITY;
	public static RecipeType<MythrilAnvilRecipe> MYTHRIL_ANVIL = RecipeType.register("mythril_anvil");

	public MythrilAnvil() {
		super(Block.Settings.of(Material.ANVIL), DonutItems.itemDefaults, "mythril_anvil");
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new MythrilAnvilEntity();
	}
	
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof MythrilAnvilEntity)
				ContainerProviderRegistry.INSTANCE.openContainer(mythril_anvil.id, player, buf -> buf.writeBlockPos(pos));
		}
		return ActionResult.SUCCESS;
	}

	public static void initialize() {
		mythril_anvil.register();
		MYTHRIL_ANVIL_ENTITY = Registry.register(
				Registry.BLOCK_ENTITY_TYPE, 
				mythril_anvil.id, 
				BlockEntityType.Builder.create(MythrilAnvilEntity::new, mythril_anvil).build(null)
		);
		ContainerProviderRegistry.INSTANCE.registerFactory(mythril_anvil.id, (syncId, identifier, player, buf) -> new MythrilAnvilContainer(syncId, player, BlockContext.create(player.world, buf.readBlockPos()))); //FIX
	}

	@Environment(EnvType.CLIENT)
	public static void initializeClient() {
		ScreenProviderRegistry.INSTANCE.<MythrilAnvilContainer>registerFactory(
				mythril_anvil.id, 
				(container) -> new CottonInventoryScreen<MythrilAnvilContainer>(container, MinecraftClient.getInstance().player)
		);
	}
}
