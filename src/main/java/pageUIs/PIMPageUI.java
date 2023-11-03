package pageUIs;

public class PIMPageUI {
    public static final String DYNAMIC_MAIN_MENU = "//ul[@class='oxd-main-menu']//span[text()='%s']";
    public static final String HEADER_USER_NAME = "//header[@class='oxd-topbar']//p[@class='oxd-userdropdown-name']";
    public static final String ADD_EMPLOYEE_BUTTON = "//div[@class='orangehrm-paper-container']//button[string()=' Add ']";
    public static final String EMPLOYEE_INFORMATION_TEXTBOX = "//div[@class='orangehrm-employee-form']//input[@placeholder='%s']";
    public static final String CREATE_LOGIN_DETAIL_CHECKBOX = "//p[text()='Create Login Details']//parent::div//span[contains(@class,'oxd-switch-input')]";
    public static final String EMPLOYEE_LOGIN_INFOR_TEXTBOX = "//label[text()='%s']//parent::div//following-sibling::div//input";
    public static final String SAVE_EMPLOYEE_BUTTON = "//div[@class='orangehrm-card-container']//button[@type='submit']";
    public static final String ADD_NEW_EMPLOYEE_SUCCESS_MESSAGE = "//div[@class='oxd-toast-start']//p[text()='Successfully Saved']";
    public static final String PERSONAL_DETAILS_LABEL = "//div[@class='orangehrm-edit-employee']//h6[text()='Personal Details']";
    public static final String EMPLOYEE_INFOR_TEXTBOX_AT_PERSONAL_DETAILS_PAGE = "//div[@class='orangehrm-edit-employee']//input[@placeholder='%s']";

}
