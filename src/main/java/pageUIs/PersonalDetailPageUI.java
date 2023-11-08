package pageUIs;

public class PersonalDetailPageUI {
    public static final String PERSONAL_DETAILS_LABEL = "//div[@class='orangehrm-edit-employee']//h6[text()='Personal Details']";
    public static final String EMPLOYEE_INFOR_TEXTBOX = "//div[@class='orangehrm-edit-employee']//input[@placeholder='%s']";
    public static final String DRIVER_LICENSE_NUMBER_TEXTBOX = "//label[text()=concat(\"Driver'\",'s License Number')]/parent::div/following-sibling::div/input";
    public static final String LICENSE_EXPIRY_DATE = "//label[text()='License Expiry Date']/parent::div/following-sibling::div//input[@placeholder='yyyy-mm-dd']";
    public static final String CUSTOM_DROPDOWN = "//div[@role='listbox']//span";
    public static final String NATIONALITY_DROPDOWN = "//label[text()='Nationality']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']";
    public static final String MARITAL_STATUS_DROPDOWN = "//label[text()='Marital Status']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']";
    public static final String DATE_OF_BIRTH_TEXTBOX = "//label[text()='Date of Birth']/parent::div/following-sibling::div//input[@placeholder='yyyy-mm-dd']";
    public static final String GENDER_RADIO_BUTTON = "//label[text()='Gender']/parent::div/parent::div//label[text()='%s']/span[contains(@class,'oxd-radio-input')]";
    public static final String SAVE_BUTTON_AT_PERSONAL_INFOR_AREA = "//div[contains(@class,'orangehrm-vertical-padding')]//button[@type='submit']";
    public static final String ADD_ATTACHMENT_BUTTON = "//div[@class='orangehrm-attachment']//button[contains(string(),'Add')]";
    public static final String SAVE_BUTTON_AT_ATTACHMENT_AREA = "//div[@class='orangehrm-attachment']//button[@type='submit']";
    public static final String IMAGE_UPLOADED_RESULT = "//div[@class='oxd-table-body']/div[@class='oxd-table-card']";
    public static final String IMAGE_UPLOADED_NAME_TEXT = "//div[@class='oxd-table-body']/div[@class='oxd-table-card']//div[2]";
    public static final String DELETE_EMPLOYEE_BUTTON_BY_ID = "//div[@class='oxd-table-card']//div[text()='%s']/parent::div/following-sibling::div//i[@class='oxd-icon bi-trash']";
    public static final String CONFIRM_DELETE_EMPLOYEE_BUTTON = "//button[text()=' Yes, Delete ']";

    public static final String ICON_SORT_BY_ID = "//div[@class='oxd-table-header']//div[@role='columnheader'][2]//i[contains(@class,'oxd-table-header-sort-icon')]";
    public static final String SORT_BY_ID_ITEM = "//div[@class='oxd-table-header']//div[@role='columnheader'][2]//ul[@role='menu']/li";
    public static final String EMPLOYEE_ID_TEXT_ITEM = "//div[@class='oxd-table-card']/div[contains(@class,'oxd-table-row')]/div[2]/div";
    public static final String TOTAL_EMPLOYEE_AT_UI_TEXT = "//span[contains(string(),'Records Found')]";


}
