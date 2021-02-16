const { I } = inject();
// Add in your custom step files

Given('I navigate to the Login page', () => {
  I.amOnPage('/');
});

Given('I Login with email and Password', (table) => {
  I.wait(3);
  const cells = table.rows[1].cells;
  I.fillField('Email', cells[0].value);
  I.fillField('Password', cells[1].value);
});

// or a simple string
When('I press "Continue"', () => {
  I.click('Continue');
});

// parameters are passed in via Cucumber expressions
Then('I should be on the tenant space', () => {
  I.wait(10);
  I.seeElement({ xpath: "//span[@id='userDiv']"})
});

