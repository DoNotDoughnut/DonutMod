package net.rhysholloway.donutmod2.lists;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.rhysholloway.donutmod2.DonutMod;
import net.rhysholloway.donutmod2.entity.mob.RedstoneCubeEntity;
import net.rhysholloway.donutmod2.entity.mob.WyvernEntity;
import net.rhysholloway.donutmod2.entity.projectile.LightDiscEntity;

public class EntityTypeList {
	
	public static EntityType<WyvernEntity> WYVERN = new WyvernEntity.Builder(SpawnGroup.MONSTER).spawnableFarFromPlayer().specificSpawnBlocks(Blocks.AIR).fireImmune().trackable(64, 20).dimensions(EntityDimensions.fixed(1f, 1f)).build();
	public static EntityType<RedstoneCubeEntity> REDSTONE_CUBE = new RedstoneCubeEntity.Builder(SpawnGroup.MONSTER).dimensions(EntityDimensions.fixed(.75f, .75f)).build();
	
	public static EntityType<LightDiscEntity> LIGHT_DISC = new LightDiscEntity.Builder(SpawnGroup.MISC).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).trackable(4, 20).build();

	public static void register() {
		
		register(WYVERN, "wyvern", WyvernEntity.createMobAttributes());
		
		register(REDSTONE_CUBE, "redstone_cube", RedstoneCubeEntity.createMobAttributes());
		
		register(LIGHT_DISC, "light_disc");
		
		
		
	}
	
	private static void register(EntityType<?> type, String id) {
		Registry.register(Registry.ENTITY_TYPE, new Identifier(DonutMod.modId, id), type);
	}

	private static void register(EntityType<? extends LivingEntity> type, String id, DefaultAttributeContainer.Builder attributes) {
		register(type, id);
		FabricDefaultAttributeRegistry.register(type, attributes);
	}
	
}
