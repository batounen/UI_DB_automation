@us6 @smoke @ui @db
Feature: Books module
  As a librarian, I should be able to add new books to the library

  Scenario Outline: Verify added book is matching with DB
    Given user login as a librarian
    And I navigate to "Books" page
    When the librarian click to add book
    And the librarian enter book name "<Book Name>"
    When the librarian enter ISBN "<ISBN>"
    And the librarian enter year "<Year>"
    When the librarian enter author "<Author>"
    And the librarian choose the book category "<Book Category>"
    And the librarian click to save changes
    Then the librarian verify new book by "<Book Name>"
    Then the librarian verify new book from database by "<Book Name>"
    Examples:
      | Book Name       | ISBN     | Year | Author       | Book Category        |
      | Java OCP11      | 09112022 | 2022 | Oracle       | Classic              |
      | Fantastic Beast | 10112022 | 2022 | Kathy Sierra | Action and Adventure |
      | SDET Lead life  | 11112022 | 2002 | Mitch Lacey  | Science Fiction      |