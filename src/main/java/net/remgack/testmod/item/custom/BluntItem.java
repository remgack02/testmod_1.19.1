package net.remgack.testmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.remgack.testmod.block.ModBlocks;
import net.remgack.testmod.item.ModItems;

public class BluntItem extends PotionItem {

    public BluntItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        hitBlunt(entity);
        entity.gameEvent(GameEvent.EAT);
        ItemStack roach = new ItemStack(ModItems.ROACH_ITEM.get(), 1);
        return roach;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            hitBlunt(player);
            player.getCooldowns().addCooldown(this, 10);
        }
        return super.use(level, player, hand);
    }

    private void hitBlunt(LivingEntity entity) {
        entity.sendSystemMessage(Component.literal("Puff Puff Hit the Blunt"));
        entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 600, 0, false, true));
    }
}
