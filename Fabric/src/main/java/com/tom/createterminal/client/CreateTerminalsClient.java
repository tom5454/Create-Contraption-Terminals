package com.tom.createterminal.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.network.chat.Component;

import com.tom.storagemod.Content;

public class CreateTerminalsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientRegistration.register();

		ItemTooltipCallback.EVENT.register((stack, ctx, lines) -> {
			if (stack.getItem() == Content.terminal.get().asItem() || stack.getItem() == Content.craftingTerminal.get().asItem()) {
				lines.add(Component.translatable("tooltip.cct.worksOnContraptions"));
			}
		});
	}
}
