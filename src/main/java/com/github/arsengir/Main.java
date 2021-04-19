package com.github.arsengir;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Frog frog = new Frog();
        Scanner scanner = new Scanner(System.in);
        System.out.println("+N - прыгни на N шагов направо\n" +
                "-N - прыгни на N шагов налево\n" +
                "<< - Undo (отмени последнюю команду)\n" +
                ">> - Redo (повтори отменённую команду)\n" +
                "!! - повтори последнюю команду\n" +
                "0 - выход");

        List<FrogCommand> commands = new ArrayList<>();
        int curCommand = -1;
        while (true) {
            String inCmd = scanner.nextLine();
            if ("0".equals(inCmd)) break;
            if ("<<".equals(inCmd)) {
                if (curCommand < 0) {
                    System.out.println("Нечего отменять!");
                } else {
                    commands.get(curCommand).undo();
                    curCommand--;
                }
            } else if (">>".equals(inCmd)) {
                if (curCommand == commands.size() - 1) {
                    System.out.println("Нечего отменять!");
                } else {
                    curCommand++;
                    commands.get(curCommand).todo();
                }
            } else if ("!!".equals(inCmd)) {
                if (curCommand < 0) {
                    System.out.println("Нечего повторять!");
                } else {
                    commands.get(curCommand).todo();
                }
            } else {
                if (curCommand != commands.size() - 1) {
                    //удаляем все команды которые были отменены
                    while (curCommand != commands.size() - 1) {
                        commands.remove(commands.size() - 1);
                    }
                }
                int steps = Integer.parseInt(inCmd);
                FrogCommand cmd;
                if (steps > 0) {
                    cmd = FrogCommands.jumpRightCommand(frog, steps);
                } else {
                    cmd = FrogCommands.jumpLeftCommand(frog, steps);
                }

                curCommand++;
                commands.add(cmd);
                cmd.todo();
            }

            //рисуем поле после команды
            System.out.println(frog.position);
        }
    }
}
