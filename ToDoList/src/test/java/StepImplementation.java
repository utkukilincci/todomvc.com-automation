import base.BaseTest;
import com.thoughtworks.gauge.Step;

public class StepImplementation extends BaseTest {

    @Step("<key> element listesi var mi")
    public void findElementsList(String key) {
        try {
            findElements(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Step("<key> element listesi bos mu")
    public void checkElementsListEmpty(String key) {
        isElementsListEmpty(key);
    }

    @Step("<sec> saniye bekle")
    public void waitSecond(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("<key> elementine <text> degerini gir")
    public void setToDoText(String key, String text) {
        sendText(key, text);
    }

    @Step("<key> elementinde enter tusla")
    public void implementation1(String key) {
        clickEnterTheElement(key);
    }

    @Step("<key> element listesi dolu mu")
    public void checkElementsListFull(String key) {
        isElementListFull(key);
    }

    @Step("<key> elementinde <text> var mi")
    public void checkTextInList(String key, String text) {
        isElementExist(key, text);
    }

    @Step("<key> element listesinde <text> yoksa <keytwo> elementine ekle")
    public void addIfIsNotThere(String key, String text, String keytwo) {
        checkAndAddToList(key, text, keytwo);
    }

    @Step("<key> 'nde <text1> elementi <text2> elementinin altinda mi")
    public void checkPositionItems(String key, String text1, String text2) {
        checkPositons(key, text1, text2);
    }

    @Step("<key> listesindeki <text> iteminin <keytwo> elementine tikla")
    public void clickItemAttirbute(String key, String text, String keytwo) {
        clickTheItemAttirbute(key, text, keytwo);
    }

    @Step("<key> listesindeki <text> isaretli mi")
    public void checkTheRadioButton(String key, String text) {
        assertRadioButtonStatus(isClickedRadioButton(key, text));
    }

    @Step("<key> listesindeki <text> isaretli degilse <keytwo> isaretle")
    public void checkAndClickRadioButon(String key, String text, String keytwo) {
        checkAndClickTheRadioButton(key, text, keytwo);
    }

    @Step("<key> listesindeki <text> isaretsiz mi")
    public void isNotChecked(String key, String text) {
        assertRadioButtonStatus(isNotClickedTheRadioButton(key, text));
    }

    @Step("<key> elementinde <text> silindi mi")
    public void isDeleted(String key, String text) {
        isElementDeleted(key, text);
    }
}