package com.tom.createterminal.behaviour;

import org.apache.commons.lang3.tuple.MutablePair;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureBlockInfo;

import com.simibubi.create.api.behaviour.interaction.MovingInteractionBehaviour;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;

import com.tom.createterminal.menu.ITerminal;

public class TerminalInteraction extends MovingInteractionBehaviour {
	private final TerminalBehaviour behaviour;

	public TerminalInteraction(TerminalBehaviour behaviour) {
		this.behaviour = behaviour;
	}

	@Override
	public boolean handlePlayerInteraction(Player player, InteractionHand activeHand, BlockPos localPos, AbstractContraptionEntity contraptionEntity) {
		if (player.level().isClientSide)return true;
		MutablePair<StructureBlockInfo, MovementContext> actor = contraptionEntity.getContraption().getActorAt(localPos);
		if (actor != null && actor.right != null) {
			ITerminal term = behaviour.getInstanceFrom(actor.right);
			player.openMenu(term);
			return true;
		}
		return false;
	}
}
