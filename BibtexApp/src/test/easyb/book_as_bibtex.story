import raemae.bibtexapp.*
import raemae.bibtexapp.domain.*
import raemae.bibtexapp.ui.*
import services.*

description 'Books can be viewed as BibTeX'

scenario "Show one book with only required fields as BibTex", {
  given 'there is one book with the required fields in the app', {
  }

  when 'command listbooks selected', {
  }

  then 'the book will be shown as BibTeX', {
  }
}

scenario "Show multiple books with only required fields as BibTex", {
  given 'there are multiple books with the required fields in the app', {
  }

  when 'command listbooks selected', {
  }

  then 'the books will be shown as BibTeX', {
  }
}

scenario "Show one book with optional fields as BibTex", {
  given 'there is one book with optional fields in the app', {
  }

  when 'command listbooks selected', {
  }

  then 'the book will be shown as BibTeX', {
  }
}

scenario "Show multiple books with optional fields as BibTex", {
  given 'there are multiple books with optional fields in the app', {
  }

  when 'command listbooks selected', {
  }

  then 'the books will be shown as BibTeX', {
  }
}