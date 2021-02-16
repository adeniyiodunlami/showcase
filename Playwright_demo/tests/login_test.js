Feature('login');

Scenario('Page is loaded', ({ I }) => {
    I.amOnPage('/');

    I.wait(3);
    I.fillField('Email', 'test@test.com');
    I.fillField('Password', 'admin');

    I.click('Continue');
    I.wait(10);
    I.seeElement({ xpath: "//span[@id='userDiv']"})
   
    // I.click('Settings');

    // I.wait(10);
    // I.click('Roles');


});





