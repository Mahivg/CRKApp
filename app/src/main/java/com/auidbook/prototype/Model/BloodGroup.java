package com.auidbook.prototype.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

public enum BloodGroup {
    O_POSITIVE("O +ve"),
    O_NEGATIVE("O -ve"),
    A_POSITIVE("A +ve"),
    A_NEGATIVE("A -ve"),
    B_POSITIVE("B +ve"),
    B_NEGATIVE("B -ve"),
    AB_POSITIVE("AB +ve"),
    AB_NEGATIVE("AB -ve");


    private String label;

    BloodGroup(String label) {
        this.label = label;
    }

    @JsonCreator
    public static BloodGroup get(String label) {
        BloodGroup[] values = BloodGroup.values();
        for (BloodGroup bloodGroup : values) {
            if (bloodGroup.label.equals(label)) {
                return bloodGroup;
            }
        }
        return null;
    }

    public static List<String> all() {
        BloodGroup[] values = BloodGroup.values();
        ArrayList<String> labels = new ArrayList<>();
        for (BloodGroup value : values) {
            labels.add(value.label);
        }
        return labels;
    }

    @Override
    @JsonValue
    public String toString() {
        return label;
    }
}
