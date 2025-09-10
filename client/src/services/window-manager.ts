// services/window-manager.ts
import { ref } from "vue";

export type WindowData = {
    id: number;
    title: string;
    component: any;                   // Place here your Vue-component
    props?: Record<string, any>;      // Additional props
};

const windows = ref<WindowData[]>([]);
let nextId = 1;

export function useWindowManager() {
    function openWindow(window: Omit<WindowData, "id">) {
        const newWindow = { ...window, id: nextId++ };
        windows.value.push(newWindow);
        return newWindow.id;
    }

    function closeWindow(id: number) {
        windows.value = windows.value.filter(w => w.id !== id);
    }

    return {
        windows,
        openWindow,
        closeWindow,
    };
}
