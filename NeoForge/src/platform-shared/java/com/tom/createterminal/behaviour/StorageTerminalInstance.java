package com.tom.createterminal.behaviour;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.tom.createterminal.menu.ITerminal.ITerminalImpl;
import com.tom.storagemod.block.entity.StorageTerminalBlockEntity;
import com.tom.storagemod.inventory.StoredItemStack;

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
	public boolean canInteractWith(Player player, boolean menuCheck) {
		return ITerminalImpl.super.canInteractWith(player, menuCheck);
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

	@Override
	public StoredItemStack pullStack(StoredItemStack stack, long max) {
		return ITerminalImpl.super.pullStack(stack, max);
	}

	@Override
	public StoredItemStack pushStack(StoredItemStack stack) {
		return ITerminalImpl.super.pushStack(stack);
	}

	@Override
	public Level getLevel0() {
		return level;
	}
}
