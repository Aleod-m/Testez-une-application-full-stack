describe('Me Spec', () => {
  it('Me', () => {
    cy.login("test@test.com", "test!1234")
    cy.intercept(
      {
        method: 'GET',
        url: '/api/user/1',
      },
      [ {
        "id": "1",
        "email": "test@test.com",
        "lastName": "Test",
        "firstName": "Test",
        "admin": false,
        "createdAt": "2024-01-16T11:34:03",
        "updatedAt": "2024-01-16T11:34:03"
      }]).as('user')
    cy.get('[routerlink="me"]').click();
    cy.wait('@user');
    cy.contains("User information")
  })
});
