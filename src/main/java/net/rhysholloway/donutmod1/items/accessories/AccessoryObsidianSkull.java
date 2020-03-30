package net.rhysholloway.donutmod1.items.accessories;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.rhysholloway.donutmod1.util.DonutRegistries.ACCESSORY;

public class AccessoryObsidianSkull extends ACCESSORY {
	
	private int lastFireTimer = 0;

	private final int maxTime;
	
	public AccessoryObsidianSkull(double seconds, Item.Settings settings, String registryName) {
		super(settings, registryName);
		this.maxTime = (int) (20 * seconds);
	}

	@Override
	public void tick(PlayerEntity livingEntity, ItemStack stack) {
		if (!livingEntity.getEntityWorld().isClient) {
			if (livingEntity.isOnFire()) {
				if (livingEntity.getFireTicks() + (int) maxTime < lastFireTimer) {
					livingEntity.setFireTicks(0);
					lastFireTimer = 0;					
				} else if(livingEntity.getFireTicks() > lastFireTimer){
					lastFireTimer = livingEntity.getFireTicks();					
				}
			}
			
		}
	}
	
	/*

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(stack, world, list, flag);
		list.add(new StringTextComponent(TextFormatting.ITALIC + "Stops you from burning after a while"));
	}

	*/
	
}
