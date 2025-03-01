package com.tom.createterminal;

import com.simibubi.create.api.behaviour.interaction.MovingInteractionBehaviour;
import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import com.simibubi.create.foundation.data.CreateRegistrate;

import com.tom.createterminal.behaviour.CraftingTerminalInstance;
import com.tom.createterminal.behaviour.StorageTerminalInstance;
import com.tom.createterminal.behaviour.TerminalBehaviour;
import com.tom.createterminal.behaviour.TerminalInteraction;
import com.tom.storagemod.Content;

public class Registration {
	private static final CreateRegistrate REGISTRATE = CreateTerminals.registrate();

	public static void register() {
		add("tooltip.cct.worksOnContraptions", "Works on Create Contraptions");
	}

	public static void postRegister() {
		TerminalBehaviour term = new TerminalBehaviour(StorageTerminalInstance::new);
		TerminalBehaviour cTerm = new TerminalBehaviour(CraftingTerminalInstance::new);

		MovementBehaviour.REGISTRY.register(Content.terminal.get(), term);
		MovementBehaviour.REGISTRY.register(Content.craftingTerminal.get(), cTerm);

		MovingInteractionBehaviour.REGISTRY.register(Content.terminal.get(), new TerminalInteraction(term));
		MovingInteractionBehaviour.REGISTRY.register(Content.craftingTerminal.get(), new TerminalInteraction(cTerm));
	}

	public static void add(String key, String value) {
		REGISTRATE.addRawLang(key, value);
	}
}
