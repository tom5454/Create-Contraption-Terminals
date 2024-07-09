package com.tom.createterminal.data;

import net.minecraft.data.DataGenerator;

import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.simibubi.create.foundation.ponder.PonderLocalization;

import com.tom.createterminal.CreateTerminals;
import com.tom.createterminal.client.ClientRegistration;

@Mod.EventBusSubscriber(modid = CreateTerminals.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		if(event.includeClient()) {
			ClientRegistration.register();
			PonderLocalization.provideRegistrateLang(CreateTerminals.registrate());
		}
	}
}
