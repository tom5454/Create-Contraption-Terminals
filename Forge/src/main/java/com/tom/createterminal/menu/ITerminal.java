package com.tom.createterminal.menu;

import java.util.stream.IntStream;

import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.items.IItemHandler;

import com.tom.createterminal.behaviour.ContraptionWorld;
import com.tom.createterminal.mixin.CraftingTerminalBlockEntityAccessor;
import com.tom.createterminal.mixin.StorageTerminalBlockEntityAccessor;
import com.tom.storagemod.util.StoredItemStack;

public interface ITerminal extends MenuProvider {
	StoredItemStack pushStack(StoredItemStack stack);
	void dropItem(ItemStack st);
	void updateServer();
	boolean canInteractWith(Player player);

	ContraptionWorld getContraption();

	public static interface ITerminalImpl extends ITerminal {

		@Override
		public default void dropItem(ItemStack st) {
			getContraption().popItem(st);
		}

		@Override
		public default void updateServer() {
			StorageTerminalBlockEntityAccessor ac = (StorageTerminalBlockEntityAccessor) this;
			if(ac.isUpdateItems()) {
				var items = ac.getItems();
				items.clear();
				IItemHandler itemHandler = getContraption().getContraption().getStorage().getAllItems();
				ac.setItemHandler(itemHandler);
				if(itemHandler != null) {
					IntStream.range(0, itemHandler.getSlots()).mapToObj(itemHandler::getStackInSlot).filter(s -> !s.isEmpty()).
					map(StoredItemStack::new).forEach(s -> items.merge(s, s.getQuantity(), (a, b) -> a + b));
				}
				ac.setUpdateItems(false);
			}
		}

		@Override
		default boolean canInteractWith(Player player) {
			return getContraption().isValid() && player.distanceToSqr(getContraption().getActualPosition()) < 64;
		}
	}

	public static interface ICraftingTerminalImpl extends ITerminalImpl {

		@Override
		default void updateServer() {
			ITerminalImpl.super.updateServer();
			((CraftingTerminalBlockEntityAccessor) this).setCraftingCooldown(0);
		}
	}
}
