package com.tom.createterminal;

import java.util.function.Consumer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.simibubi.create.content.logistics.vault.ItemVaultBlock;
import com.simibubi.create.content.logistics.vault.ItemVaultBlockEntity;

public class VaultMultiblockDetector {

	public static void checkVault(Level level, BlockPos p, BlockState state, Consumer<BlockPos> extra) {
		if (state.getBlock() instanceof ItemVaultBlock) {
			if (level.getBlockEntity(p) instanceof ItemVaultBlockEntity be) {
				ItemVaultBlockEntity controllerBE = be.getControllerBE();
				BlockPos pos = controllerBE.getBlockPos();

				int radius = controllerBE.getWidth();
				int length = controllerBE.getHeight();

				Axis axis = controllerBE.getMainConnectionAxis();

				int zMax = (axis == Axis.X ? radius : length);
				int xMax = (axis == Axis.Z ? radius : length);

				for (int y = 0; y < radius; y++) {
					for (int z = 0; z < zMax; z++) {
						for (int x = 0; x < xMax; x++) {
							extra.accept(pos.offset(x, y, z));
						}
					}
				}
			}
		}
	}
}
