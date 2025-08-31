const categorias = {
  NOVEL: "Romance",
  FANTASY: "Fantasia",
  ACTION: "Ação",
  THRILLER: "Suspense",
  ADVENTURE: "Aventura",
  ETC: "Outros"
};

let editandoId = null; // Guarda o ID do livro que está sendo editado
let livros = [];       // Lista global de livros

window.addEventListener("DOMContentLoaded", () => {
  carregarLivros();

  const form = document.getElementById("formCadastro");
  form.addEventListener("submit", async (event) => {
    event.preventDefault();
    if (editandoId) {
      await atualizarLivro(editandoId);
    } else {
      await cadastrarLivro();
    }
  });

  document.getElementById("atualizarLivros").addEventListener("click", carregarLivros);

  // Delegação de eventos: edição e exclusão
  const lista = document.getElementById("lista");
  lista.addEventListener("click", (e) => {
    const id = Number(e.target.dataset.id);
    if (!id) return;

    if (e.target.classList.contains("editar")) preencherFormulario(id);
    if (e.target.classList.contains("excluir")) excluirLivro(id);
  });
});

async function carregarLivros() {
  try {
    const resposta = await fetch("http://localhost:8081/books");
    livros = await resposta.json();

    const lista = document.getElementById("lista");
    lista.innerHTML = "";

    livros.forEach(livro => {
      const li = document.createElement("li");
      li.innerHTML = `
        Título: ${livro.title} | Autor: ${livro.author} | Ano: ${livro.year} | Gênero: ${categorias[livro.category] || livro.category} | ISBN: ${livro.isbn}
        <span class="editar" data-id="${livro.id}">✏️</span>
        <span class="excluir" data-id="${livro.id}">❌</span>
      `;
      lista.appendChild(li);
    });

  } catch (e) {
    console.error(e);
    alert("Erro ao carregar livros!");
  }
}

function pegarDadosFormulario() {
  return {
    title: document.getElementById("title").value,
    author: document.getElementById("author").value,
    isbn: document.getElementById("isbn").value,
    year: document.getElementById("year").value,
    category: document.getElementById("category").value
  };
}

function resetFormulario() {
  document.getElementById("title").value = "";
  document.getElementById("author").value = "";
  document.getElementById("isbn").value = "";
  document.getElementById("year").value = "";
  document.getElementById("category").value = "NOVEL";
  editandoId = null;
}

async function cadastrarLivro() {
  try {
    const livro = pegarDadosFormulario();
    const resposta = await fetch("http://localhost:8081/books", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(livro)
    });

    if (!resposta.ok) throw new Error("Erro ao cadastrar livro");

    resetFormulario();
    await carregarLivros();
  } catch (e) {
    console.error(e);
    alert("Erro ao cadastrar livro!");
  }
}

async function atualizarLivro(id) {
  try {
    const livro = pegarDadosFormulario();
    const resposta = await fetch(`http://localhost:8081/books/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(livro)
    });

    if (!resposta.ok) throw new Error("Erro ao atualizar livro");

    resetFormulario();
    await carregarLivros();
  } catch (e) {
    console.error(e);
    alert("Erro ao atualizar livro!");
  }
}

async function excluirLivro(id) {
  if (!confirm("Deseja realmente excluir este livro?")) return;

  try {
    const resposta = await fetch(`http://localhost:8081/books/${id}`, {
      method: "DELETE"
    });

    if (!resposta.ok) throw new Error("Erro ao excluir livro");

    await carregarLivros();
  } catch (e) {
    console.error(e);
    alert("Erro ao excluir livro!");
  }
}

function preencherFormulario(id) {
  const livro = livros.find(l => l.id === id);
  if (!livro) return;

  document.getElementById("title").value = livro.title;
  document.getElementById("author").value = livro.author;
  document.getElementById("isbn").value = livro.isbn;
  document.getElementById("year").value = livro.year;
  document.getElementById("category").value = livro.category;
  editandoId = id;
}
