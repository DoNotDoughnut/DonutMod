package net.rhysholloway.donutmod1.blocks.mythril_anvil;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.rhysholloway.donutmod1.DonutMod;
import net.rhysholloway.donutmod1.items.DonutItems;
import net.rhysholloway.donutmod1.lib.DonutRegistries.BLOCK;

public class MythrilAnvil extends BLOCK implements BlockEntityProvider {

	public static MythrilAnvil mythril_anvil = new MythrilAnvil();
	public static BlockEntityType<MythrilAnvilEntity> MYTHRIL_ANVIL_ENTITY;

	public MythrilAnvil() {
		super(FabricBlockSettings.of(Material.ANVIL).build(), DonutItems.itemDefaults, "mythril_anvil");
	}

	@Override
	public BlockEntity createBlockEntity(BlockView arg0) {
		return new MythrilAnvilEntity();
	}

	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof MythrilAnvilEntity) {
				ContainerProviderRegistry.INSTANCE.openContainer(new Identifier(DonutMod.modId, "mythril_anvil"), player, buf -> buf.writeBlockPos(pos));
			}
		}
		return ActionResult.SUCCESS;
	}

	public static void initialize() {
		mythril_anvil.register();
		MYTHRIL_ANVIL_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, mythril_anvil.id, BlockEntityType.Builder.create(MythrilAnvilEntity::new, mythril_anvil).build(null));
		ContainerProviderRegistry.INSTANCE.registerFactory(mythril_anvil.id, (syncId, identifier, player, buf) -> new MythrilAnvilController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
	}

	@Environment(EnvType.CLIENT)
	public static void initializeClient() {
		ScreenProviderRegistry.INSTANCE.<MythrilAnvilController>registerFactory(mythril_anvil.id, (container) -> new MythrilAnvilScreen(container, MinecraftClient.getInstance().player));
	}
}
