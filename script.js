window.addEventListener("DOMContentLoaded", () => {
    carregarLivros();
    
    const form =document.getElementById("formCadastro");
  form.addEventListener("submit", async (event) => {
    event.preventDefault(); // evita reload da página
    await cadastrarLivros();
  });
});

async function carregarLivros() {
  try {
    const resposta = await fetch("http://localhost:8080/books");
    if (!resposta.ok) throw new Error("Erro ao buscar livros");

    const livros = await resposta.json();

    const lista = document.getElementById("lista");
    lista.innerHTML = "";

    livros.forEach((livro) => {
      const li = document.createElement("li");
      li.textContent = `Título: ${livro.title} | Autor: ${livro.author} | Ano de Publicação: ${livro.year} | Gênero: ${livro.category} | ID Internacional: ${livro.isbn} `;
      lista.appendChild(li);
    });
  } catch (e) {
    console.error(e);
    alert("Erro ao carregar livros!");
  }
}

async function cadastrarLivros() {
    try {
        const title = document.getElementById("title").value;
        const author = document.getElementById("author").value;
        const isbn = document.getElementById("isbn").value;
        const year = document.getElementById("year").value;
        const category = document.getElementById("category").value;

        const livro = { title, author, isbn, year, category };

        const resposta = await fetch("http://localhost:8080/books", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(livro)
        });

        if (!resposta.ok) throw new Error("Erro ao cadastrar livro");

        const livroCriado = await resposta.json();
        alert(`O seguinte livro cadastrado: ${livroCriado.title}`);

        // Limpa o formulário
        document.getElementById("title").value = "";
        document.getElementById("author").value = "";
        document.getElementById("isbn").value = "";
        document.getElementById("year").value = "";
        document.getElementById("category").value = "NOVEL";

        carregarLivros();
    } catch (e) {
        console.error(e);
        alert("Erro ao cadastrar livro!");
    }
}

