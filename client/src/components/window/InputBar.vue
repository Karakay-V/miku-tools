<template>
    <footer>
        <div class="input-container">
            <p class="host-and-user">tools@miku<span class="sign">$</span></p>

            <AutoResizeSelect />

            <TerminalInput v-model="input" />
        </div>

        <SquareButton :icon="ArrowPic" :focused="true" width="57px" height="47px" border-radius="0 0 20px 0" />
    </footer>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import SquareButton from '../UI/SquareButton.vue';
import ArrowPic from '../../assets/icons/arrow.svg';
import AutoResizeSelect from '../UI/AutoResizeSelect.vue';
import TerminalInput from '../UI/TerminalInput.vue';

export default defineComponent({
    name: "InputBar",
    data() {
        return({
            input: ref(''),
            ArrowPic,
        });
    },
    props: {

    },
    methods: {

    },
    components: {
        SquareButton,
        AutoResizeSelect,
        TerminalInput,
    },
});
</script>

<style lang="scss" scoped>
@use "/src/assets/styles/fonts";
@use "/src/assets/styles/colors" as *;
@use "/src/assets/styles/image";

footer {
    height: max-content;
    background-color: $neutral;
    border-radius: 0 0 20px 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.input-container {
    width: calc(100% - 14px - 14px);
    height: 100%;
    padding: 9px 14px;
    display: flex;
    justify-content: flex-start;
    align-items: center;

    .host-and-user,
    .input_stream {
        @include fonts.ubuntu-mono-font();
        @include fonts.responsive-font(26, 18, 1366);
        color: $white;
    }

    .sign {
        margin: 0 15px;
        
        @media (max-width: 768px) {
            margin: 0 11px;
        }
    }

    .input_stream {
        background: transparent;
        border: none;
        
        &:active,
        &:focus,
        &:focus-visible,
        &:focus-within {
            outline: none;
        }
    }
}
</style>