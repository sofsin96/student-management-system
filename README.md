# Student-Management-System
## Endpoints
### POST
#### Create student
http://localhost:8080/student-management-system/api/v1/students
#### Request body example
```json
{
  "firstName": "Sofia",
  "lastName": "Rodriguez",
  "email": "sofia.rodriguez@iths.se",
  "phoneNumber": "0123456789"
}
```
### GET
#### Get all students
http://localhost:8080/student-management-system/api/v1/students
#### Get student by ID
http://localhost:8080/student-management-system/api/v1/students/1
#### Get students by Last name
http://localhost:8080/student-management-system/api/v1/students/getAllByLastName?lastname=Rodriguez
### PUT
#### Replace student
http://localhost:8080/student-management-system/api/v1/students/1
#### Request body example
```json
{
  "firstName": "Sofia",
  "lastName": "Rodriguez",
  "email": "sofia.rodriguez@iths.se",
  "phoneNumber": "0123456789"
}
```
### PATCH
#### Update email field
http://localhost:8080/student-management-system/api/v1/students/1?email=sofia.rodriguez@iths.se
### DELETE
#### Delete student
http://localhost:8080/student-management-system/api/v1/students/1


 


