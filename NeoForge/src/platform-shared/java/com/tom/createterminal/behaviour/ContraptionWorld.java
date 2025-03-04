package com.tom.createterminal.behaviour;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureBlockInfo;
import net.minecraft.world.phys.Vec3;

import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;

public class ContraptionWorld {
	private TerminalBehaviour bh;
	private MovementContext context;
	private BlockEntity be;

	public ContraptionWorld(TerminalBehaviour bh, MovementContext context) {
		this.bh = bh;
		this.context = context;
	}

	public void setNbt(CompoundTag tag) {
		context.blockEntityData = tag;
		var pos = getPos();
		context.contraption.entity.setBlock(pos, new StructureBlockInfo(pos, getState(), tag));
	}

	public BlockPos getPos() {
		return context.localPos;
	}

	public Contraption getContraption() {
		return context.contraption;
	}

	public Vec3 getActualPosition() {
		var pos = context.localPos;
		return context.contraption.entity.toGlobalVector(new Vec3(pos.getX(), pos.getY(), pos.getZ()), 0f);
	}

	public void setBE(BlockEntity be) {
		be.setLevel(context.world);
		if (context.blockEntityData != null)
			be.loadWithComponents(context.blockEntityData, context.world.registryAccess());
		this.be = be;
	}

	public void saveBE() {
		if (!context.world.isClientSide && be != null) {
			setNbt(be.saveWithoutMetadata(context.world.registryAccess()));
		}
	}

	public void popItem(ItemStack actualStack) {
		bh.dropItem(context, actualStack);
	}

	public boolean isValid() {
		return context.contraption.entity.isAlive();
	}

	public BlockState getState() {
		return context.state;
	}
}
