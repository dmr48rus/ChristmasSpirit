package com.tm.cspirit.event;

import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.main.CSConfig;
import com.tm.cspirit.util.helper.PresentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class MobArmorEvent {

    @SubscribeEvent
    public void onEntitySpawn(LivingSpawnEvent.SpecialSpawn event) {

        LivingEntity entity = event.getEntityLiving();

        if (event.getSpawnReason() == SpawnReason.NATURAL) {

            Random random = new Random();

            if (entity instanceof ZombieEntity || entity instanceof SkeletonEntity) {

                if (random.nextInt(CSConfig.misc.mobArmorRarity.get()) == 0) {

                    MobEntity mob = (MobEntity) event.getEntityLiving();

                    Item[] helmetList = new Item[]{InitItems.CHRISTMAS_HAT.get(), InitItems.BEANIE_BLACK.get(), InitItems.BEANIE_RED.get(), InitItems.BEANIE_GREEN.get()};
                    Item[] chestList = new Item[]{InitItems.SWEATER_BLACK.get(), InitItems.SWEATER_RED.get(), InitItems.SWEATER_GREEN.get()};
                    Item[] legsList = new Item[]{InitItems.WINTER_JEANS.get()};
                    Item[] bootsList = new Item[]{InitItems.WINTER_BOOTS.get(), InitItems.ICE_SKATES.get()};

                    Item helmet = helmetList[random.nextInt(helmetList.length)];
                    Item chest = chestList[random.nextInt(chestList.length)];
                    Item legs = legsList[random.nextInt(legsList.length)];
                    Item boots = bootsList[random.nextInt(bootsList.length)];

                    mob.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(helmet));
                    mob.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(chest));
                    mob.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(legs));
                    mob.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(boots));

                    ItemStack giftStack = new ItemStack(InitItems.PRESENT_WRAPPED_RED.get());
                    PresentHelper.getSantaPresent("Anybody", 0).toStack(giftStack);

                    mob.setItemStackToSlot(EquipmentSlotType.OFFHAND, giftStack);

                    mob.setDropChance(EquipmentSlotType.OFFHAND, 1);
                }
            }
        }
    }
}
