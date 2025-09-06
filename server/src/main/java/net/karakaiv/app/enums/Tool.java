package net.karakaiv.app.enums;

public enum Tool {
    PING,
    TRACEROUTE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static Tool fromString(String value) {
        for (Tool tool : Tool.values()) {
            if (tool.name().equalsIgnoreCase(value)) {
                return tool;
            }
        }
        throw new IllegalArgumentException("Unknown tool: " + value);
    }
}
