package net.xun.unoredinary.client.animation;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;

import java.util.*;

public class AnimationController<E extends Entity> {
    private final E entity;
    private final Map<String, AnimationDefinition> animations;
    private final Map<String, AnimationState> states;
    private final Map<String, Integer> animationTimers;
    private final List<AnimationTransition> animationTransitions;
    private final String initialAnimation;

    private boolean initialized;

    private AnimationController(
            E entity,
            Map<String, AnimationDefinition> animations,
            Map<String, AnimationState> states,
            Map<String, Integer> animationTimers,
            List<AnimationTransition> animationTransitions,
            String initialAnimation
    ) {
        this.entity = entity;
        this.animations = animations;
        this.states = states;
        this.animationTimers = animationTimers;
        this.animationTransitions = Objects.requireNonNullElse(animationTransitions, new ArrayList<>());
        this.initialAnimation = initialAnimation;
    }

    public void tick() {
        if (!entity.level().isClientSide) {
            return;
        }

        if (!initialized) {
            initialized = true;

            if (initialAnimation != null) {
                playAnimation(initialAnimation);
            }
        }

        animations.forEach((id, animation) -> {
            if (!animation.looping()) {
                tickOneShot(id);
            }
        });
    }

//    public void tickLooping(String animationId) {
//        Objects.requireNonNull(animationId);
//
//        AnimationDefinition animation = animations.get(animationId);
//        AnimationState state = states.get(animationId);
//        if (animation == null || state == null) return;
//
//        int animationTimeout = animationTimers.getOrDefault(animationId, 0);
//        if (animationTimeout <= 0) {
//            animationTimeout = getAnimationTicks(animation);
//            state.start(entity.tickCount);
//        } else {
//            --animationTimeout;
//        }
//        animationTimers.put(animationId, animationTimeout);
//    }

    public void tickOneShot(String animationId) {
        AnimationState state = states.get(animationId);
        if (state == null || !state.isStarted()) return;

        int remaining = animationTimers.getOrDefault(animationId, 0);
        if (remaining > 0) {
            animationTimers.put(animationId, remaining - 1);
        } else {
            state.stop();
            animationTimers.put(animationId, 0);

            transitionFrom(animationId);
        }
    }

    public void playAnimation(String animationId) {
        Objects.requireNonNull(animationId);

        AnimationDefinition animation = animations.get(animationId);
        AnimationState state = states.get(animationId);

        if (animation == null || state == null) {
            return;
        }

        states.forEach((id, otherState) -> {
            if (!id.equals(animationId)) {
                otherState.stop();
            }
        });

        state.start(entity.tickCount);
        if (!animation.looping()) {
            animationTimers.put(animationId, getAnimationTicks(animation));
        }
    }

    public void stopAnimation(String animationId) {
        Objects.requireNonNull(animationId);
        AnimationState state = states.get(animationId);
        if (state == null) return;

        state.stop();
        animationTimers.put(animationId, 0);
    }

    private void transitionFrom(String animationId) {
        for (AnimationTransition transition : animationTransitions) {
            if (transition.fromAnimation().equals(animationId)) {
                playAnimation(transition.toAnimation());
                return;
            }
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
        private final E entity;
        private final Map<String, AnimationDefinition> animations = new HashMap<>();
        private final Map<String, AnimationState> states = new HashMap<>();
        private final Map<String, Integer> animationTimers = new HashMap<>();
        private final List<AnimationTransition> animationTransitions = new ArrayList<>();
        private String initialAnimation;

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

        public Builder<E> transition(String fromAnimation, String toAnimation) {
            animationTransitions.add(new AnimationTransition(fromAnimation, toAnimation));
            return this;
        }

        public Builder<E> initialAnimation(String animationId) {
            this.initialAnimation = animationId;
            return this;
        }

        public AnimationController<E> build() {
            return new AnimationController<>(entity, animations, states, animationTimers, animationTransitions, initialAnimation);
        }
    }

    public record AnimationTransition(String fromAnimation, String toAnimation) {
        public AnimationTransition {
            Objects.requireNonNull(fromAnimation, "fromAnimation");
            Objects.requireNonNull(toAnimation, "toAnimation");
        }
    }
}
