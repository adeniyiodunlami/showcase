package contracts.sampleTest

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("When a GET request with an Employee id=1 is made, the Employee object is returned")
    request {
        method 'GET'
        url '/employee/1'
        headers {
            contentType(applicationJson())

        }
    }
    response {
        status 200
        body("""
  {
    "id": "1",
    "fname": "Jane",
    "lname": "Doe",
    "salary": "123000.00",
    "gender": "M"
  }
  """)
        headers {
            contentType(applicationJson())
        }
    }
}