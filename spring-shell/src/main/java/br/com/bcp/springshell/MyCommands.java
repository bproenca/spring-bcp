package br.com.bcp.springshell;

import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class MyCommands {

    @ShellMethod("Add two integers together.")
    public int add(final int a, final int b) {
        return a + b;
    }

    @ShellMethod(value= "Say hello.", key="greet")
    public String doGreet(@ShellOption(defaultValue="World") String who) {
        return "Hello " + who;
    }
}