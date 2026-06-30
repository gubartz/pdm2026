curl -X POST http://localhost:5000/filme \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Inception","titulo_original":"Inception","data_lancamento":"2010-07-16","duracao":148,"sinopse":"A thief","generos":[{"id":1},{"id":2}]}'

curl -X POST http://localhost:5000/auth \
  -H "Content-Type: application/json" \
  -d '{"email": "jose@teste.com.br", "senha": "123"}'  