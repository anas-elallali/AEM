import { element, by, ElementFinder } from 'protractor';

export class NetworkMemberComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-network-member div table .btn-danger'));
  title = element.all(by.css('jhi-network-member div h2#page-heading span')).first();
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

export class NetworkMemberUpdatePage {
  pageTitle = element(by.id('jhi-network-member-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  networkSelect = element(by.id('field_network'));
  profileSelect = element(by.id('field_profile'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async networkSelectLastOption(): Promise<void> {
    await this.networkSelect.all(by.tagName('option')).last().click();
  }

  async networkSelectOption(option: string): Promise<void> {
    await this.networkSelect.sendKeys(option);
  }

  getNetworkSelect(): ElementFinder {
    return this.networkSelect;
  }

  async getNetworkSelectedOption(): Promise<string> {
    return await this.networkSelect.element(by.css('option:checked')).getText();
  }

  async profileSelectLastOption(): Promise<void> {
    await this.profileSelect.all(by.tagName('option')).last().click();
  }

  async profileSelectOption(option: string): Promise<void> {
    await this.profileSelect.sendKeys(option);
  }

  getProfileSelect(): ElementFinder {
    return this.profileSelect;
  }

  async getProfileSelectedOption(): Promise<string> {
    return await this.profileSelect.element(by.css('option:checked')).getText();
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

export class NetworkMemberDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-networkMember-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-networkMember'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
