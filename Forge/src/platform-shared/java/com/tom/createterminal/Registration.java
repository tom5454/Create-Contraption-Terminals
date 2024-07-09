package com.tom.createterminal;

import com.simibubi.create.AllInteractionBehaviours;
import com.simibubi.create.AllMovementBehaviours;
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

		AllMovementBehaviours.registerBehaviour(Content.terminal.get(), term);
		AllMovementBehaviours.registerBehaviour(Content.craftingTerminal.get(), cTerm);

		AllInteractionBehaviours.registerBehaviour(Content.terminal.get(), new TerminalInteraction(term));
		AllInteractionBehaviours.registerBehaviour(Content.craftingTerminal.get(), new TerminalInteraction(cTerm));
	}

	public static void add(String key, String value) {
		REGISTRATE.addRawLang(key, value);
	}
}
