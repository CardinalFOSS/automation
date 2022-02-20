package com.github.foss.automation.items;

import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

import static com.github.foss.automation.items.ItemMethods.build;

/*
assets (texture and model) at src/resources/assets...
crafting recipe at src/resources/data/automation/recipes
*/
public class House extends Item {
    public House(Settings settings) {
        super(settings);
    }

    // overriding original method of class Item
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        playerEntity.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);

        // instead of blocks Fabric uses "BlockState" to represent blocks
        // world.setBlockState(playerEntity.getBlockPos().down(), Blocks.OAK_WOOD.getDefaultState());

        // layout for structure, with -1 for "none," 0 as "air," 1 as "place"
        final int[][] FLOOR = {
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, 1, 1, 1, 1, 1, -1},
                {-1, 1, 1, 1, 1, 1, -1},
                {-1, 1, 1, 1, 1, 1, -1},
                {-1, 1, 1, 1, 1, 1, -1},
                {-1, 1, 1, 1, 1, 1, -1},
                {-1, -1, -1, -1, -1, -1, -1},
        };

        final int[][] WALLS = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };

        BlockPos playerPos = playerEntity.getBlockPos().down();

        build(playerPos, world, Blocks.STONE, FLOOR, 0);
        build(playerPos.add(0, 1, 0), world, Blocks.OAK_WOOD, WALLS, 3);
        build(playerPos.add(0, 4, 0), world, Blocks.STONE, FLOOR, 0);

        world.setBlockState(playerPos.add(5, 3, 3), Blocks.OAK_WOOD.getDefaultState());
        world.setBlockState(playerPos.add(5, 1, 3), Blocks.IRON_DOOR.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST));
//        world.setBlockState(playerPos.add(4, 1, 2), Blocks.ACACIA_FENCE_GATE.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST));

        playerEntity.getStackInHand(hand).decrement(1);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add( new TranslatableText("An item that automagically places a house at your location").formatted(Formatting.RED) );
    }
}
