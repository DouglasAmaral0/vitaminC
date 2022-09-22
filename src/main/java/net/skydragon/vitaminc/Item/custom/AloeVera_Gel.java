package net.skydragon.vitaminc.Item.custom;

import net.fabricmc.fabric.mixin.content.registry.VillagerEntityAccessor;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.skydragon.vitaminc.Item.ModItems;
import net.skydragon.vitaminc.effect.custom.ModEffects;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AloeVera_Gel extends Item {


    public AloeVera_Gel(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED, SoundCategory.PLAYERS, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        if (!world.isClient && user.isOnFire()) {
            user.extinguish();
            user.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
            user.giveItemStack(new ItemStack(Items.GLASS_BOTTLE, 1));
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
