package com.tom.createterminal;

import org.slf4j.Logger;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;

import com.tom.createterminal.client.ClientRegistration;

@Mod(CreateTerminals.MODID)
public class CreateTerminals {
	public static final String MODID = "createcontraptionterminals";
	public static final Logger LOGGER = LogUtils.getLogger();

	private static CreateRegistrate registrate;

	public CreateTerminals(ModContainer mc, IEventBus bus) {
		bus.addListener(this::setup);
		bus.addListener(this::doClientStuff);

		registrate = CreateRegistrate.create(MODID).registerEventListeners(bus);

		Registration.register();
	}

	private void setup(final FMLCommonSetupEvent event) {
		LOGGER.info("Create Contraption Terminals starting");
		Registration.postRegister();
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		event.enqueueWork(ClientRegistration::register);
	}

	public static CreateRegistrate registrate() {
		return registrate;
	}
}
