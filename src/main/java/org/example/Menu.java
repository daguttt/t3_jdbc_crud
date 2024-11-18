package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {

    public enum MenuStatus {
        OPEN,
        CLOSED
    }

    protected static final String DEFAULT_TITLE = "Menu";
    protected static String FOOTER = "************************************************";

    protected final List<Menu> subMenus;
    protected final List<MenuOption> menuOptions;
    protected final String title;
    protected MenuStatus status;

    public Menu(List<MenuOption> menuOptions) {
        this(menuOptions, DEFAULT_TITLE);
    }


    public Menu(List<MenuOption> menuOptions, String title) {
        this.menuOptions = menuOptions;
        this.title = title;

        this.subMenus = new ArrayList<>();
    }

    public void setStatus(MenuStatus status) {
    }

    public void open() {
        if (this.status == MenuStatus.CLOSED) return;

        for (Menu subMenu : this.subMenus) {
            subMenu.open();
        }
    }

    public void close() {
        this.status = MenuStatus.CLOSED;
    }

    protected Menu subMenu(Menu subMenu) {
        this.subMenus.add(subMenu);
        return this;
    }

    protected String getFormattedTitle(String baseTitle) {
        final int evenTitleLength = baseTitle.length() % 2 == 0 ? baseTitle.length() : baseTitle.length() + 1;
        final int maxLineLength = FOOTER.length();
        final int spacesAroundTitle = 2;
        final int paddingLength = (maxLineLength - evenTitleLength - spacesAroundTitle) / 2;
        return String.format("%s %s %s", "*".repeat(paddingLength), baseTitle, "*".repeat(paddingLength));
    }
}
