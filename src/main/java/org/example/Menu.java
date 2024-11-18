package org.example;

import org.example.commands.RegisterCommand;
import org.example.utils.InputRequester;

import java.util.List;

public class Menu {
    private static final String DEFAULT_TITLE = "Menu";
    private static final String DEFAULT_DESCRIPTION = "Ingresa una opción:";
    private static final String FOOTER = "************************************************";

    private final List<MenuOption> menuOptions;
    private String title;
    private String description;

    public Menu(List<MenuOption> menuOptions) {
        this(menuOptions, DEFAULT_TITLE, DEFAULT_DESCRIPTION);
    }

    public Menu(List<MenuOption> menuOptions, String title, String description) {
        this.menuOptions = menuOptions;
        this.title = title;
        this.description = description;
    }

    public void display() {
        List<String> commandOptionsAsStringList = this.menuOptions.stream().map(MenuOption::text).toList();
        System.out.printf("commandOptionsAsStringList: %s", commandOptionsAsStringList);
        int choice = InputRequester.requestAnIndexFrom(commandOptionsAsStringList, "Ingresa una opcion:",
                (basePrompt) -> String.format("""
                    %s
                    
                    %s

                    %s
                    """, this.getFormattedTitle(), basePrompt, FOOTER));

        this.menuOptions.get(choice).command().execute();

    }

    private String getFormattedTitle() {
        final int evenTitleLength = this.title.length() % 2 == 0 ? this.title.length() : this.title.length() + 1;
        final int maxLineLength = FOOTER.length();
        final int spacesAroundTitle = 2;
        final int paddingLength = (maxLineLength - evenTitleLength - spacesAroundTitle) / 2;
        return String.format("%s %s %s", "*".repeat(paddingLength), this.title, "*".repeat(paddingLength));
    }
}
