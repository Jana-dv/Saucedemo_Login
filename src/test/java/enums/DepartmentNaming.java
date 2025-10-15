package enums;

import lombok.Getter;

public enum DepartmentNaming {
    PRODUCTS("Products"),
    CART("Your Cart");

    @Getter
    private final String displayName;

    DepartmentNaming(String displayName) {
        this.displayName = displayName;
    }


}
