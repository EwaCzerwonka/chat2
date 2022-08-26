package com.ewa.commons;

public enum OptionTypes {
    MENU("""
                    Options:
                    :quit - exit room/chat
                    :room number - create/join room at this number, e.g. :room 1
                    :log room_number - read history log from room / main room is 0 or nothing
                    :help - display this menu
                    """
    ),
    QUIT(":quit"),
    JOIN(":room"),
    HELP(":help"),
    HISTORY(":log");
    public final String label;

    private OptionTypes(String label){
        this.label = label;
    }

    public static OptionTypes ifContains(String text){
        for(OptionTypes enumValue : values()){
            if(text.contains(enumValue.label)){
                return enumValue;
            }
        }
        return null;
    }

}
