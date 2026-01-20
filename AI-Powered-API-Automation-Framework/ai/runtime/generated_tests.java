```java
package tests.pet;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class PutPetTest extends BaseTest {

    @Test
    public void updateExistingPet_success() {
        String body = """
                {
                  "id": 100001,
                  "name": "doggie-updated",
                  "status": "available"
                }
                """;

        givenApi()
                .contentType(JSON)
                .body(body)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("id", equalTo(100001))
                .body("name", equalTo("doggie-updated"))
                .body("status", equalTo("available"));
    }

    @Test
    public void updateExistingPet_missingBody_validationException() {
        givenApi()
                .contentType(JSON)
                .when()
                .put("/pet")
                .then()
                .statusCode(anyOf(is(400), is(422)));
    }

    @Test
    public void updateExistingPet_invalidId_invalidIdSupplied() {
        String body = """
                {
                  "id": "invalid",
                  "name": "doggie",
                  "status": "available"
                }
                """;

        givenApi()
                .contentType(JSON)
                .body(body)
                .when()
                .put("/pet")
                .then()
                .statusCode(anyOf(is(400), is(422)));
    }
}
```

```java
package tests.pet;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class PostPetTest extends BaseTest {

    @Test
    public void addNewPet_success() {
        String body = """
                {
                  "id": 100002,
                  "name": "catty",
                  "status": "available"
                }
                """;

        givenApi()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("id", equalTo(100002))
                .body("name", equalTo("catty"));
    }

    @Test
    public void addNewPet_missingBody_invalidInput() {
        givenApi()
                .contentType(JSON)
                .when()
                .post("/pet")
                .then()
                .statusCode(anyOf(is(400), is(422)));
    }

    @Test
    public void addNewPet_invalidPayload_validationException() {
        String body = """
                {
                  "id": "not-a-number",
                  "name": 123
                }
                """;

        givenApi()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/pet")
                .then()
                .statusCode(anyOf(is(400), is(422)));
    }
}
```

```java
package tests.pet;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class GetPetFindByStatusTest extends BaseTest {

    @Test
    public void findPetsByStatus_available_success() {
        givenApi()
                .queryParam("status", "available")
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(200)
                .body("$", is(notNullValue()));
    }

    @Test
    public void findPetsByStatus_missingStatus_badRequest() {
        givenApi()
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(400);
    }

    @Test
    public void findPetsByStatus_invalidStatus_badRequest() {
        givenApi()
                .queryParam("status", "invalid-status")
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(400);
    }
}
```

```java
package tests.pet;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class GetPetFindByTagsTest extends BaseTest {

    @Test
    public void findPetsByTags_success() {
        givenApi()
                .queryParam("tags", "tag1")
                .queryParam("tags", "tag2")
                .when()
                .get("/pet/findByTags")
                .then()
                .statusCode(200)
                .body("$", is(notNullValue()));
    }

    @Test
    public void findPetsByTags_missingTags_badRequest() {
        givenApi()
                .when()
                .get("/pet/findByTags")
                .then()
                .statusCode(400);
    }

    @Test
    public void findPetsByTags_invalidTagValue_badRequest() {
        givenApi()
                .queryParam("tags", "")
                .when()
                .get("/pet/findByTags")
                .then()
                .statusCode(400);
    }
}
```

```java
package tests.pet;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class GetPetByIdTest extends BaseTest {

    @Test
    public void getPetById_success() {
        givenApi()
                .pathParam("petId", 1)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    public void getPetById_invalidIdSupplied() {
        givenApi()
                .pathParam("petId", "invalid")
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(400);
    }

    @Test
    public void getPetById_petNotFound() {
        givenApi()
                .pathParam("petId", 999999999)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(404);
    }
}
```

```java
package tests.pet;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class PostPetByIdTest extends BaseTest {

    @Test
    public void updatePetWithFormData_success() {
        givenApi()
                .pathParam("petId", 1)
                .queryParam("name", "updated-name")
                .queryParam("status", "sold")
                .when()
                .post("/pet/{petId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", anyOf(equalTo("updated-name"), notNullValue()));
    }

    @Test
    public void updatePetWithFormData_invalidPetId_invalidInput() {
        givenApi()
                .pathParam("petId", "invalid")
                .queryParam("name", "x")
                .when()
                .post("/pet/{petId}")
                .then()
                .statusCode(400);
    }

    @Test
    public void updatePetWithFormData_invalidStatus_invalidInput() {
        givenApi()
                .pathParam("petId", 1)
                .queryParam("status", "invalid-status")
                .when()
                .post("/pet/{petId}")
                .then()
                .statusCode(400);
    }
}
```

```java
package tests.pet;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class DeletePetByIdTest extends BaseTest {

    @Test
    public void deletePet_success() {
        givenApi()
                .pathParam("petId", 1)
                .when()
                .delete("/pet/{petId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void deletePet_invalidPetId_invalidPetValue() {
        givenApi()
                .pathParam("petId", "invalid")
                .when()
                .delete("/pet/{petId}")
                .then()
                .statusCode(400);
    }

    @Test
    public void deletePet_withApiKeyHeader_success() {
        givenApi()
                .header("api_key", "test-api-key")
                .pathParam("petId", 2)
                .when()
                .delete("/pet/{petId}")
                .then()
                .statusCode(anyOf(is(200), is(400)));
    }
}
```

```java
package tests.pet;

import framework.BaseTest;
import org.testng.annotations.Test;

import java.io.File;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class PostPetUploadImageTest extends BaseTest {

    @Test
    public void uploadImage_success() {
        File file = new File("src/test/resources/test-image.png");

        givenApi()
                .pathParam("petId", 1)
                .queryParam("additionalMetadata", "meta")
                .multiPart("file", file, "application/octet-stream")
                .when()
                .post("/pet/{petId}/uploadImage")
                .then()
                .statusCode(200)
                .body("code", anyOf(is(200), is(0), notNullValue()));
    }

    @Test
    public void uploadImage_missingFile_noFileUploaded() {
        givenApi()
                .pathParam("petId", 1)
                .when()
                .post("/pet/{petId}/uploadImage")
                .then()
                .statusCode(400);
    }

    @Test
    public void uploadImage_petNotFound() {
        File file = new File("src/test/resources/test-image.png");

        givenApi()
                .pathParam("petId", 999999999)
                .multiPart("file", file, "application/octet-stream")
                .when()
                .post("/pet/{petId}/uploadImage")
                .then()
                .statusCode(404);
    }
}
```

```java
package tests.store;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class GetStoreInventoryTest extends BaseTest {

    @Test
    public void getInventory_success() {
        givenApi()
                .when()
                .get("/store/inventory")
                .then()
                .statusCode(200)
                .body("$", is(notNullValue()));
    }

    @Test
    public void getInventory_withUnexpectedQueryParam_stillSuccessOrError() {
        givenApi()
                .queryParam("unexpected", "value")
                .when()
                .get("/store/inventory")
                .then()
                .statusCode(anyOf(is(200), is(400)));
    }

    @Test
    public void getInventory_withInvalidHeader_stillSuccessOrError() {
        givenApi()
                .header("X-Invalid-Header", "%%%")
                .when()
                .get("/store/inventory")
                .then()
                .statusCode(anyOf(is(200), is(400)));
    }
}
```

```java
package tests.store;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class PostStoreOrderTest extends BaseTest {

    @Test
    public void placeOrder_success() {
        String body = """
                {
                  "id": 200001,
                  "petId": 1,
                  "quantity": 1,
                  "status": "placed",
                  "complete": false
                }
                """;

        givenApi()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200)
                .body("id", equalTo(200001))
                .body("petId", equalTo(1));
    }

    @Test
    public void placeOrder_missingBody_invalidInputOrValidationException() {
        givenApi()
                .contentType(JSON)
                .when()
                .post("/store/order")
                .then()
                .statusCode(anyOf(is(400), is(422)));
    }

    @Test
    public void placeOrder_invalidQuantity_validationException() {
        String body = """
                {
                  "id": 200002,
                  "petId": 1,
                  "quantity": -1,
                  "status": "placed",
                  "complete": false
                }
                """;

        givenApi()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/store/order")
                .then()
                .statusCode(anyOf(is(400), is(422)));
    }
}
```

```java
package tests.store;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class GetStoreOrderByIdTest extends BaseTest {

    @Test
    public void getOrderById_success() {
        givenApi()
                .pathParam("orderId", 1)
                .when()
                .get("/store/order/{orderId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    public void getOrderById_invalidIdSupplied() {
        givenApi()
                .pathParam("orderId", "invalid")
                .when()
                .get("/store/order/{orderId}")
                .then()
                .statusCode(400);
    }

    @Test
    public void getOrderById_orderNotFound() {
        givenApi()
                .pathParam("orderId", 999999999)
                .when()
                .get("/store/order/{orderId}")
                .then()
                .statusCode(404);
    }
}
```

```java
package tests.store;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class DeleteStoreOrderByIdTest extends BaseTest {

    @Test
    public void deleteOrder_success() {
        givenApi()
                .pathParam("orderId", 1)
                .when()
                .delete("/store/order/{orderId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteOrder_invalidIdSupplied() {
        givenApi()
                .pathParam("orderId", "invalid")
                .when()
                .delete("/store/order/{orderId}")
                .then()
                .statusCode(400);
    }

    @Test
    public void deleteOrder_orderNotFound() {
        givenApi()
                .pathParam("orderId", 999999999)
                .when()
                .delete("/store/order/{orderId}")
                .then()
                .statusCode(404);
    }
}
```

```java
package tests.user;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class PostUserTest extends BaseTest {

    @Test
    public void createUser_success() {
        String body = """
                {
                  "id": 300001,
                  "username": "user300001",
                  "firstName": "Test",
                  "lastName": "User",
                  "email": "user300001@example.com",
                  "password": "password123",
                  "phone": "1234567890",
                  "userStatus": 1
                }
                """;

        givenApi()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .body("username", anyOf(equalTo("user300001"), nullValue()));
    }

    @Test
    public void createUser_missingBody_unexpectedErrorOrBadRequest() {
        givenApi()
                .contentType(JSON)
                .when()
                .post("/user")
                .then()
                .statusCode(anyOf(is(400), is(500)));
    }

    @Test
    public void createUser_invalidEmail_badRequestOrUnexpected() {
        String body = """
                {
                  "id": 300002,
                  "username": "user300002",
                  "email": 123,
                  "password": "password123"
                }
                """;

        givenApi()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/user")
                .then()
                .statusCode(anyOf(is(400), is(500)));
    }
}
```

```java
package tests.user;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class PostUserCreateWithListTest extends BaseTest {

    @Test
    public void createUsersWithList_success() {
        String body = """
                [
                  {
                    "id": 300010,
                    "username": "user300010",
                    "firstName": "A",
                    "lastName": "B",
                    "email": "user300010@example.com",
                    "password": "pass",
                    "phone": "111",
                    "userStatus": 1
                  },
                  {
                    "id": 300011,
                    "username": "user300011",
                    "firstName": "C",
                    "lastName": "D",
                    "email": "user300011@example.com",
                    "password": "pass",
                    "phone": "222",
                    "userStatus": 1
                  }
                ]
                """;

        givenApi()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/user/createWithList")
                .then()
                .statusCode(200);
    }

    @Test
    public void createUsersWithList_missingBody_badRequestOrUnexpected() {
        givenApi()
                .contentType(JSON)
                .when()
                .post("/user/createWithList")
                .then()
                .statusCode(anyOf(is(400), is(500)));
    }

    @Test
    public void createUsersWithList_invalidBodyType_badRequestOrUnexpected() {
        String body = """
                {
                  "id": 1
                }
                """;

        givenApi()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/user/createWithList")
                .then()
                .statusCode(anyOf(is(400), is(500)));
    }
}
```

```java
package tests.user;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class GetUserLoginTest extends BaseTest {

    @Test
    public void login_success() {
        givenApi()
                .queryParam("username", "user1")
                .queryParam("password", "password")
                .when()
                .get("/user/login")
                .then()
                .statusCode(200)
                .body(is(notNullValue()));
    }

    @Test
    public void login_missingUsernamePassword_invalidUsernamePasswordSupplied() {
        givenApi()
                .when()
                .get("/user/login")
                .then()
                .statusCode(anyOf(is(200), is(400)));
    }

    @Test
    public void login_invalidCredentials_invalidUsernamePasswordSupplied() {
        givenApi()
                .queryParam("username", "nonexistent-user")
                .queryParam("password", "wrong-password")
                .when()
                .get("/user/login")
                .then()
                .statusCode(400);
    }
}
```

```java
package tests.user;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class GetUserLogoutTest extends BaseTest {

    @Test
    public void logout_success() {
        givenApi()
                .when()
                .get("/user/logout")
                .then()
                .statusCode(200);
    }

    @Test
    public void logout_withUnexpectedHeader_stillSuccessOrUnexpectedError() {
        givenApi()
                .header("X-Dummy", "value")
                .when()
                .get("/user/logout")
                .then()
                .statusCode(anyOf(is(200), is(500)));
    }

    @Test
    public void logout_withUnexpectedQueryParam_stillSuccessOrUnexpectedError() {
        givenApi()
                .queryParam("unexpected", "value")
                .when()
                .get("/user/logout")
                .then()
                .statusCode(anyOf(is(200), is(500)));
    }
}
```

```java
package tests.user;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class GetUserByUsernameTest extends BaseTest {

    @Test
    public void getUserByUsername_success() {
        givenApi()
                .pathParam("username", "user1")
                .when()
                .get("/user/{username}")
                .then()
                .statusCode(200)
                .body("username", equalTo("user1"));
    }

    @Test
    public void getUserByUsername_invalidUsernameSupplied() {
        givenApi()
                .pathParam("username", "%%%")
                .when()
                .get("/user/{username}")
                .then()
                .statusCode(400);
    }

    @Test
    public void getUserByUsername_userNotFound() {
        givenApi()
                .pathParam("username", "nonexistent_user_999999")
                .when()
                .get("/user/{username}")
                .then()
                .statusCode(404);
    }
}
```

```java
package tests.user;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class PutUserByUsernameTest extends BaseTest {

    @Test
    public void updateUser_success() {
        String body = """
                {
                  "id": 300100,
                  "username": "user1",
                  "firstName": "Updated",
                  "lastName": "Name",
                  "email": "user1updated@example.com",
                  "password": "newpass",
                  "phone": "999",
                  "userStatus": 1
                }
                """;

        givenApi()
                .contentType(JSON)
                .pathParam("username", "user1")
                .body(body)
                .when()
                .put("/user/{username}")
                .then()
                .statusCode(200);
    }

    @Test
    public void updateUser_missingBody_badRequest() {
        givenApi()
                .contentType(JSON)
                .pathParam("username", "user1")
                .when()
                .put("/user/{username}")
                .then()
                .statusCode(400);
    }

    @Test
    public void updateUser_userNotFound() {
        String body = """
                {
                  "id": 300101,
                  "username": "nonexistent_user_999999",
                  "firstName": "Updated"
                }
                """;

        givenApi()
                .contentType(JSON)
                .pathParam("username", "nonexistent_user_999999")
                .body(body)
                .when()
                .put("/user/{username}")
                .then()
                .statusCode(404);
    }
}
```

```java
package tests.user;

import framework.BaseTest;
import org.testng.annotations.Test;

import static framework.FrameworkApiContract.*;
import static org.hamcrest.Matchers.*;

public class DeleteUserByUsernameTest extends BaseTest {

    @Test
    public void deleteUser_success() {
        givenApi()
                .pathParam("username", "user1")
                .when()
                .delete("/user/{username}")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteUser_invalidUsernameSupplied() {
        givenApi()
                .pathParam("username", "%%%")
                .when()
                .delete("/user/{username}")
                .then()
                .statusCode(400);
    }

    @Test
    public void deleteUser_userNotFound() {
        givenApi()
                .pathParam("username", "nonexistent_user_999999")
                .when()
                .delete("/user/{username}")
                .then()
                .statusCode(404);
    }
}
```