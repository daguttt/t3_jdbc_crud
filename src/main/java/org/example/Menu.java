package org.example;

import org.example.utils.InputRequester;

import java.util.List;

public class Menu {

    public enum MenuStatus {
        OPEN,
        CLOSED
    }

    private static final String DEFAULT_TITLE = "Menu";
    private static final String FOOTER = "************************************************";

    private final List<MenuOption> menuOptions;
    private final String title;
    private final String basePrompt;
    private MenuStatus status;
    private Menu subMenu;
    private Menu parentMenu;
    private boolean canOpenSubMenu;


    public Menu(List<MenuOption> menuOptions) {
        this(menuOptions, DEFAULT_TITLE, "Ingresa una opcion:");
    }


    public Menu(List<MenuOption> menuOptions, String title, String basePrompt) {
        this.menuOptions = menuOptions;
        this.title = title;
        this.basePrompt = basePrompt;
        this.status = MenuStatus.CLOSED;
        this.canOpenSubMenu = true;
    }

    public void setCanOpenSubMenu(boolean canOpenSubMenu) {
        this.canOpenSubMenu = canOpenSubMenu;
    }

    public void open() {
        if (this.isSubMenu() && this.parentMenu.isClosed()) return;

        this.status = MenuStatus.OPEN;

        while (this.status == MenuStatus.OPEN) {
            List<String> commandOptionsAsStringList = this.menuOptions.stream().map(MenuOption::text).toList();
            int choice = InputRequester.requestAnIndexFrom(
                    commandOptionsAsStringList,
                    this.basePrompt,
                    (formattedPromptWithOptions) -> String.format("""
                        %s
                        
                        %s
    
                        %s
                        """, this.getFormattedTitle(this.title), formattedPromptWithOptions, FOOTER)
            );

            this.menuOptions.get(choice).command().execute(this);

            if (this.subMenu != null && this.canOpenSubMenu) this.subMenu.open();
        }

    }

    public void close() {
        this.status = MenuStatus.CLOSED;
        if (this.subMenu != null) this.subMenu.close();
    }

    public Menu subMenu(Menu subMenu) {
        this.subMenu = subMenu;
        this.subMenu.parentMenu = this;
        return this;
    }

    public boolean isClosed() {
        return this.status == MenuStatus.CLOSED;
    }

    public boolean isSubMenu() {
        return this.parentMenu != null;
    }

    private void executeSelectedCommand(int choice) {
        MenuCommand commandToExecute = this.menuOptions.get(choice).command();
        if (this.isSubMenu() && commandToExecute.getClass() == CloseAllCommand.class) {
            commandToExecute.execute(this.parentMenu);
        } else {
            commandToExecute.execute(this);
        }
    }

    private String getFormattedTitle(String baseTitle) {
        final int evenTitleLength = baseTitle.length() % 2 == 0 ? baseTitle.length() : baseTitle.length() + 1;
        final int maxLineLength = FOOTER.length();
        final int spacesAroundTitle = 2;
        final int paddingLength = (maxLineLength - evenTitleLength - spacesAroundTitle) / 2;
        return String.format("%s %s %s", "*".repeat(paddingLength), baseTitle, "*".repeat(paddingLength));
    }
}
