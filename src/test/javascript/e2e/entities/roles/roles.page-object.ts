import { element, by, ElementFinder } from 'protractor';

export class RolesComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-roles div table .btn-danger'));
  title = element.all(by.css('jhi-roles div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class RolesUpdatePage {
  pageTitle = element(by.id('jhi-roles-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  roleSelect = element(by.id('field_role'));

  networkMemberSelect = element(by.id('field_networkMember'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setRoleSelect(role: string): Promise<void> {
    await this.roleSelect.sendKeys(role);
  }

  async getRoleSelect(): Promise<string> {
    return await this.roleSelect.element(by.css('option:checked')).getText();
  }

  async roleSelectLastOption(): Promise<void> {
    await this.roleSelect.all(by.tagName('option')).last().click();
  }

  async networkMemberSelectLastOption(): Promise<void> {
    await this.networkMemberSelect.all(by.tagName('option')).last().click();
  }

  async networkMemberSelectOption(option: string): Promise<void> {
    await this.networkMemberSelect.sendKeys(option);
  }

  getNetworkMemberSelect(): ElementFinder {
    return this.networkMemberSelect;
  }

  async getNetworkMemberSelectedOption(): Promise<string> {
    return await this.networkMemberSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class RolesDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-roles-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-roles'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
