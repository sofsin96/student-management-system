# Student-Management-System
## Students
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

## Subjects
### POST
#### Create subject
http://localhost:8080/student-management-system/api/v1/subjects
#### Request body example
```json
{
  "name": "Java"
}
```
#### Add student to subject
http://localhost:8080/student-management-system/api/v1/subjects/1/add/student/2
#### Add teacher to subject
http://localhost:8080/student-management-system/api/v1/subjects/1/add/student/2
### GET
#### Get all Subjects
http://localhost:8080/student-management-system/api/v1/subjects
#### Get subject by ID
http://localhost:8080/student-management-system/api/v1/subjects/1
#### Get students by Name
http://localhost:8080/student-management-system/api/v1/subjects/getAllByName?name=Java
### DELETE
#### Delete subject
http://localhost:8080/student-management-system/api/v1/subjects/1
#### Delete student from subject
http://localhost:8080/student-management-system/api/v1/subjects/1/delete/student/2
#### Delete teacher from subject
http://localhost:8080/student-management-system/api/v1/subjects/1/delete/teacher/2

## Teachers
### POST
#### Create teacher
http://localhost:8080/student-management-system/api/v1/teachers
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
#### Get all teachers
http://localhost:8080/student-management-system/api/v1/teachers
#### Get teacher by ID
http://localhost:8080/student-management-system/api/v1/teachers/1
#### Get teachers by Last name
http://localhost:8080/student-management-system/api/v1/teachers/getAllByLastName?lastname=Rodriguez
### PUT
#### Replace teacher
http://localhost:8080/student-management-system/api/v1/teachers/1
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
http://localhost:8080/student-management-system/api/v1/teachers/1?email=sofia.rodriguez@iths.se
### DELETE
#### Delete teacher
http://localhost:8080/student-management-system/api/v1/teachers/1

 


