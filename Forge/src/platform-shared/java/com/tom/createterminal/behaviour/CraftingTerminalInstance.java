package com.tom.createterminal.behaviour;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.tom.createterminal.menu.ITerminal.ICraftingTerminalImpl;
import com.tom.storagemod.tile.CraftingTerminalBlockEntity;

public class CraftingTerminalInstance extends CraftingTerminalBlockEntity implements ICraftingTerminalImpl {
	private ContraptionWorld world;

	public CraftingTerminalInstance(ContraptionWorld w) {
		super(w.getPos(), w.getState());
		world = w;
		world.setBE(this);
	}

	@Override
	public void pushOrDrop(ItemStack st) {
		ICraftingTerminalImpl.super.pushOrDrop(st);
	}

	@Override
	public void updateServer() {
		ICraftingTerminalImpl.super.updateServer();
	}

	@Override
	public boolean canInteractWith(Player player) {
		return ICraftingTerminalImpl.super.canInteractWith(player);
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
}
