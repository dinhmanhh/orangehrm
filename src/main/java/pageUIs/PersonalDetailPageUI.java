package pageUIs;

public class PersonalDetailPageUI {
    public static final String PERSONAL_DETAILS_LABEL = "//div[@class='orangehrm-edit-employee']//h6[text()='Personal Details']";
    public static final String EMPLOYEE_INFOR_TEXTBOX = "//div[@class='orangehrm-edit-employee']//input[@placeholder='%s']";
    public static final String DRIVER_LICENSE_NUMBER_TEXTBOX = "//label[text()=concat(\"Driver'\",'s License Number')]/parent::div/following-sibling::div/input";
    public static final String LICENSE_EXPIRED_DATE = "//label[text()='License Expiry Date']/parent::div/following-sibling::div//input[@placeholder='yyyy-mm-dd']";
    public static final String CUSTOM_DROPDOWN = "//div[@role='listbox']//span";
    public static final String NATIONALITY_DROPDOWN = "//label[text()='Nationality']/parent::div/following-sibling::div//div[text()='-- Select --']";
    public static final String MARITAL_STATUS_DROPDOWN = "//label[text()='Marital Status']/parent::div/following-sibling::div//div[text()='-- Select --']";
    public static final String DATE_OF_BIRTH_TEXTBOX = "//label[text()='Date of Birth']/parent::div/following-sibling::div//input[@placeholder='yyyy-mm-dd']";
    public static final String GENDER_RADIO_BUTTON = "//label[text()='Gender']/parent::div/parent::div//label[text()='%s']/span[contains(@class,'oxd-radio-input')]";
    public static final String SAVE_BUTTON = "//button[@type='submit']";


}
