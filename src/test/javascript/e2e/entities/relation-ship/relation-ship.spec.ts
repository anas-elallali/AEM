import { browser, ExpectedConditions as ec /* , promise */ } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  RelationShipComponentsPage,
  /* RelationShipDeleteDialog, */
  RelationShipUpdatePage,
} from './relation-ship.page-object';

const expect = chai.expect;

describe('RelationShip e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let relationShipComponentsPage: RelationShipComponentsPage;
  let relationShipUpdatePage: RelationShipUpdatePage;
  /* let relationShipDeleteDialog: RelationShipDeleteDialog; */

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load RelationShips', async () => {
    await navBarPage.goToEntity('relation-ship');
    relationShipComponentsPage = new RelationShipComponentsPage();
    await browser.wait(ec.visibilityOf(relationShipComponentsPage.title), 5000);
    expect(await relationShipComponentsPage.getTitle()).to.eq('aemApp.relationShip.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(relationShipComponentsPage.entities), ec.visibilityOf(relationShipComponentsPage.noResult)),
      1000
    );
  });

  it('should load create RelationShip page', async () => {
    await relationShipComponentsPage.clickOnCreateButton();
    relationShipUpdatePage = new RelationShipUpdatePage();
    expect(await relationShipUpdatePage.getPageTitle()).to.eq('aemApp.relationShip.home.createOrEditLabel');
    await relationShipUpdatePage.cancel();
  });

  /* it('should create and save RelationShips', async () => {
        const nbButtonsBeforeCreate = await relationShipComponentsPage.countDeleteButtons();

        await relationShipComponentsPage.clickOnCreateButton();

        await promise.all([
            relationShipUpdatePage.setRelationShipInput('relationShip'),
            relationShipUpdatePage.networkMemberSelectLastOption(),
        ]);

        expect(await relationShipUpdatePage.getRelationShipInput()).to.eq('relationShip', 'Expected RelationShip value to be equals to relationShip');

        await relationShipUpdatePage.save();
        expect(await relationShipUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await relationShipComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    }); */

  /* it('should delete last RelationShip', async () => {
        const nbButtonsBeforeDelete = await relationShipComponentsPage.countDeleteButtons();
        await relationShipComponentsPage.clickOnLastDeleteButton();

        relationShipDeleteDialog = new RelationShipDeleteDialog();
        expect(await relationShipDeleteDialog.getDialogTitle())
            .to.eq('aemApp.relationShip.delete.question');
        await relationShipDeleteDialog.clickOnConfirmButton();

        expect(await relationShipComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
