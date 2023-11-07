package pageUIs;

public class PIMPageUI {
    public static final String ADD_EMPLOYEE_BUTTON = "//div[@class='orangehrm-paper-container']//button[string()=' Add ']";
    public static final String EMPLOYEE_INFORMATION_TEXTBOX = "//div[@class='orangehrm-employee-form']//input[@placeholder='%s']";
    public static final String EMPLOYEE_ID_TEXTBOX = "//label[text()='Employee Id']/parent::div/following-sibling::div/input";
    public static final String CREATE_LOGIN_DETAILS_CHECKBOX = "//p[text()='Create Login Details']//parent::div//span[contains(@class,'oxd-switch-input')]";
    public static final String EMPLOYEE_LOGIN_INFOR_TEXTBOX = "//label[text()='%s']//parent::div//following-sibling::div//input";
    public static final String SAVE_EMPLOYEE_BUTTON = "//div[@class='orangehrm-card-container']//button[@type='submit']";


}
