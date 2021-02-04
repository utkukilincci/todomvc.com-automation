package base;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import helper.ElementHelper;
import helper.StoreHelper;
import model.ElementInfo;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    public static WebDriver driver;
    static int deffaultListSize = 0;

    @BeforeScenario
    public void setup(){
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://todomvc.com/examples/vue/");
    }

    public WebElement findElement(String key){
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }

    public List<WebElement> findElements(String key){
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        return driver.findElements(infoParam);
    }

    public By findElementBy(String key){
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        return infoParam;
    }

    public void isElementsListEmpty(String key){
        WebElement fullList = findElement(key);
        List<WebElement> ulFullList = fullList.findElements(By.tagName("li"));
        int listSize = ulFullList.size();
        int expectedEmptyListSize = 0;
        Assert.assertEquals(expectedEmptyListSize,listSize);
    }

    public void sendText(String key, String text){
        findElement(key).sendKeys(text);
    }

    public void clickEnterTheElement(String key){
        findElement(key).sendKeys(Keys.ENTER);
    }

    public void isElementListFull(String key){
        WebElement fullList = findElement(key);
        List<WebElement> ulFullList = fullList.findElements(By.tagName("li"));
        int listSize = ulFullList.size();
        if(listSize > deffaultListSize){
            Assert.assertNotEquals(deffaultListSize,listSize);
        }else {
            Assert.assertNotEquals(deffaultListSize,listSize);
        }
    }

    public void isElementExist(String key, String text){
        List<String> list = forListElements(key);
        Assert.assertTrue("Aradiginiz element bulunamadi",list.contains(text));
    }

    public void isElementDeleted(String key, String text){
        List<String> list = forListElements(key);
        Assert.assertFalse("Aradiginiz element mevcut",list.contains(text));
    }

    public void checkAndAddToList(String key, String text, String keytwo){
        List<WebElement> list = findElements(key);
        int listSize = list.size();
        if (listSize > deffaultListSize){
           List<String> listTexts = forListElements(key);
           boolean result = listTexts.contains(text);
           if(result == false){
               sendText(keytwo,text);
               clickEnterTheElement(keytwo);
           }
        }else{
            sendText(keytwo,text);
            clickEnterTheElement(keytwo);
        }
    }

    public void checkPositons(String key, String text1, String text2){
        List<String> listTexts = forListElements(key);
        int firstIndex = listTexts.indexOf(text1);
        int secondIndex = listTexts.indexOf(text2);
        Assert.assertTrue(firstIndex > secondIndex);
    }

    public void clickTheItemAttirbute(String key, String text, String keytwo){
        WebElement toDoList = findElement(key);
        List<WebElement> ulToDoList = toDoList.findElements(By.tagName("li"));
        List<String> list = forListElements(key);
        int index = list.indexOf(text);
        WebElement element = ulToDoList.get(index).findElement(findElementBy(keytwo));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void assertRadioButtonStatus(boolean condition){
        Assert.assertTrue("Radio button beklenen statude degil",condition);
    }

    public boolean isClickedRadioButton(String key, String text){
        WebElement toDoList = findElement(key);
        List<WebElement> ulToDoList = toDoList.findElements(By.tagName("li"));
        List<String> list = forListElements(key);
        int index = list.indexOf(text);
        String isChecked = ulToDoList.get(index).getAttribute("class");
        String checkedString = "todo completed";
        return (isChecked.equals(checkedString));
    }

    public void checkAndClickTheRadioButton(String key, String text, String keytwo){
        boolean check = false;
        if (isClickedRadioButton(key,text) == check){
            clickTheItemAttirbute(key, text, keytwo);
        }
    }

    public boolean isNotClickedTheRadioButton(String key, String text){
        WebElement toDoList = findElement(key);
        List<WebElement> ulToDoList = toDoList.findElements(By.tagName("li"));
        List<String> list = forListElements(key);
        int index = list.indexOf(text);
        String isChecked = ulToDoList.get(index).getAttribute("class");
        String checkedString = "todo";
        return (isChecked.equals(checkedString));
    }

    public List<String> forListElements(String key){
        WebElement toDoList = findElement(key);
        List<WebElement> ulToDoList = toDoList.findElements(By.tagName("li"));
        List<String> list = new ArrayList<>();
        for(WebElement i : ulToDoList){
            list.add(i.getText());
        }
        return list;
    }

    @AfterScenario
    public void tearDown(){
        driver.close();
    }
}