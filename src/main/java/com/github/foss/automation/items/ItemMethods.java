package com.github.foss.automation.items;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMethods {
    // builds based on position, material (blocK), layout, and horizontal repetition
    protected static void build(BlockPos blockPos, World world, Block material, int[][] layout, int up) {
        while (up > -1) {
            for (int i = 0; i < layout.length; i++) {
                for (int j = 0; j < layout[i].length; j++) {
                    if (layout[i][j] == -1) continue;
                    if (layout[i][j] == 0) {
                        world.setBlockState(blockPos.add(i, up, j), Blocks.AIR.getDefaultState());
                        continue;
                    }
                    world.setBlockState(blockPos.add(i, up, j), material.getDefaultState());
                }
            }
            up--;
        }
    }
}
