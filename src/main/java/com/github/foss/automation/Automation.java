package com.github.foss.automation;

import com.github.foss.automation.items.House;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Automation implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("automation");

    // making a new creative menu icon to group items
    public static final ItemGroup AUTOMATION = FabricItemGroupBuilder.build(
            new Identifier("automation", "automation"),
            () -> new ItemStack(Blocks.BARRIER)
    );

// used for final implementation

//    public static final ItemGroup OTHER_GROUP = FabricItemGroupBuilder.create(
//                    new Identifier("tutorial", "other"))
//            .icon(() -> new ItemStack(Items.BOWL))
//            .appendItems(stacks -> {
//                stacks.add(new ItemStack(Blocks.BONE_BLOCK));
//                stacks.add(new ItemStack(Items.APPLE));
//                stacks.add(PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER));
//                stacks.add(ItemStack.EMPTY);
//                stacks.add(new ItemStack(Items.IRON_SHOVEL));
//            })
//            .build();

    // new item via House.java
    public static final House HOUSE = new House(new FabricItemSettings().group(Automation.AUTOMATION).maxCount(16));



    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");

        // registering the item HOUSE we created
        Registry.register(Registry.ITEM, new Identifier("automation", "house"), HOUSE);
    }
}
