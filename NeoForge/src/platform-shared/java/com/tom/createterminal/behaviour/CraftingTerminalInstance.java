package com.tom.createterminal.behaviour;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.tom.createterminal.menu.ITerminal.ICraftingTerminalImpl;
import com.tom.storagemod.block.entity.CraftingTerminalBlockEntity;
import com.tom.storagemod.inventory.StoredItemStack;

public class CraftingTerminalInstance extends CraftingTerminalBlockEntity implements ICraftingTerminalImpl {
	private ContraptionWorld world;

	public CraftingTerminalInstance(ContraptionWorld w) {
		super(w.getPos(), w.getState());
		world = w;
		world.setBE(this);
	}

	@Override
	public void dropItem(ItemStack stack) {
		ICraftingTerminalImpl.super.dropItem(stack);
	}

	@Override
	public void updateServer() {
		ICraftingTerminalImpl.super.updateServer();
	}

	@Override
	public boolean canInteractWith(Player player, boolean menuCheck) {
		return ICraftingTerminalImpl.super.canInteractWith(player, menuCheck);
	}

	@Override
	public StoredItemStack pullStack(StoredItemStack stack, long max) {
		return ICraftingTerminalImpl.super.pullStack(stack, max);
	}

	@Override
	public StoredItemStack pushStack(StoredItemStack stack) {
		return ICraftingTerminalImpl.super.pushStack(stack);
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
	public void setChanged() {
		world.saveBE();
	}

	@Override
	public Level getLevel0() {
		return level;
	}
}
