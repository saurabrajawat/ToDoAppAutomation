package org.todo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.setProperty("webdriver.chrome.driver", "/Users/ac02f5bb0md6m/Desktop/ToDoApp/ToDoAppAutomationSuit/src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
    }
}