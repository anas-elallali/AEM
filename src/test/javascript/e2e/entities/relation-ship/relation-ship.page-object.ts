import { element, by, ElementFinder } from 'protractor';

export class RelationShipComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-relation-ship div table .btn-danger'));
  title = element.all(by.css('jhi-relation-ship div h2#page-heading span')).first();
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

export class RelationShipUpdatePage {
  pageTitle = element(by.id('jhi-relation-ship-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  relationShipInput = element(by.id('field_relationShip'));

  networkMemberSelect = element(by.id('field_networkMember'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setRelationShipInput(relationShip: string): Promise<void> {
    await this.relationShipInput.sendKeys(relationShip);
  }

  async getRelationShipInput(): Promise<string> {
    return await this.relationShipInput.getAttribute('value');
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

export class RelationShipDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-relationShip-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-relationShip'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
