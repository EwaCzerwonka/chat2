package com.ewa.commons;

public enum OptionTypes {
    MENU("""
                    Options:
                    :q - exit room/chat
                    :room number - create/join room at this number, e.g. :room 1
                    :log room_number - read history log from room / main room is 0 or nothing
                    :help - display this menu
                    """
    ),
    QUIT(":q"),
    JOIN(":room"),
    HELP(":help"),
    HIST_ERROR("No access"),
    HISTORY(":log");
    public final String label;

    private OptionTypes(String label){
        this.label = label;
    }


}
