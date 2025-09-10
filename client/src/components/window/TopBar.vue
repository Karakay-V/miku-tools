<template>
    <header class="top_bar-wrapper">
        <nav class="buttons">
            <SquareButton :icon="ClosePic" :focused="true" border-radius="20px 0 0 0" />
            <SquareButton :icon="MaximizePic" />
            <SquareButton :icon="HelpPic" />
        </nav>
    </header>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import SquareButton from '../UI/SquareButton.vue';
import ClosePic from '../../assets/icons/close.svg';
import MaximizePic from '../../assets/icons/maximize.svg';
import HelpPic from '../../assets/icons/help.svg';

export default defineComponent({
    name: "TopBar",
    data() {
        return({
            ClosePic,
            MaximizePic,
            HelpPic,
        });
    },
    components: {
        SquareButton,
    },
});
</script>

<style lang="scss" scoped>
@use "/src/assets/styles/fonts";
@use "/src/assets/styles/colors" as *;
@use "/src/assets/styles/image";

.top_bar-wrapper {
    width: 100%;
    background-color: $neutral;
    display: flex;
    justify-content: flex-start;
    border-radius: 20px 20px 0 0;
    cursor: move;

    nav {
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        height: max-content
    }
}
</style>