package io.swagger.model.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum IncludeFrozen {

    YES("Yes"),

    No("No");

    private String value;

    IncludeFrozen(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static IncludeFrozen fromValue(String text) {
        for (IncludeFrozen b : IncludeFrozen.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}