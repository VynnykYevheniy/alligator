package com.alligator.model.enumeration;

import lombok.Getter;

@Getter
public enum BusinessCategory {
    AQUAPARK("aquapark", "Аквапарк"),
    FITNESS_CENTER("fitness-center", "Фітнес-центр"),
    HOTEL("hotel", "Готель"),
    RESTAURANT("restaurant", "Ресторан"),
    SAUNA("sauna", "Сауна"),
    SPA("spa", "SPA"),
    TOURISM("tourism", "ТУРИЗМ");

    private final String name;
    private final String uaName;

    BusinessCategory(String name, String uaName) {
        this.name = name;
        this.uaName = uaName;
    }

    public static BusinessCategory fromString(String text) {
        for (BusinessCategory b : BusinessCategory.values()) {
            if (b.name.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("No constant with name " + text + " found");
    }

    @Override
    public String toString() {
        return name;
    }
}