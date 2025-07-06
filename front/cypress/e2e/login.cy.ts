describe('Login spec', () => {
  it('Login Admin', () => {
    cy.login("yoga@studio.com", "test!1234")
    cy.url().should('include', '/sessions')
  })
});
