package pageUIs;

public class EmployeeListPageUI {
    public static final String DELETE_EMPLOYEE_BUTTON_BY_ID = "//div[@class='oxd-table-card']//div[text()='%s']/parent::div/following-sibling::div//i[@class='oxd-icon bi-trash']";
    public static final String EDIT_EMPLOYEE_BUTTON_BY_ID = "//div[@class='oxd-table-card']//div[text()='%s']/parent::div/following-sibling::div//i[@class='oxd-icon bi-pencil-fill']";
    public static final String CONFIRM_DELETE_EMPLOYEE_BUTTON = "//button[text()=' Yes, Delete ']";
    public static final String ICON_SORT_BY_ID = "//div[@class='oxd-table-header']//div[@role='columnheader'][2]//i[contains(@class,'oxd-table-header-sort-icon')]";
    public static final String SORT_BY_ID_ITEM = "//div[@class='oxd-table-header']//div[@role='columnheader'][2]//ul[@role='menu']/li";
    public static final String EMPLOYEE_ID_TEXT_ITEM = "//div[@class='oxd-table-card']/div[contains(@class,'oxd-table-row')]/div[2]/div";
    public static final String TOTAL_EMPLOYEE_AT_UI_TEXT = "//span[contains(string(),'Records Found')]";

}
