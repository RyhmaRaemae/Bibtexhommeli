import raemae.bibtexapp.*
import raemae.bibtexapp.domain.*
import raemae.bibtexapp.ui.*
import services.*

description 'User can add books into the app'

scenario "user can login with correct password", {
  given 'command addbook selected', {
  }

  when 'the required fields are filled', {
  }

  then 'the book will be saved', {
  }
}

scenario "user cannot add a book without the author field", {
  given 'command addbook selected', {
  }

  when 'the Author field is left empty', {
  }

  then 'the book will not be saved', {
  }
}

scenario "user cannot add a book without the title field", {
  given 'command addbook selected', {
  }

  when 'the title field is left empty', {
  }

  then 'the book will not be saved', {
  }
}

scenario "user cannot add a book without the year field", {
  given 'command addbook selected', {
  }

  when 'the title year is left empty', {
  }

  then 'the book will not be saved', {
  }
}