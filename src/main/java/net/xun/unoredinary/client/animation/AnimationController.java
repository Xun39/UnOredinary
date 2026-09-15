package net.xun.unoredinary.client.animation;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AnimationController<E extends Entity> {
    private final E entity;
    private final Map<String, AnimationDefinition> animations;
    private final Map<String, AnimationState> states;
    private final Map<String, Integer> animationTimers;

    private AnimationController(E entity, Map<String, AnimationDefinition> animations, Map<String, AnimationState> states, Map<String, Integer> animationTimers) {
        this.entity = entity;
        this.animations = animations;
        this.states = states;
        this.animationTimers = animationTimers;
    }

    public void tick(String animationId) {
        Objects.requireNonNull(animationId);

        AnimationDefinition animation = animations.get(animationId);
        AnimationState state = states.get(animationId);
        if (animation == null || state == null) return;

        int animationTimeout = animationTimers.getOrDefault(animationId, 0);
        if (animationTimeout <= 0) {
            animationTimeout = getAnimationTicks(animation);
            state.start(entity.tickCount);
        } else {
            --animationTimeout;
        }
        animationTimers.put(animationId, animationTimeout);
    }

    public void playOneShot(String animationId) {
        Objects.requireNonNull(animationId);
        AnimationDefinition animation = animations.get(animationId);
        AnimationState state = states.get(animationId);
        if (animation == null || state == null) {
            return;
        }
        if (!state.isStarted()) {
            state.start(entity.tickCount);
            animationTimers.put(animationId, getAnimationTicks(animation));
        }
    }

    public void tickOneShot(String animationId) {
        AnimationState state = states.get(animationId);
        if (state == null || !state.isStarted()) return;

        int remaining = animationTimers.getOrDefault(animationId, 0);
        if (remaining > 0) {
            animationTimers.put(animationId, remaining - 1);
        } else {
            state.stop();
        }
    }

    public AnimationState getState(String animationId) {
        return this.states.get(animationId);
    }

    private int getAnimationTicks(AnimationDefinition animation) {
        return Math.max(1, Mth.ceil(animation.lengthInSeconds() * 20.0F));
    }

    public static <E extends Entity> Builder<E> builder(E entity) {
        return new Builder<>(entity);
    }

    public static final class Builder<E extends Entity> {
        private final Map<String, AnimationDefinition> animations = new HashMap<>();
        private final Map<String, AnimationState> states = new HashMap<>();
        private final Map<String, Integer> animationTimers = new HashMap<>();
        private final E entity;

        public Builder(E entity) {
            this.entity = entity;
        }

        public Builder<E> animation(String animationId, AnimationDefinition value) {
            Objects.requireNonNull(animationId);
            Objects.requireNonNull(value);
            animations.putIfAbsent(animationId, value);
            states.putIfAbsent(animationId, new AnimationState());
            animationTimers.putIfAbsent(animationId, 0);
            return this;
        }

        public AnimationController<E> build() {
            return new AnimationController<>(entity, animations, states, animationTimers);
        }
    }
}
