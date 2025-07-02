<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Gerenciar Livros</title>
  <!-- Fontes Google -->
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Poppins:wght@500;700&display=swap" rel="stylesheet">
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
</head>
<body>
  <div class="container">

    <div class="form-section">
      <c:choose>
        <c:when test="${not empty editBook}">
          <h1>✏️ Editar Livro</h1>
          <p class="edit-warning">Você está editando o livro com ID <strong>${editBook.id}</strong>.</p>

          <form action="${pageContext.request.contextPath}/books/edit" method="post">
            <input type="hidden" name="id" value="${editBook.id}" />
            <label>Título:
              <input type="text" name="title" value="${editBook.title}" required />
            </label>
            <label>Autor:
              <input type="text" name="author" value="${editBook.author}" required />
            </label>
            <button type="submit">Salvar Alterações</button>
          </form>
          <br/>
          <a href="${pageContext.request.contextPath}/books" class="btn">← Cancelar Edição</a>
        </c:when>

        <c:otherwise>
          <h1>+ Adicionar Novo Livro</h1>
          <form action="${pageContext.request.contextPath}/books/create" method="post">
            <label>Título:
              <input type="text" name="title" required />
            </label>
            <label>Autor:
              <input type="text" name="author" required />
            </label>
            <button type="submit">Salvar</button>
          </form>
        </c:otherwise>
      </c:choose>
    </div>
		<div class="search-section">
		  <form method="get" action="${pageContext.request.contextPath}/books" class="search-form">
		    <label for="searchId">🔎 Buscar livro por ID:</label>
		    <input type="text" name="searchId" id="searchId" placeholder="Ex: 1" />
		    <button type="submit">Buscar</button>
		    <a href="${pageContext.request.contextPath}/books" class="btn reset">Limpar</a>
		  </form>
		
		  <c:if test="${notFound}">
		    <p class="warning-msg">⚠️ Livro com esse ID não foi encontrado.</p>
		  </c:if>
		
		  <c:if test="${invalidSearch}">
		    <p class="warning-msg">⚠️ ID inválido. Digite apenas números.</p>
		  </c:if>
		</div>
    <div class="table-section">
      <h1>📚 Livros Cadastrados</h1>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Autor</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="b" items="${books}">
            <tr>
              <td>${b.id}</td>
              <td>${b.title}</td>
              <td>${b.author}</td>
              <td class="actions">
                <a href="books?editId=${b.id}" class="btn">✏️</a>

                <form action="${pageContext.request.contextPath}/books/delete" method="post"
                      onsubmit="return confirm('Deseja realmente excluir o livro?');" style="display:inline;">
                  <input type="hidden" name="id" value="${b.id}" />
                  <button type="submit" class="delete" title="Excluir">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                         fill="currentColor" viewBox="0 0 16 16">
                      <path d="M5.5 5.5A.5.5 0 0 1 6 5h4a.5.5 0 0 1 0 1H6a.5.5 0 0 1-.5-.5zm1 
                        3A.5.5 0 0 1 7 8h2a.5.5 0 0 1 0 1H7a.5.5 0 0 1-.5-.5z"/>
                      <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 
                        2H5a2 2 0 0 1-2-2V4H2.5a1 1 0 0 1 0-2h3.086a1 1 0 0 
                        1 .707.293l.707.707h4l.707-.707A1 1 0 0 1 10.914 
                        2h3.086a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 
                        0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118z"/>
                    </svg>
                  </button>
                </form>
              </td>
            </tr>
          </c:forEach>
          <c:if test="${empty books}">
            <tr>
              <td colspan="4"><i>Nenhum livro cadastrado.</i></td>
            </tr>
          </c:if>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>
