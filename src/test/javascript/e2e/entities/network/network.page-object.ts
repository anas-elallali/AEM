import { element, by, ElementFinder } from 'protractor';

export class NetworkComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-network div table .btn-danger'));
  title = element.all(by.css('jhi-network div h2#page-heading span')).first();
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

export class NetworkUpdatePage {
  pageTitle = element(by.id('jhi-network-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nameInput = element(by.id('field_name'));
  descriptionInput = element(by.id('field_description'));
  avatarInput = element(by.id('file_avatar'));
  typeSelect = element(by.id('field_type'));
  statusInput = element(by.id('field_status'));

  parentNetworkSelect = element(by.id('field_parentNetwork'));
  addressSelect = element(by.id('field_address'));
  ownerSelect = element(by.id('field_owner'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name: string): Promise<void> {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput(): Promise<string> {
    return await this.nameInput.getAttribute('value');
  }

  async setDescriptionInput(description: string): Promise<void> {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput(): Promise<string> {
    return await this.descriptionInput.getAttribute('value');
  }

  async setAvatarInput(avatar: string): Promise<void> {
    await this.avatarInput.sendKeys(avatar);
  }

  async getAvatarInput(): Promise<string> {
    return await this.avatarInput.getAttribute('value');
  }

  async setTypeSelect(type: string): Promise<void> {
    await this.typeSelect.sendKeys(type);
  }

  async getTypeSelect(): Promise<string> {
    return await this.typeSelect.element(by.css('option:checked')).getText();
  }

  async typeSelectLastOption(): Promise<void> {
    await this.typeSelect.all(by.tagName('option')).last().click();
  }

  getStatusInput(): ElementFinder {
    return this.statusInput;
  }

  async parentNetworkSelectLastOption(): Promise<void> {
    await this.parentNetworkSelect.all(by.tagName('option')).last().click();
  }

  async parentNetworkSelectOption(option: string): Promise<void> {
    await this.parentNetworkSelect.sendKeys(option);
  }

  getParentNetworkSelect(): ElementFinder {
    return this.parentNetworkSelect;
  }

  async getParentNetworkSelectedOption(): Promise<string> {
    return await this.parentNetworkSelect.element(by.css('option:checked')).getText();
  }

  async addressSelectLastOption(): Promise<void> {
    await this.addressSelect.all(by.tagName('option')).last().click();
  }

  async addressSelectOption(option: string): Promise<void> {
    await this.addressSelect.sendKeys(option);
  }

  getAddressSelect(): ElementFinder {
    return this.addressSelect;
  }

  async getAddressSelectedOption(): Promise<string> {
    return await this.addressSelect.element(by.css('option:checked')).getText();
  }

  async ownerSelectLastOption(): Promise<void> {
    await this.ownerSelect.all(by.tagName('option')).last().click();
  }

  async ownerSelectOption(option: string): Promise<void> {
    await this.ownerSelect.sendKeys(option);
  }

  getOwnerSelect(): ElementFinder {
    return this.ownerSelect;
  }

  async getOwnerSelectedOption(): Promise<string> {
    return await this.ownerSelect.element(by.css('option:checked')).getText();
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

export class NetworkDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-network-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-network'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
