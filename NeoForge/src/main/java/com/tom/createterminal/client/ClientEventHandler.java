package com.tom.createterminal.client;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import com.tom.createterminal.CreateTerminals;
import com.tom.storagemod.Content;

@EventBusSubscriber(value = Dist.CLIENT, modid = CreateTerminals.MODID)
public class ClientEventHandler {

	@SubscribeEvent
	public static void addToItemTooltip(ItemTooltipEvent event) {
		if (event.getEntity() == null)
			return;

		ItemStack stack = event.getItemStack();
		if (stack.getItem() == Content.terminal.get().asItem() || stack.getItem() == Content.craftingTerminal.get().asItem()) {
			event.getToolTip().add(Component.translatable("tooltip.cct.worksOnContraptions"));
		}
	}
}
