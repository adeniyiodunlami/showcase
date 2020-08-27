package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return even number when input is even"
    request {
        method GET()
        url("/validate/even-number") {
            queryParameters {
                parameter("number",2)
            }
        }
    }
    response {
        body("Even")
        status 200
    }
}
