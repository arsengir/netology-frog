package com.github.arsengir;

public class FrogCommands {

    public static FrogCommand jumpRightCommand(Frog frog, int steps) {
        return new FrogCommand() {
            @Override
            public boolean todo() {
                return frog.jump(steps);
            }

            @Override
            public boolean undo() {
                return frog.jump(-steps);
            }
        };
    }
}
