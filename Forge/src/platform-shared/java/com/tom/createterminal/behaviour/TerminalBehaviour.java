package com.tom.createterminal.behaviour;

import java.util.function.Function;

import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;

import com.tom.createterminal.menu.ITerminal;

public class TerminalBehaviour implements MovementBehaviour {
	private final Function<ContraptionWorld, ITerminal> instanceFactory;

	public TerminalBehaviour(Function<ContraptionWorld, ITerminal> instanceFactory) {
		this.instanceFactory = instanceFactory;
	}

	@Override
	public void tick(MovementContext context) {
		getInstanceFrom(context).updateServer();
	}

	public ITerminal getInstanceFrom(MovementContext ctx) {
		if (!(ctx.temporaryData instanceof ITerminal))
			ctx.temporaryData = instanceFactory.apply(new ContraptionWorld(this, ctx));
		return (ITerminal) ctx.temporaryData;
	}
}
