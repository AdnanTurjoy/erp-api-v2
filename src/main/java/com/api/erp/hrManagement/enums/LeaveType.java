package com.api.erp.hrManagement.enums;


public enum LeaveType {
    SICK_LEAVE("Sick Leave"),
    CASUAL_LEAVE("Casual Leave"),
    PAID_LEAVE("Paid Leave");

    private final String displayName;

    LeaveType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
