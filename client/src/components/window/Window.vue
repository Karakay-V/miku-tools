<template>
    <div    class="window_wrapper" 
            :style="`width: ${width}; height: ${height};`">
        
        <!-- Top Bar -->
         <TopBar />

        <div class="output-container">
            <slot>
                <!-- Output View -->
            </slot>
        </div>
        
        <!-- Input Bar -->
        <InputBar />
        
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import TopBar from './TopBar.vue';
import InputBar from './InputBar.vue';

export default defineComponent({
    name: "Window",
    data() {
        return({

        });
    },
    props: {
        width: {
            type: String,
            required: false,
            default: '100%',
        },
        height: {
            type: String,
            required: false,
            default: '100%',
        },
    },
    components: {
        TopBar,
        InputBar,
    },
});
</script>

<style lang="scss" scoped>
@use "/src/assets/styles/colors" as *;

.window_wrapper {
    border-radius: 20px;
    background-color: $black;

    .output-container {
        padding: 0 5px;
        background-color: $neutral;
    }
}
</style>
