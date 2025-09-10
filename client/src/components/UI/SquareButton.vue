<template>
    <div    tabindex="0"
            :class="`square_button ${isComponent(icon) ? 'may-be-hovered' : ''} ${focused ? 'focused' : ''}`"
            :style="`width: ${width}; height: ${height}; border-radius: ${borderRadius};`">
        <!-- If it a Vue component -->
        <component  v-if="isComponent(icon)"
                    :is="icon"
                    class="icon" />

        <!-- If it a static path to picture -->
        <img    v-else
                :src="icon"
                alt="icon"
                class="icon" />
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import HelpButton from "../../assets/icons/help.svg";

export default defineComponent({
    name: "SquareButton",
    props: {
        width: {
            type: String,
            required: false,
            default: '35px',
        },
        height: {
            type: String,
            required: false,
            default: '35px',
        },
        icon: {
            type: String,
            required: false,
            default: HelpButton,
        },
        focused: {
            type: Boolean,
            required: false,
            default: false,
        },
        borderRadius: {
            type: String,
            required: false,
            default: '0px',
        },
    },
    methods: {
        isComponent(obj: any): boolean {
            // If it a Vue component

            return typeof obj === "object";
        },
    },
});
</script>

<style lang="scss" scoped>
@use "/src/assets/styles/fonts";
@use "/src/assets/styles/colors" as *;
@use "/src/assets/styles/image";

.square_button {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: $white;
    cursor: pointer;
    transition: all ease-in 0.2s;
    user-select: none;
    border: 3px solid transparent;

    &.focused {
        background-color: $pink;

        .icon {
            color: $white;
            fill: $white;
        }
    }

    .icon {
        transition: all ease-in 0.2s, transform ease-in-out 0.2s;
        width: 50%;
        pointer-events: none;
    }
}

.square_button.may-be-hovered {
    &:hover {
        border-color: $pink;
    }
    
    &:active {
        outline: none;
        border-color: $pink;

        .icon {
            transform: scale(1.2);
        }
    }

    .icon {
        color: $pink;
        fill: $pink;
    }

    &.focused {
        &:hover {
            border-color: $white;
        }
        
        &:active {
            border-color: $white;
        }
    }
}
</style>