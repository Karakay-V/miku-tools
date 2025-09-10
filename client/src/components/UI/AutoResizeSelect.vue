<template>
    <div class="select-wrapper">
        <select 
            ref="selectEl"
            class="tools-select" 
            name="tools" 
            v-model="selected"
            @change="resizeSelect"
        >
            <option v-for="opt in options" :value="opt">{{ opt }}</option>
        </select>
        
        <span ref="hiddenSpan" class="hidden-span">{{ selected }}</span>
    </div>
</template>

<script lang="ts">
import { defineComponent, nextTick } from 'vue';

export default defineComponent({
  name: "AutoResizeSelect",
  data() {
    return {
      selected: this.options[0].toString(),
    };
  },
  props: {
    options: {
        type: Array<String>,
        required: false,
        default: ['ping', 'traceroute'],
    },
  },
  mounted() {
    nextTick(() => {
      this.resizeSelect();
    });
  },
  methods: {
    resizeSelect() {
      if (this.$refs.selectEl && this.$refs.hiddenSpan) {
        const selectEl = this.$refs.selectEl as HTMLSelectElement;
        const hiddenSpan = this.$refs.hiddenSpan as HTMLElement;

        hiddenSpan.textContent = this.selected;
        selectEl.style.width = `${hiddenSpan.offsetWidth}px`;
      }
    },
  },
});
</script>

<style lang="scss" scoped>
@use "/src/assets/styles/fonts";
@use "/src/assets/styles/colors" as *;

.select-wrapper {
    display: inline-block;
    position: relative;
}

.tools-select,
.hidden-span {
    @include fonts.ubuntu-mono-font();
    @include fonts.responsive-font(26, 18, 1366);
    color: $white;
}

.tools-select {
    background: transparent;
    border: none;
    appearance: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    padding-right: 0;
    margin-right: 9px;

    &:active,
    &:focus,
    &:focus-visible,
    &:focus-within {
        outline: none;
    }

    option {
        text-align: center;
        background-color: $black;
        padding: 2px 4px;

        &:hover,
        &:checked {
            background-color: $neutral;
        }
    }
}

.hidden-span {
    position: absolute;
    visibility: hidden;
    white-space: nowrap;
    pointer-events: none;
}
</style>
