package org.example;

import org.example.utils.InputRequester;

import java.util.ArrayList;
import java.util.List;

public class MenuImpl extends Menu {


    public MenuImpl(List<MenuOption> menuOptions) {
        super(menuOptions);
    }

    public MenuImpl(List<MenuOption> menuOptions, String title) {
        super(menuOptions, title);
    }

    @Override
    public void open() {

        List<String> commandOptionsAsStringList = this.menuOptions.stream().map(MenuOption::text).toList();
        System.out.printf("commandOptionsAsStringList: %s", commandOptionsAsStringList);
        int choice = InputRequester.requestAnIndexFrom(commandOptionsAsStringList, "Ingresa una opcion:",
                (basePrompt) -> String.format("""
                    %s
                    
                    %s

                    %s
                    """, this.getFormattedTitle(this.title), basePrompt, FOOTER));

        this.menuOptions.get(choice).command().execute(this);

        super.open();

    }
}
