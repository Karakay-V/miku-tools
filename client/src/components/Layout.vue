<template>
    <div class="layout_wrapper">
        <img class="background" :src="Background" alt="Cool Background" />

        <div class="content_inner">
            <div class="content_wrapper">
                <h1 class="title">Net Tools</h1>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Background from "../assets/covers/background.png"
// import Ping from ""


export default defineComponent({
    name: "Layout",
    data() {
        return({
            Background,
        });
    },
    components: {
        // Ping,
    },
});
</script>

<style lang="scss" scoped>
@use "/src/assets/styles/fonts";
@use "/src/assets/styles/colors" as *;
@use "/src/assets/styles/image";
@use "/src/assets/styles/shadow";

.layout_wrapper {
    width: 100%;
    height: 100%;
    position: relative;
    @include image.prevent-manipulations;

    .background {
        width: 100%;
        height: 100%;
    }

    .content_inner {
        position: absolute;
        top: 0;
        z-index: 1;
        width: 100%;
        height: 100%;

        .content_wrapper {
            margin: 0 auto;
            height: 100%;
            width: 73%;
            background-color: $black;
            @include shadow.shadow-deep;

            @media (max-width: 920px) {
                width: 100%;
            }

            .title {
                @include fonts.ubuntu-mono-font(400);
                @include fonts.responsive-font(55, 55, 1366);
                color: $white;
                text-align: center;
                padding: 16px 0 16px;
            }
        }
    }
}
</style>