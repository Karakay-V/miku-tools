<template>
    <div class="cmd" @click="focusInput">
        <span>{{ internalCommand }}</span>
        <div :class="`cursor ${cursorVisible ? 'visible' : ''}`"></div>
    </div>
    <input
        ref="input"
        v-model="internalCommand"
        @blur="stopCursor"
        :type="type"
        :maxlength="maxlength"
        name="command"
    />
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
    name: 'TerminalInput',
    data() {
        return {
            internalCommand: this.modelValue,
            cursorVisible: true,
            cursorInterval: 0 as number | undefined,
        };
    },
    props: {
      modelValue: {
        type: String,
        required: true,
      },
      type: {
        type: String, 
        required: false,
        default: 'text',
      },
      maxlength: {
        type: Number,
        required: false,
        default: 50,
      },
    },
    watch: {
      // Sync prop changes to internal state
      modelValue(newVal: string) {
        this.internalCommand = newVal;
      },
      // Emit changes from internal state to parent
      internalCommand(newVal: string) {
        this.$emit('update:modelValue', newVal);
      },
    },
    methods: {
        focusInput() {
            (this.$refs.input as HTMLInputElement)?.focus();
            this.startCursor();
        },
        startCursor() {
            this.stopCursor();
            this.cursorInterval = window.setInterval(() => {
                this.cursorVisible = !this.cursorVisible;
            }, 750);
        },
        stopCursor() {
            if (this.cursorInterval) {
                clearInterval(this.cursorInterval);
                this.cursorInterval = undefined;
            }
            this.cursorVisible = true;
        },
    },
    beforeUnmount() {
        this.stopCursor();
    },
});
</script>

<style lang="scss" scoped>
@use "/src/assets/styles/fonts";
@use "/src/assets/styles/colors" as *;

.cmd {
    @include fonts.ubuntu-mono-font();
    @include fonts.responsive-font(26, 18, 1366);
    color: $white;
    background: transparent;
    overflow: hidden;
    cursor: text;
    display: flex;
    align-items: center;
    width: 100%;
    max-width: 100%;
    height: 100%;
}

.cmd span {
    float: left;
    padding-left: 3px;
    white-space: pre;
}

.cursor {
    visibility: hidden;
    float: left;
    width: 9px;
    height: 20px;
    background: transparent;
    border: 2px solid $white;
    margin: 0 5px;

    &.visible {
        visibility: visible;
    }
}

input {
    width: 0;
    height: 0;
    opacity: 0;
    position: absolute;
}
</style>
