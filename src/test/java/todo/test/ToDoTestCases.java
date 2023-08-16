package todo.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import static org.testng.Assert.*;

public class ToDoTestCases {

    WebDriver driver;
    String taskName = "MyTask1";
   @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/ac02f5bb0md6m/Desktop/ToDoApp/ToDoAppAutomationSuit/src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://simple-todo-app-gamma.vercel.app/");
    }

    @Test(testName = "The 'Add Task' button should be initially disabled.",priority = 1)
    public void testInputValidation() {

        driver.findElement(By.cssSelector("#app > div:nth-child(2) > input[type=date]:nth-child(2)")).sendKeys("16/08/2023");
        WebElement addButton = driver.findElement(By.className("add-task-button"));
        boolean isDisabled = addButton.getAttribute("disabled") != null;

        if (isDisabled) {
            System.out.println("The 'add-task-button' element is disabled.");
        } else {
            System.out.println("The 'add-task-button' element is not disabled.");
        }
        assertEquals(isDisabled, true);
    }

    @Test(testName = "Add Task",priority = 2)
    public void addTask()
    {
        testAddTask(taskName);

    }


    public void testAddTask(String taskName) {

        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/input[1]")).sendKeys(taskName);
        driver.findElement(By.cssSelector("#app > div:nth-child(2) > input[type=date]:nth-child(2)")).sendKeys("16/08/2023");
        driver.findElement(By.className("add-task-button")).click();
        WebElement taskElement = driver.findElement(By.xpath("//*[@id=\"app\"]/ul"));
        System.out.printf("Task name: %s", taskElement.getText());
        assertNotNull(taskElement.getText().contains(taskName));
    }

  /*  public void markTaskDone()
    {
        testAddTask("Task2");


    }*/


    @Test(testName = "markSpecificTaskAsDone",priority = 3)
    public void markSpecificTaskAsDone() {

        String taskName = "test12";
        testAddTask(taskName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement task = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li/span[contains(text(), '" + taskName + "')]")));
        WebElement markDoneButton = task.findElement(By.xpath("./following-sibling::button[@class='mark-done-button']"));
        markDoneButton.click();
        assertTrue(task.getAttribute("class").contains("done"));
    }

    @Test(testName = "testDeleteSpecificTask",priority = 4)
    public void testDeleteSpecificTask() {

      //  String taskName = "test"; // Replace with the specific task name
       // testAddTask(taskName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement task = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li/span[contains(text(), '" + taskName + "')]")));
        WebElement deleteButton = task.findElement(By.xpath("./following-sibling::button[@class='delete-button']"));
        deleteButton.click();
        WebElement taskElement = driver.findElement(By.xpath("//*[@id=\"app\"]/ul"));
        assertFalse(taskElement.getText().contains(taskName));
    }

    @Test(testName = "Mark all Task as Done",priority = 5)
    public void markAllDone()
    {
        testAddTask("Task1");
        testAddTask("Task2");
        testAddTask("Task3");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.className("mark-all-button")).click();
       // document.getElementsByClassName('done').length;
        int length = driver.findElements(By.className("done")).size();
        System.out.println("length is "+length);
        assertTrue(length > 0,"Count of done tasks should be greater than 0");
    }

    @Test(testName = "Delete all Task",priority = 6)
    public void deleteAllTask()
    {
        testAddTask("Task4");
        testAddTask("Task5");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.className("delete-all-button")).click();
        int length = driver.findElements(By.className("done")).size();
        System.out.println("length is "+length);
        assertTrue(length == 0,"Count of done tasks should be 0");
    }

    @Test(testName = "Mark all Task as Not Done",priority = 7)
    public void markAllNotDone()
    {
        testAddTask("Task4");
        testAddTask("Task5");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.className("mark-all-not-done-button")).click();
        int length = driver.findElements(By.className("done")).size();
        System.out.println("length is "+length);
        assertTrue(length == 0,"Count of done tasks should be 0");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}


