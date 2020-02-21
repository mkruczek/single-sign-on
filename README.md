#SINGLE SIGN ON

Managing users via REST API

Develop a REST API microservice for managing users.

Users should be HTTP resources that follow the REST semantics:

    GET /userEntity/ should list all users in the system
    GET /userEntity/{id} should present the userEntity with given id
    GET /userEntity/?{key}={value} whould list all users with property being equal value
    POST /userEntity/ should store new resource
    PUT /userEntity/{id} should update a resource
    DELETE /userEntity/{id} should remove a resource

Development standard requirements:

    use the respective HTTP Status Codes and other headers where needed
    do not store users in database - keep them in memory for simplicity
    properties of a userEntity resource are up to you

Bonus points for:

    implementing authentication mechanism of any kind for non-GET calls (JWT counts double)

