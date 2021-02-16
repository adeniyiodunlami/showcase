exports.config = {
  output: './output',
  helpers: {
    Playwright: {
      url: 'https://orionjan01.fiixcmms-devserver.com/',
      show: true,
      waitForNavigation: 'domcontentloaded',
      browser: 'webkit'
    }
  },
  include: {
    I: './steps_file.js'
  },
  mocha: {},
  bootstrap: null,
  teardown: null,
  hooks: [],
  gherkin: {
    features: './features/*.feature',
    steps: ['./step_definitions/steps.js']
  },
  plugins: {
    screenshotOnFail: {
      enabled: true
    },
    pauseOnFail: {},
    retryFailedStep: {
      enabled: true
    },
    tryTo: {
      enabled: true
    }
  },
  tests: 'tests/*_test.js',
  name: 'Playwright_demo'
}