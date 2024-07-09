package com.tom.createterminal.client;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import com.simibubi.create.content.trains.station.StationBlock;
import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.WorldSectionElement;

public class PonderScenes {

	public static void terminals(SceneBuilder scene, SceneBuildingUtil util) {
		scene.title("terminals", "Using Tom's Simple Storage Terminals with Create");
		scene.configureBasePlate(1, 0, 9);
		scene.scaleSceneView(.75f);
		scene.setSceneOffsetY(-1);
		scene.showBasePlate();

		ElementLink<WorldSectionElement> vault = scene.world.showIndependentSection(util.select.fromTo(5, 3, 3, 7, 5, 5), Direction.DOWN);
		scene.world.moveSection(vault, util.vector.of(-1, -2, 0), 0);
		scene.idle(10);

		ElementLink<WorldSectionElement> terminal = scene.world.showIndependentSection(util.select.position(4, 4, 4), Direction.DOWN);
		scene.world.moveSection(terminal, util.vector.of(-1, -2, 0), 0);
		scene.idle(10);

		scene.overlay.showText(70)
		.pointAt(util.vector.of(3.35f, 3, 4))
		.placeNearTarget()
		.attachKeyFrame()
		.text("Storage Terminals can easily access the contents of Vaults");
		scene.idle(60);

		var stationPos = new BlockPos(2, 1, 1);

		scene.world.hideIndependentSection(terminal, Direction.UP);
		scene.idle(10);
		scene.world.hideIndependentSection(vault, Direction.UP);
		scene.idle(10);

		scene.world.showSection(util.select.fromTo(0, 1, 4, 10, 1, 4), Direction.DOWN);//Tracks
		scene.idle(10);

		scene.world.showSection(util.select.position(stationPos), Direction.DOWN);//Station
		scene.idle(10);

		ElementLink<WorldSectionElement> train = scene.world.showIndependentSection(util.select.fromTo(1, 2, 3, 8, 2, 5), Direction.DOWN);//Train Floor
		scene.world.cycleBlockProperty(stationPos, StationBlock.ASSEMBLING);
		scene.idle(10);

		scene.world.showSectionAndMerge(util.select.fromTo(5, 3, 3, 7, 5, 5), Direction.DOWN, train);//Vault
		scene.idle(10);

		scene.world.showSectionAndMerge(util.select.fromTo(1, 3, 4, 4, 3, 4), Direction.DOWN, train);//Controls
		scene.idle(10);

		scene.world.showSectionAndMerge(util.select.position(4, 4, 4), Direction.DOWN, train);//Terminal
		scene.idle(10);

		scene.overlay.showText(70)
		.pointAt(util.vector.of(4.35f, 5, 4))
		.placeNearTarget()
		.attachKeyFrame()
		.text("Terminals also work on assembled contraptions");
		scene.idle(60);

		scene.world.cycleBlockProperty(stationPos, StationBlock.ASSEMBLING);
		scene.effects.indicateSuccess(stationPos);
		scene.world.animateTrainStation(stationPos, true);
		scene.world.toggleControls(new BlockPos(2, 3, 4));
		scene.idle(20);

		scene.world.moveSection(train, util.vector.of(-1, 0, 0), 20);
		scene.world.animateBogey(util.grid.at(3, 2, 4), 1f, 20);
		scene.world.animateBogey(util.grid.at(7, 2, 4), 1f, 20);
		scene.world.animateTrainStation(stationPos, false);
		scene.idle(40);

		scene.overlay.showText(70)
		.pointAt(util.vector.of(3.35f, 5, 4))
		.placeNearTarget()
		.attachKeyFrame()
		.text("The terminal allows accessing the entire contraption inventory");
		scene.idle(60);

		scene.world.moveSection(train, util.vector.of(-10, 0, 0), 80);
		scene.world.animateBogey(util.grid.at(3, 2, 4), 10f, 80);
		scene.world.animateBogey(util.grid.at(7, 2, 4), 10f, 80);
		scene.idle(40);

		scene.world.hideIndependentSection(train, null);
	}
}
