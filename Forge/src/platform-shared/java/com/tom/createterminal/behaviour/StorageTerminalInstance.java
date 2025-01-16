package com.tom.createterminal.behaviour;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.tom.createterminal.menu.ITerminal.ITerminalImpl;
import com.tom.storagemod.tile.StorageTerminalBlockEntity;

public class StorageTerminalInstance extends StorageTerminalBlockEntity implements ITerminalImpl {
	private ContraptionWorld world;

	public StorageTerminalInstance(ContraptionWorld w) {
		super(w.getPos(), w.getState());
		world = w;
		world.setBE(this);
	}

	@Override
	public void dropItem(ItemStack stack) {
		ITerminalImpl.super.dropItem(stack);
	}

	@Override
	public void updateServer() {
		ITerminalImpl.super.updateServer();
	}

	@Override
	public boolean canInteractWith(Player player) {
		return ITerminalImpl.super.canInteractWith(player);
	}

	@Override
	public ContraptionWorld getContraption() {
		return world;
	}

	@Override
	public void setSorting(int newC) {
		super.setSorting(newC);
		world.saveBE();
	}
}
