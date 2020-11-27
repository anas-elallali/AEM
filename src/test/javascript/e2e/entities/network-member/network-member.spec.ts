import { browser, ExpectedConditions as ec /* , promise */ } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  NetworkMemberComponentsPage,
  /* NetworkMemberDeleteDialog, */
  NetworkMemberUpdatePage,
} from './network-member.page-object';

const expect = chai.expect;

describe('NetworkMember e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let networkMemberComponentsPage: NetworkMemberComponentsPage;
  let networkMemberUpdatePage: NetworkMemberUpdatePage;
  /* let networkMemberDeleteDialog: NetworkMemberDeleteDialog; */

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load NetworkMembers', async () => {
    await navBarPage.goToEntity('network-member');
    networkMemberComponentsPage = new NetworkMemberComponentsPage();
    await browser.wait(ec.visibilityOf(networkMemberComponentsPage.title), 5000);
    expect(await networkMemberComponentsPage.getTitle()).to.eq('aemApp.networkMember.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(networkMemberComponentsPage.entities), ec.visibilityOf(networkMemberComponentsPage.noResult)),
      1000
    );
  });

  it('should load create NetworkMember page', async () => {
    await networkMemberComponentsPage.clickOnCreateButton();
    networkMemberUpdatePage = new NetworkMemberUpdatePage();
    expect(await networkMemberUpdatePage.getPageTitle()).to.eq('aemApp.networkMember.home.createOrEditLabel');
    await networkMemberUpdatePage.cancel();
  });

  /* it('should create and save NetworkMembers', async () => {
        const nbButtonsBeforeCreate = await networkMemberComponentsPage.countDeleteButtons();

        await networkMemberComponentsPage.clickOnCreateButton();

        await promise.all([
            networkMemberUpdatePage.networkSelectLastOption(),
            networkMemberUpdatePage.profileSelectLastOption(),
        ]);


        await networkMemberUpdatePage.save();
        expect(await networkMemberUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await networkMemberComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    }); */

  /* it('should delete last NetworkMember', async () => {
        const nbButtonsBeforeDelete = await networkMemberComponentsPage.countDeleteButtons();
        await networkMemberComponentsPage.clickOnLastDeleteButton();

        networkMemberDeleteDialog = new NetworkMemberDeleteDialog();
        expect(await networkMemberDeleteDialog.getDialogTitle())
            .to.eq('aemApp.networkMember.delete.question');
        await networkMemberDeleteDialog.clickOnConfirmButton();

        expect(await networkMemberComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
